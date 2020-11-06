package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensa.pfa.entities.Client;

public interface ClientDao extends JpaRepository<Client, Long> {
	@Query("select c from Client c where c.users.username=:x")
	public Client getClientByUsername(@Param("x") String username);
	@Query("select DISTINCT c from Client c where c.users.username like %:x% ")
	public List<Client> searchClient(@Param("x") String input);
}
