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
public class VillasDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.villas")
    public DataSourceProperties villasDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("villasDataSource")
	@Primary
	public DataSource villasDataSource() {
	    return villasDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate villasJdbcTemplate(@Qualifier("villasDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/