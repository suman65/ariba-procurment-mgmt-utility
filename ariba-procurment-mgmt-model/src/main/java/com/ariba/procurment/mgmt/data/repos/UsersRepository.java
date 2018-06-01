package com.ariba.procurment.mgmt.data.repos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ariba.procurment.mgmt.data.model.Users;
/**
 * @author shrisowdhaman
 * Dec 14, 2017
 */
public interface UsersRepository extends TableRepository<Users, Long>, JpaSpecificationExecutor<Users> {
	
	 Users findByUsername(String username);
}
