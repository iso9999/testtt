package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Offreur;

public interface OffreurDao extends JpaRepository<Offreur, Long> {
	@Query("select o from Offreur o where o.users.username=:x")
	public Offreur getOffreurByUsername(@Param("x") String username);
	
	@Query("select DISTINCT o from Offreur o where o.users.username like %:x% ")
	public List<Offreur> searchOffreur(@Param("x") String input);
}
