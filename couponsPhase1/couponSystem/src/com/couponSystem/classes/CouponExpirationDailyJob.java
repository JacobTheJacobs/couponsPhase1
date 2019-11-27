package com.couponSystem.classes;

import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.facade.AdminFacade;
import com.couponSystem.pool.ConnectionPool;
import com.couponSystem.pool.LoginManager;

public class CouponExpirationDailyJob implements Runnable {

	private boolean quit = false;
	private CompaniesDAO companiesDao;
	private CustomersDAO customersDao;
	

	public CouponExpirationDailyJob() {

		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance();
		} catch (CouponSystemException e) {
			System.out.println("cannot connect the thread");
			e.printStackTrace();
		}

	}

	public void run() {
		while (!quit) {
		try {
				LoginManager lm = LoginManager.getInstance();
				AdminFacade af = (AdminFacade) lm.login("admin", "1234", ClientType.ADMINISTRATOR);
				af.deleteCoupon(af.getExpired());

				Thread.sleep(1000 * 60 * 60 * 24);

			
		} catch (CouponSystemException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			break;}
		}
		

	}

	public void stop() {
		quit = true;
		System.out.println("stopped'");
		
	}
}
