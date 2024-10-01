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
public class AtotonilcoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.atotonilco")
    public DataSourceProperties atotonilcoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("atotonilcoDataSource")
	@Primary
	public DataSource atotonilcoDataSource() {
	    return atotonilcoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate atotonilcoJdbcTemplate(@Qualifier("atotonilcoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
