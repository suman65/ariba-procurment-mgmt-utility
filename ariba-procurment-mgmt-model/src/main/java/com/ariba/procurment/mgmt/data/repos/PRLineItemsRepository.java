package com.ariba.procurment.mgmt.data.repos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ariba.procurment.mgmt.data.model.PRLineItems;

public interface PRLineItemsRepository extends TableRepository<PRLineItems, Long>,JpaSpecificationExecutor<PRLineItems>
{

}
