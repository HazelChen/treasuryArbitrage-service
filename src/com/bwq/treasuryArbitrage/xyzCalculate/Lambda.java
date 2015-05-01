package com.bwq.treasuryArbitrage.xyzCalculate;

import java.util.Date;

public class Lambda {

	private int group;
	private Date time;
	private double Lambda1;
	private double Lambda2;
	
	public Lambda(int group,Date time, double Lambda1, double Lambda2){
		this.group = group;
		this.time = time;
		this.Lambda1 = Lambda1;
		this.Lambda2 = Lambda2;
	}
	
	public Lambda(int group, double Lambda1, double Lambda2) {
		this.setGroup(group);
		this.setLambda1(Lambda1);
		this.setLambda2(Lambda2);
	}
	public Lambda(int group) {
		this.setGroup(group);
		this.setLambda1(0);
		this.setLambda2(0);
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
	public double getLambda1() {
		return Lambda1;
	}
	public void setLambda1(double lambda1) {
		Lambda1 = lambda1;
	}
	public double getLambda2() {
		return Lambda2;
	}
	public void setLambda2(double lambda2) {
		Lambda2 = lambda2;
	}
	
}
