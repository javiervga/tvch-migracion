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
public class SanCayetanoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.sancayetano")
    public DataSourceProperties sancayetanoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("sancayetanoDataSource")
	@Primary
	public DataSource sancayetanoDataSource() {
	    return sancayetanoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate sancayetanoJdbcTemplate(@Qualifier("sancayetanoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/