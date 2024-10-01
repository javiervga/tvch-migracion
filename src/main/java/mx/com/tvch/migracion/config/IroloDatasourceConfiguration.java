package mx.com.tvch.migracion.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class IroloDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.irolo")
    public DataSourceProperties iroloDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("iroloDataSource")
	@Primary
	public DataSource iroloDataSource() {
	    return iroloDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate iroloJdbcTemplate(@Qualifier("iroloDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
