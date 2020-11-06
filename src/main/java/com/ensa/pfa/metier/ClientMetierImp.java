package com.ensa.pfa.metier;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.pfa.dao.ClientDao;
import com.ensa.pfa.dao.PosteDao;
import com.ensa.pfa.dao.PropositionDao;
import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Poste;
import com.ensa.pfa.entities.Proposition;


@Service
public class ClientMetierImp implements ClientMetier {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private PosteDao posteDao;
	
	@Autowired 
	private PropositionDao propositionDao;
	
	
	@Override
	public Boolean saveClient(Client client) {
		Client cl = clientDao.save(client);
		if(cl==null) {return false;}
		else { return true;}
	}

	@Override
	public Boolean Savepost(Poste poste) {
		// TODO Auto-generated method stub
		
		posteDao.save(poste);
		return true;
	}

	@Override
	public Set<Proposition> getInfoPost(Long idPost) {
		// TODO Auto-generated method stub
		Set<Proposition> propositions = new HashSet<>();
		propositions = propositionDao.getPropByIdPost(idPost);
		return propositions;
	}

}
