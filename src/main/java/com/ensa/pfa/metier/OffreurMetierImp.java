package com.ensa.pfa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.pfa.dao.OffreurDao;
import com.ensa.pfa.dao.PropositionDao;
import com.ensa.pfa.entities.Offreur;
import com.ensa.pfa.entities.Proposition;


@Service
public class OffreurMetierImp implements OffreurMetier {
	
	@Autowired
	OffreurDao offreurDao;
	@Autowired
	PropositionDao propositionDao;
	@Override
	public Boolean saveOffreur(Offreur offreur) {
		// TODO Auto-generated method stub
		offreurDao.save(offreur);
		return true;
	}

	@Override
	public Boolean proposer(Proposition proposition) {
		// TODO Auto-generated method stub
		propositionDao.save(proposition);
		return true;
	}

}
