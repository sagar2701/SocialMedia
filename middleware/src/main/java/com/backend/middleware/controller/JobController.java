package com.backend.middleware.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.backend.colloboration.dao.JobDao;
import com.backend.colloboration.dao.UserDao;
import com.backend.colloboration.model.Job;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Error;
@RestController
public class JobController {
	@Autowired
	private JobDao jobDao;
		@Autowired
	private UserDao userDao;
		@PostMapping("/project/addJob")
		public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			//CHECK FOR AUTHENTICATION
	    	if(email==null){
				Error errorClazz=new Error(4,"Unauthorized access... please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
	    	//CHECK FOR AUTHORIZATION[ROLE]
	    	C_User user=userDao.getUser(email);
	    	if(!user.getRole().equals("ADMIN")){
	    		Error errorClazz=new Error(5,"Access Denied...You are not authorized to post a job");
	    		return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
	    	}
	    	try{
	        job.setPostedOn(new Date());		
	    	jobDao.saveJob(job);
	    	}catch(Exception e){
	    		Error errorClazz=new Error(6,"Unable to post job details");
	    		return new ResponseEntity<Error>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		@GetMapping("/project/getAllJobs")
		public ResponseEntity<?> getAllJobs(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(4,"Unauthorized access... please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<Job> jobs=jobDao.getAllJobs();
			return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		}
}
