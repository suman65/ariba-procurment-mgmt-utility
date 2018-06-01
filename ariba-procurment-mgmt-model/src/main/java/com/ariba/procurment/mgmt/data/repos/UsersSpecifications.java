package com.ariba.procurment.mgmt.data.repos;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.ariba.procurment.mgmt.data.model.Users;

public class UsersSpecifications {

	public static Sort sortByIdAsc() {
	    return new Sort(Sort.Direction.ASC, "id");
	  }

	  /**
	   * Returns a new object which specifies the the wanted result page.
	   *
	   * @param pageIndex The index of the wanted result page
	   * @return
	   */
	  public static Pageable constructPageSpecification(int pageIndex, int pageSize) {
		    Pageable pageSpecification = new PageRequest(pageIndex, pageSize);
		    return pageSpecification;
		  }

	public static Specification<Users> getUsers(String mobileNumber) 
	{
		return new Specification<Users>() 
		{
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder) 
			{
				Predicate predicate = criteriaBuilder.conjunction();
				
				if (!StringUtils.isEmpty(mobileNumber)) 
				{
					Expression<String> rootName = root.get("username");
					predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(rootName,mobileNumber));
				}
				return predicate;
			}
		};
	}
}