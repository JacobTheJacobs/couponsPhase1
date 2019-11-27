package com.couponSystem.dao;

import java.util.List;

import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;

public interface CouponsDAO {

	void addCupon(Coupon coupon) throws CouponSystemException;
	void updateCoupon(Coupon coupon) throws CouponSystemException;
	void deleteCoupon(int couponID) throws CouponSystemException;
	List<Coupon> getAllCoupons() throws CouponSystemException;
	Coupon getOneCoupon(int coponID) throws CouponSystemException;
	boolean couponExists(String title) throws CouponSystemException;
	Coupon getCustomerCoupons(int customerID) throws CouponSystemException;
	List<Coupon> dates() throws CouponSystemException;
	int getExpired() throws CouponSystemException;
}
