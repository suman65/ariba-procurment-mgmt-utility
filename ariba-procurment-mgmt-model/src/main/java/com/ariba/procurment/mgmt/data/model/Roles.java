package com.ariba.procurment.mgmt.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "roles_seq", sequenceName = "roles_seq", initialValue = 1)
public class Roles implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "roles_seq")
	private Long id;
	private String name;
	private String description;
}
