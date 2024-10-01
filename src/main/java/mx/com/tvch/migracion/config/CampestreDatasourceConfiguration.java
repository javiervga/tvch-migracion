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
public class CampestreDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.campestre")
    public DataSourceProperties campestreDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("campestreDataSource")
	@Primary
	public DataSource campestreDataSource() {
	    return campestreDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate campestreJdbcTemplate(@Qualifier("campestreDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/