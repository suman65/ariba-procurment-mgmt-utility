package com.ariba.procurment.mgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.ariba.procurment.mgmt.data.model.PRLineItems;
import com.ariba.procurment.mgmt.data.repos.PRLineItemsRepository;
import com.ariba.procurment.mgmt.data.repos.PRLineItemsSpecifications;
import com.ariba.procurment.mgmt.data.repos.SpecificationUtils;
import com.ariba.procurment.mgmt.dto.PRLineItemsDTO;
import com.ariba.procurment.mgmt.event.PageReadEvent;
import com.ariba.procurment.mgmt.event.ReadPRLineItemsSetEvent;
import com.ariba.procurment.mgmt.util.NepheleValidationUtils;

public interface PRLineItemsService 
{
	public PageReadEvent<PRLineItemsDTO> readData(ReadPRLineItemsSetEvent request);
	
	@Service
	public class impl implements PRLineItemsService
	{
		@Autowired private PRLineItemsRepository repository;

		@Override
		public PageReadEvent<PRLineItemsDTO> readData(ReadPRLineItemsSetEvent request) 
		{
			Page<PRLineItems> dbContent = repository.findAll(new PRLineItemsSpecifications(request.getName(),request.getFromDate(),request.getToDate(),request.getLName()),
					PRLineItemsSpecifications.constructPageSpecification(request.getPageable().getPageNumber(),request.getPageable().getPageSize(),
					SpecificationUtils.sortBySortKey(request.getSortColumnName(), request.getSortDirection())));
			List<PRLineItemsDTO> content = new ArrayList<>();
			for (PRLineItems record : NepheleValidationUtils.nullSafe(dbContent)) 
			{
				PRLineItemsDTO details = PRLineItemsDTO.builder()
					.id(record.getId())
										.build();
				content.add(details);
			}
			Page<PRLineItemsDTO> page = new PageImpl<>(content, request.getPageable(), dbContent != null ? dbContent.getTotalElements() : 0);
			return new PageReadEvent<>(page);
	  }
	}
}
