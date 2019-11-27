package com.couponSystem.facade;

import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.dao.CustomersDAO;

public abstract class ClientFacade {

	CompaniesDAO companiesDao;
	CustomersDAO customersDao;
	CouponsDAO couponsDao;
	public abstract boolean login(String email,String password);
		
	
	protected ClientFacade(CompaniesDAO companiesDao, CustomersDAO customersDao, CouponsDAO couponsDao) {
		super();
		this.companiesDao = companiesDao;
		this.customersDao = customersDao;
		this.couponsDao = couponsDao;
		
	}
	
}
