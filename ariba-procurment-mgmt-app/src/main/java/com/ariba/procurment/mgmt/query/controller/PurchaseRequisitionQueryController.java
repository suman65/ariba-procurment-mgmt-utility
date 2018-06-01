package com.ariba.procurment.mgmt.query.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ariba.procurment.mgmt.assembler.PurchaseRequisitionResourceAssembler;
import com.ariba.procurment.mgmt.dto.PurchaseRequisitionDTO;
import com.ariba.procurment.mgmt.event.PageReadEvent;
import com.ariba.procurment.mgmt.event.ReadPurchaseRequisitionSetEvent;
import com.ariba.procurment.mgmt.resource.PurchaseRequisitionResource;
import com.ariba.procurment.mgmt.service.PurchaseRequisitionService;
import com.ariba.procurment.mgmt.util.QueryParameterConstants;

@RestController
@RequestMapping("v1/prData")
public class PurchaseRequisitionQueryController 
{
	@Autowired private PurchaseRequisitionService service;
	@Autowired private PurchaseRequisitionResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedResources<PurchaseRequisitionResource>> readEnergyData(@RequestParam(value = QueryParameterConstants.SORT_DIRECTION, required = false) String sortDirection,
			@RequestParam(value = QueryParameterConstants.SORT_COLUMN_NAME, required = false) String sortColumnName,
			@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable,
			PagedResourcesAssembler<PurchaseRequisitionDTO> pagedAssembler,HttpServletRequest httpServletRequest) 
	{
		ReadPurchaseRequisitionSetEvent request = new ReadPurchaseRequisitionSetEvent().setPageable(pageable);
		request.setSortDirection(sortDirection);
		request.setSortColumnName(sortColumnName);

		PageReadEvent<PurchaseRequisitionDTO> event = service.readData(request);
		Page<PurchaseRequisitionDTO> page=event.getPage();
		PagedResources<PurchaseRequisitionResource> pagedResources = pagedAssembler.toResource(page, assembler);
		return new ResponseEntity<>(pagedResources, HttpStatus.OK);
	}
}
