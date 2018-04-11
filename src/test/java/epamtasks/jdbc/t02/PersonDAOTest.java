package epamtasks.jdbc.t02;

import epamtasks.jdbc.t01.SimplePerson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static epamtasks.jdbc.t01.SimplePerson.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {
private  ConnectionPool connectionPool = new ConnectionPool();

    @Test
    void findAllTest() {
        String sql = "SELECT * FROM Person WHERE id='1'";
        String sql2 = "SELECT * FROM Person WHERE id='15'";
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        List<SimplePerson> list = personDAO.findAll(sql);
        assertEquals(1,list.size());
        assertEquals("Jose",list.get(0).getFirstName());
        list=personDAO.findAll(sql2);
        assertTrue(list.isEmpty());

    }

    @Test
    void findByIdTest() {
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        String sql = "SELECT * FROM Person WHERE id='1'";
        List<SimplePerson> list = personDAO.findAll(sql);
        assertEquals(1,list.size());
        assertEquals("Jose",list.get(0).getFirstName());

        SimplePerson person = personDAO.findById(1);
        assertTrue(person != null);
        assertEquals("Jose",person.getFirstName());

        person = personDAO.findById(10);
        assertTrue(person != null);
        assertEquals(getDefaultPerson(),person);
    }

    @Test
    void insertTest() {
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        SimplePerson person = getDefaultPerson()
                .setFirstName("Mark")
                .setLastName("Renton")
                .setAddress("Grow stree, 15")
                .setDob("1988-06-14")
                .setEmail("Mark_Ren@gmail.com")
                .setPass("bjrs429ckmk")
                .setPermission(true)
                .setTelephone("+99578941473");
        personDAO.insert(person);
        String sql = "SELECT * FROM Person WHERE last_name='Renton'";
        List<SimplePerson> list = personDAO.findAll(sql);
        assertEquals(1,list.size());
        assertEquals(list.get(0),person);

    }

    @Test
    void updateTest() {
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        SimplePerson person = getDefaultPerson()
                .setFirstName("Mark")
                .setLastName("Renton")
                .setAddress("Grow stree, 15")
                .setDob("1988-06-14")
                .setEmail("Mark_Ren@gmail.com")
                .setPass("bjrs429ckmk")
                .setPermission(true)
                .setTelephone("+99578941473");
        personDAO.insert(person);
        String sqlUpdate = "UPDATE Person SET dob='1800-07-11'\n" +
                "WHERE first_name='Mark'";
        personDAO.update(sqlUpdate);
        String sql = "SELECT * FROM Person WHERE last_name='Renton'";
        List<SimplePerson> list = personDAO.findAll(sql);
        assertEquals(1,list.size());
        assertEquals(list.get(0).getDob(),"1800-07-11");
    }

    @Test
    void batchSQLTest() {
        String sql0 = "SELECT * FROM Person ";
        String sql1 = "UPDATE Person SET dob='1515-06-30'";
        String sql2 = "UPDATE Person SET address='Red square, 1'";
        String sql3 = "UPDATE Person SET email='anon_lol@gg.com'";
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        List<SimplePerson> list = personDAO.findAll(sql0);
        assertTrue(!list.isEmpty());
        for (SimplePerson pers:list){
           assertTrue(!pers.getDob().contains("1515-06-30"));
            assertTrue(!pers.getAddress().contains("Red square, 1"));
            assertTrue(!pers.getEmail().contains("anon_lol@gg.com"));
        }

        personDAO.batchSQL(sql1,sql2,sql3);
        list = personDAO.findAll(sql0);
        assertTrue(!list.isEmpty());
        for (SimplePerson pers:list){
            assertTrue(pers.getDob().contains("1515-06-30"));
            assertTrue(pers.getAddress().contains("Red square, 1"));
            assertTrue(pers.getEmail().contains("anon_lol@gg.com"));
        }


    }

    @Test
    void deleteByIdTest() {
        PersonDAO personDAO = new PersonDAO(connectionPool.get());
        SimplePerson person = personDAO.findById(1);
        assertTrue(person != null);
        assertEquals("Jose",person.getFirstName());
        personDAO.deleteById(1);
        person = personDAO.findById(1);
        assertEquals(getDefaultPerson(), person);

    }
}