package com.couponSystem.pool;

import java.util.Scanner;


import com.couponSystem.classes.ClientType;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.dbdao.CompaniesDBDAO;
import com.couponSystem.dbdao.CouponDBDAO;
import com.couponSystem.dbdao.CustomerDBDAO;
import com.couponSystem.facade.AdminFacade;
import com.couponSystem.facade.ClientFacade;
import com.couponSystem.facade.CompanyFacade;
import com.couponSystem.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance;
	Scanner sc = new Scanner(System.in);

	private LoginManager() {

	}

	public static LoginManager getInstance() {

		if (instance == null) {
//		return instance=new LoginManager.login(user, pass, ClientType.valueOf(type));
			instance = new LoginManager();
		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {

		CompaniesDAO companiesDao = new CompaniesDBDAO();
		CustomersDAO customersDao = new CustomerDBDAO();
		CouponsDAO couponsDao = new CouponDBDAO();
		if (clientType == ClientType.ADMINISTRATOR) {
			AdminFacade adminFacade = new AdminFacade(companiesDao, customersDao, couponsDao);
			if (adminFacade.login(email, password)) {
				System.out.println("you are connected to the admin account");
				return adminFacade;
			}
		} else if (clientType == ClientType.COMPANY) {
			CompanyFacade companyFacade = new CompanyFacade(companiesDao, customersDao, couponsDao);
			if (companyFacade.login(email, password)) {
				System.out.println("you are connected to the company account");
				return companyFacade;
			}
		} else if (clientType == ClientType.CUSTOMER) {
			CustomerFacade customerFacade = new CustomerFacade(companiesDao, customersDao, couponsDao);
			if (customerFacade.login(email, password)) {
				System.out.println("you are connected to the customer account");
				return customerFacade;
			}
		}

		return null;
	}
}