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
public class AlmoloyaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.almoloya")
    public DataSourceProperties almoloyaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("almoloyaDataSource")
	@Primary
	public DataSource almoloyaDataSource() {
	    return almoloyaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate almoloyaJdbcTemplate(@Qualifier("almoloyaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
