package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();
        userService.saveUser("Vadim","Isayenko",(byte)32);
        List<User> allUsers = userService.getAllUsers();
        allUsers.size();

//    public static void main(String[] args) {
//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();
//
//        userDao.createUsersTable();
//
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
//
//        userDao.removeUserById(1);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
