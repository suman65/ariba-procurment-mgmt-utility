package com.ariba.procurment.mgmt.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ReadPurchaseRequisitionSetEvent extends ReadPageEvent<ReadPurchaseRequisitionSetEvent>
{
	private Long id;
	private String sortDirection;
	private String sortColumnName;
	private Integer limit;
	private Integer page;
	private Integer start;
}