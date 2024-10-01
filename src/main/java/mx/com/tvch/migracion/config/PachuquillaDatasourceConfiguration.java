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
public class PachuquillaDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.pachuquilla")
    public DataSourceProperties pachuquillaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("pachuquillaDataSource")
	@Primary
	public DataSource pachuquillaDataSource() {
	    return pachuquillaDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate pachuquillaJdbcTemplate(@Qualifier("pachuquillaDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/