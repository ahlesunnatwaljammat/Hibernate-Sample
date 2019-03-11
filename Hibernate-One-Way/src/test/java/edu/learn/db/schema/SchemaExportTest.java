package edu.learn.db.schema;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SchemaExportTest  {
    private final String MAPPING = "org/hibernate/test/schemaupdate/mapping.hbm.xml";

	private ServiceRegistry serviceRegistry;

	@Before
	public void setUp() {
		/*serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( Environment.getProperties() );*/
	}

	@After
	public void tearDown() {
		/*ServiceRegistryBuilder.destroy( serviceRegistry );*/
		serviceRegistry = null;
	}

	protected JdbcServices getJdbcServices() {
		return serviceRegistry.getService( JdbcServices.class );
	}

	/*@Test
	public void testCreateAndDropOnlyType() {
		Configuration cfg = new Configuration();
		cfg.addResource( MAPPING );
		SchemaExport schemaExport = new SchemaExport( serviceRegistry, cfg );
		// create w/o dropping first; (OK because tables don't exist yet
		schemaExport.execute( false, true, false, true );
		assertEquals( 0, schemaExport.getExceptions().size() );
		// create w/o dropping again; should be an exception for each table
		// (2 total) because the tables exist already
		assertEquals( 0, schemaExport.getExceptions().size() );
		schemaExport.execute( false, true, false, true );
		assertEquals( 2, schemaExport.getExceptions().size() );
		// drop tables only
		schemaExport.execute( false, true, true, false );
		assertEquals( 0, schemaExport.getExceptions().size() );
	}

	@Test
	public void testBothType() {
		Configuration cfg = new Configuration();
		cfg.addResource( MAPPING );
		SchemaExport schemaExport = new SchemaExport( serviceRegistry, cfg );
		// drop before create (nothing to drop yeT)
		schemaExport.execute( false, true, false, false );
		assertEquals( 0, schemaExport.getExceptions().size() );
		// drop before crete again (this time drops the tables before re-creating)
		schemaExport.execute( false, true, false, false );
		assertEquals( 0, schemaExport.getExceptions().size() );
		// drop tables
		schemaExport.execute( false, true, true, false );
		assertEquals( 0, schemaExport.getExceptions().size() );
	}

	@Test
	public void testCreateAndDrop() {
		Configuration cfg = new Configuration();
		cfg.addResource( MAPPING );
		SchemaExport schemaExport = new SchemaExport( serviceRegistry, cfg );
		// should drop before creating, but tables don't exist yet
		schemaExport.create( false, true);
		assertEquals( 0, schemaExport.getExceptions().size() );
		// call create again; it should drop tables before re-creating
		schemaExport.create( false, true );
		assertEquals( 0, schemaExport.getExceptions().size() );
		// drop the tables
		schemaExport.drop( false, true );
		assertEquals( 0, schemaExport.getExceptions().size() );
	}*/

	public void generateSchema(){
		/*EntityManagerFactoryImpl emf=(EntityManagerFactoryImpl)lcemfb.getNativeEntityManagerFactory();
		SessionFactoryImpl sf=emf.getSessionFactory();
		StandardServiceRegistry serviceRegistry = sf.getSessionFactoryOptions().getServiceRegistry();
		MetadataSources metadataSources = new MetadataSources(new BootstrapServiceRegistryBuilder().build());
		Metadata metadata = metadataSources.buildMetadata(serviceRegistry);
		SchemaUpdate update=new SchemaUpdate(serviceRegistry,metadata); //To create SchemaUpdate

		// You can either create SchemaExport from the above details, or you can get the existing one as follows:
		try {
			Field field = SessionFactoryImpl.class.getDeclaredField("schemaExport");
			field.setAccessible(true);
			SchemaExport schemaExport = (SchemaExport) field.get(serviceRegistry);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}*/
	}
}