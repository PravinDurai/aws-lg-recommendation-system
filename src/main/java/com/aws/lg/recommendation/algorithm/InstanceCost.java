package com.aws.lg.recommendation.algorithm;

public class InstanceCost {
	
	private double micro;
	private double small;
	private double medium;
	private double large;
	private double xtraLarge;
	
	public InstanceCost() {
		super();
	}

	public InstanceCost(double micro, double small, double medium, double large, double xtraLarge) {
		super();
		this.micro = micro;
		this.small = small;
		this.medium = medium;
		this.large = large;
		this.xtraLarge = xtraLarge;
	}

	public double getMicro() {
		return micro;
	}

	public void setMicro(double micro) {
		this.micro = micro;
	}

	public double getSmall() {
		return small;
	}

	public void setSmall(double small) {
		this.small = small;
	}

	public double getMedium() {
		return medium;
	}

	public void setMedium(double medium) {
		this.medium = medium;
	}

	public double getLarge() {
		return large;
	}

	public void setLarge(double large) {
		this.large = large;
	}

	public double getXtraLarge() {
		return xtraLarge;
	}

	public void setXtraLarge(double xtraLarge) {
		this.xtraLarge = xtraLarge;
	}

	@Override
	public String toString() {
		return "InstanceCost [micro=" + micro + ", small=" + small + ", medium=" + medium + ", large=" + large
				+ ", xtraLarge=" + xtraLarge + "]";
	}

}
