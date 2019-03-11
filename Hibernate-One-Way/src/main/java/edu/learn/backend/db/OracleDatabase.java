package edu.learn.backend.db;

import edu.learn.backend.db.factory.DBFactory;
import org.hibernate.SessionFactory;

/**
 * Created by nabbasi on 1/3/2017.
 */
public class OracleDatabase extends DBFactory {
    public OracleDatabase(){
        configurationFile = "hib.oracle.cfg.xml";
    }

    @Override
    public SessionFactory getSessionFactory() {
        return configure();
    }
}
