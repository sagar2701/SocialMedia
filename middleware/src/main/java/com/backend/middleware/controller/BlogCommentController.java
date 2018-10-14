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

import com.backend.colloboration.dao.BlogCommentDao;
import com.backend.colloboration.dao.BlogDao;
import com.backend.colloboration.dao.NotificationDao;
import com.backend.colloboration.dao.UserDao;
import com.backend.colloboration.model.Blog;
import com.backend.colloboration.model.BlogComment;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Error;
@RestController
public class BlogCommentController {
	@Autowired
	private BlogCommentDao blogCommentDao;
	@Autowired
	private UserDao userDao;
	@PostMapping("/project/addComment")
	public ResponseEntity<?> addBlogComment(@RequestBody Blog blog,@RequestParam String commentTxt,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			Error errorClazz=new Error(5,"Unauthorized access.. please login..");
			return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		BlogComment blogComment=new BlogComment();
		blogComment.setCommentTxt(commentTxt);
		blogComment.setBlog(blog);
		C_User user=userDao.getUser(email);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		blogCommentDao.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
	}
	@GetMapping("project/getcomments/{blogId}")
	public ResponseEntity<?> getBlogComments(@PathVariable int blogId,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			Error errorClazz=new Error(5,"Unauthorized access.. please login..");
			return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		
		List<BlogComment> comments=blogCommentDao.getBlogComments(blogId);
		return new ResponseEntity<List<BlogComment>>(comments,HttpStatus.OK);
	}
}

