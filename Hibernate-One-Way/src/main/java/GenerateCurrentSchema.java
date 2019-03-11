import edu.learn.backend.dao.exception.DatabaseException;
import edu.learn.backend.db.factory.DBFactory;
import edu.learn.backend.db.factory.db.DatabaseType;
import org.hibernate.SessionFactory;

/**
 * Created by nabbasi on 1/2/2017.
 */
public class GenerateCurrentSchema {
    public static void main(String[] args) throws Exception {
        DBFactory dbFactory = DBFactory.getDatabaseConnection(DatabaseType.H2_DATABASE);
        SessionFactory sessionFactory = dbFactory.getSessionFactory();
        dbFactory.generate(sessionFactory);
    }
}
