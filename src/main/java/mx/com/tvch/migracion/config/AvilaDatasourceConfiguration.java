/*package mx.com.tvch.migracion.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AvilaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.avila")
    public DataSourceProperties avilaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("avilaDataSource")
	@Primary
	public DataSource avilaDataSource() {
	    return avilaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate avilaJdbcTemplate(@Qualifier("avilaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/