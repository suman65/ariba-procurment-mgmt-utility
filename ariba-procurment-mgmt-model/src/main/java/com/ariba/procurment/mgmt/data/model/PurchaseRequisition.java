package com.ariba.procurment.mgmt.data.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@SequenceGenerator(name = "purchase_requisition_seq", sequenceName = "purchase_requisition_seq", initialValue = 1)
@Table(name = "purchase_requisition")
public class PurchaseRequisition implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "purchase_requisition_seq")
	private Long id;
	
	@Column(name = "purchase_requisition_number")
	private String purchaseRequisitionNumber;
	
	@Column(name = "vendor")
	private String vendor;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "commodity")
	private String commodity;
	
	@Column(name = "requisition_title")
	private String title;
	
	@Column(name = "on_behalf_of")
	private String onBehalfOf;
	
	@Column(name = "company_code")
	private String companyCode;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users createdBy;
	
	@Column(name = "created_date")
	private Timestamp createdDate;
	
	private String reason;
	
	private String status;
	private String seqNumber;
}
