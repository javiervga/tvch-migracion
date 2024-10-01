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
public class TepeapulcoDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.tepeapulco")
    public DataSourceProperties tepeapulcoDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("tepeapulcoDataSource")
	@Primary
	public DataSource tepeapulcoDataSource() {
	    return tepeapulcoDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate tepeapulcoJdbcTemplate(@Qualifier("tepeapulcoDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
