package com.ensa.pfa.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ensa.pfa.dao.AvisDao;
import com.ensa.pfa.dao.CategorieDao;
import com.ensa.pfa.dao.ClientDao;
import com.ensa.pfa.dao.DetailDao;
import com.ensa.pfa.dao.OffreurDao;
import com.ensa.pfa.dao.PosteDao;
import com.ensa.pfa.dao.PropositionDao;
import com.ensa.pfa.dao.ServiceDao;
import com.ensa.pfa.entities.Avis;
import com.ensa.pfa.entities.Categorie;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Details;
import com.ensa.pfa.entities.Offreur;
import com.ensa.pfa.entities.Poste;
import com.ensa.pfa.entities.Proposition;
import com.ensa.pfa.entities.Service;
import com.ensa.pfa.entitiesMessaging.ConvMessagingDao;
import com.ensa.pfa.entitiesMessaging.ConversationMessages;
import com.ensa.pfa.entitiesMessaging.Messaging;
import com.ensa.pfa.entitiesMessaging.MessagingDao;
import com.ensa.pfa.entitiesMessaging.UserConversations;
import com.ensa.pfa.notification.NotifResult;
import com.ensa.pfa.notification.NotifSender;
import com.ensa.pfa.notification.Notification;
import com.ensa.pfa.notification.NotificationDao;

@Controller
public class OffereurController {
	
	@Autowired
	private PosteDao posteDao;
	
	@Autowired
	private OffreurDao offreurDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private AvisDao avisDao;
	
	@Autowired
	private CategorieDao categorieDao;
	
	@Autowired
	private DetailDao detailDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private ConvMessagingDao convMessagingDao;
	@Autowired
	private MessagingDao messagingDao;
	
	
	@Autowired
	private PropositionDao propositionDao;
	
	@Autowired
	private NotifSender notifSender;
	
	@Autowired
	private NotificationDao notificationDao;
	
	
	@ModelAttribute(name="offreur")
	public Offreur getOffreur(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return offreurDao.getOffreurByUsername(principal.getName());
	}
	
	@RequestMapping("/FREELANCER/page")
	public String page() {
		return "/FREELANCER/page";
	}
	
	@RequestMapping("/ROLE_FREELANCER/home")
	public String home(Model model, @ModelAttribute Offreur offreur) {
		model.addAttribute("offreur", offreur);
		return "redirect:/FREELANCER/profile";
	}
	
	
	@RequestMapping("/FREELANCER/register")
	public String register(Model model, @ModelAttribute Offreur offreur) {
		List<Categorie> categories = categorieDao.findAll();
		model.addAttribute("categories", categories);
		return "/FREELANCER/register";
	}
	
	
	
