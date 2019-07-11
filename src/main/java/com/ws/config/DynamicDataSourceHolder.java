package com.ws.config;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 获取数据源
 */
public class DynamicDataSourceHolder {

    private static ThreadLocal<DataSourceEnum> source = new ThreadLocal<>();

    private static AtomicLong syncNum = new AtomicLong();


    public static void setMaster() {
        source.set(DataSourceEnum.MASTER);
    }

    public static void setSlave() {
        source.set(getSlaveSource());
    }

    private static DataSourceEnum getSlaveSource() {
        long num = syncNum.getAndIncrement();
        return (num & 1) == 0 ? DataSourceEnum.SLAVE2 : DataSourceEnum.SLAVE3;
    }

    public static void remove() {
        source.remove();
    }

    public static DataSourceEnum getSource() {
        DataSourceEnum dataSourceEnum = source.get();
        return dataSourceEnum == null ? DataSourceEnum.MASTER : dataSourceEnum;
    }
}
