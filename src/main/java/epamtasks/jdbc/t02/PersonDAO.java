package epamtasks.jdbc.t02;


import epamtasks.jdbc.t01.SimplePerson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements Manageable<SimplePerson> {
    private Connection connection;

    public PersonDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<SimplePerson> findAll(String sql) {

        List<SimplePerson>list = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                list.add(convertToPerson(resultSet));
            }
            return list;
        }catch (SQLException e){
            throw new ConnectionPoolException("Read data error!",new SQLException());
        }

    }

    private SimplePerson convertToPerson(ResultSet resultSet) {
        SimplePerson simplePerson = SimplePerson.getDefaultPerson();
        try{
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++ ){

              String name = metaData.getColumnName(i);
                  if(name.equalsIgnoreCase("first_name")){
                      simplePerson.setFirstName(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("last_name")){
                      simplePerson.setLastName(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("permission")){
                      simplePerson.setPermission(resultSet.getBoolean(i));
                  }else if(name.equalsIgnoreCase("dob")){
                      simplePerson.setDob(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("email")){
                      simplePerson.setEmail(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("password")){
                      simplePerson.setPass(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("address")){
                      simplePerson.setAddress(resultSet.getString(i));
                  }else if(name.equalsIgnoreCase("telephone")){
                      simplePerson.setTelephone(resultSet.getString(i));
                  }

            }
            return simplePerson;
        }catch (SQLException e){

            throw new ConnectionPoolException("Read data error!",new SQLException());
        }
    }

    @Override
    public SimplePerson findById(int id) {
        String sql = String.format(" SELECT * FROM Person WHERE id='%d'",id);
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
        if(resultSet.next()){
             return convertToPerson(resultSet);
         } else
             return SimplePerson.getDefaultPerson();
        }catch (SQLException e){
            throw new ConnectionPoolException("Read data error!",new SQLException());
        }

    }

    @Override
    public void insert(SimplePerson entity) {
        String sql = "INSERT INTO Person(first_name,last_name,permission,dob,email,password,address,telephone)\n" +
                "    VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.setBoolean(3,entity.isPermission());
            preparedStatement.setString(4,entity.getDob());
            preparedStatement.setString(5,entity.getEmail());
            preparedStatement.setString(6,entity.getPass());
            preparedStatement.setString(7,entity.getAddress());
            preparedStatement.setString(8,entity.getTelephone());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new ConnectionPoolException("Write data error!",new SQLException());
        }

    }

    @Override
    public void update(String sql) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
           throw new ConnectionPoolException("Write error",e);
        }
    }

    @Override
    public void batchSQL(String... sqls) {
        try(Statement statement = connection.createStatement()) {
            for (String sql:sqls) {
                statement.addBatch(sql);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Write error",e);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = String.format("DELETE FROM Person\n" +
                "WHERE ID ='%d'",id);
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Write error",e);
        }
    }
}
