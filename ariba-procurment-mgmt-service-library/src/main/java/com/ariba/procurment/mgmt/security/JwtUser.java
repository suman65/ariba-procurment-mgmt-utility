package com.ariba.procurment.mgmt.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails 
{

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;
	private final String password;
	private final boolean isEnabled;
	private final String mobileNumber;
	private final String firstName;
	private final String lastName;
	private final Date  lastPasswordResetDate;

	public JwtUser(Long id, String username,Collection<? extends GrantedAuthority> authorities,String password,boolean isEnabled,
			String mobileNumber,
			String firstName,
			String lastName,Date  lastPasswordResetDate) 
	{
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.password = password;
		this.isEnabled = isEnabled;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}


	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

}
