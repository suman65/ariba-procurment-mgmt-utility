package com.ariba.procurment.mgmt.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseRequisitionDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String purchaseRequisitionNumber;
	private String vendor;
	private String accountType;
	private String glAccount;
	private String commodity;
	private String title;
	private String onBehalfOf;
	private String companyCode;
	private String costCenter;
	private Long userId;
	private String createdBy;
	private Timestamp createdDate;
	
	private String itemDescription;
	private String quantity;
	private String price;
	private String uom;
	private String needByDate;
	private String shippingAddress;
	private Long prId;
	private String comments;
	private String supplierPartNumber;
	private String eccPlant;
}
