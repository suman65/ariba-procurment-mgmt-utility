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

import com.ariba.procurment.mgmt.assembler.PRLineItemsResourceAssembler;
import com.ariba.procurment.mgmt.dto.PRLineItemsDTO;
import com.ariba.procurment.mgmt.event.PageReadEvent;
import com.ariba.procurment.mgmt.event.ReadPRLineItemsSetEvent;
import com.ariba.procurment.mgmt.resource.PRLineItemsResource;
import com.ariba.procurment.mgmt.service.PRLineItemsService;
import com.ariba.procurment.mgmt.util.QueryParameterConstants;

@RestController
@RequestMapping("v1/prLineItems")
public class PRLineItemsQueryController 
{
	@Autowired private PRLineItemsService service;
	@Autowired private PRLineItemsResourceAssembler assembler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagedResources<PRLineItemsResource>> readEnergyData(@RequestParam(value = QueryParameterConstants.SORT_DIRECTION, required = false) String sortDirection,
			@RequestParam(value = QueryParameterConstants.SORT_COLUMN_NAME, required = false) String sortColumnName,
			ReadPRLineItemsSetEvent request,
			@PageableDefault(value = Integer.MAX_VALUE) Pageable pageable,
			PagedResourcesAssembler<PRLineItemsDTO> pagedAssembler,HttpServletRequest httpServletRequest) 
	{
		request.setPageable(pageable);
		request.setSortDirection(sortDirection);
		request.setSortColumnName(sortColumnName);

		PageReadEvent<PRLineItemsDTO> event = service.readData(request);
		Page<PRLineItemsDTO> page=event.getPage();
		PagedResources<PRLineItemsResource> pagedResources = pagedAssembler.toResource(page, assembler);
		return new ResponseEntity<>(pagedResources, HttpStatus.OK);
	}
}
