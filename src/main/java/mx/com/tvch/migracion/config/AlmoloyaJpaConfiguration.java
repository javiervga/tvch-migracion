package mx.com.tvch.migracion.config;

import java.util.HashMap;
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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "mx.com.tvch.migracion.repository.almoloya", 
		entityManagerFactoryRef = "almoloyaEntityManagerFactory", 
		transactionManagerRef = "almoloyaTransactionManager")
public class AlmoloyaJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean almoloyaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("almoloyaDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.almoloya")
		          .build();
	}
	
	@Bean
	@Qualifier("almoloyaEntityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}

	@Bean
	public PlatformTransactionManager almoloyaTransactionManager(
			@Qualifier("almoloyaEntityManagerFactory") LocalContainerEntityManagerFactoryBean almoloyaEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(almoloyaEntityManagerFactory.getObject()));
	}

}
