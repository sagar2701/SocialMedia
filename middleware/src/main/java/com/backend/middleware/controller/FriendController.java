package com.backend.middleware.controller;
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

import com.backend.colloboration.dao.FriendDao;
import com.backend.colloboration.dao.UserDao;
import com.backend.colloboration.model.Friend;
import com.backend.colloboration.model.C_User;
import com.backend.colloboration.model.Error;
@RestController
public class FriendController {
	@Autowired
	private FriendDao friendDao;
		@Autowired
	private UserDao userDao;
		@GetMapping("/project/suggestedusers")
		public ResponseEntity<?> getAllSuggestedUsers(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<C_User> suggestedUsers=friendDao.getSuggestedUsers(email);
			return new ResponseEntity<List<C_User>>(suggestedUsers,HttpStatus.OK);
		}
		//create new friend object [id,toId,fromId,status]
		@PostMapping("/project/friendrequest")
		public ResponseEntity<?> addFriendRequest(@RequestBody C_User friendRequestToId,HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			C_User fromId=userDao.getUser(email);
			Friend friend=new Friend();
			friend.setFromId(fromId);
			friend.setToId(friendRequestToId);
			friend.setStatus('P');
			friendDao.addFriendRequest(friend);
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		@GetMapping("/project/pendingrequests")
		public ResponseEntity<?> getPendingRequests(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<Friend> pendingRequests=friendDao.getPendingRequests(email);
			return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
		}
		@PutMapping("/project/acceptRequest")
		public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend,HttpSession session){
			System.out.println("Friend ID is "+friend.getId());
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			friend.setStatus('A');
			friendDao.acceptFriendRequest(friend);
			return new ResponseEntity<Friend>(friend,HttpStatus.OK);
		}
		@PutMapping("/project/deleteRequest")
		public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend,HttpSession session){
			System.out.println("Friend ID is "+friend.getId());
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			friendDao.deleteFriendRequest(friend);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		@GetMapping("/project/listofFriends")
		public ResponseEntity<?> listOfFriends(HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			List<C_User> friendsDetails=friendDao.listOfFriends(email);
			return new ResponseEntity<List<C_User>>(friendsDetails,HttpStatus.OK);
		}
	}
