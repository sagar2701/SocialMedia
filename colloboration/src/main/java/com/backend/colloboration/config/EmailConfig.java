package com.backend.colloboration.config;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
@Configuration
@ComponentScan("com.backend.colloboration.service")
public class EmailConfig {

	@Bean(name="mailSender")
	public JavaMailSender getMailSender()
	{
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("sagar123@gmail.com");
		mailSender.setPassword("12345678");
		mailSender.setJavaMailProperties(getMailProperties());
		
		return mailSender;
	}

	private Properties getMailProperties() {
		Properties mailProperties=new Properties();
		mailProperties.put("mail.transport.protocol", "smtp");
		mailProperties.put("mail.smtp.auth", "true");
		mailProperties.put("mail.smtp.starttls.enable", "true");
		mailProperties.put("mail.debug", "true");
		
		
		return mailProperties;
	}
	
}
