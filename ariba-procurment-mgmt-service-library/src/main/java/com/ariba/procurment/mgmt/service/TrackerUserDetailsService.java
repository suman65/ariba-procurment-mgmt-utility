package com.ariba.procurment.mgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ariba.procurment.mgmt.data.model.Users;
import com.ariba.procurment.mgmt.data.repos.UsersRepository;
import com.ariba.procurment.mgmt.security.JwtUserFactory;

public interface TrackerUserDetailsService extends UserDetailsService
{
	public void updateOTPValidation(String username);
	@Service
	public class impl implements TrackerUserDetailsService
	{
		@Autowired
	    private UsersRepository userRepository;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
		{
			Users user = userRepository.findByUsername(username);
			return JwtUserFactory.create(user);
		}

		@Override
		public void updateOTPValidation(String username) 
		{
			Users user = userRepository.findByUsername(username);
			userRepository.save(user);
		}
	}
}
