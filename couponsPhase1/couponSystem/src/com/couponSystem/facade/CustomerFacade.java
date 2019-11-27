package com.couponSystem.facade;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.couponSystem.UIDemo;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.classes.Customer;
import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.dbdao.CompaniesDBDAO;
import com.couponSystem.dbdao.CouponDBDAO;
import com.couponSystem.dbdao.CustomerDBDAO;

public class CustomerFacade extends ClientFacade{

	private int customerID;
	UIDemo ui = new UIDemo();
	Scanner sc=new Scanner(System.in);
	String[] options= {"ADMINISTRATOR","COMPANY","CUSTOMER"};
	public CustomerFacade(CompaniesDAO companiesDao, CustomersDAO customersDao, CouponsDAO couponsDao) {
		super(companiesDao, customersDao, couponsDao);
		companiesDao=new CompaniesDBDAO();
		customersDao=new CustomerDBDAO();
		couponsDao=new CouponDBDAO();
	}

	@Override
	public boolean login(String email, String password) {
		if(email.equals("cust")&& password.equals("1111")) {
			ui.maincustomer();
			return true;
		}else {
			System.out.println("wrong username or password please try again");
			System.out.println("enter username:");
			String user = sc.nextLine();
			System.out.println("enter password: ");
			String pass = sc.nextLine();
			System.out.println("enter type: ");
			
			String type = (String) JOptionPane.showInputDialog(null,"Choose one", "Input",JOptionPane.INFORMATION_MESSAGE, null,options, options[0]);
			login(user, pass);
			return false;
			}
	}
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
			couponsDao.addCupon(coupon);
	}
	public ArrayList<Coupon> getCustomerCoupons() throws CouponSystemException {
			couponsDao.getCustomerCoupons(customerID);
		return null;
		
	}
	public ArrayList<Coupon> getCustomerCoupons(int Customer_id) throws CouponSystemException {
		couponsDao.getCustomerCoupons(customerID);
		return null;
		
	}
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemException {
		couponsDao.getCustomerCoupons(customerID);
		return null;
		
	}
	public Customer getCustomerDetails() throws CouponSystemException {
		customersDao.getOneCustomer(customerID);
		return null;
		
	}

	public int getCustomerID() throws CouponSystemException {
		return customerID;
	}
	public boolean isCustomerExists(String email,String password) throws CouponSystemException {
		return customersDao.isCustomerExists(email, password);
		
		
	}

	public void setCustomerID(int customerID) throws CouponSystemException {
		this.customerID = customerID;
	}
	public void help() {
		 System.out.println("main: ");
		 System.out.println("to purchaseCoupon coupon type--> purCoupon");
		 System.out.println("to get Customer Coupons type--> getCoupon");
		 System.out.println("to get Customer Details type--> getcustomer");
		 System.out.println("for help type--> help  ");
	}

	

}
