package com.backend.colloboration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.backend.colloboration.model.C_User;

import javax.mail.MessagingException;
import javax.mail.internet.*;
@Service("emailService")
public class EmailService {

	// Autowired the mail sender bean here

	@Autowired
	private JavaMailSender mailSender;

	// email name which is not similar to the username
	private static String from = "Social-Site";

	/**
	 * approvedUserMessage method will be called using emailService that can be
	 * Autowired args - User requires the user object to fetch the email and
	 * other content of the user to send the email
	 * @return 
	 */
	public void approvedUserMessage(C_User user) {

		// Mime message is used to send email like an HTML page
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = null;

		try {

			// instantiating the helper and attaching it with mimeMessage
			helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

			// set up your HTML message here
			StringBuilder htmlMsg = new StringBuilder();

			htmlMsg.append("<h1>Welcome " + user.getFirstname() + "<br>Your user id is" + user.getUserid() + " on my Social Site!</h1>");
			htmlMsg.append("<a href='http://localhost:8081/middleware/approveUser/'"+user.getUserid()+">http://localhost:8081/middleware/approveUser/"+user.getUserid()+"</a><br/>");
			htmlMsg.append("<p>Please click on the link to activate your account!</p><br/>");
			htmlMsg.append("<p>Thanks for joining with us!</p><br/>");
			

			// add the HTML content to the mimeMessage
			mimeMessage.setContent(htmlMsg.toString(), "text/html");

			// set the subject and recipient of the email
			helper.setTo(user.getEmail());
			helper.setSubject("WELCOME TO MY SOCIAL SITE");
			helper.setFrom(from);

			// send the message
			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
	
		}
	}

}

