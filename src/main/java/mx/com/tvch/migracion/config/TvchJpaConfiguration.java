package mx.com.tvch.migracion.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "mx.com.tvch.migracion.repository.tvch", 
		entityManagerFactoryRef = "tvchEntityManagerFactory", 
		transactionManagerRef = "tvchTransactionManager")
public class TvchJpaConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean tvchEntityManagerFactory(
			@Qualifier("tvchDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.tvch")
		          .build();
	}

	@Bean
	public PlatformTransactionManager tvchTransactionManager(
			@Qualifier("tvchEntityManagerFactory") LocalContainerEntityManagerFactoryBean tvchEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(tvchEntityManagerFactory.getObject()));
	}
}
