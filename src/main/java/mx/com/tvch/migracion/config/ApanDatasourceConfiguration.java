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
public class ApanDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.apan")
    public DataSourceProperties apanDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("apanDataSource")
	@Primary
	public DataSource apanDataSource() {
	    return apanDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate apanJdbcTemplate(@Qualifier("apanDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/