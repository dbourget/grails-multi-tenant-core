package grails.plugin.multitenant.core.datasource;

import grails.plugin.multitenant.core.CurrentTenant;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import javax.sql.DataSource;

/**
 * This class allows pooled data sources to be used for multiple tenant databases.
 */
public class TenantPooledDataSource extends BasicDataSource implements TenantDataSource {
// ======================================================================================================================== 
//    Static Fields 
// ======================================================================================================================== 

    public static final boolean multiTenant = true;

// ======================================================================================================================== 
//    Instance Fields
// ======================================================================================================================== 

    private CurrentTenant currentTenant;
    private DataSourceUrlResolver dataSourceUrlResolver;

// ======================================================================================================================== 
//    Public Instance Methods
// ======================================================================================================================== 

    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public void setCurrentTenant(CurrentTenant currentTenant) {
        this.currentTenant = currentTenant;
    }

    public void setDataSourceUrlResolver(DataSourceUrlResolver dataSourceUrlResolver) {
        this.dataSourceUrlResolver = dataSourceUrlResolver;
    }

    public <T> T unwrap(Class<T> tClass) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

// ======================================================================================================================== 
//    Non-Public Instance Methods
// ======================================================================================================================== 

    @Override
    protected DataSource createDataSource() throws SQLException {
        final String dataSourceUrl = dataSourceUrlResolver.getDataSourceUrl(currentTenant.get());
        if (dataSourceUrl != null) {
            this.url = dataSourceUrl;
        }
        return super.createDataSource();
    }
}
