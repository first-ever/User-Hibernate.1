package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static Connection conn;
    private static SessionFactory sessionFactory = null;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Crazymbappe5612";
    private static final String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Moscow";

    public Util() { }
    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Crazymbappe5612");
            Statement statement = conn.createStatement();
            return conn;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static SessionFactory getSessionFactory() {
       if (sessionFactory == null) {
           try {
               Configuration configuration = new Configuration()
                       .setProperty(Environment.DRIVER, DRIVER)
                       .setProperty(Environment.URL,URL)
                       .setProperty(Environment.USER,USERNAME)
                       .setProperty(Environment.PASS,PASSWORD)
                       .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                       .addAnnotatedClass(User.class);
               ServiceRegistry registry = new StandardServiceRegistryBuilder()
                       .applySettings(configuration.getProperties()).build();
               sessionFactory = configuration.buildSessionFactory(registry);
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
        return sessionFactory;
    }
}
