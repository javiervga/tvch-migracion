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
public class RealDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.real")
    public DataSourceProperties realDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("realDataSource")
	@Primary
	public DataSource realDataSource() {
	    return realDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate realJdbcTemplate(@Qualifier("realDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
