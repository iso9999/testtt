package com.ensa.pfa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.pfa.dao.ImageDao;
import com.ensa.pfa.entities.Image;

@Service
public class ImageMetierImp implements ImageMetier {
	@Autowired
	ImageDao imageDao;

	@Override
	public void saveImageDefault() {
		imageDao.save(new Image("/img/myImages/default.png", null));
		
	}

	@Override
	public void saveImage() {
		imageDao.save(new Image("/img/myImages/image1.jpg", null));
		
	}
}
