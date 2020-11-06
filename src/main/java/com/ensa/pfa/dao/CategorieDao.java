package com.ensa.pfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.pfa.entities.Categorie;

public interface CategorieDao extends JpaRepository<Categorie, Long> {

}
