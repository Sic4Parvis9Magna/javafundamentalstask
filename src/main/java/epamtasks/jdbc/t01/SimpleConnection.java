package epamtasks.jdbc.t01;

import epamtasks.exceptions.t02.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class SimpleConnection {
    private static final Logger log = LogManager.getLogger(SimpleConnection.class);
    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    private Connection connection;
    private String sql;
    private static final String ERROR_MSG;

    static {
       ERROR_MSG = "Error {} apears from {}";
   }

    private SimpleConnection(String driver,String url,String user,String password){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public static Optional<SimpleConnection> initialiseSC(String filename){
        PropertyReader pr = new PropertyReader();
        if(!pr.initialiseResource(filename)){
            return Optional.empty();
        }else{
            List<String> args = new ArrayList<>();
            if(pr.releaseKey("driver")) args.add( pr.getKeyContent());
            if(pr.releaseKey("url")) args.add( pr.getKeyContent());
            if(pr.releaseKey("user")) args.add( pr.getKeyContent());
            if(pr.releaseKey("password")) args.add( pr.getKeyContent());
            if(args.contains("NOT_FOUND") || args.size()<4) {
                return Optional.empty();
            }else{
                return Optional.of(new SimpleConnection(args.get(0),
                        args.get(1),
                        args.get(2),
                        args.get(3)));
            }
        }

    }
    public boolean tryGetConnection(){
        try {
            Class.forName(this.getDriver());
            connection = getConnection(this.getUrl(),
                    this.getUser(),this.getPassword());
            return true ;
        } catch (ClassNotFoundException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        } catch (SQLException e2) {
            log.error(ERROR_MSG,e2.getMessage(),e2.getStackTrace());
            return false;
        }

    }

    public boolean isConnected(){
        return connection != null ;
    }
    public boolean createTable(String fileName){
       if(!isConnected()|| !uploadSQLfromFile(fileName)) {
           return false;
       }
        try (Statement st = connection.createStatement();){
           int countRows = st.executeUpdate(sql);
           log.info("changed rows: {}",countRows);
           log.info("Table was created");
           return true;

        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }

    }
    public boolean deleteTable(String tableName){
        try(Statement statement = connection.createStatement()){
            String sql = String.format("DROP TABLE %s",tableName);
            int countRows = statement.executeUpdate(sql);
            log.info("changed rows: {}",countRows);
            log.info("Table was deleted");
            return true;

        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }
    }

    public List<SimplePerson> getPersonWithName(String name){
        String sql = "SELECT * FROM Person WHERE first_name=?";
        List<SimplePerson> people = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,name);

            try ( ResultSet result = preparedStatement.executeQuery();) {

                while (result.next()) {
                    people.add(new SimplePerson()
                            .setFirstName(result.getString("first_name"))
                            .setLastName(result.getString("last_name"))
                            .setPermission(result.getBoolean("permission"))
                            .setAddress(result.getString("address"))
                            .setDob(result.getString("dob"))
                            .setEmail(result.getString("email"))
                            .setPass(result.getString("password"))
                            .setTelephone(result.getString("telephone")));

                }
            }

            return people;

        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return Collections.emptyList();
        }
    }
    public boolean updateWithSQL(String sql) {
        try(Statement statement = connection.createStatement()) {
           int changed = statement.executeUpdate(sql);
           log.info("Changed rows: {}",changed);
            return true;
        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }
    }

    public boolean uploadSQLfromFile(String fileName){

       StringBuilder sb = new StringBuilder();
       String temp= "";
       try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
           while ((temp = br.readLine()) != null) {
               sb.append(temp);
           }
           sql=sb.toString();
           return true;
       } catch (FileNotFoundException e) {
           log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
           return false;
       } catch (IOException e) {
           log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
           return false;
       }

    }

    public boolean insertPerson(SimplePerson person){
        String prepSql = "INSERT INTO Person(first_name,last_name,permission,dob,email,password,address,telephone)\n" +
                "    VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement =connection
                    .prepareStatement(prepSql)){
            preparedStatement.setString(1,person.getFirstName());
            preparedStatement.setString(2,person.getLastName());
            preparedStatement.setBoolean(3,person.isPermission());
            preparedStatement.setString(4,person.getDob());
            preparedStatement.setString(5,person.getEmail());
            preparedStatement.setString(6,person.getPass());
            preparedStatement.setString(7,person.getAddress());
            preparedStatement.setString(8,person.getTelephone());
            int changed = preparedStatement.executeUpdate();
            log.info("Changed rows: {}",changed);
            return true;
        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }
    }


    public boolean printPhoneByQuery(String sql) {

        StringBuilder sb = new StringBuilder("Query Result:\n");
        try(Statement statement = connection.createStatement();
          ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                sb.append(String.format("%s %s with telephone number %s",
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("telephone"))).append("\n");
            }
            log.info(sb.toString());

            return true;
        } catch (SQLException e) {
            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }
    }


    public String getDriver() {
        return driver;
    }
    public String getUrl() {
        return url;
    }
    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public Connection getConn(){return  connection;}

    public String getSql() {
        return sql;
    }




    public boolean tryCloseConnection(){

        if(!isConnected())return true;
        try {
            connection.close();
            connection = null;
            return true;
        } catch (SQLException e) {

            log.error(ERROR_MSG,e.getMessage(),e.getStackTrace());
            return false;
        }
    }

    @Override
    public String toString() {
        return "SimpleConnection{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static List<String> getFileNames(String pathToDir){
       File file = new File(pathToDir);
       if(!file.exists() || !file.isDirectory()){
           return Collections.emptyList();
       }
        List<String> list = new ArrayList<>();
        for (File f:file.listFiles(File::isFile) ){
            list.add(f.getAbsolutePath());
        }
        return list;

    }


}
