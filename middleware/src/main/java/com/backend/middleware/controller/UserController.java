package com.backend.middleware.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.colloboration.dao.UserDao;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Error;
import com.backend.colloboration.service.EmailService;
@RestController
public class UserController {
@Autowired
private UserDao userDao;
@Autowired
EmailService emailService;
@RequestMapping("/")
public String test()
{
return "index";	
}
@GetMapping("/project/getAllUsers")
public ResponseEntity<?> getAllUsers()
{
	System.out.println("List");
List<C_User>users=userDao.getAllUsers();
if(users.isEmpty()) {
	Error err=new Error(2,"No records found");
	return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);
}
else {
	return new ResponseEntity<List<C_User>>(users,HttpStatus.OK);
}
}

@GetMapping("/test")
public void test1()
{
	System.out.println("Controller test");
}


@PostMapping("/project/addUser")
public ResponseEntity<?> addUser(@RequestBody C_User user)
{
	
	try {
		userDao.addUser(user);
		emailService.approvedUserMessage(user);
		System.out.println("user is saved");
		return new ResponseEntity<C_User>(user,HttpStatus.OK);
	}
	catch(Exception e)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
	}	

}
/*@DeleteMapping("/project/deleteUser/{id}")
public ResponseEntity<?> deleteUser(@PathVariable int id)
{
	try {
		userDao.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	catch(Exception e)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
	}
	
}*/
@PutMapping("/project/updateUser")
public ResponseEntity<?> updateUser(@RequestBody C_User user,HttpSession session)
{String email=(String)session.getAttribute("loggedInUser");
if(email==null){
	Error errorClazz=new Error(4,"Unauthorized access... please login..");
	return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
}
	try {
		userDao.updateUser(user);
		return new ResponseEntity<C_User>(user,HttpStatus.OK);
	}
	catch(Exception e)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
	}
	
}
/*@GetMapping("/project/getUser/{id}")
public ResponseEntity<?> getUser(@PathVariable int id)
{
	C_User user=userDao.getUser(id);
	if(user==null)
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	
	else
		return new ResponseEntity<C_User>(user,HttpStatus.OK);
}*/
@GetMapping("/approveUser/{userId}")
public ResponseEntity<?> approveUser(@PathVariable("email") String email)
{
	try {
		C_User c_User=userDao.getUser(email);
		c_User.setStatus(true);
		userDao.updateUser(c_User);
		return new ResponseEntity<C_User>(c_User,HttpStatus.OK);
	}
	catch(Exception e)
	{
		Error err=new Error(2,"Null value");
		return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
	}
}
@PutMapping(value="/login")

public ResponseEntity<?> login(@RequestBody C_User user,HttpSession session){
C_User validUser=userDao.login(user);

if(validUser==null){
	Error err=new Error(2,"User not authorised or invalid email address");
	return new ResponseEntity<Error>(err,HttpStatus.NOT_FOUND);	
}
else{//valid credentials,valid email and password
	validUser.setOnlinestatus(true);
	userDao.updateUser(validUser);
	session.setAttribute("loggedInUser", validUser.getEmail());
	System.out.println("Session Id" + session.getId());
	System.out.println("Session Attribute " + session.getAttribute("loggedInUser"));
	System.out.println("Created On " + session.getCreationTime());
	return new ResponseEntity<C_User>(validUser,HttpStatus.OK);
}
}
@PutMapping(value="/logout")
public ResponseEntity<?> logout(HttpSession session){
	String email=(String)session.getAttribute("loggedInUser");
	if(email==null){
		Error errorClazz=new Error(4,"Unauthorized access... please login..");
		return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	C_User user=userDao.getUser(email);
	user.setOnlinestatus(false);
	userDao.updateUser(user);
	session.removeAttribute("loggedInUser");
	session.invalidate();
	return new ResponseEntity<Void>(HttpStatus.OK);
}

}
