package com.ensa.pfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ensa.pfa.entities.Users;

public interface UsersDao extends JpaRepository<Users, String> {
	@Query("select u from Users u")
	public List<Users> getAllUsers();

}
