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
public class TepeyahualcoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.tepeyahualco")
    public DataSourceProperties tepeyahualcoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("tepeyahualcoDataSource")
	@Primary
	public DataSource tepeyahualcoDataSource() {
	    return tepeyahualcoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate tepeyahualcoJdbcTemplate(@Qualifier("tepeyahualcoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/