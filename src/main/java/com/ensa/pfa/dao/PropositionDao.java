package com.ensa.pfa.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Proposition;
import com.ensa.pfa.entities.Proposition_PK;

public interface PropositionDao extends JpaRepository<Proposition, Proposition_PK> {
	@Query("select p from Proposition p where p.pk.poste.idPoste=:x")
	public Set<Proposition> getPropByIdPost(@Param("x")Long idPoste);

}
