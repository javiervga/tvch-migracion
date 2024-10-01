package mx.com.tvch.migracion.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class TvchDatasourceConfiguration {

	@Bean
    @ConfigurationProperties("spring.datasource.tvch")
    public DataSourceProperties tvchDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean
	public DataSource tvchDataSource() {
	    return tvchDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate tvchJdbcTemplate(@Qualifier("tvchDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	
}
