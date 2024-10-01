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
public class VeinteNoviembreDatasourceConfiguration {
	
	@Bean
    @ConfigurationProperties("spring.datasource.veintenoviembre")
    public DataSourceProperties veintenoviembreDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Bean("veintenoviembreDataSource")
	@Primary
	public DataSource veintenoviembreDataSource() {
	    return veintenoviembreDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
	
	@Bean
	public JdbcTemplate veintenoviembreJdbcTemplate(@Qualifier("veintenoviembreDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}

}
*/