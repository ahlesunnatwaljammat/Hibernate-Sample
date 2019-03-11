package edu.learn.backend.db.factory.db;

import edu.learn.backend.db.factory.DBFactory;
import org.hibernate.SessionFactory;

/**
 * Created by Noman Ali on 12/24/2016.
 */
public class H2Database extends DBFactory {
    public H2Database(){
        configurationFile = "hib.h2.cfg.xml";
    }

    public SessionFactory getSessionFactory() {
        return configure();
    }
}
