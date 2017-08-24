package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {
                System.out.println("Couldn't close SessionFactory" + ignored);
            }
        }
    }

    public static void closeSession(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                System.out.println(("Couldn't close Session" + ignored));
            }
        }
    }

    public static void rollback(Transaction tx) {
        try {
            if (tx != null) {
                tx.rollback();
            }
        } catch (HibernateException ignored) {
            System.out.println("Couldn't rollback Transaction" + ignored);
        }
    }
}