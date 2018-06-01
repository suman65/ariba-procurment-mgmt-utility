package com.ariba.procurment.mgmt.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Builder
@Entity
@Data@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", initialValue = 1)
public class Users implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "users_seq")
	private Long id;
	
	@Column(name="username", nullable = false,unique = true)
	private String username;
	
	@Column(name="mobile_number" , nullable = false,unique = true)	
	private String mobileNumber;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")	
	private String firstName;
	
	@Column(name="last_name")	
	private String lastName;
	
	@Column(name = "isEnabled", nullable = false)
	private Boolean isEnabled;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AUTHORITY", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id", unique=false) }, 
	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id" , unique=false) })
	private List<Roles> authorities;
	
	@Column(name = "last_password_reset_date")
	private Date lastPasswordResetDate;
	
}
