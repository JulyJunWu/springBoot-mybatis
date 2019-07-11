package com.ws.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;

@Component
@Primary
@Slf4j
public class CustomerRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println(DynamicDataSourceHolder.getSource());
        return DynamicDataSourceHolder.getSource();
    }


    public  CustomerRoutingDataSource(
            @Qualifier("masterDatasource") DataSource masterDatasource,
            @Qualifier("slave2Datasource") DataSource slave2Datasource,
            @Qualifier("salve3Datasource") DataSource salve3Datasource) {

        HashMap<Object, Object> map = new HashMap<>(8);
        map.put(DataSourceEnum.MASTER, masterDatasource);
        map.put(DataSourceEnum.SLAVE2, slave2Datasource);
        map.put(DataSourceEnum.SLAVE3, salve3Datasource);

        setDefaultTargetDataSource(masterDatasource);
        setTargetDataSources(map);
    }
}
