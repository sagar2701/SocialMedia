package com.backend.colloboration.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.backend.colloboration.model.Chat;
import com.backend.colloboration.model.Blog;
import com.backend.colloboration.model.BlogComment;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Friend;
import com.backend.colloboration.model.Job;
import com.backend.colloboration.model.Notification;
import com.backend.colloboration.model.ProfilePicture;
@Configuration

@ComponentScan("com.backend")
@EnableWebMvc
@EnableTransactionManagement
public class HibernateConfig {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");

		dataSource.setUsername("sk");
		dataSource.setPassword("sagar");
		
		
		return dataSource;
	}
      
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.use_sql_comments", "true");
    	//properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
    	return properties;
    }
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	
    	sessionBuilder.addAnnotatedClasses(C_User.class);
    	sessionBuilder.addAnnotatedClasses(Blog.class);
    	sessionBuilder.addAnnotatedClasses(Job.class);
    	sessionBuilder.addAnnotatedClasses(Notification.class);
    	sessionBuilder.addAnnotatedClasses(BlogComment.class);
    	sessionBuilder.addAnnotatedClasses(Friend.class);
    	sessionBuilder.addAnnotatedClasses(ProfilePicture.class);
    	sessionBuilder.addAnnotatedClasses(Chat.class);

    	return sessionBuilder.buildSessionFactory();}
    @Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);

		return transactionManager;
	}

	@Bean
    public CommonsMultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }
   }
