package ru.job4j.accidents.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.accidents.model.Accident;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class HbmConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(@Value("${hibernate.dialect}") String dialect,
                                                  @Value("${pathToScan}") String pathScan,
                                                  DataSource ds) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan(pathScan);
        Properties cfg = new Properties();
        cfg.setProperty("hibernate.dialect", dialect);
        sessionFactory.setHibernateProperties(cfg);
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager htx(SessionFactory sf) {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sf);
        return tx;
    }
}
