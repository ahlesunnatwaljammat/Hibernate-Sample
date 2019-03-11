package edu.learn.backend.launcher;

import edu.learn.backend.dao.exception.DatabaseException;
import edu.learn.backend.db.factory.DBFactory;
import edu.learn.backend.db.factory.db.DatabaseType;
import edu.learn.backend.entities.Configuration;
import edu.learn.backend.entities.Firm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * Created by Noman Ali on 12/24/2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            DBFactory dbFactory = DBFactory.getDatabaseConnection(DatabaseType.H2_DATABASE);
            SessionFactory sessionFactory = dbFactory.getSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            transaction.begin();

            Firm firm = new Firm();
            firm.setFirmName("Test Firm");
            session.save(firm);

            Configuration configuration = new Configuration();
            configuration.setSalt("188x0001989");
            configuration.setFirm(firm);
            session.save(firm);

            transaction.commit();

            Firm getFirm = session.get(Firm.class,1);
            System.out.println(getFirm);

            Configuration getFirmConfiguration = session.get(Configuration.class,getFirm.getFirmId());

            transaction = session.beginTransaction();
            transaction.begin();
            firm.setFirmName("Test Firm 1");
            session.saveOrUpdate(firm);
            transaction.commit();

            getFirm = session.get(Firm.class,1);
            System.out.println(getFirm);

            session.close();
            sessionFactory.close();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
