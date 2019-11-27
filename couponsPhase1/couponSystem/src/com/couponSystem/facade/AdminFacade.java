package com.couponSystem.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.couponSystem.UIDemo;
import com.couponSystem.classes.Company;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.classes.Customer;
import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.pool.ConnectionPool;

public class AdminFacade extends ClientFacade {

	ConnectionPool connectionPool;
	UIDemo ui = new UIDemo();
	Scanner sc=new Scanner(System.in);
	String[] options= {"ADMINISTRATOR","COMPANY","CUSTOMER"};

	@Override
	public boolean login(String email, String password) {
		if(email.equals("admin")&& password.equals("1234")) {
			System.out.println("enter 'help' for help");
			return true;
		}
		else {
			System.out.println("wrong username or password please try again");
			System.out.println("enter username:");
			String user = sc.nextLine();
			System.out.println("enter password: ");
			String pass = sc.nextLine();
			System.out.println("enter type: ");
			String type = (String) JOptionPane.showInputDialog(null,"Choose one", "Input",JOptionPane.INFORMATION_MESSAGE, null,options, options[0]);
			login(user, pass);
			return true;
			}
	}

	public AdminFacade(CompaniesDAO companiesDao, CustomersDAO customersDao, CouponsDAO couponsDao) {
		super(companiesDao, customersDao, couponsDao);
	}

		public void addCompany(Company company) throws CouponSystemException {
			if(companiesDao.getAllCompanies()!=null) {
				
			}
			companiesDao.addCompany(company);
		}


		public void updateCompany(Company company) throws CouponSystemException {
			companiesDao.updateCompany(company);

		}

		public boolean isCompanyExists(String email, String password) throws CouponSystemException {
			return companiesDao.isCompanyExists(email,password);
		}

		public Company getOneCompany(int companyID) throws CouponSystemException {
			return companiesDao.getOneCompany(companyID);
		}

		public List<Company> getAllCompanies() throws CouponSystemException {
			return companiesDao.getAllCompanies();
		}

		public void deleteCompany(int companyID) throws CouponSystemException {
			companiesDao.deleteCompany(companyID);

		}

		
		public List<Coupon> getCompanyCoupons(int id) throws CouponSystemException {

			return companiesDao.getCompanyCoupons(id);
		}

		public void updateCustomer(Customer customer) throws CouponSystemException {
			customersDao.updateCustomer(customer);
		}

		public boolean isCustomerExists(String email, String password) throws CouponSystemException {
			return customersDao.isCustomerExists(email, password);
		}

		public Customer getOneCustomer(int customerID) throws CouponSystemException {
			return customersDao.getOneCustomer(customerID);
		}

		public List<Customer> getAllCustomers() throws CouponSystemException {
			return customersDao.getAllCustomers();
		}

		public void deleteCustomer(int customerID) throws CouponSystemException {
			customersDao.deleteCustomer(customerID);
		}

		public void addCustomer(Customer customer) throws CouponSystemException {
			customersDao.addCustomer(customer);
		}

		public void updateCoupon(Coupon coupon) throws CouponSystemException {
			couponsDao.updateCoupon(coupon);
		}

		public Coupon getOneCoupon(int couponID) throws CouponSystemException {
			return couponsDao.getOneCoupon(couponID);
		}

		public List<Coupon> getAllCoupons() throws CouponSystemException {
			return couponsDao.getAllCoupons();
		}

		public void deleteCoupon(int couponID) throws CouponSystemException {
			couponsDao.deleteCoupon(couponID);
		}

		public void addCupon(Coupon coupon) throws CouponSystemException {
			couponsDao.addCupon(coupon);
		}

		public boolean couponExists(String title) throws CouponSystemException {
				return couponsDao.couponExists(title);
		}
		public int getExpired() throws CouponSystemException {
			return couponsDao.getExpired();
			
		}

		
}