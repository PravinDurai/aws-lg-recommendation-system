package com.aws.lg.recommendation.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.lg.recommendation.dto.LoginDto;
import com.aws.lg.recommendation.entity.LoginEntity;
import com.aws.lg.recommendation.service.LoginDao;
import com.aws.lg.recommendation.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Override
	public String validateLogin(String eMail, String password) {
		String op = null;
		try {
			LoginEntity loginEntity = loginDao.findByeMailAndPassword(eMail, password);
			if (loginEntity != null) {
				System.out.println("First Name :\t" + loginEntity.getFirstName());
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				LoginDto loginDto = modelMapper.map(loginEntity, LoginDto.class);
				op=loginDto.getFirstName();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			return (op);
		}
	}
}
