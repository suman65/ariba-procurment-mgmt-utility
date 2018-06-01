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

import com.ariba.procurment.mgmt.data.model.PRLineItems;

public class PRLineItemsSpecifications {

	public static Sort sortByIdAsc() {
	    return new Sort(Sort.Direction.ASC, "id");
	  }

	  /**
	   * Returns a new object which specifies the the wanted result page.
	   *
	   * @param pageIndex The index of the wanted result page
	   * @return
	   */
	 public static Pageable constructPageSpecification(int pageIndex, int pageSize, Sort sortingOrderSpec) {
		    Pageable pageSpecification = new PageRequest(pageIndex, pageSize, sortingOrderSpec);
		    return pageSpecification;
		  }
	public static Specification<PRLineItems> getLineItems(Long purchaseRequisitionId) 
	{
		return new Specification<PRLineItems>() 
		{
			@Override
			public Predicate toPredicate(Root<PRLineItems> root, CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder) 
			{
				Predicate predicate = criteriaBuilder.conjunction();
				
				if (!StringUtils.isEmpty(purchaseRequisitionId)) 
				{
					Expression<Long> rootName = root.get("purchaseRequisition").get("id");
					predicate = criteriaBuilder.and(predicate,criteriaBuilder.equal(rootName,purchaseRequisitionId));
				}
				return predicate;
			}
		};
	}
}
