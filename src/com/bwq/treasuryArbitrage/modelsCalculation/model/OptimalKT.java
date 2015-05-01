package com.bwq.treasuryArbitrage.modelsCalculation.model;

import java.util.Date;

public class OptimalKT {

	private int group;
	private Date time;
	private double OptimalK;
	private double OptimalT;
	
	public OptimalKT(int group, Date time, double OptimalK, double OptimalT) {
		this.group = group;
		this.time = time;
		this.OptimalK = OptimalK;
		this.OptimalT = OptimalT;
	}
	
	public OptimalKT(int group, double OptimalK, double OptimalT) {
		this.setGroup(group);
		this.setOptimalK(OptimalK);
		this.setOptimalT(OptimalT);
	}
	public OptimalKT(int group) {
		this.setGroup(group);
		this.setOptimalK(0);
		this.setOptimalT(0);
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getOptimalK() {
		return OptimalK;
	}
	public void setOptimalK(double optimalK) {
		OptimalK = optimalK;
	}
	public double getOptimalT() {
		return OptimalT;
	}
	public void setOptimalT(double optimalT) {
		OptimalT = optimalT;
	}
	
}
