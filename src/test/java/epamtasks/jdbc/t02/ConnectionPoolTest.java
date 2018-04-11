package epamtasks.jdbc.t02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static epamtasks.jdbc.t02.ConnectionPool.getSQLfromDir;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void getSQLfromDirTest(){
       System.out.println(getSQLfromDir("sql"));
       // System.out.println(getSQLfromDir("D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql"));
        File file = new File("resources/sql");
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);


    }

    static final String SQL =
            "select id, first_name, last_name, permission, dob, email, password, address, telephone from Person";

    @Test

    @DisplayName("Get method works correctly")
    void get() {

       ConnectionPool connectionPool = new ConnectionPool();

        try (Connection connection = connectionPool.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL)) {
            if (resultSet.next())
                System.out.printf("Hello, %s", resultSet.getString("first_name"));
        }catch (ConnectionPoolException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}