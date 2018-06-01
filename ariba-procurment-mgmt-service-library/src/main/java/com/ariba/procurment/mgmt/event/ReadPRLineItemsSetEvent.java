package com.ariba.procurment.mgmt.event;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class ReadPRLineItemsSetEvent extends ReadPageEvent<ReadPRLineItemsSetEvent>
{
	private String sortDirection;
	private String sortColumnName;
	private Long prId;
}