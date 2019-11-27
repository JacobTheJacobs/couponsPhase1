package com.couponSystem.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.couponSystem.classes.Coupon;
import com.couponSystem.classes.CouponSystemException;
import com.couponSystem.dao.CouponsDAO;
import com.couponSystem.pool.ConnectionPool;

public class CouponDBDAO implements CouponsDAO {

	private ConnectionPool connectionPool;
	int id;

	public CouponDBDAO() {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (CouponSystemException e) {
			System.out.println("something went wrong getting connection" + e);
			e.printStackTrace();
		}
	}

	@Override
	public void addCupon(Coupon coupon) throws CouponSystemException {
		String sql = "INSERT INTO COUPONS(title,description,start_date,end_date,amount,price,image,company_id,category_id) VALUES (?,?,?,?,?,?,?,?,?)";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, coupon.getTitle());
			pstmt.setString(2, coupon.getDescription());
			pstmt.setDate(3, coupon.getStartDate());
			pstmt.setDate(4, coupon.getEndDate());
			pstmt.setInt(5, coupon.getAmount());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setInt(8, coupon.getCompanyID());
			pstmt.setInt(9, coupon.getCategoryID());
			pstmt.executeUpdate();
			System.out.println("coupon added successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong adding coupon", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		String sql = "update coupons set title=?,description=?,start_date=?,end_Date= ?,amount=?,price=?, image=?,category=? where id=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, coupon.getTitle());
			pstmt.setString(2, coupon.getDescription());
			pstmt.setDate(3, coupon.getStartDate());
			pstmt.setDate(4, coupon.getEndDate());
			pstmt.setInt(5, coupon.getAmount());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setString(8, String.valueOf(coupon.getCategory()));
			pstmt.setInt(9, coupon.getId());
			pstmt.executeUpdate();
			System.out.println("coupon number: " + coupon.getId() + " updated successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong updating coupon", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
	}

	@Override
	public void deleteCoupon(int couponID) throws CouponSystemException {
		String sql = "delete from coupons where id=?";
		String sql1 = "delete from CUSTOMERS_VS_COUPONS where COUPON_ID=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponID);
			connectionPool.restoreConnection(con);
			pstmt.executeUpdate();
			System.out.println("coupon number: " + couponID + " deleted successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong deleting coupon", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
		try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
			pstmt.setInt(1, couponID);
			connectionPool.restoreConnection(con);
			pstmt.executeUpdate();
			System.out.println("coupon number: " + couponID + " deleted successfully");
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong deleting coupon", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
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
			throw new CouponSystemException("something went wrong getting all coupons", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
		return coup;
	}

	@Override
	public Coupon getOneCoupon(int couponID) throws CouponSystemException {
		String sql = "select * from coupons where id=?";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon(couponID);
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, couponID);
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
	public boolean couponExists(String title) {
		String sql = "select * from COUPONS where title=?";
		Connection con = connectionPool.getConnection();
		try (PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Coupon getCustomerCoupons(int customerID) throws CouponSystemException {
		String sql = "select * from COUPONS,CUSTOMERS_VS_COUPONS where CUSTOMER_ID=?";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon(customerID);
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, customerID);
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
	public List<Coupon> dates() throws CouponSystemException {
		String sql = "select start_date, end_date from coupons";
		Connection con = connectionPool.getConnection();
		Coupon coupon = new Coupon(id);
		List<Coupon> coup = new ArrayList<Coupon>();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				coupon.setStartDate(rs.getDate("start_Date"));
				coupon.setEndDate(rs.getDate("end_Date"));

				coup.add(coupon);
			}
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong getting all coupons", e);
		} finally {
			connectionPool.restoreConnection(con);
		}
		return coup;
	}

	public int getExpired() throws CouponSystemException {
		String sql = "select * from COUPONS where now()>END_DATE";
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
				System.out.println(coupon);

			}
		} catch (SQLException e) {
			throw new CouponSystemException("something went wrong getting coupon", e);
		} finally {

			connectionPool.restoreConnection(con);
		}
		return coupon.getId();
	}
}
