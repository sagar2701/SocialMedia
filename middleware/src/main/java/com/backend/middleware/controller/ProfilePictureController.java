package com.backend.middleware.controller;
import javax.servlet.http.HttpSession;
import com.backend.colloboration.dao.ProfilePictureDao;
import com.backend.colloboration.model.ProfilePicture;
import com.backend.colloboration.model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@RestController
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
		@PostMapping("/project/uploadprofilepicture")
		public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
			String email=(String)session.getAttribute("loggedInUser");
			if(email==null){
				Error errorClazz=new Error(5,"Unauthorized access.. please login..");
				return new ResponseEntity<Error>(errorClazz,HttpStatus.UNAUTHORIZED);
			}
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setEmail(email);
			profilePicture.setImage(image.getBytes());
			profilePictureDao.uploadProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);
		}
		@GetMapping("/project/getimage")
		public @ResponseBody byte[] getProfilePicture(@RequestParam String email,HttpSession session){
	    	System.out.println("email is " + email);
			String authEmail=(String)session.getAttribute("loggedInUser");
			if(authEmail==null){
				return null;
			}
			ProfilePicture profilePicture=profilePictureDao.getProfilePicture(email);
			if(profilePicture==null)//No image
				return null;
			else
				return profilePicture.getImage();
		}
}
