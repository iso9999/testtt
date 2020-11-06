package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Avis;
import com.ensa.pfa.entities.Avis_PK;

public interface AvisDao extends JpaRepository<Avis, Avis_PK> {
	@Query("select a from Avis a where a.pk.offreur.idOffreur=:x")
	public List<Avis> freelancerRating(@Param("x")Long idOffreur);
	@Query("select count(a) from Avis a where a.pk.offreur.idOffreur=:x and a.noteAvis=:y")
	public Long nbrOfRate(@Param("x")Long idOffreur, @Param("y")int nbr);
	@Query("select avg(a.noteAvis) from Avis a where a.pk.offreur.idOffreur=:x")
	public Double moyenneRating(@Param("x")Long idOffreur);
}
