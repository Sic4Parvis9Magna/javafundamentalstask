package epamtasks.jdbc.t02;

import epamtasks.exceptions.t02.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;
public class ConnectionFactory implements Supplier<Connection> {

    private static final Logger log = LogManager.getLogger(ConnectionFactory.class);
    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    private final int poolSize;
    private String initScriptsPath;

    private static final String ERROR_MSG;

    static {
        ERROR_MSG = "Error {} apears from {}";
    }

    private static boolean isNotInited;



    private ConnectionFactory(String driver,String url,
                              String user,String password,
                              int poolSize,String initScriptsPath ){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        this.initScriptsPath = initScriptsPath;
    }


    public static ConnectionFactory initializeFactory(String propetyName){
        PropertyReader pr = new PropertyReader();
        if(!pr.initialiseResource(propetyName)) {
            throw new ConnectionPoolException("Wrong property name",new FileNotFoundException());
        }
        return  new ConnectionFactory(getProperty(pr,"driver"),
                getProperty(pr,"url"),
                getProperty(pr,"user"),
                getProperty(pr,"password"),
                Integer.parseInt(getProperty(pr,"poolSize")),
                        getProperty(pr,"initScriptsPath") );

    }

    private static String getProperty(PropertyReader pr, String key){
        pr.releaseKey(key);
        return pr.getKeyContent();
    }

    public String getInitScriptsPath() {
        return initScriptsPath;
    }
    public int getPoolSize(){return poolSize;}

    @Override
    public Connection get() {
        if(!isNotInited){
            isNotInited = true;
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            }
        }
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            throw new ConnectionPoolException("Can't get Connection error!",new SQLException());
        }
    }
}