	@RequestMapping("/FREELANCER/saveSkills")
	public String saveSkills(@RequestParam("serviceId") List<String> servicesIds,  @ModelAttribute Offreur offreur, Model model) {
		if(servicesIds !=null) {
			for(String id : servicesIds) {
				Service service = serviceDao.findById(new Long(id)).get();
				Details details = new Details(offreur, service, 4);
				detailDao.save(details);
			}
		}
		List<Poste> postes = posteDao.freelancePostes(offreur.getIdOffreur());
		List<Avis> avis = avisDao.freelancerRating(offreur.getIdOffreur());
		if(avis.size() != 0) {
			model.addAttribute("moy",avisDao.moyenneRating(offreur.getIdOffreur()));
			model.addAttribute("star1", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 1))/avis.size())*100);
			model.addAttribute("star2", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 2))/avis.size())*100);
			model.addAttribute("star3", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 3))/avis.size())*100);
			model.addAttribute("star4", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 4))/avis.size())*100);
			model.addAttribute("star5", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 5))/avis.size())*100);
		}
		else {
			model.addAttribute("moy",0);
			model.addAttribute("star1", 0);
			model.addAttribute("star2", 0);
			model.addAttribute("star3", 0);
			model.addAttribute("star4", 0);
			model.addAttribute("star5", 0);
		}
		model.addAttribute("total", avis.size());
		model.addAttribute("avis", avis);
		model.addAttribute("postes", postes);
		model.addAttribute("skills",detailDao.getServicesByidOffreur(offreur.getIdOffreur()));
		return "/FREELANCER/profileFreelance";
	}
	
	@RequestMapping("/FREELANCER/profile")
	public String profile( @ModelAttribute Offreur offreur, Model model) {
		model.addAttribute("offreur", offreur);
		List<Poste> postes = posteDao.freelancePostes(offreur.getIdOffreur());
		List<Avis> avis = avisDao.freelancerRating(offreur.getIdOffreur());
		if(avis.size() != 0) {
			model.addAttribute("moy",avisDao.moyenneRating(offreur.getIdOffreur()));
			model.addAttribute("star1", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 1))/avis.size())*100);
			model.addAttribute("star2", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 2))/avis.size())*100);
			model.addAttribute("star3", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 3))/avis.size())*100);
			model.addAttribute("star4", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 4))/avis.size())*100);
			model.addAttribute("star5", (new Double(avisDao.nbrOfRate(offreur.getIdOffreur(), 5))/avis.size())*100);
		}
		else {
			model.addAttribute("moy",0);
			model.addAttribute("star1", 0);
			model.addAttribute("star2", 0);
			model.addAttribute("star3", 0);
			model.addAttribute("star4", 0);
			model.addAttribute("star5", 0);
		}
		model.addAttribute("total", avis.size());
		model.addAttribute("avis", avis);
		model.addAttribute("postes", postes);
		model.addAttribute("skills",detailDao.getServicesByidOffreur(offreur.getIdOffreur()));
		return "/FREELANCER/profileFreelance";
	}
	
	@RequestMapping("/FREELANCER/search")
	public String searchClient(@RequestParam("index")String index, @ModelAttribute Offreur offreur, Model model) {
		List<Client> clients = clientDao.searchClient(index);
		model.addAttribute("clients", clients);
		return "/FREELANCER/results";
		
		}
	@RequestMapping("/FREELANCER/profileCLient")
	public String profileClient(@ModelAttribute Offreur offreur, Model model, @RequestParam("username") String username) {
		Client client = clientDao.getClientByUsername(username);
		model.addAttribute("OpenProjects", posteDao.nbrPosteOpen(client.getIdClient()));
		model.addAttribute("ActiveProjects", posteDao.nbrPosteActive(client.getIdClient()));
		model.addAttribute("PastProjects", posteDao.nbrPostePast(client.getIdClient()));
		model.addAttribute("TotalProjects", posteDao.nbrPosteTotal(client.getIdClient()));
		List<Poste> postes = posteDao.clientPostes(client.getIdClient());
		model.addAttribute("postes", postes);
		model.addAttribute("client",client);
		return "/FREELANCER/profile";
	}
	
	@RequestMapping("/FREELANCER/chatPage")
	public String chatPage(Model model, @ModelAttribute Offreur offreur,String user) {
		model.addAttribute("user", user);
		model.addAttribute("client", clientDao.getClientByUsername(user));
		Long idUser = clientDao.getClientByUsername(user).getIdClient();
		List<ConversationMessages> messages = convMessagingDao.getConv(offreur.getIdOffreur(), idUser);
		model.addAttribute("msgs", messages);
		return "/FREELANCER/chat";
	}
	
	@RequestMapping("/FREELANCER/posts")
	public String getOpenPosts(Model model, @ModelAttribute Offreur offreur) {
		
		List<Poste> postes = posteDao.getOpenPosts();
		model.addAttribute("postes", postes);
		return "/FREELANCER/posts";
	}
	
	
	@RequestMapping("/FREELANCER/post/{idPoste}")
	public String post(Model model, @ModelAttribute Offreur offreur, @PathVariable Long idPoste) {
		Poste poste = posteDao.findById(idPoste).get();
		model.addAttribute("poste", poste);
		model.addAttribute("client", poste.getClient());
		model.addAttribute("propositions", poste.getProposition());
		for(Proposition p : poste.getProposition()) {
			if(p.getOffreur().getIdOffreur() == offreur.getIdOffreur()) {
				model.addAttribute("test", "test");
			}
		}
		return "FREELANCER/post";
	}
	
	@RequestMapping("/FREELANCER/saveProp")
	public @ResponseBody Boolean saveProp(Model model, @ModelAttribute Offreur offreur, @RequestParam int period,@RequestParam Long price ,@RequestParam Long idPoste) {
		Poste poste = posteDao.findById(idPoste).get();
		Proposition proposition = new Proposition(poste, offreur, period, "", price);
		propositionDao.save(proposition);
		Client client = posteDao.findById(idPoste).get().getClient();
		Notification not = new Notification("you get a proposition from "+offreur.getNomOffreur()+" "+offreur.getPrenomOffreur(),"/CLIENT/postes/"+poste.getIdPoste());
		not.setClient(client);
		notificationDao.save(not);
		NotifResult notifResult = new NotifResult(not.getIdNotif(), not.getTitre(), false, not.getClient().getIdClient(),not.getUrlNotif());
		notifSender.sendNotif(client.getUsers().getUsername(), notifResult);
		
		return true;
	}
	
	@RequestMapping("/FREELANCER/getAllConv")
	public @ResponseBody HashMap<String, UserConversations> getAllConv(Long idOffreur) {
		List<Messaging> messagings =messagingDao.getConvsByIdOffreur(idOffreur);
		System.out.println(messagings.size());
		HashMap<String, UserConversations> conversations = new HashMap<>();
		for(Messaging m : messagings) {
			ConversationMessages conversationMessages = convMessagingDao.getLastMsg(idOffreur, m.getClient().getIdClient()).get(0);
			UserConversations userConversations = new UserConversations(m.getClient().getUsers().getUsername(), m.getClient().getNomClient()+" "+m.getClient().getPrenomClient(), conversationMessages.getMsg(), m.getClient().getImage().getUrlImage(), conversationMessages.getDateMsg());
			conversations.put(m.getClient().getUsers().getUsername(), userConversations);
		}
		return conversations;
	}
	
	
}
