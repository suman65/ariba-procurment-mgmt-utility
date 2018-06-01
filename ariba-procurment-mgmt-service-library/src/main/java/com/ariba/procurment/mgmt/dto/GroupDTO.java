package com.ariba.procurment.mgmt.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class GroupDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String groupName;
	private String code;
	private String userName;
	private Boolean isClosedGroup;
	private Date createdDate;
	private Long memberId;
	private Long groupId;
}
