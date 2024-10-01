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
public class LazaroDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.lazaro")
    public DataSourceProperties lazaroDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("lazaroDataSource")
	@Primary
	public DataSource lazaroDataSource() {
	    return lazaroDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate lazaroJdbcTemplate(@Qualifier("lazaroDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
