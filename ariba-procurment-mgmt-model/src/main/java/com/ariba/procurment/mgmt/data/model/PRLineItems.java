package com.ariba.procurment.mgmt.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "pr_line_items_seq", sequenceName = "pr_line_items_seq", initialValue = 1)
@Table(name = "pr_line_items")
public class PRLineItems implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "pr_line_items_seq")
	private Long id;
	
	@Column(name = "item_description")
	private String itemDescription;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "uom")
	private String uom;
	
	@Column(name = "need_by_date")
	private String needByDate;
	
	@Column(name = "shipping_address")
	private String shippingAddress;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "supplier_part_number")
	private String supplierPartNumber;
	
	@Column(name = "ecc_plant")
	private String eccPlant;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pr_id")
	private PurchaseRequisition purchaseRequisition;
}
