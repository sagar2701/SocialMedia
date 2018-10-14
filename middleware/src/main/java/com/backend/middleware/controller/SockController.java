package com.backend.middleware.controller;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


//import org.springframework.messaging.simp.annotation.SubscribeEvent;

import org.springframework.stereotype.Controller;
import com.backend.colloboration.model.Message;
import com.backend.colloboration.model.OutputMessage;
@Controller
public class SockController {
	private static final Logger logger = 
			LoggerFactory.getLogger(SockController.class);
	  
	  @MessageMapping("/chat_forum")   ///sendMessage
	  @SendTo("/topic/message")        //receiveMessage
	  public OutputMessage sendMessage(Message message) {
		  logger.debug("Calling the method sendMessage");
	  
		//  logger.debug(" Message ID : ",message.getId());
	    return new OutputMessage(message, new Date()); 
	  }
	  
}
