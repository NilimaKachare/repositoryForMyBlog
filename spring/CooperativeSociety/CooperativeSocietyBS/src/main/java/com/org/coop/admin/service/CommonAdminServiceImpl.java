package com.org.coop.admin.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.society.data.admin.entities.SecurityQuestion;
import com.org.coop.society.data.admin.repositories.SecurityQuestionRepository;

@Service
public class CommonAdminServiceImpl {
	private static final Logger log = Logger.getLogger(AdminLoginServiceImpl.class); 
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Transactional(value="adminTransactionManager")
	public List<SecurityQuestion> getAllSecurityQuestions() {
		return securityQuestionRepository.findAll();
	}
	
}