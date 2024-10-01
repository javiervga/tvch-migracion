/*package mx.com.tvch.migracion.config;

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
		basePackages = "mx.com.tvch.migracion.repository.apan", 
		entityManagerFactoryRef = "apanEntityManagerFactory", 
		transactionManagerRef = "apanTransactionManager")
public class ApanJpaConfiguration {
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean apanEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("apanDataSource") DataSource dataSource) {
		return builder
		          .dataSource(dataSource)
		          .packages("mx.com.tvch.migracion.entity.apan")
		          .build();
	}
	
	@Bean
	@Qualifier("apanEntityManagerFactoryBuilder")
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}

	@Bean
	public PlatformTransactionManager apanTransactionManager(
			@Qualifier("apanEntityManagerFactory") LocalContainerEntityManagerFactoryBean apanEntityManagerFactory) {
		return new JpaTransactionManager(Objects.requireNonNull(apanEntityManagerFactory.getObject()));
	}

}
*/