package com.ensa.pfa.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.pfa.entities.Roles;

public interface RoleDao extends JpaRepository<Roles, String> {

}
