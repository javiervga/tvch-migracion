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
		basePackages = "mx.com.tvch.migracion.repository.chimalpa", 
		entityManagerFactoryRef = "chimalpaEntityManagerFactory", 
		transactionManagerRef = "chimalpaTransactionManager")
public class ChimalpaJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean chimalpaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("chimalpaDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.chimalpa")
		          .build();
	}

	@Bean
	public PlatformTransactionManager chimalpaTransactionManager(
			@Qualifier("chimalpaEntityManagerFactory") LocalContainerEntityManagerFactoryBean chimalpaEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(chimalpaEntityManagerFactory.getObject()));
	}

}
