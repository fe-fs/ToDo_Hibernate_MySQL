/*Modulo 05 - Assignment
 * Java Todo List Application with Hibernate and MySQL database
 * Name: Fernanda Frederico Ribeiro da Silva
 * Class: Software Development II CEN-4025C-24671
 * Professor: Walauskis
 */
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


/*This file is important because it tells Hibernate how to connect to your database and what entities to map.
 You can use either XML or Java-based configuration.*/
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/todo_db");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Sql#1");
                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update"); //this configuration keeps the data saved in the database
                                                                    // for erasing the database each time that program closes use: "create-drop"

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Todo_db_Entity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();


                /*SessionFactory is responsible for reading the hibernate configuration parameters and connecting to the database.
                 Once it’s created, it should be kept for later use. You would need one SessionFactory object per database using
                 a separate configuration file.*/
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}