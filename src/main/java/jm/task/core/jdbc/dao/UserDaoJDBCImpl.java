package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection ;

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "CREATE TABLE `mydbtest`.`users` (" +
                "`ID` INT NOT NULL AUTO_INCREMENT, " +
                "`NAME` VARCHAR(45) NOT NULL," +
                "`LASTNAME` VARCHAR(45) NOT NULL," +
                "`AGE` INT(3) NOT NULL," +
                "PRIMARY KEY (`id`));";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("table created in given database");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "DROP TABLE `mydbtest`.`users`";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("table removed");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO mydbtest.users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM `mydbtest`.`users` WHERE ID=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * from mydbtest.users";

        ResultSet resultSet = null;
        User user = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("FIRSTNAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));


                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "TRUNCATE TABLE `mydbtest`.`users`";
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("table cleared");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


    }
}
