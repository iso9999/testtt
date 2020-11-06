package com.ensa.pfa.metier;

import java.util.Set;

import com.ensa.pfa.entities.Client;
import com.ensa.pfa.entities.Poste;
import com.ensa.pfa.entities.Proposition;

public interface ClientMetier {
	public Boolean saveClient(Client client);
	public Boolean Savepost(Poste poste);
	public Set<Proposition> getInfoPost(Long idPost);
}
