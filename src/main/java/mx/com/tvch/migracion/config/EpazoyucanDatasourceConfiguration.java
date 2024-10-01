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
public class EpazoyucanDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.epazoyucan")
    public DataSourceProperties epazoyucanDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("epazoyucanDataSource")
	@Primary
	public DataSource epazoyucanDataSource() {
	    return epazoyucanDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate epazoyucanJdbcTemplate(@Qualifier("epazoyucanDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
