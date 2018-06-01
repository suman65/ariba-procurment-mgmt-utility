package com.ariba.procurment.mgmt.resource;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class PurchaseRequisitionResource extends ResourceSupport 
{
	
	private Long prId;
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
}
