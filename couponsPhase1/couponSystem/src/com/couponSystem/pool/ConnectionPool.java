package com.couponSystem.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.couponSystem.classes.CouponSystemException;

public class ConnectionPool {

	private static ConnectionPool instance;
	public static final int MAX_CONNECTIONS = 10;
	private Set<Connection> connections = new HashSet<>();
	private String DBName="CouponSystem";
	private String username="root";
	private String password="";
	private String driverName = "com.mysql.jdbc.Driver";
	
	private String url = "jdbc:mysql://localhost:3306/"+DBName;

	private ConnectionPool() throws CouponSystemException {
		try {
			Class.forName(driverName);
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				connections.add(DriverManager.getConnection(url,username,password));
			}
		} catch (ClassNotFoundException e) {
			throw new CouponSystemException("driver loading failed", e);
		} catch (SQLException e) {
			throw new CouponSystemException("creating connection failed", e);
		}
	}

	public static ConnectionPool getInstance() throws CouponSystemException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	public synchronized void restoreConnection(Connection con) {
		connections.add(con);
		notify();
	}

	public void closeAllConnections() throws CouponSystemException {
		for (Connection c : connections) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new CouponSystemException("closeAllConnections failed", e);
			}
		}
	}

}
