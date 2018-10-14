package com.backend.middleware.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.backend.colloboration.model.Error;
import com.backend.colloboration.dao.NotificationDao;
import com.backend.colloboration.model.Notification;
@RestController
public class NotificationController {
	
			@Autowired
		private NotificationDao notificationDao;
			@RequestMapping(value="/notifications",method=RequestMethod.GET)
		public ResponseEntity<?> getNotificationsNotViewed(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(4,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<Notification> notifications=notificationDao.getNotificationNotViewed(email);
			return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
		}
			@RequestMapping(value="/notification/{id}",method=RequestMethod.GET)
			public ResponseEntity<?> getNotification(@PathVariable int id,HttpSession session){
				String email=(String)session.getAttribute("loggedInUser");
				if(email==null){
					Error errorClazz=new Error(5,"Unauthorized access.. please login..");
					return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
				Notification notification=notificationDao.getNotification(id);
				return new ResponseEntity<Notification>(notification,HttpStatus.OK);
			}
			@PutMapping(value="updatenotification/{id}")
			public ResponseEntity<?> UpdateNotification(@PathVariable int id,HttpSession session){
				String email=(String)session.getAttribute("loggedInUser");
				if(email==null){
					Error errorClazz=new Error(5,"Unauthorized access.. please login..");
					return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
				}
				Notification notification=notificationDao.updateNotification(id);
				return new ResponseEntity<Notification>(notification,HttpStatus.OK);
			}
		}



