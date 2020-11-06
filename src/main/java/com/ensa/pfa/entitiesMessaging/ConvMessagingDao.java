package com.ensa.pfa.entitiesMessaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConvMessagingDao extends JpaRepository<ConversationMessages, Long> {
	@Query("select cv from ConversationMessages cv where cv.messaging.pk.offreur.idOffreur=:x and cv.messaging.pk.client.idClient=:y")
	public List<ConversationMessages> getConv(@Param("x") Long idOffreur,@Param("y") Long idClient);
	
	@Query("select cv from ConversationMessages cv where cv.messaging.pk.offreur.idOffreur=:x and cv.messaging.pk.client.idClient=:y ORDER BY cv.dateMsg DESC")
	public List<ConversationMessages> getLastMsg(@Param("x") Long idOffreur,@Param("y") Long idClient);
	

}
