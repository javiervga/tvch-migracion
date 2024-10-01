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
		basePackages = "mx.com.tvch.migracion.repository.pachuquilla", 
		entityManagerFactoryRef = "pachuquillaEntityManagerFactory", 
		transactionManagerRef = "pachuquillaTransactionManager")
public class PachuquillaJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean pachuquillaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("pachuquillaDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.pachuquilla")
		          .build();
	}

	@Bean
	public PlatformTransactionManager pachuquillaTransactionManager(
			@Qualifier("pachuquillaEntityManagerFactory") LocalContainerEntityManagerFactoryBean pachuquillaEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(pachuquillaEntityManagerFactory.getObject()));
	}

}
*/