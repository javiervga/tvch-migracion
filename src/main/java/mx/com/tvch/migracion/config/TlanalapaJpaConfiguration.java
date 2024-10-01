/*package mx.com.tvch.migracion.config;

import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "mx.com.tvch.migracion.repository.tlanalapa", 
		entityManagerFactoryRef = "tlanalapaEntityManagerFactory", 
		transactionManagerRef = "tlanalapaTransactionManager")
public class TlanalapaJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean tlanalapaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("tlanalapaDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.tlanalapa")
		          .build();
	}

	@Bean
	public PlatformTransactionManager tlanalapaTransactionManager(
			@Qualifier("tlanalapaEntityManagerFactory") LocalContainerEntityManagerFactoryBean tlanalapaEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(tlanalapaEntityManagerFactory.getObject()));
	}

}
*/