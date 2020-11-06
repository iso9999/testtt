package com.ensa.pfa.entitiesMessaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessagingDao extends JpaRepository<Messaging, Messaging_PK> {
	@Query("select m from Messaging m where (m.pk.client.users.username=:from and m.pk.offreur.users.username=:to) or (m.pk.client.users.username=:to and m.pk.offreur.users.username=:from)")
	public Messaging getMessaging(@Param("from") String from, @Param("to") String to);
	@Query("select m from Messaging m where m.pk.client.idClient=:x")
	List<Messaging> getConvsByIdClient(@Param("x") Long idClient);
	@Query("select m from Messaging m where m.pk.offreur.idOffreur=:x")
	List<Messaging> getConvsByIdOffreur(@Param("x") Long idOffreur);
}
