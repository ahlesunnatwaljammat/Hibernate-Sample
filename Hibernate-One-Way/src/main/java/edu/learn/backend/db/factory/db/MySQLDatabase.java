package edu.learn.backend.db.factory.db;

import edu.learn.backend.db.factory.DBFactory;
import org.hibernate.SessionFactory;

/**
 * Created by nabbasi on 12/30/2016.
 */
public class MySQLDatabase extends DBFactory {
    public MySQLDatabase(){
        configurationFile = "hib.mysql.cfg.xml";
    }

    @Override
    public SessionFactory getSessionFactory() {
        return configure();
    }
}
