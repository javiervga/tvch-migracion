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
public class TlanalapaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.tlanalapa")
    public DataSourceProperties tlanalapaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("tlanalapaDataSource")
	@Primary
	public DataSource tlanalapaDataSource() {
	    return tlanalapaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate tlanalapaJdbcTemplate(@Qualifier("tlanalapaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/