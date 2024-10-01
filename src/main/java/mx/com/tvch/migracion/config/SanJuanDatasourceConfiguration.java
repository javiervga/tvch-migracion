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
public class SanJuanDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.sanjuan")
    public DataSourceProperties sanjuanDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("sanjuanDataSource")
	@Primary
	public DataSource sanjuanDataSource() {
	    return sanjuanDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate sanjuanJdbcTemplate(@Qualifier("sanjuanDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
