package com.ariba.procurment.mgmt.data.repos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ariba.procurment.mgmt.data.model.Roles;

public interface RolesRepository extends TableRepository<Roles, Long>, JpaSpecificationExecutor<Roles> {

}
