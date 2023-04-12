package efids.efids.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import efids.efids.model.User_login;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "efids/efids/repository",
        entityManagerFactoryRef = "efidsEntityManagerFactory",
        transactionManagerRef = "efidsTransactionManager")
public class EfidsDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties efidsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.mysql.configuration")
    public DataSource efidsDataSource() {
        return efidsDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "efidsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean efidsEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(efidsDataSource())
                .packages( User_login.class)
                .build();
    }

    @Primary
    @Bean(name = "efidsTransactionManager")
    public PlatformTransactionManager efidsTransactionManager(
            final @Qualifier("efidsEntityManagerFactory") LocalContainerEntityManagerFactoryBean efidsEntityManagerFactory) {
        return new JpaTransactionManager(efidsEntityManagerFactory.getObject());
    }
}