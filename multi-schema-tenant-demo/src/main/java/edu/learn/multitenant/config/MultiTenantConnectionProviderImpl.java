package edu.learn.multitenant.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;
import org.hibernate.service.spi.Stoppable;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

public class MultiTenantConnectionProviderImpl implements Serializable, MultiTenantConnectionProvider, Stoppable {

    private HikariDataSource hikariDataSource;

    public MultiTenantConnectionProviderImpl(){
        HikariConfig hikariDataSource = new HikariConfig();
        hikariDataSource.setPoolName("H2-Database");
        hikariDataSource.setMaximumPoolSize(1);
        hikariDataSource.setIdleTimeout(2);
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:test;MODE=MySQL;INIT=RUNSCRIPT FROM './sql-scripts/h2/init.sql'");
        //hikariDataSource.setJdbcUrl("jdbc:h2:./test;create=true");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        this.hikariDataSource = new HikariDataSource(hikariDataSource);
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        if (this.hikariDataSource.getMaximumPoolSize() == 10) {

        }

        Connection connection = this.hikariDataSource.getConnection();
        return connection;
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try {
            //This is DB specific syntax. This work for MSSQL and MySQL
            //Oracle uses the ALTER SESSION SET SCHEMA command
            //H2 SET SCHEMA SCHEMA_NAME;
            connection.createStatement().execute("SET SCHEMA " + tenantIdentifier);
        } catch (SQLException e) {
            throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            this.releaseAnyConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return ConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        } else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }

    //Stoppable interface
    @Override
    public void stop() {
        hikariDataSource.close();
    }
}
