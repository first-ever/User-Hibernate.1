package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao  {
    private static  Connection conn = Util.getConnection();
    public UserDaoJDBCImpl()  {
        super();
    }

    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "name CHAR(30) NOT NULL, " +
                    "lastName CHAR(30), " +
                    "age MEDIUMINT NOT NULL, " +
                    "PRIMARY KEY(id));");
        } catch (SQLException e) {
            System.out.println("Исключение в createUsersTable");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("drop table if exists users");
        } catch (SQLException e) {
            System.out.println("Исключение в dropUsersTable");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pStatement = conn.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?,?,?)")) {
            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);
            pStatement.executeUpdate();
            System.out.println("Пользователь с именем - " + name + " был добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Исключение в saveUser");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement pStatement = conn.prepareStatement("delete from users where id = ?")) {
            pStatement.setLong(1, id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Исключение в removeUserById");;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User(rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Исключение в getAllUsers");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("Исключение в cleanUsersTable");;
        }
    }
}
