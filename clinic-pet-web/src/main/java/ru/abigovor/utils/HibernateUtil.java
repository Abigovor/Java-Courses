package ru.abigovor.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getFactory() {
        if (null == sessionFactory)
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable e) {
                throw new ExceptionInInitializerError(e);
            }
        return sessionFactory;
    }
}
