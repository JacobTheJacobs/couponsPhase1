package com.couponSystem.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.couponSystem.UIDemo;
import com.couponSystem.classes.Category;
import com.couponSystem.classes.Company;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.pool.ConnectionPool;

public class CompanyFacade extends ClientFacade{

	private int CompanyId;
	ConnectionPool connectionPool;
	UIDemo ui = new UIDemo();
	Scanner sc=new Scanner(System.in);
	String[] options= {"ADMINISTRATOR","COMPANY","CUSTOMER"};
	public CompanyFacade(CompaniesDAO companiesDao, CustomersDAO customersDao, CouponsDAO couponsDao) {
		super(companiesDao, customersDao, couponsDao);
	}

	@Override
	public boolean login(String email, String password) {
		if(email.equals("comp")&& password.equals("1212")) {
			ui.mainCompany();
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
	public void addCoupon(Coupon coupon) throws CouponSystemException {
		try {
			couponsDao.addCupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
		
	}
	public void updateCoupon(Coupon coupon) throws CouponSystemException{
		try {
			couponsDao.updateCoupon(coupon);
		} catch (CouponSystemException e) {
			e.printStackTrace();
		}
	}
	public void deleteCoupon(int couponId) throws CouponSystemException{
		try {
			couponsDao.deleteCoupon(couponId);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Coupon> getCompanyCoupons() throws CouponSystemException{
		try {
			return companiesDao.getCompanyCoupons(CompanyId);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCompanyCoupons();
		
	}
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws CouponSystemException{
		companiesDao.getCompanyCoupons(CompanyId);
		return null;
		
	}
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException{
		companiesDao.getCompanyCoupons(CompanyId);
		return null;
		
	}
	public Company getCompanyDetails() throws CouponSystemException {
		companiesDao.getCompanyCoupons(CompanyId);
		return null;
		
	}

	public int getCompanyId() throws CouponSystemException{
		return CompanyId;
	}

	public void setCompanyId(int companyId) throws CouponSystemException{
		CompanyId = companyId;
	}
	public void help() {
		System.out.println("main: ");
		 System.out.println("to add new Coupon type--> addCoupon");
		 System.out.println("to update Coupon type--> updateCoupon");
		 System.out.println("to delete Coupon type--> deleteCoupon");
		 System.out.println("to get Company Coupons type--> CompanyCoupons");
		 System.out.println("to get Company Details type--> getCompany");
		 System.out.println("to get Company Id type--> getCompanyId");
		 System.out.println("for help type--> help  ");
	}
}
