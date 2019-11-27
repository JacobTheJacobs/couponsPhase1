package com.couponSystem.classes;

import java.sql.Date;

public class Coupon {
	
	private int id;
	private int companyID;
	private int categoryID;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;
	int customerID;
	
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getCompanyID() {
		return companyID;
	}
	public Coupon(int id) {
		this.id=id;
		
	}
public Coupon() {
		
	}
	public Coupon( Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image, int companyID, int categoryID) {
		super();
		this.companyID = companyID;
		this.categoryID=categoryID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	public Coupon(int id, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image, int companyID, int categoryID) {
		super();
		this.id=id;
		this.companyID = companyID;
		this.categoryID=categoryID;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}
	
	
	public Coupon(Category category) {
		this.category=category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCompanyID(int company_id) {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date date) {
		this.startDate = date;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyID + ", categoryID=" + categoryID + ", category=" + category
				+ ", title=" + title + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", amount=" + amount + ", price=" + price + ", image=" + image + "]";
	}	
	
	
	

}
