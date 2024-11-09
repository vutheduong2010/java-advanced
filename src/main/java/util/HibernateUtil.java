package util;

import entity.Department;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory() {
        var url = "jdbc:mysql://localhost:3306/lesson_01?createDatabaseIfNotExist=true";
        var configuration = new Configuration()
                .addAnnotatedClass(Department.class)
                .setProperty(AvailableSettings.URL, url)
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "root")
                .setProperty(AvailableSettings.HBM2DDL_AUTO, "create")
                .setProperty(AvailableSettings.SHOW_SQL, "true")
                .setProperty(AvailableSettings.HIGHLIGHT_SQL, "true");
        return configuration.buildSessionFactory();
    }
}
