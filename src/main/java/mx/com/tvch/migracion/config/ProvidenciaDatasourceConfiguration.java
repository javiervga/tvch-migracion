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
public class ProvidenciaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.providencia")
    public DataSourceProperties providenciaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("providenciaDataSource")
	@Primary
	public DataSource providenciaDataSource() {
	    return providenciaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate providenciaJdbcTemplate(@Qualifier("providenciaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}*/
