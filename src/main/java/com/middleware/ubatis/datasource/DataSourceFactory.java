package com.middleware.ubatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description 数据源工厂
 * @author Eric-ZC
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
