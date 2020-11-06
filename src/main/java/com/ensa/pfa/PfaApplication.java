package com.ensa.pfa;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ensa.pfa.dao.ImageDao;
import com.ensa.pfa.entities.Categorie;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Offreur;
import com.ensa.pfa.entities.Poste;
import com.ensa.pfa.entities.Proposition;
import com.ensa.pfa.entities.Service;
import com.ensa.pfa.metier.CategorieMetier;
import com.ensa.pfa.metier.ClientMetier;
import com.ensa.pfa.metier.ImageMetier;
import com.ensa.pfa.metier.OffreurMetier;
import com.ensa.pfa.metier.ServiceMetier;

@SpringBootApplication
public class PfaApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(PfaApplication.class, args);
		/*
		CategorieMetier categorieMetier = ctx.getBean(CategorieMetier.class);
		Categorie categorie = new Categorie("Web Develeppement", null);
		categorieMetier.saveCategorie(categorie);
		Categorie categorie2 = new Categorie("Design", null);
		categorieMetier.saveCategorie(categorie2);
		
		ServiceMetier serviceMetier = ctx.getBean(ServiceMetier.class);
		Service service = new Service("php", true, categorie);
		serviceMetier.saveService(service);
		Service service2 = new Service("java", true, categorie);
		serviceMetier.saveService(service2);
		serviceMetier.saveService(new Service("Reby", true, categorie));
		serviceMetier.saveService(new Service("Phython", true, categorie));
		serviceMetier.saveService(new Service("CSS", true, categorie2));
		serviceMetier.saveService(new Service("Photoshop", true, categorie2));
		
		ImageMetier imageMetier = ctx.getBean(ImageMetier.class);
		imageMetier.saveImageDefault();
		imageMetier.saveImage();
		/*
		Image
		
		System.out.println(clientMetier.saveClient(client));
		Poste poste = new Poste("siteWeb", "i want ", LocalDate.now(), 30, 25, 2000, 3000, false, "Agadir", null, client,services);
		clientMetier.Savepost(poste);
		
		List<Service> list = serviceMetier.getServicesByCategorie(categorie.getIdCategorie());
		for (Service s : list) {
			System.out.println(s.getLibelleService());
		}
		
		OffreurMetier offreurMetier = ctx.getBean(OffreurMetier.class);
		Offreur offreur = new Offreur("Housni", "med", "tikiouine", 'a');
		offreurMetier.saveOffreur(offreur);
		Proposition proposition = new Proposition(poste, offreur, 27, "i will");
		offreurMetier.proposer(proposition);
		
		Set<Proposition> propositions = clientMetier.getInfoPost(poste.getIdPoste());
		for (Proposition pr : propositions) {
			System.out.println(pr.getOffreur().getNomOffreur()+" "+pr.getOffreur().getPrenomOffreur());
			System.out.println(pr.getDureePropositon());
			System.out.println(pr.getCommentaireProposition());
		}
		
			*/	
		
	}
}
