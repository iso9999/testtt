package com.ensa.pfa.metier;

import java.util.List;

import com.ensa.pfa.entities.Service;

public interface ServiceMetier {
	public Boolean saveService(Service service);
	public List<Service> getServicesByCategorie(Long idCategorie);
}
