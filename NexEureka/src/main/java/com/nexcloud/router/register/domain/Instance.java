package com.nexcloud.router.register.domain;

public class Instance {
	private Register instance;

	public Register getInstance() {
		if( instance == null )
			instance = new Register();
		return instance;
	}

	public void setInstance(Register instance) {
		this.instance = instance;
	}
	
	
}
