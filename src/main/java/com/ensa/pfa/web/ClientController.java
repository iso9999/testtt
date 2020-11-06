package com.ensa.pfa.web;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
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
import com.ensa.pfa.entities.Avis;
import com.ensa.pfa.entities.Categorie;
import com.ensa.pfa.entities.Client;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
public class ClientController {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private PropositionDao propositionDao;
	
	@Autowired
	private PosteDao posteDao;
	
	@Autowired
	private OffreurDao offreurDao;
	
	@Autowired
	private AvisDao avisDao;
	
	@Autowired
	private DetailDao detailDao;
	
	@Autowired
	private ConvMessagingDao convMessagingDao;
	
	@Autowired
	private CategorieDao categorieDao;
	
	@Autowired
	private NotifSender notifSender;
	
	@Autowired
	private MessagingDao messagingDao;
	
	@Autowired
	private NotificationDao notificationDao;

	
	
	@ModelAttribute(name="client")
	public Client getClient(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		return clientDao.getClientByUsername(principal.getName());
	}
	
	@RequestMapping("/CLIENT/postes/{idPoste}")
	public String project(Model model, @ModelAttribute("client") Client client,@PathVariable Long idPoste) {
		Optional<Poste> poste = posteDao.findById(idPoste);
		model.addAttribute("poste", poste.get());
		List<Poste> postes = posteDao.clientPostes(client.getIdClient());
		model.addAttribute("postes", postes);
		Set<Proposition> propositions = propositionDao.getPropByIdPost(idPoste);
		model.addAttribute("propositions",propositions);
		return "/CLIENT/project";
	}
	
	@RequestMapping("/ROLE_CLIENT/home")
	public String home(Model model, @ModelAttribute("client") Client client) {
		model.addAttribute("client", client);
		return "redirect:/CLIENT/profile";
	}
	
	
	
	@RequestMapping("/CLIENT/profile")
	public String page(Model model, @ModelAttribute("client") Client client) {
		model.addAttribute("client", client);
		model.addAttribute("OpenProjects", posteDao.nbrPosteOpen(client.getIdClient()));
		model.addAttribute("ActiveProjects", posteDao.nbrPosteActive(client.getIdClient()));
		model.addAttribute("PastProjects", posteDao.nbrPostePast(client.getIdClient()));
		model.addAttribute("TotalProjects", posteDao.nbrPosteTotal(client.getIdClient()));
		List<Poste> postes = posteDao.clientPostes(client.getIdClient());
		model.addAttribute("postes", postes);
		return "/CLIENT/profile";
	}
	
	
	@RequestMapping("/CLIENT/fre/{idOffreur}")
	public String freeprofile(Model model, @ModelAttribute("client") Client client, @PathVariable Long idOffreur) {
		model.addAttribute("offreur", offreurDao.findById(idOffreur).get());
		List<Poste> postes = posteDao.freelancePostes(idOffreur);
		List<Avis> avis = avisDao.freelancerRating(idOffreur);
		if(avis.size() != 0) {
			model.addAttribute("moy",avisDao.moyenneRating(idOffreur));
			model.addAttribute("star1", (new Double(avisDao.nbrOfRate(idOffreur, 1))/avis.size())*100);
			model.addAttribute("star2", (new Double(avisDao.nbrOfRate(idOffreur, 2))/avis.size())*100);
			model.addAttribute("star3", (new Double(avisDao.nbrOfRate(idOffreur, 3))/avis.size())*100);
			model.addAttribute("star4", (new Double(avisDao.nbrOfRate(idOffreur, 4))/avis.size())*100);
			model.addAttribute("star5", (new Double(avisDao.nbrOfRate(idOffreur, 5))/avis.size())*100);
		}
		else {
			model.addAttribute("moy",0);
			model.addAttribute("star1", 0);
			model.addAttribute("star2", 0);
			model.addAttribute("star3", 0);
			model.addAttribute("star4", 0);
			model.addAttribute("star5", 0);
		}
		for(Avis a : avis) {
			if(a.getClient().getIdClient() == client.getIdClient()) {
				model.addAttribute("test", "test");
			}
		}
		model.addAttribute("total", avis.size());
		model.addAttribute("avis", avis);
		model.addAttribute("postes", postes);
		model.addAttribute("skills",detailDao.getServicesByidOffreur(idOffreur));
		return "/CLIENT/profileFreelance";
	}
	
	
	@RequestMapping("/CLIENT/chatPage")
	public String chatPage(Model model, @ModelAttribute("client") Client client,String user) {
		model.addAttribute("user", user);
		model.addAttribute("offreur", offreurDao.getOffreurByUsername(user));
		Long idUser = offreurDao.getOffreurByUsername(user).getIdOffreur();
		List<ConversationMessages> messages = convMessagingDao.getConv(idUser, client.getIdClient());
		messages.forEach(m->System.out.println(m.getMsg()));
		model.addAttribute("msgs", messages);
		return "/CLIENT/chat";
	}
	
