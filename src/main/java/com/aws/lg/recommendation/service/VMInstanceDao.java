package com.aws.lg.recommendation.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aws.lg.recommendation.entity.VMInfoEntity;

public interface VMInstanceDao extends JpaRepository<VMInfoEntity, Integer> {
	
}
