package com.ariba.procurment.mgmt.event;

public class ReadEntityEvent<T extends ReadEntityEvent<T> > {

	
	private String id;

	public T setId(String id) {
		this.id = id;

		@SuppressWarnings("unchecked")
		T ret = (T)this;
		return ret;
	}
}
