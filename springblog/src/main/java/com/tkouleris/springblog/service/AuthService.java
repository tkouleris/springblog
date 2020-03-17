package com.tkouleris.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tkouleris.springblog.dto.RegisterRequest;
import com.tkouleris.springblog.model.User;
import com.tkouleris.springblog.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository R_User;
	
	public void signUp(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());

		R_User.save(user);
	}
}
