package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Service;

public interface ServiceDao extends JpaRepository<Service, Long> {
	@Query("select s from Service s where s.categorie.idCategorie=:x")
	public List<Service> getServicesByCat(@Param("x") Long idCategorie);
}
