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
public class SantaClaraDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.santaclara")
    public DataSourceProperties santaclaraDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("santaclaraDataSource")
	@Primary
	public DataSource santaclaraDataSource() {
	    return santaclaraDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate santaclaraJdbcTemplate(@Qualifier("santaclaraDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
