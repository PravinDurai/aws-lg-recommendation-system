package com.aws.lg.recommendation.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aws.lg.recommendation.entity.AWSPriceListEntity;

public interface AwsPriceListDao extends JpaRepository<AWSPriceListEntity, Integer> {
	List<AWSPriceListEntity> findByRegion(String region);
}