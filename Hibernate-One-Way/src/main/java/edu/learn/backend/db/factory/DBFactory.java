package edu.learn.backend.db.factory;

import edu.learn.backend.dao.exception.DatabaseException;
import edu.learn.backend.db.OracleDatabase;
import edu.learn.backend.db.factory.db.DatabaseType;
import edu.learn.backend.db.factory.db.H2Database;
import edu.learn.backend.db.factory.db.MySQLDatabase;
import edu.learn.backend.entities.Configuration;
import edu.learn.backend.entities.Firm;
import edu.learn.backend.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Noman Ali on 12/24/2016.
 */
public abstract class DBFactory {
    private Logger log = Logger.getLogger(this.getClass().getName());
    public abstract SessionFactory getSessionFactory();
    protected String configurationFile = null;
    protected static SessionFactory sessionFactory;

    protected SessionFactory configure(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =  new StandardServiceRegistryBuilder()
                .configure(getConfigurationFile()).build();
        if(sessionFactory == null){
            try {
                final MetadataSources metadataSources = new MetadataSources( registry );
                getClasses().forEach(pojoClasses -> metadataSources.addAnnotatedClass(pojoClasses));
                sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
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

    public static DBFactory getDatabaseConnection(DatabaseType databaseType) throws DatabaseException {
        switch (databaseType){
            case H2_DATABASE:
               return new H2Database();
            case MYSQL_DATABASE:
                return new MySQLDatabase();
            case ORACLE:
                return new OracleDatabase();
            default:
                throw new DatabaseException("Please provide a database type");
        }
    }

    public void generate(SessionFactory sessionFactory) throws Exception {
        Dialect dialect = ((SessionFactoryImplementor) sessionFactory).getDialect();

        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", dialect)
                        .build());


        for (Class clazz : getClasses()) {
            metadata.addAnnotatedClass(clazz);
        }

        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.SCRIPT);
        SchemaExport export = new SchemaExport();
        export.setDelimiter(";");
        export.setOutputFile("E:\\ddl_" + dialect+ ".sql");
        export.setFormat(true);
        export.execute(targetTypes, SchemaExport.Action.CREATE, metadata.buildMetadata());
        System.exit(1);
    }

    private List<Class> getClasses() {
        List<Class> pojoClasses = new ArrayList<>();
        pojoClasses.add(Firm.class);
        pojoClasses.add(Configuration.class);
        pojoClasses.add(User.class);
        return pojoClasses;
    }

    public String getConfigurationFile() {
        try{
            if(configurationFile == null){
                log.log(Level.SEVERE,"Please provide configuration file name");
            }
        }catch (NullPointerException ne){

        }

        return configurationFile;
    }
}
