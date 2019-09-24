package com.etranzact.config;

import com.etranzact.core.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by johnadeshola on 9/21/19.
 */
@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final Environment env;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSourceBuilder.url(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(env.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(SecurityUtils.twoXDecryptStringData(env.getProperty("spring.datasource.password")));
        return dataSourceBuilder.build();
    }

}
