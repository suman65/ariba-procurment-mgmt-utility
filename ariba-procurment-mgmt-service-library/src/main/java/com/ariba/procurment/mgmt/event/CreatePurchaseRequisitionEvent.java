package com.ariba.procurment.mgmt.event;

import java.util.List;

import com.ariba.procurment.mgmt.dto.PurchaseRequisitionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class CreatePurchaseRequisitionEvent 
{
	private List<PurchaseRequisitionDTO> purchaseRequisitionDTOs;

}
