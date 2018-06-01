package com.ariba.procurment.mgmt.config.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Builder
@Setter
@Getter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
public class NotificationDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407057457701172593L;
	private Long id;
	private String from;
    private List<String> to;
    private String message; 
    private String type; //SMS or EMAIL

}
