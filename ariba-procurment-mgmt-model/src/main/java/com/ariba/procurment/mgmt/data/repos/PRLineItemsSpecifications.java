package com.ariba.procurment.mgmt.data.repos;

import java.sql.Timestamp;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.ariba.procurment.mgmt.data.model.PRLineItems;

public class PRLineItemsSpecifications implements Specification<PRLineItems> 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Timestamp fromDate;
	private Timestamp toDate;
	private String lName;

	public PRLineItemsSpecifications(String name,Timestamp fromDate,Timestamp toDate,String lName) 
	{
		super();
		this.name = name;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.lName = lName;
	}

	public static Sort sortByIdAsc() 
	{
		return new Sort(Sort.Direction.ASC, "id");
	}

	public static Pageable constructPageSpecification(int pageIndex, int pageSize, Sort sortingOrderSpec) 
	{
		Pageable pageSpecification = PageRequest.of(pageIndex, pageSize, sortingOrderSpec);
		return pageSpecification;
	}

	@Override
	public Predicate toPredicate(Root<PRLineItems> root, CriteriaQuery<?> cq,CriteriaBuilder criteriaBuilder) 
	{
		Predicate predicate = criteriaBuilder.conjunction();
		if (!StringUtils.isEmpty(name)) 
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("name"), name));
		}
		if (!StringUtils.isEmpty(lName)) 
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("lName"), lName));
		}
		if (!StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(toDate)) 
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.between(root.get("fromDate"),fromDate, toDate));
		}
		return predicate;
	}
}
