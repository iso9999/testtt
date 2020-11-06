package com.ensa.pfa.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ensa.pfa.dao.ServiceDao;
import com.ensa.pfa.entities.Service;


@org.springframework.stereotype.Service
public class ServiceMetierImp implements ServiceMetier {
	
	@Autowired
	ServiceDao serviceDao;
	@Override
	public Boolean saveService(Service service) {
		// TODO Auto-generated method stub
		serviceDao.save(service);
		return true;
	}
	@Override
	public List<Service> getServicesByCategorie(Long idCategorie) {
		// TODO Auto-generated method stub
		List<Service> services = serviceDao.getServicesByCat(idCategorie);
		return services;
	}

}
