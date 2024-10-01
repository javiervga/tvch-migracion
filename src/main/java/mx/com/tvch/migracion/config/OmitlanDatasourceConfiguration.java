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
public class OmitlanDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.omitlan")
    public DataSourceProperties omitlanDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("omitlanDataSource")
	@Primary
	public DataSource omitlanDataSource() {
	    return omitlanDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate omitlanJdbcTemplate(@Qualifier("omitlanDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
