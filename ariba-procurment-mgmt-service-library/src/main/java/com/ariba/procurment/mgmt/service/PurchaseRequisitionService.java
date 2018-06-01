package com.ariba.procurment.mgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.ariba.procurment.mgmt.data.model.PRLineItems;
import com.ariba.procurment.mgmt.data.model.PurchaseRequisition;
import com.ariba.procurment.mgmt.data.repos.PRLineItemsRepository;
import com.ariba.procurment.mgmt.data.repos.PurchaseRequisitionRepository;
import com.ariba.procurment.mgmt.data.repos.UsersRepository;
import com.ariba.procurment.mgmt.dto.PurchaseRequisitionDTO;
import com.ariba.procurment.mgmt.event.CreatePurchaseRequisitionEvent;
import com.ariba.procurment.mgmt.event.PageReadEvent;
import com.ariba.procurment.mgmt.event.ReadPurchaseRequisitionSetEvent;
import com.ariba.procurment.mgmt.util.DateUitls;
import com.ariba.procurment.mgmt.util.NepheleValidationUtils;

public interface PurchaseRequisitionService 
{
	public void save(CreatePurchaseRequisitionEvent event);
	public PageReadEvent<PurchaseRequisitionDTO> readData(ReadPurchaseRequisitionSetEvent request);
	
	@Service
	public class impl implements PurchaseRequisitionService
	{
		@Autowired private UsersRepository usersRepository;
		@Autowired private PRLineItemsRepository lineItemsRepository;
		@Autowired private PurchaseRequisitionRepository repository;

		@Override
		public void save(CreatePurchaseRequisitionEvent event) 
		{
			List<PurchaseRequisitionDTO> dtos = event.getPurchaseRequisitionDTOs();
			
			PurchaseRequisition purchaseRequisition = PurchaseRequisition.builder()
					.accountType(dtos.get(0).getAccountType())
					.commodity(dtos.get(0).getCommodity())
					.companyCode(dtos.get(0).getCompanyCode())
					.costCenter(dtos.get(0).getCostCenter())
					//.createdBy(usersRepository.findByUsername(dto.getCreatedBy()))
					.createdDate(DateUitls.getCurrentSystemTimestamp())
					.glAccount(dtos.get(0).getGlAccount())
					.onBehalfOf(dtos.get(0).getOnBehalfOf())
					.purchaseRequisitionNumber(dtos.get(0).getPurchaseRequisitionNumber())
					.title(dtos.get(0).getTitle())
					.vendor(dtos.get(0).getVendor())
					.build();
				purchaseRequisition = repository.save(purchaseRequisition);
			
			for (PurchaseRequisitionDTO dto : dtos)
			{
				PRLineItems lineItems = PRLineItems.builder()
					.comments(dto.getComments())
					.eccPlant(dto.getEccPlant())
					.itemDescription(dto.getItemDescription())
					.needByDate(dto.getNeedByDate())
					.price(dto.getPrice())
					.purchaseRequisition(purchaseRequisition)
					.quantity(dto.getQuantity())
					.shippingAddress(dto.getShippingAddress())
					.supplierPartNumber(dto.getSupplierPartNumber())
					.uom(dto.getUom())
					.build();
				lineItemsRepository.save(lineItems);
			}
		}

		@Override
		public PageReadEvent<PurchaseRequisitionDTO> readData(ReadPurchaseRequisitionSetEvent request)
		{
			Page<PurchaseRequisition> dbContent = repository.findAll(request.getPageable());
			
			List<PurchaseRequisitionDTO> content = new ArrayList<>();
			for (PurchaseRequisition record : NepheleValidationUtils.nullSafe(dbContent)) 
			{
				PurchaseRequisitionDTO details = PurchaseRequisitionDTO.builder()
					.id(record.getId())
					.accountType(record.getAccountType())
	    			.commodity(record.getCommodity())
	    			.companyCode(record.getCompanyCode())
	    			.costCenter(record.getCostCenter())
	    			.glAccount(record.getGlAccount())
	    			.onBehalfOf(record.getOnBehalfOf())
	    			.purchaseRequisitionNumber(record.getPurchaseRequisitionNumber())
	    			.title(record.getTitle())
	    			.vendor(record.getVendor())
	    			.createdDate(record.getCreatedDate())
					.build();
				content.add(details);
			}
			Page<PurchaseRequisitionDTO> page = new PageImpl<>(content, request.getPageable(), dbContent != null ? dbContent.getTotalElements() : 0);
			return new PageReadEvent<>(page);
		}
	}
}
