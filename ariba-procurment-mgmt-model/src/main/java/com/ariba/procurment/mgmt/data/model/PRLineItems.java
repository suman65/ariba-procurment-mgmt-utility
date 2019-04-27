package com.ariba.procurment.mgmt.data.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@SequenceGenerator(name = "pr_line_items_seq", sequenceName = "pr_line_items_seq", initialValue = 1)
@Table(name = "pr_line_items")
public class PRLineItems implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "pr_line_items_seq")
	private Long id;
	private String name;
	private Timestamp fromDate;
	private Timestamp toDate;
	private String lName;
}
