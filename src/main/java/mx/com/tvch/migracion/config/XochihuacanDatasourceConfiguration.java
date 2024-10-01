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
public class XochihuacanDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.xochihuacan")
    public DataSourceProperties xochihuacanDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("xochihuacanDataSource")
	@Primary
	public DataSource xochihuacanDataSource() {
	    return xochihuacanDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .driverClassName("com.mysql.cj.jdbc.Driver")
	      .build();
	}
	
	@Bean
	public JdbcTemplate xochihuacanJdbcTemplate(@Qualifier("xochihuacanDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/