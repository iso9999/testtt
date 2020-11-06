package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Poste;

public interface PosteDao extends JpaRepository<Poste, Long> {
	@Query("select count(p.idPoste) from Poste p where p.etatProjet=0 and p.client.idClient=:x")
	public Long nbrPosteOpen(@Param("x") Long idClient);
	@Query("select count(p.idPoste) from Poste p where p.etatProjet=1 and p.client.idClient=:x")
	public Long nbrPosteActive(@Param("x") Long idClient);
	@Query("select count(p.idPoste) from Poste p where p.etatProjet=2 and p.client.idClient=:x")
	public Long nbrPostePast(@Param("x") Long idClient);
	@Query("select count(p.idPoste) from Poste p where p.client.idClient=:x")
	public Long nbrPosteTotal(@Param("x") Long idClient);
	@Query("select p from Poste p where p.etatProjet=:y and p.client.idClient=:x")
	public List<Poste> clientPostesByEtat(@Param("x") Long idClient, @Param("y") int etat);
	@Query("select p from Poste p where p.client.idClient=:x")
	public List<Poste> clientPostes(@Param("x") Long idClient);
	
	@Query("select p from Poste p where p.offreur.idOffreur=:x")
	public List<Poste> freelancePostes(@Param("x") Long idOffreur);
	
	@Query("select p from Poste p where p.etatProjet=0")
	public List<Poste> getOpenPosts();
	
	
	
}
