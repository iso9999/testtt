package com.ensa.pfa.metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.pfa.dao.CategorieDao;
import com.ensa.pfa.entities.Categorie;

@Service
public class CategorieMetierImp implements CategorieMetier {
	
	@Autowired
	private CategorieDao categorieDao;

	
	@Override
	public Boolean saveCategorie(Categorie categorie) {
		// TODO Auto-generated method stub
		categorieDao.save(categorie);
		return true;
	}


}
