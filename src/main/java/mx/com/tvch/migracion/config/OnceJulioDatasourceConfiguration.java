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
public class OnceJulioDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.oncejulio")
    public DataSourceProperties oncejulioDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("oncejulioDataSource")
	@Primary
	public DataSource oncejulioDataSource() {
	    return oncejulioDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate oncejulioJdbcTemplate(@Qualifier("oncejulioDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/