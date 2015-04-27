package com.bwq.treasuryArbitrage.xyzCalculate;

import java.util.Date;

public class SimpleArbitrage {

	private double price;
	private Date date;

	public SimpleArbitrage(double price, Date date) {
		this.price = price;
		this.date = date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getPrice() {
		return price;
	}
}
