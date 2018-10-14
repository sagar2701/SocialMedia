package com.backend.colloboration.test;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
public class AppTest extends TestCase
   
{
	public AppTest(String testname)
	{
		super(testname);
	}
	public static Test suite()
	{
		return new TestSuite(AppTest.class);
	}
	public void testApp()
	{
		assertTrue(true);
	}
	
}
