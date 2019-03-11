package edu.learn.multitenant.config;

import edu.learn.multitenant.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory(){
        final StandardServiceRegistry registry =  new StandardServiceRegistryBuilder().configure("hib.h2.cfg.xml").build();

        if(sessionFactory == null){
            try {
                final MetadataSources metadataSources = new MetadataSources( registry );
                metadataSources.addAnnotatedClass(User.class);
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
                return sessionFactory;
            }
            catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
                StandardServiceRegistryBuilder.destroy( registry );
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }

}
