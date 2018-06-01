package com.ariba.procurment.mgmt.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8274828054852659793L;
	private String message;
    private Long args;
}
