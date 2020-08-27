package com.aws.lg.recommendation.service;

import com.aws.lg.recommendation.dto.ECInstanceDto;
import com.aws.lg.recommendation.model.ECInstanceModel;

public interface HomeService {
	
	public ECInstanceDto calculate(ECInstanceModel model);

}
