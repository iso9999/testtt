package com.ensa.pfa.metier;

import com.ensa.pfa.entities.Offreur;
import com.ensa.pfa.entities.Proposition;

public interface OffreurMetier {
	public Boolean saveOffreur(Offreur offreur);
	public Boolean proposer(Proposition proposition);
}
