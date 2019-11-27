package com.couponSystem.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.couponSystem.classes.Company;
import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.dao.CompaniesDAO;
import com.couponSystem.pool.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {
	int id;
	private ConnectionPool connectionPool;

	@Override
	public boolean isCompanyExists(String email, String password) throws CouponSystemException {
		String sql = "select * from companies where email=? and password=?";
		List<Company>companies=new ArrayList<Company>();
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company(id);
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				companies.add(company);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("we can't read companies", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}if(companies.size()>0) {
			
			return true;
		}return false;
		
	}

	public CompaniesDBDAO() {
		try {
			connectionPool = ConnectionPool.getInstance();
			System.out.println("you are connected");
		} catch (CouponSystemException e) {
			System.out.println("can't get connection");
			e.printStackTrace();
		}
	}

	@Override
	public void addCompany(Company company) throws CouponSystemException {
		String sql = "insert into COMPANIES(name,email,password) values(?,?,?)";

		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();

			System.out.println("company " + company.getName() + " was added successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("the company didn't added please check parameters and try again", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");

		}

	}

	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		String sql = "update companies set name=?,email=?,password=? where ID=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.setInt(4, company.getId());
			pstmt.executeUpdate();

			System.out.println("company updated successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("the company didn't updated, please check parameters and try again", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}
	}

	@Override
	public void deleteCompany(int companyID) throws CouponSystemException {
		String sql = "delete from companies where id=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, companyID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("company " + companyID + " not deleted", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}

	}

	@Override
	public List<Company> getAllCompanies() throws CouponSystemException {
		String sql = "select * from companies";
		List<Company>companies=new ArrayList<Company>();
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company(id);
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				companies.add(company);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("we can't read all companies", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}
		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) throws CouponSystemException {
		String sql = "select * from companies where id=?";
		Company company = new Company(id);
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, companyID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company.setId(companyID);
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			throw new CouponSystemException("error reading a company " + companyID, e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}
		return company;

	}

	@Override
	public List<Coupon> getCompanyCoupons(int company_id) throws CouponSystemException {
		String sql = "select * from coupons where COMPANY_ID=?";
		List<Coupon> comcou=new ArrayList<>();
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			Coupon coupon = new Coupon();
			pstmt.setInt(1,company_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				coupon.setId(rs.getInt(1));
				coupon.setTitle(rs.getString(2));
				coupon.setStartDate(rs.getDate(3));
				coupon.setEndDate(rs.getDate(4));
				coupon.setAmount(rs.getInt(5));
				coupon.setPrice(rs.getInt(6));
				coupon.setDescription(rs.getString(7));
				coupon.setCompanyID(rs.getInt(8));
				coupon.setCategoryID(rs.getInt(9));
				comcou.add(coupon);
				System.out.println(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("we can't read company coupons", e);
		} finally {
			connectionPool.restoreConnection(con);
			System.out.println("connection returned");
		}
		return comcou;
	}

}
