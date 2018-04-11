package epamtasks.jdbc.t02;


import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.function.Supplier;


import static epamtasks.jdbc.t02.ConnectionFactory.initializeFactory;
import static org.apache.logging.log4j.LogManager.*;

public class ConnectionPool implements Supplier<Connection>,Closeable {
    private static final Logger log = getLogger(ConnectionPool.class);
    private final BlockingQueue<PooledConnection> connectionQueue;
    private volatile boolean opened;
    private static final String ERROR_MSG;

    static {
        ERROR_MSG = "Error {} apears from {}";
    }

    public ConnectionPool(){

        ConnectionFactory connectionFactory = initializeFactory("db");
        int poolSize = connectionFactory.getPoolSize();
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        for (int i=0; i < poolSize; i++){
            connectionQueue.add(new PooledConnection(this::closePolledConnection,
                    connectionFactory.get()));
        }


        try (Connection connection = get();
             Statement statement = connection.createStatement()) {
            for (String sql: getSQLfromDir(connectionFactory.getInitScriptsPath())) {
                statement.addBatch(sql);
            }
            statement.executeBatch();

        }catch (SQLException e){
            throw new ConnectionPoolException(
                    "Error creating and filling tables from files.", e);
        }
    }


    public static List<String> getSQLfromDir(String initScriptsPath) {
       List<String> list = new ArrayList<>();
        String  absolutePath = initScriptsPath;
        URL resource = ConnectionPool.class.getResource("/sql");
        try {
           absolutePath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        } catch (URISyntaxException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
        }
       File file = new File(absolutePath);
       log.info("absolute path{}",file.getAbsolutePath());
       log.info("is exists {} : is dir {}",file.exists(),file.isDirectory());
       if(!file.exists() && !file.isDirectory()){
           return Collections.emptyList();
       }
       for(File f:file.listFiles(File::isFile)){
           list.add(readSQLFrom(f));
       }
       return list;
    }

    private static String readSQLFrom(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String content;
            StringBuilder container = new StringBuilder();
            while((content = br.readLine())!= null){
                container.append(content);
            }
            log.info("Success read from {}",file.getName());
            return container.toString();
        } catch (IOException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            throw new ConnectionPoolException("Error read sql from file",e);
        }

    }

    public Connection takeConnection() {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException(
                    "Error connecting to the data source.", e);
        }
    }

    private void closePolledConnection(PooledConnection connection) {
        if (opened) {
            try {
                if (connection.isClosed())
                    throw new ConnectionPoolException("Attempting to close closed connection.",
                            new SQLException());
                if (connection.isReadOnly())
                    connection.setReadOnly(false);
                if (!connectionQueue.offer(connection))
                    throw new ConnectionPoolException("Error allocating connection in the pool.",
                            new SQLException());
            }catch (SQLException e){
                log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
                throw new ConnectionPoolException("Error in try close PolledConnection",
                        new SQLException());
            }

        } else
            connection.reallyClose();
    }
    @Override
    public void close()  {
        opened = false;
        connectionQueue.forEach(PooledConnection::reallyClose);
    }

    @Override
    public Connection get() {
        return takeConnection();
    }
}
