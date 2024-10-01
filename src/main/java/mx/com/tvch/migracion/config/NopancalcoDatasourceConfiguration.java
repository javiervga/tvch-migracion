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
public class NopancalcoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.nopancalco")
    public DataSourceProperties nopancalcoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("nopancalcoDataSource")
	@Primary
	public DataSource nopancalcoDataSource() {
	    return nopancalcoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate nopancalcoJdbcTemplate(@Qualifier("nopancalcoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/