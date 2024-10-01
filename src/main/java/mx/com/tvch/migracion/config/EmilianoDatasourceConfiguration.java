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
public class EmilianoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.emiliano")
    public DataSourceProperties emilianoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("emilianoDataSource")
	@Primary
	public DataSource emilianoDataSource() {
	    return emilianoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate emilianoJdbcTemplate(@Qualifier("emilianoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
