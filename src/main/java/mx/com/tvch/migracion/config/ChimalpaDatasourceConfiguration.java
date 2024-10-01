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
public class ChimalpaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.chimalpa")
    public DataSourceProperties chimalpaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("chimalpaDataSource")
	@Primary
	public DataSource chimalpaDataSource() {
	    return chimalpaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate chimalpaJdbcTemplate(@Qualifier("chimalpaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
