package com.aws.lg.recommendation.service;

import java.util.List;
import java.util.Set;

import com.aws.lg.recommendation.algorithm.ECInstance;
import com.aws.lg.recommendation.algorithm.OptimisedResult;
import com.aws.lg.recommendation.model.ECInstanceModel;
import com.aws.lg.recommendation.model.InstanceModel;

public interface HomeService {
	
	public OptimisedResult calculateUserLoad(InstanceModel model);
	
	public OptimisedResult calculateThroughput(InstanceModel model);
	
	public Set<String> getAllRegions();
	
	public Set<String> getAllScriptComplexity();

}
