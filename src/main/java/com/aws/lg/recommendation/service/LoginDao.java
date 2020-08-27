package com.aws.lg.recommendation.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aws.lg.recommendation.entity.LoginEntity;

public interface LoginDao extends JpaRepository<LoginEntity, Integer> {
	
	LoginEntity findByeMailAndPassword(String eMail, String password);

}
