package mx.com.tvch.migracion.config;

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
		basePackages = "mx.com.tvch.migracion.repository.real", 
		entityManagerFactoryRef = "realEntityManagerFactory", 
		transactionManagerRef = "realTransactionManager")
public class RealJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean realEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("realDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.real")
		          .build();
	}

	@Bean
	public PlatformTransactionManager realTransactionManager(
			@Qualifier("realEntityManagerFactory") LocalContainerEntityManagerFactoryBean realEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(realEntityManagerFactory.getObject()));
	}

}
