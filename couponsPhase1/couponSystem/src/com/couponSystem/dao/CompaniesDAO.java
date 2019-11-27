package com.couponSystem.dao;

import java.util.List;

import com.couponSystem.classes.Company;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String password) throws CouponSystemException;
	void addCompany(Company company) throws CouponSystemException;
	void updateCompany(Company company) throws CouponSystemException;
	void deleteCompany(int companyID) throws CouponSystemException;
	List<Company> getAllCompanies() throws CouponSystemException;
	Company getOneCompany(int companyID) throws CouponSystemException;
	List<Coupon> getCompanyCoupons(int company_id) throws CouponSystemException;
	
}
