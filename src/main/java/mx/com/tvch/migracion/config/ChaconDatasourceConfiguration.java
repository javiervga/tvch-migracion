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
public class ChaconDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.chacon")
    public DataSourceProperties chaconDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("chaconDataSource")
	@Primary
	public DataSource chaconDataSource() {
	    return chaconDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate chaconJdbcTemplate(@Qualifier("chaconDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/