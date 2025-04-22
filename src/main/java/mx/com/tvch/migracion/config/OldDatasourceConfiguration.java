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
public class OldDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.old")
    public DataSourceProperties oldDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("oldDataSource")
	@Primary
	public DataSource oldDataSource() {
	    return oldDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate oldJdbcTemplate(@Qualifier("oldDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