	@RequestMapping("/CLIENT/addPost")
	public String addPost(Model model, @ModelAttribute("client") Client client) {
		List<Categorie> categories = categorieDao.findAll();
		JsonArray data = new JsonArray();
		for(Categorie c : categories) {
			for(Service s : c.getServices()) {
				JsonObject object = new JsonObject();
				object.addProperty("id", s.getIdService());
				object.addProperty("name", s.getLibelleService());
				object.addProperty("groupName", c.getLibelleCategorie());
				object.addProperty("groupId", c.getIdCategorie());
				data.add(object);
			}
		}
		JsonObject json = new JsonObject();
		json.add("data", data);
		model.addAttribute("poste", new Poste());
		model.addAttribute("json", json);
		return "/CLIENT/addPoste";
	}
	
	
	@RequestMapping("/CLIENT/savePost")
	public String savePost(Model model, @ModelAttribute("client") Client client,@Valid Poste poste) {
		Set<Categorie> categories = new HashSet<>();
		for(Service s : poste.getServices()) {
			Boolean test = false;
			Long idCat = s.getCategorie().getIdCategorie();
			for(Categorie c : categories) {
				if(c.getIdCategorie() == idCat) {
					test = true;
				}
			}
			if(!test) categories.add(s.getCategorie());
		}
		for(Categorie c : categories) {
			poste.addCategorie(c);
		}

		if(poste.getEtatProjet() == null) {
			poste.setEtatProjet(0);
		}
		
		poste.setDatePoste(LocalDate.now());		
		poste.setPayementEnLigne(false);
		poste.setVillePost(client.getVilleClient());
		poste.setClient(client);
		poste = posteDao.save(poste);
		return "redirect:/CLIENT/postes/"+poste.getIdPoste();
	}
	
	@RequestMapping("/CLIENT/projects")
	public String getProjects(Model model, @ModelAttribute("client") Client client,  @RequestParam(defaultValue="4") int etat) {
		List<Poste> postes;
		if(etat == 4) {
			postes = posteDao.clientPostes(client.getIdClient());
		}
		else {
			postes = posteDao.clientPostesByEtat(client.getIdClient(), etat);
		}
		
		model.addAttribute("postes",postes);
		return "/CLIENT/projects";
	}
	
	@RequestMapping("/CLIENT/editPoste")
	public String editPost(Model model, @ModelAttribute("client") Client client, @RequestParam Long idPoste) {
		Poste poste = posteDao.findById(idPoste).get();
		List<Offreur> offreurs = offreurDao.findAll();
		List<Categorie> categories = categorieDao.findAll();
		JsonArray data = new JsonArray();
		
		for(Categorie c : categories) {
			for(Service s : c.getServices()) {
				JsonObject object = new JsonObject();
				object.addProperty("id", s.getIdService());
				object.addProperty("name", s.getLibelleService());
				object.addProperty("groupName", c.getLibelleCategorie());
				object.addProperty("groupId", c.getIdCategorie());
				if(poste.getServices().contains(s)) {
					object.addProperty("selected", "true");
				}
				data.add(object);
			}
		}
		
		JsonObject json = new JsonObject();
		
		json.add("data", data);
		model.addAttribute("json", json);
		model.addAttribute("poste", poste);
		model.addAttribute("offreurs", offreurs);

		
		return "/CLIENT/editPost";
	}
	
	@RequestMapping("/CLIENT/search")
	public String searchClient(@RequestParam("index")String index, @ModelAttribute Client client, Model model) {
		if(index!=null) {
			List<Offreur> offreurs = offreurDao.searchOffreur(index);
			model.addAttribute("offreurs", offreurs);
		}
		else {
			model.addAttribute("offreurs", offreurDao.findAll());
		}
		
		return "/CLIENT/results";
		
		}
	
	@RequestMapping("/CLIENT/setAvis")
	public @ResponseBody Boolean setAvis(@RequestParam("comment")String comment, @RequestParam("rank")int rank, @ModelAttribute Client client, @RequestParam("idOffreur")Long idOffreur) {
		Offreur offreur = offreurDao.getOne(idOffreur);
		
		Avis avis = new Avis(client, offreur, rank, comment);
		avisDao.save(avis);
		
		
		Notification not = new Notification(client.getNomClient()+" "+client.getPrenomClient()+" leave a Review","/FREELANCER/profile");
		not.setOffreur(offreur);
		notificationDao.save(not);
		NotifResult notifResult = new NotifResult(not.getIdNotif(), not.getTitre(), false, not.getOffreur().getIdOffreur(),not.getUrlNotif());
		notifSender.sendNotif(offreur.getUsers().getUsername(), notifResult);
		return true;
	}
	
	@RequestMapping("/CLIENT/getAllConv")
	public @ResponseBody HashMap<String, UserConversations> getAllConv(Long idClient) {
		List<Messaging> messagings =messagingDao.getConvsByIdClient(idClient);
		HashMap<String, UserConversations> conversations = new HashMap<>();
		for(Messaging m : messagings) {
			ConversationMessages conversationMessages = convMessagingDao.getLastMsg(m.getOffreur().getIdOffreur(), idClient).get(0);
			UserConversations userConversations = new UserConversations(m.getOffreur().getUsers().getUsername(), m.getOffreur().getNomOffreur()+" "+m.getOffreur().getPrenomOffreur(), conversationMessages.getMsg(), m.getOffreur().getImage().getUrlImage(), conversationMessages.getDateMsg());
			conversations.put(m.getOffreur().getUsers().getUsername(), userConversations);
		}
		return conversations;
	}
	
}
