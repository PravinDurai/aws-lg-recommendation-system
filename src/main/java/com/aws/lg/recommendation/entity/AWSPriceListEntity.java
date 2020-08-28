package com.aws.lg.recommendation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="aws_price_list",schema="db_mapping")
public class AWSPriceListEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="linux_price")
	private Double linuxPrice;
	
	@Column(name="windows_price")
	private Double windowsPrice;
	
	@Column(name="instance_type")
	private String instanceType;
	
	@Column(name="region")
	private String region;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Double getLinuxPrice() {
		return linuxPrice;
	}
	public void setLinuxPrice(Double linuxPrice) {
		this.linuxPrice = linuxPrice;
	}
	public Double getWindowsPrice() {
		return windowsPrice;
	}
	public void setWindowsPrice(Double windowsPrice) {
		this.windowsPrice = windowsPrice;
	}
	public void setId(int id) {
		this.id = id;
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
