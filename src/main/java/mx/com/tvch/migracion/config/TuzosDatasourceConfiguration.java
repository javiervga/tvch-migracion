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
public class TuzosDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.tuzos")
    public DataSourceProperties tuzosDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("tuzosDataSource")
	@Primary
	public DataSource tuzosDataSource() {
	    return tuzosDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate tuzosJdbcTemplate(@Qualifier("tuzosDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/