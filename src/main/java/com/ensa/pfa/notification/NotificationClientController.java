package com.ensa.pfa.notification;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensa.pfa.dao.ClientDao;
import com.ensa.pfa.entities.Client;


@Controller
public class NotificationClientController {
	

	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private ClientDao clientDao;
	
	
	@ModelAttribute(name="client")
	public Client getClient(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return clientDao.getClientByUsername(principal.getName());
	}
	
	
	@RequestMapping("/CLIENT/getNotif")
	public @ResponseBody List<NotifResult> getNotif(@RequestParam Long idClient){
		List<Notification> ntfs = new ArrayList<>();
		ntfs = notificationDao.getNotifsClient(idClient);
		List<NotifResult> notifResults = new ArrayList<>();
		for(Notification n : ntfs) {
			notifResults.add(new NotifResult(n.getIdNotif(), n.getTitre(), n.getEtat(), idClient, n.getUrlNotif()));
		}
		
		return notifResults;
	}
	
	@RequestMapping("/readNotif")
	public @ResponseBody Boolean readNotif(@RequestParam Long idNotif) {
		Optional<Notification> notification = notificationDao.findById(idNotif);
		if(notification.isPresent() == false)return false;
		else {
			notification.get().setEtat(true);
			notificationDao.save(notification.get());
			return true;
		}
	}
	
	@RequestMapping("/CLIENT/readAllNotif")
	public @ResponseBody Boolean readAllNotifClient(@ModelAttribute Client client) {
		notificationDao.updateEtatAllNotifClient(client.getIdClient());
		return true;
	}
}
