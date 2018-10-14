/*package com.backend.colloboration.test;



import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.backend.colloboration.dao.*;
import com.backend.colloboration.model.*;


public class UserTest {
	public static AnnotationConfigApplicationContext context;
	public static UserDao userDao;
	
	@BeforeClass
	public static void init()
	{
		
		context=new AnnotationConfigApplicationContext();
		context.scan("com.backend");
		context.refresh();
		userDao=(UserDao)context.getBean("userDao");
	}
	@Test

	public void testAddUser(){
	    C_User user=new C_User();
		user.setFirstname("s2");
		user.setLastname("K2");
		user.setEmail("ss2");
		user.setMobile(99118);
		user.setPassword("jkfdjk1");
		user.setDob("27/01/1997");
		
		
		Assert.assertEquals("successfully insertion",true,userDao.addUser(user));
	}
	

}
*/