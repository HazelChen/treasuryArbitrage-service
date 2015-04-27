package com.bwq.treasuryArbitrage.xyzCalculate;

public class Xyz {
	private int group;
	private double x;
	private double y;
	private double z;
	
	public Xyz(int group, double x, double y, double z) {
		this.group = group;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Xyz(int group) {
		this.group = group;
	}
	
	public int getGroup() {
		return group;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}

}
