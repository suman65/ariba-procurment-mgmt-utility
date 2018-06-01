package com.ariba.procurment.mgmt.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ariba.procurment.mgmt.dto.PRLineItemsDTO;
import com.ariba.procurment.mgmt.query.controller.PRLineItemsQueryController;
import com.ariba.procurment.mgmt.resource.PRLineItemsResource;

@Component
public class PRLineItemsResourceAssembler extends ResourceAssemblerSupport<PRLineItemsDTO, PRLineItemsResource> 
{
    public PRLineItemsResourceAssembler() 
    {
		super(PRLineItemsQueryController.class, PRLineItemsResource.class);
	}

    @Override
    public PRLineItemsResource toResource(PRLineItemsDTO record) 
    {
    	return PRLineItemsResource.builder()
    			.comments(record.getComments())
    			.eccPlant(record.getEccPlant())
    			.itemDescription(record.getItemDescription())
    			.itemId(record.getId())
    			.needByDate(record.getNeedByDate())
    			.price(record.getPrice())
    			.prNumber(record.getPrNumber())
    			.quantity(record.getQuantity())
    			.shippingAddress(record.getShippingAddress())
    			.supplierPartNumber(record.getSupplierPartNumber())
    			.uom(record.getUom())
		.build();
    }
}