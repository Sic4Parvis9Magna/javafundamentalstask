package epamtasks.jdbc.t01;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;
import static org.junit.jupiter.api.Assertions.*;
@Log4j2
class SimpleConnectionTest {
    private static final String ERROR_MSG;

    static {
        ERROR_MSG = "Error {} apears from {}";
    }



    @Test
    void tryGetConnectionTest(){
       Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
       assertTrue(op.isPresent());
       SimpleConnection connection = op.get();
       assertTrue(connection.tryGetConnection());
        assertTrue(connection.isConnected());
        System.out.println("Connection is ready");
        assertTrue(connection.tryCloseConnection());
        System.out.println("Connection successfully close");


    }

    @Test
    void createTableTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String fullPath ="D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql\\1.sql";
        assertTrue(con.createTable(fullPath));
        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");

    }

    @Test
    void insertInfo(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String toDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql";
        List<String> sqlFiles = SimpleConnection.getFileNames(toDir);
        System.out.println(sqlFiles);
        for (String sqlFile:sqlFiles) {
            assertTrue(con.uploadSQLfromFile(sqlFile));
            assertTrue(con.updateWithSQL(con.getSql()));
        }

        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");

    }
    @Test
    void getResultSetTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String toDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql";
        List<String> sqlFiles = SimpleConnection.getFileNames(toDir);
        System.out.println(sqlFiles);
        for (String sqlFile:sqlFiles) {
            assertTrue(con.uploadSQLfromFile(sqlFile));
            assertTrue(con.updateWithSQL(con.getSql()));
        }
        String sql = "SELECT * FROM Person";
        String sql2 = "SELECT * FROM Person WHERE password='qwerty';";
        assertTrue(con.printPhoneByQuery(sql2));

        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");
    }

    @Test
    void deleteTableTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String toDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql";
        List<String> sqlFiles = SimpleConnection.getFileNames(toDir);
        System.out.println(sqlFiles);
        for (String sqlFile:sqlFiles) {
            assertTrue(con.uploadSQLfromFile(sqlFile));
            assertTrue(con.updateWithSQL(con.getSql()));
        }
        assertTrue(con.deleteTable("Person"));
        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");
    }

    @Test
    void insertPersonTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        String createSQL = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql\\1.sql";
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        assertTrue(con.createTable(createSQL));
        SimplePerson person = new SimplePerson()
                .setFirstName("Bob")
                .setLastName("Wood")
                .setAddress("Every Green Street,33")
                .setTelephone("+20789954");
        assertTrue(con.insertPerson(person));
        assertTrue(con.deleteTable("Person"));
        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");
    }

    @Test
    void  getPersonWithNameTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String toDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql";
        List<String> sqlFiles = SimpleConnection.getFileNames(toDir);
        System.out.println(sqlFiles);
        for (String sqlFile:sqlFiles) {
            assertTrue(con.uploadSQLfromFile(sqlFile));
            assertTrue(con.updateWithSQL(con.getSql()));
        }

        String pasosDob = "1987-03-12";
        List<SimplePerson> people = con.getPersonWithName("Pasos");
        assertTrue(!people.isEmpty());
        assertEquals(pasosDob,people.get(0).getDob());
        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");
    }

    @Test
    void updateDBDataTest(){
        Optional <SimpleConnection> op = SimpleConnection.initialiseSC("db");
        assertTrue(op.isPresent());
        SimpleConnection con = op.get();
        assertTrue(con.tryGetConnection());
        assertTrue(con.isConnected());
        System.out.println("Connection is ready");
        String toDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\resourses\\sql";
        List<String> sqlFiles = SimpleConnection.getFileNames(toDir);
        System.out.println(sqlFiles);
        for (String sqlFile:sqlFiles) {
            assertTrue(con.uploadSQLfromFile(sqlFile));
            assertTrue(con.updateWithSQL(con.getSql()));
        }

        String update = "UPDATE Person\n" +
                "SET dob='1800-07-11'\n" +
                "WHERE first_name='Pasos'";
        assertTrue(con.updateWithSQL(update));
        List<SimplePerson> people = con.getPersonWithName("Pasos");
        assertTrue(!people.isEmpty());
       assertEquals("1800-07-11",people.get(0).getDob());
        System.out.printf("New Pasos dob %s%n",people.get(0).getDob());
        assertTrue(con.tryCloseConnection());
        System.out.println("Connection successfully close");

    }

}