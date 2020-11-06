package com.ensa.pfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.pfa.entities.Image;

public interface ImageDao extends JpaRepository<Image, Long> {

}
