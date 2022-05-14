package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Lara", "Croft", (byte) 22);
        userService.saveUser("Jim", "Brown", (byte) 33);
        userService.saveUser("Nick", "Hamilson", (byte) 44);
        userService.saveUser("Elvis", "Presli", (byte) 55);

        userService.removeUserById(2);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        System.out.println(userService.getAllUsers());

    }
}