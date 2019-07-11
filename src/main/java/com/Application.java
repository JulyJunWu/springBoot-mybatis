package com;

import com.ws.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 若不加上@Import 和 exclude = {DataSourceAutoConfiguration.class} 会导致循环引用
 */
@Import({DataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
