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
		basePackages = "mx.com.tvch.migracion.repository.providencia", 
		entityManagerFactoryRef = "providenciaEntityManagerFactory", 
		transactionManagerRef = "providenciaTransactionManager")
public class ProvidenciaJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean providenciaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("providenciaDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.providencia")
		          .build();
	}

	@Bean
	public PlatformTransactionManager providenciaTransactionManager(
			@Qualifier("providenciaEntityManagerFactory") LocalContainerEntityManagerFactoryBean providenciaEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(providenciaEntityManagerFactory.getObject()));
	}

}*/
