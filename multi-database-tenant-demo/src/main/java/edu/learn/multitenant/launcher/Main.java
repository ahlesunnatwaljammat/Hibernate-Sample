package edu.learn.multitenant.launcher;

import edu.learn.multitenant.config.HibernateUtil;
import edu.learn.multitenant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.withOptions().tenantIdentifier("pub1").openSession()) {
            allUsers(session, "select u from User as u");

            session.close();
        }


        try (Session session = sessionFactory.withOptions().tenantIdentifier("pub2").openSession()) {
            allUsers(session, "from User");
            session.close();
        }

        sessionFactory.close();
    }

    private static void allUsers(Session session, String s) {
        Query<User> from_user = session.createQuery(s);
        List<User> list = from_user.list();

        if (!list.isEmpty()) {
            for (User user : list) {
                log.info(user.toString());
            }
        }
    }
}
