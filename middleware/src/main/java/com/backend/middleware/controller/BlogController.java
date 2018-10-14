package com.backend.middleware.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.colloboration.dao.BlogDao;
import com.backend.colloboration.dao.NotificationDao;
import com.backend.colloboration.dao.UserDao;
import com.backend.colloboration.model.Blog;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Error;
import com.backend.colloboration.model.Notification;
@RestController
@CrossOrigin(origins="http://localhost:8081")
public class BlogController {
	@Autowired
	
	private  BlogDao blogDao;
	@Autowired
	private  UserDao userDao;
	@Autowired
	private  NotificationDao notificationDao;
	
	@GetMapping("/project/getAllBlogs")
	public ResponseEntity<?> getAllBlog(HttpSession session)
	{System.out.println("Approve Blogs");
		String email=(String)session.getAttribute("loggedInUser");
	
	if(email==null)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);		
	}
		System.out.println("List");
	List<Blog>blog=blogDao.getAllBlog();
	if(blog.isEmpty()) {
		Error err=new Error(2,"No records found");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);
	}
	else {
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	}
	@PostMapping("/project/addblog")
	public ResponseEntity<?> addBlog(@RequestBody Blog blog,HttpSession session)
	{System.out.println(blog.getBlogname());
		String email=(String)session.getAttribute("loggedInUser");
	
		if(email==null)
		{
			Error err=new Error(2,"Null value");
			return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);		
		}
		
			blog.setBlogCreation(new Date());
			blog.setPostedby(userDao.getUser(email));
			try {
			blogDao.addblog(blog);
		}
		catch(Exception e)
		{
			Error err=new Error(2,"Null valuse");
			return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
		}
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/project/updateBlog")
	public ResponseEntity<?> updateUser(@RequestBody Blog blog)
	{
		try {
			blogDao.updateblog(blog);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error err=new Error(2,"Null value");
			return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
		}
	}
	@GetMapping("/project/getBlog/{blogid}")
	public ResponseEntity<?> getBlog(@PathVariable int blogid,HttpSession session)
	{String email=(String)session.getAttribute("loggedInUser");
	if(email==null){
		Error errorClazz=new Error(5,"Unauthorized access.. please login..");
		return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
		Blog blog=blogDao.getBlog(blogid);
		System.out.println("blog details");
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	@PutMapping("/approveblog")
	public ResponseEntity<?> approveBlog(@RequestBody Blog blog,HttpSession session)
	{String email=(String)session.getAttribute("loggedInUser");
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
		try {
			blog.setApprovalStatus(true);
			blogDao.updateblog(blog);
			Notification notification=new Notification();
			notification.setApprovalStatus("Approved");
			notification.setBlogPostTitle(blog.getBlogname());
			notification.setEmail(blog.getPostedby().getEmail());
	        notificationDao.addNotification(notification);
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		catch(Exception e)
		{
			Error err=new Error(2,"Null value");
			return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
		}
	}
	@GetMapping("/project/getUnApproveBlogs")
	public ResponseEntity<?> getUnapproveBlog(HttpSession session)
	{System.out.println("Unapprove Blogs");
		String email=(String)session.getAttribute("loggedInUser");
	C_User user=userDao.getUser(email);
	if(email==null)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);		
	}
	if(!user.getRole().equals("ADMIN")){
		Error errorClazz=new Error(5,"Access Denied...You are not authorized to post a job");
		return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	System.out.println("List");
	List<Blog>blog=blogDao.getUnApproveBlogs();
	return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}
	@PutMapping(value="/project/rejectblog")
	public ResponseEntity<?> rejectBlog(@RequestBody Blog blog,@RequestParam String rejectionReason,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			Error errorClazz=new Error(5,"Unauthorized access.. please login..");
			return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		C_User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){
			Error errorClazz=new Error(6,"Access Denied..");
			return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		Notification notification=new Notification();
		notification.setApprovalStatus("Rejected");
		notification.setBlogPostTitle(blog.getBlogname());
		notification.setEmail(blog.getPostedby().getEmail());
		notification.setRejectionReason(rejectionReason);
		notificationDao.addNotification(notification);
		
		blogDao.deleteblog(blog);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
