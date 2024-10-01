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
public class SantaJuliaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.santajulia")
    public DataSourceProperties santajuliaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("santajuliaDataSource")
	@Primary
	public DataSource santajuliaDataSource() {
	    return santajuliaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate santajuliaJdbcTemplate(@Qualifier("santajuliaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/