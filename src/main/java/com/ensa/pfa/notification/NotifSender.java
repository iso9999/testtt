package com.ensa.pfa.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
public class NotifSender {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void sendNotif(String user,NotifResult msg) {
		simpMessagingTemplate.convertAndSendToUser(user, "/queue/notify", msg);
	}

}
