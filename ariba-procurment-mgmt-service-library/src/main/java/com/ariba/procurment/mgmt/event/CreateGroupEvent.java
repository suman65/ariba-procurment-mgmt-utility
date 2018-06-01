package com.ariba.procurment.mgmt.event;

import com.ariba.procurment.mgmt.dto.GroupDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class CreateGroupEvent 
{
	private GroupDTO groupDTO;
}
