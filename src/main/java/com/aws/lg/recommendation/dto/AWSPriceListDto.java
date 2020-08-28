package com.aws.lg.recommendation.dto;

public class AWSPriceListDto {
	
	private int id;
	private String type;
	private double linuxPrice;
	private double windowsPrice;
	private String instanceType;
	private String region;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLinuxPrice() {
		return linuxPrice;
	}
	public void setLinuxPrice(double linuxPrice) {
		this.linuxPrice = linuxPrice;
	}
	public double getWindowsPrice() {
		return windowsPrice;
	}
	public void setWindowsPrice(double windowsPrice) {
		this.windowsPrice = windowsPrice;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

}
