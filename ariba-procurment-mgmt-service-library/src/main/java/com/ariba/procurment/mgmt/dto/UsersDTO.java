package com.ariba.procurment.mgmt.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@JsonInclude(Include.NON_DEFAULT)
public class UsersDTO implements Serializable 
{
	private static final long serialVersionUID = -7860549093000563956L;
	private Long id;
	private Long roleId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String mobileNumber;
	private Boolean isEnabled;
	private Boolean isOTPValidated;
	private String status;
}