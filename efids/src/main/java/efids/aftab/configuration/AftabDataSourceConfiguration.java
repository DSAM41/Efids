package efids.aftab.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import efids.aftab.model.AftabArrival;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "efids/aftab/repository",
        entityManagerFactoryRef = "aftabEntityManagerFactory",
        transactionManagerRef = "aftabTransactionManager")
public class AftabDataSourceConfiguration {
    @Bean
    //@Primary
    @ConfigurationProperties("spring.datasource.oracle")
    public DataSourceProperties aftabDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    //@Primary
    @ConfigurationProperties("spring.datasource.oracl.configuration")
    public DataSource aftabDataSource() {
        return aftabDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    //@Primary
    @Bean(name = "aftabEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean aftabEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(aftabDataSource())
                .packages( AftabArrival.class)
                .build();
    }

    //@Primary
    @Bean(name = "aftabTransactionManager")
    public PlatformTransactionManager aftabTransactionManager(
            final @Qualifier("aftabEntityManagerFactory") LocalContainerEntityManagerFactoryBean aftabEntityManagerFactory) {
        return new JpaTransactionManager(aftabEntityManagerFactory.getObject());
    }
}