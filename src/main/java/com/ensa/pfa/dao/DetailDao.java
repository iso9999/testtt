package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Details;
import com.ensa.pfa.entities.Details_PK;

public interface DetailDao extends JpaRepository<Details, Details_PK> {
	@Query("select d from Details d where d.pk.offreur.idOffreur=:x")
	public List<Details> getServicesByidOffreur(@Param("x")Long idOffreur);
}
