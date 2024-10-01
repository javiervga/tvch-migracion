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
public class PiracantosDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.piracantos")
    public DataSourceProperties piracantosDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("piracantosDataSource")
	@Primary
	public DataSource piracantosDataSource() {
	    return piracantosDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate piracantosJdbcTemplate(@Qualifier("piracantosDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/