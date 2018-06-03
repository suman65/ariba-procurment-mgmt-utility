package com.ariba.procurment.mgmt.dto;

import java.io.Serializable;

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
public class PRLineItemsDTO implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String itemDescription;
	private String quantity;
	private String price;
	private String uom;
	private String needByDate;
	private String shippingAddress;
	private Long prId;
	private String prNumber;
	private String comments;
	private String supplierPartNumber;
	private String eccPlant;
	private String costCenter;
	private String glAccount;
}
