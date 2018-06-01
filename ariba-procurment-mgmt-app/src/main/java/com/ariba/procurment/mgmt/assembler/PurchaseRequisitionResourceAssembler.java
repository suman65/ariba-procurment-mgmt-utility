package com.ariba.procurment.mgmt.assembler;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.ariba.procurment.mgmt.dto.PurchaseRequisitionDTO;
import com.ariba.procurment.mgmt.query.controller.PurchaseRequisitionQueryController;
import com.ariba.procurment.mgmt.resource.PurchaseRequisitionResource;

@Component
public class PurchaseRequisitionResourceAssembler extends ResourceAssemblerSupport<PurchaseRequisitionDTO, PurchaseRequisitionResource> 
{
    public PurchaseRequisitionResourceAssembler() 
    {
		super(PurchaseRequisitionQueryController.class, PurchaseRequisitionResource.class);
	}

    @Override
    public PurchaseRequisitionResource toResource(PurchaseRequisitionDTO record) 
    {
    	return PurchaseRequisitionResource.builder()
    			.accountType(record.getAccountType())
    			.commodity(record.getCommodity())
    			.companyCode(record.getCompanyCode())
    			.costCenter(record.getCostCenter())
    			.glAccount(record.getGlAccount())
    			.onBehalfOf(record.getOnBehalfOf())
    			.prId(record.getId())
    			.purchaseRequisitionNumber(record.getPurchaseRequisitionNumber())
    			.title(record.getTitle())
    			.vendor(record.getVendor())
		.build();
    }
}