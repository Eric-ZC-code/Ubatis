package com.middleware.ubatis.datasource.pooled;

import com.middleware.ubatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;

/**
 * @description 池化数据源的工厂
 * @author Eric-ZC
 */
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
