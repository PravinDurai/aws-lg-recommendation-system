package com.aws.lg.recommendation.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ECInstance {
	
	private int micro;
	private int small;
	private int medium;
	private int large;
	private int xtraLarge;
	private int totalUsers;
	private double totalCost;
	
	
	public ECInstance() {
		super();
	}
	
	public ECInstance(ECInstance instance) {
		super();
		this.micro = instance.getMicro();
		this.small = instance.getSmall();
		this.medium = instance.getMedium();
		this.large = instance.getLarge();
		this.xtraLarge = instance.getXtraLarge();
		this.totalCost=0;
		this.totalUsers=0;
	}
	
	public ECInstance(int micro, int small, int medium, int large, int xtraLarge) {
		super();
		this.micro = micro;
		this.small = small;
		this.medium = medium;
		this.large = large;
		this.xtraLarge = xtraLarge;
		this.totalCost=0;
		this.totalUsers=0;
	}

	public int getMicro() {
		return micro;
	}
	public void setMicro(int micro) {
		this.micro = micro;
	}
	public int getSmall() {
		return small;
	}
	public void setSmall(int small) {
		this.small = small;
	}
	public int getMedium() {
		return medium;
	}
	public void setMedium(int medium) {
		this.medium = medium;
	}
	public int getLarge() {
		return large;
	}
	public void setLarge(int large) {
		this.large = large;
	}
	public int getXtraLarge() {
		return xtraLarge;
	}
	public void setXtraLarge(int xtraLarge) {
		this.xtraLarge = xtraLarge;
	}
	
	public int getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public void reset() {
		this.micro=0;
		this.small=0;
		this.medium=0;
		this.large=0;
		this.xtraLarge=0;
		this.totalCost=0;
		this.totalUsers=0;
	}
	
	public int[] toArray() {
		int myArray[]= {this.micro,this.small,this.medium,this.large,this.xtraLarge};
		return(myArray);
	}

	@Override
	public String toString() {
		return "ECInstance [micro=" + micro + ", small=" + small + ", medium=" + medium + ", large=" + large
				+ ", xtraLarge=" + xtraLarge + ", totalUsers=" + totalUsers + ", totalCost=" + totalCost + "]";
	}

	//Compare to VMObject to see if they are identical if Identical return true else false
	public boolean equals(ECInstance obj) {
		if((this.micro==obj.micro)&&(this.small==obj.small)&&(this.medium==obj.medium)&&(this.large==obj.large)&&(this.xtraLarge==obj.xtraLarge)) {
			return(true);
		}else {
			return(false);
		}
	}

}
