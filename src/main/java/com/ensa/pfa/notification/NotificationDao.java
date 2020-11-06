package com.ensa.pfa.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NotificationDao extends JpaRepository<Notification, Long> {
	@Query("select n from Notification n where n.client.idClient=:x")
	public List<Notification> getNotifsClient(@Param("x") Long idClient);
	
	@Query("update Notification n set n.etat=true where n.idNotif=:x")
	public void updateEtatNotif(@Param("x") Long idNotif);
	
	@Query("select n from Notification n where n.offreur.idOffreur=:x")
	public List<Notification> getNotifsOffreur(@Param("x") Long idOffreur);
	
	@Transactional
    @Modifying
	@Query("update Notification n set n.etat=true where n.client.idClient=:x")
	public void updateEtatAllNotifClient(@Param("x") Long idClient);
	
	@Transactional
    @Modifying
	@Query("update Notification n set n.etat=true where n.offreur.idOffreur=:x")
	public void updateEtatAllNotifOffreur(@Param("x") Long idOffreur);

}
