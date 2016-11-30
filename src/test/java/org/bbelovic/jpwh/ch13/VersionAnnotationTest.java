package org.bbelovic.jpwh.ch13;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

public class VersionAnnotationTest {

    private SessionFactory sessionFactory;

    public void setUp() {
        Map<Object, Object> settings = new HashMap<>();
        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");

        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.show_sql", "true");

        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/jpwh3");
        settings.put("hibernate.connection.username", "testuser");
        settings.put("hibernate.connection.password", "test_pass");


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(settings);
        Metadata metadata = new MetadataSources(builder.build()).buildMetadata();
        sessionFactory = metadata.buildSessionFactory();


    }

}
