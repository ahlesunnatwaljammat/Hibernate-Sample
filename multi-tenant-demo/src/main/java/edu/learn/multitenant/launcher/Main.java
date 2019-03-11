package edu.learn.multitenant.launcher;

import edu.learn.multitenant.config.HibernateUtil;
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
        Query from_user = session.createQuery("from User");
        List list = from_user.list();

        if(!list.isEmpty()){
            log.info(list.toString());
        }

        session = sessionFactory.withOptions().tenantIdentifier("pub2").openSession();
        from_user = session.createQuery("from User");
        list = from_user.list();

        if(!list.isEmpty()){
            log.info(list.toString());
        }

        session.close();
    }
}
