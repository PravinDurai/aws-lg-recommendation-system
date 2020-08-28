package com.aws.lg.recommendation.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.lg.recommendation.dto.AWSPriceListDto;
import com.aws.lg.recommendation.dto.ECInstanceDto;
import com.aws.lg.recommendation.dto.VMInfoDto;
import com.aws.lg.recommendation.entity.AWSPriceListEntity;
import com.aws.lg.recommendation.entity.VMInfoEntity;
import com.aws.lg.recommendation.model.InstanceModel;
import com.aws.lg.recommendation.service.AwsPriceListDao;
import com.aws.lg.recommendation.service.HomeService;
import com.aws.lg.recommendation.service.VMInstanceDao;

@Service
public class HomeServiceImpl implements HomeService{

	@Autowired
	private VMInstanceDao vMInstanceDao;
	
	@Autowired
	private AwsPriceListDao awsPriceListDao;
	
	@Override
	public Set<String> getAllRegions() {
		List<AWSPriceListEntity> priceListEntity=awsPriceListDao.findAll();
		Set<String> regions=new HashSet<String>();
		priceListEntity.forEach(temp->{
			regions.add(temp.getRegion());
		});
		return(regions);
	}
	
	@Override
	public Set<String> getAllScriptComplexity() {
		List<VMInfoEntity> vMinfoEntity=vMInstanceDao.findAll();
		Set<String> scriptComplexitySet=new HashSet<String>();
		vMinfoEntity.forEach(temp->{
			scriptComplexitySet.add(temp.getScriptComplexity());
		});
		return(scriptComplexitySet);
	}	
	
	@Override
	public List<ECInstanceDto> calculate(InstanceModel model) {
		return null;
	}	
}
