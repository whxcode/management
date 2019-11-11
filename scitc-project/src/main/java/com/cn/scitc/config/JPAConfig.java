package com.cn.scitc.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author jswzj
 * spring data jpa 配置
 */

@ConfigurationProperties
@EnableJpaRepositories(basePackages = "com.cn.scitc.com.cn.scitc.repository")
@EnableTransactionManagement
public class JPAConfig {

   @Bean
   @ConfigurationProperties(prefix = "spring.datasource")
   public DataSource dataSource(){

       return DataSourceBuilder.create().build();
   }


   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
       HibernateJpaVendorAdapter japVendor = new HibernateJpaVendorAdapter();
       japVendor.setGenerateDdl(false);

       LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
       entityManagerFactory.setDataSource(dataSource());
       entityManagerFactory.setJpaVendorAdapter(japVendor);
       entityManagerFactory.setPackagesToScan("com.cn.scitc.entity");
       return entityManagerFactory;
   }


   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(entityManagerFactory);
       return transactionManager;
   }
}