package cn.marssky.dtfweb.config;
//Durid数据源配置类
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Durid {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DuridDataSoure(){
        return  new DruidDataSource();
    }

}
