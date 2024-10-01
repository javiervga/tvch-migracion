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
		basePackages = "mx.com.tvch.migracion.repository.epazoyucan", 
		entityManagerFactoryRef = "epazoyucanEntityManagerFactory", 
		transactionManagerRef = "epazoyucanTransactionManager")
public class EpazoyucanJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean epazoyucanEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("epazoyucanDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.epazoyucan")
		          .build();
	}

	@Bean
	public PlatformTransactionManager epazoyucanTransactionManager(
			@Qualifier("epazoyucanEntityManagerFactory") LocalContainerEntityManagerFactoryBean epazoyucanEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(epazoyucanEntityManagerFactory.getObject()));
	}

}
