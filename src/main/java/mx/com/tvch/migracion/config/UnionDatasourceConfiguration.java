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
public class UnionDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.union")
    public DataSourceProperties unionDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("unionDataSource")
	@Primary
	public DataSource unionDataSource() {
	    return unionDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate unionJdbcTemplate(@Qualifier("unionDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
