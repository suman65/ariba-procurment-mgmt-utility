package com.ariba.procurment.mgmt.data.repos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ariba.procurment.mgmt.data.model.PurchaseRequisition;

public interface PurchaseRequisitionRepository extends TableRepository<PurchaseRequisition, Long>,JpaSpecificationExecutor<PurchaseRequisition>
{

}
