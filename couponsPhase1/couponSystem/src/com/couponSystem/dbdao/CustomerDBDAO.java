package com.couponSystem.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.couponSystem.classes.Company;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.classes.Customer;
import com.couponSystem.dao.CustomersDAO;
import com.couponSystem.pool.ConnectionPool;

public class CustomerDBDAO implements CustomersDAO {
	private ConnectionPool connectionPool;
	int id;

	public CustomerDBDAO() {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (CouponSystemException e) {
			System.out.println("cannot be connected" + e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean isCustomerExists(String email, String password) throws CouponSystemException {
		String sql = "select * from Customers where email=? and password=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}else {
				System.out.println("there is no customer with that email and password");
			return false;}
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong checking if customer exists", e);
		}
	}

	@Override
	public void addCustomer(Customer customer) throws CouponSystemException {
		isCustomerExists(customer.getEmail(), customer.getPassword());
		String sql = "insert into customers(first_name,last_name,email,password) values(?,?,?,?)";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.executeUpdate();
			System.out.println("customer " + customer.getFirstName() + " added successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("cannot add customer", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		String sql = "update CUSTOMERS set FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PASSWORD=? where id=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());
			pstmt.executeUpdate();
			System.out.println("customer " + customer.getId() + " updated successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("cannot update customer", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCustomer(int customerID) throws CouponSystemException {
		String sql = "delete from customers where id=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);
			pstmt.executeUpdate();
			System.out.println("customer " + customerID + " deleted successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("cannot delete customer", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public List<Customer> getAllCustomers() throws CouponSystemException {
		String sql = "select * from customers";
		Connection con = connectionPool.getConnection();
		List<Customer> customer1 = new ArrayList<Customer>();
		try (Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Customer customer = new Customer(id);
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_Name"));
				customer.setLastName(rs.getString("last_Name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
				customer1.add(customer);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("cannot get customers", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
		return customer1;
	}

	@Override
	public Customer getOneCustomer(int customerID) throws CouponSystemException {
		String sql = "select * from customers where id=?";
		Connection con = connectionPool.getConnection();
		Customer customer = new Customer(customerID);
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_Name"));
				customer.setLastName(rs.getString("last_Name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));

			}
		} catch (SQLException e) {
			throw new CouponSystemException("cannot get customer", e);
		} finally {
			connectionPool.restoreConnection(con);
		}

		return customer;
	}

	@Override
	public List<Coupon> getAllCoupons() throws CouponSystemException {
		String sql = "select * from coupons";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon(id);
		List<Coupon> coup = new ArrayList<Coupon>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				coupon.setId(rs.getInt("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("DESCRIPTION"));
				coupon.setStartDate(rs.getDate("start_Date"));
				coupon.setEndDate(rs.getDate("end_Date"));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setCompanyID(rs.getInt("company_id"));
				coupon.setCategoryID(rs.getInt("category_id"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));
				coup.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("we can't read all companies", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}
		return coup;
	}

	@Override
	public Coupon getCouponsByCategoryID(int categoryID) throws CouponSystemException {
		String sql = "select * from coupons where id=?";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon(categoryID);
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, String.valueOf(categoryID));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				coupon.setId(rs.getInt("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("DESCRIPTION"));
				coupon.setStartDate(rs.getDate("start_Date"));
				coupon.setEndDate(rs.getDate("end_Date"));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setCompanyID(rs.getInt("company_id"));
				coupon.setCategoryID(rs.getInt("category_id"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

			}
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong getting coupon", e);
		} finally {

			connectionPool.restoreConnection(con);
		}
		return coupon;
	}

	@Override
	public Coupon getCouponsByMaxPrice(double maxPrice) throws CouponSystemException {
		String sql = "SELECT * FROM COUPONS WHERE PRICE = (SELECT MAX (price)FROM COUPONS)";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				coupon.setId(rs.getInt("id"));
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("DESCRIPTION"));
				coupon.setStartDate(rs.getDate("start_Date"));
				coupon.setEndDate(rs.getDate("end_Date"));
				coupon.setAmount(rs.getInt("amount"));
				coupon.setCompanyID(rs.getInt("company_id"));
				coupon.setCategoryID(rs.getInt("category_id"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));

			}
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong getting coupon", e);
		} finally {

			connectionPool.restoreConnection(con);
		}
		return coupon;
	}
	public Coupon purCoupon(Coupon coupon) throws CouponSystemException {
		String sql="INSERT INTO CUSTOMERS_VS_COUPONS(CUSTOMER_ID,COUPON_ID) VALUES (?, ?)";
		Connection con = connectionPool.getConnection();
		Customer customer=new Customer();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customer.getId());
			pstmt.setInt(2, coupon.getId());
			pstmt.executeUpdate();
			System.out.println("coupon purchased successfully");
		
		} catch (SQLException e) {
			throw new CouponSystemException("cannot purchase coupon", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	
		
		return null;
		
	}
	
}
