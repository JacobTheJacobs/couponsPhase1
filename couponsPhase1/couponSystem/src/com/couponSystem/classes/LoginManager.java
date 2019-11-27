package com.couponSystem.classes;

import com.couponSystem.facade.ClientFacade;

public class LoginManager {
	

	private LoginManager instance;
	
	protected LoginManager(LoginManager instance) {
		super();
		this.instance = instance;
	}
	
	public LoginManager getInstance(){
		return instance;
		
	}
	public ClientFacade login(String email, String password, ClientType clientType) {
		return null;
		
	}

}
