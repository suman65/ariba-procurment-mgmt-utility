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
public class PRLineItemsResource extends ResourceSupport 
{
	private Long itemId;
	private String itemDescription;
	private String quantity;
	private String price;
	private String uom;
	private String needByDate;
	private String shippingAddress;
	private String prNumber;
	private String comments;
	private String supplierPartNumber;
	private String eccPlant;
}
