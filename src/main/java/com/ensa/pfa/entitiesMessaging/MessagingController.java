package com.ensa.pfa.entitiesMessaging;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensa.pfa.entitiesMessaging.ConversationMessages;
import com.ensa.pfa.entitiesMessaging.Message;
import com.ensa.pfa.entitiesMessaging.Messaging;
import com.ensa.pfa.entitiesMessaging.UserConversations;
import com.ensa.pfa.dao.ClientDao;
import com.ensa.pfa.dao.OffreurDao;
import com.ensa.pfa.dao.UsersDao;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Users;



@Controller
public class MessagingController {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private OffreurDao offreurDao;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private ConvMessagingDao convMessagingDao;
	
	@Autowired
	private MessagingDao messagingDao;
	
	@Autowired
	private UsersDao usersDao;
	
	/*@RequestMapping("/chatPage")
	public String videoChatPage(Model model, @ModelAttribute("client") Client client,String user) {
		model.addAttribute("user", user);
		Long idUser = clientDao.getClientByUsername(user).getIdClient();
		List<ConversationMessages> messages = convMessagingDao.getConv(idUser, client.getIdClient());
		model.addAttribute("msgs", messages);
		return "chatPage";
	}*/
	
	@ModelAttribute(name="client")
	public Client getClient(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return clientDao.getClientByUsername(principal.getName());
	}
	
	
	@MessageMapping("/send")
	@SendToUser
	public Boolean sendMsg(Message message) {
		Users users = usersDao.findById(message.getFrom()).get();
		if(users.getOffreur() == null) {
			
		}
		else {
			System.out.println("it's from freelancer");
		}
		Messaging messaging = messagingDao.getMessaging(message.getFrom(), message.getTo());
		if(messaging == null) {
			System.out.println("null--------------------------------");
			if(users.getOffreur() == null) {
				messaging = new Messaging(clientDao.getClientByUsername(message.getFrom()), offreurDao.getOffreurByUsername(message.getTo()));
			}
			else {
				messaging = new Messaging(clientDao.getClientByUsername(message.getTo()), offreurDao.getOffreurByUsername(message.getFrom()));
			}
			
			messagingDao.save(messaging);
			convMessagingDao.save(new ConversationMessages(message.getMsg(), LocalDateTime.now(), messaging, message.getFrom()));
		}
		else{
			System.out.println("not null-----------------------------");
			convMessagingDao.save(new ConversationMessages(message.getMsg(), LocalDateTime.now(), messaging, message.getFrom()));
		}
		simpMessagingTemplate.convertAndSendToUser(message.getTo(), "topic/msg/"+message.getFrom(), message.getMsg());
		simpMessagingTemplate.convertAndSendToUser(message.getTo(), "topic/msg", message.getMsg());
		return true;
	}
}
