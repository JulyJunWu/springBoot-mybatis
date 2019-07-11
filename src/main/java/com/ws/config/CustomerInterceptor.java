package com.ws.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class CustomerInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //设置数据源
        Object result;
        try {
            MappedStatement arg = (MappedStatement) invocation.getArgs()[0];
            SqlCommandType sqlCommandType = arg.getSqlCommandType();

            if (sqlCommandType == SqlCommandType.SELECT && !arg.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                DynamicDataSourceHolder.setSlave();
            } else {
                DynamicDataSourceHolder.setMaster();
            }

            result = invocation.proceed();

        } finally {
            DynamicDataSourceHolder.remove();
        }

        return result;
    }


    @Override
    public Object plugin(Object target) {

        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }

        return target;
    }


    @Override
    public void setProperties(Properties properties) {
    }


}
