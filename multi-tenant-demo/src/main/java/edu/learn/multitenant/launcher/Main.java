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
        Session session = sessionFactory.withOptions().tenantIdentifier("pub1").openSession();
        Query<User> from_user = session.createQuery("select u from User as u");
        List<User> list = from_user.list();

        if(!list.isEmpty()){
            for (User user : list) {
                log.info(user.toString());
            }
        }

        session = sessionFactory.withOptions().tenantIdentifier("pub2").openSession();
        from_user = session.createQuery("from User");
        list = from_user.list();

        if(!list.isEmpty()){
            for (User user : list) {
                log.info(user.toString());
            }
        }

        session.close();
        sessionFactory.close();
    }
}
