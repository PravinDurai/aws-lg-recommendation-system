package com.aws.lg.recommendation.service;

import java.util.List;
import java.util.Set;

import com.aws.lg.recommendation.dto.ECInstanceDto;
import com.aws.lg.recommendation.model.ECInstanceModel;
import com.aws.lg.recommendation.model.InstanceModel;

public interface HomeService {
	
	public List<ECInstanceDto> calculate(InstanceModel model);
	
	public Set<String> getAllRegions();
	
	public Set<String> getAllScriptComplexity();

}
