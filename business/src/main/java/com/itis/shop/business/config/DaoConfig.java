package com.itis.shop.business.config;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(basePackages = "com.itis.shop.business.repository")
@PropertySource("classpath:dao.properties")
public class DaoConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }



    @Bean(name = "entityManagerFactory")
    @PersistenceUnit(unitName = "")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean =
                new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("com.itis.shop.model");
        factoryBean.setJpaDialect(new HibernateJpaDialect());
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DIALECT, PostgreSQL95Dialect.class);
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
//        properties.put("hibernate.search.default.directory_provider", "filesystem");
        properties.put("hibernate.search.default.directory_provider", "filesystem");
        properties.put("hibernate.search.default.indexBase", System.getProperty("user.home") + "/spring_shop/lucene/indexes");
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    @Bean
    public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        return adapter;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

//    @Bean(name = "entityManager")
//    public EntityManager entityManager() {
//        return sessionFactory().getObject().getCurrentSession();
//    }
//    @Bean(name = "sessionFactory")
//    @PersistenceUnit(unitName = "sessionFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("com.itis.shop", "com.itis.shop.model");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//        return sessionFactory;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put(AvailableSettings.DIALECT, PostgreSQL95Dialect.class);
//        properties.put(AvailableSettings.SHOW_SQL, true);
//        properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
//        properties.put(AvailableSettings.FORMAT_SQL, true);
//        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
////        properties.put("hibernate.search.default.directory_provider", "filesystem");
////        properties.put("hibernate.search.default.indexBase", System.getProperty("user.home") + "/var/lucene/indexes");
//
//        return properties;
//    }
}
