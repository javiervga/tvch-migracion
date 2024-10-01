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
public class AhuazotepecDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.ahuazotepec")
    public DataSourceProperties ahuazotepecDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("ahuazotepecDataSource")
	@Primary
	public DataSource ahuazotepecDataSource() {
	    return ahuazotepecDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate ahuazotepecJdbcTemplate(@Qualifier("ahuazotepecDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
