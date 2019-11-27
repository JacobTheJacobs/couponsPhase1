package com.couponSystem.classes;

public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Coupon coupons;
	
	public Customer( String firstName, String lastName, String email, String password,
			Coupon coupon) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupons = coupon;
	}
	public Customer(int id) {
		this.id=id;
	}
	public Customer() {
	}
	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;
		
	
	}
	public Customer(int id,String firstName, String lastName, String email, String password) {
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Coupon getCoupons() {
		return coupons;
	}
	public void setCoupons(Coupon coupons) {
		this.coupons = coupons;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password +  "]";
	}
	
	
}
