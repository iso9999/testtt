
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
import com.ensa.pfa.dao.OffreurDao;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Offreur;

@Controller
public class NotificationOffreurController {
	
	@Autowired
	private NotifSender notifSender;
	
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private OffreurDao offreurDao;
	
	
	@ModelAttribute(name="offreur")
	public Offreur getOffreur(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return offreurDao.getOffreurByUsername(principal.getName());
	}
	
	@RequestMapping("/FREELANCER/getNotif")
	public @ResponseBody List<NotifResult> getNotif(@RequestParam Long idOffreur){
		List<Notification> ntfs = new ArrayList<>();
		ntfs = notificationDao.getNotifsOffreur(idOffreur);
		List<NotifResult> notifResults = new ArrayList<>();
		for(Notification n : ntfs) {
			notifResults.add(new NotifResult(n.getIdNotif(), n.getTitre(), n.getEtat(), idOffreur,n.getUrlNotif()));
		}
		
		return notifResults;
	}
	
	
	@RequestMapping("/FREELANCER/readAllNotif")
	public @ResponseBody Boolean readAllNotifClient(@ModelAttribute Offreur offreur) {
		notificationDao.updateEtatAllNotifOffreur(offreur.getIdOffreur());
		return true;
	}
}
