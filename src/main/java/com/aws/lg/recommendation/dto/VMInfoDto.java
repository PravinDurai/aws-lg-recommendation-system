package com.aws.lg.recommendation.dto;


import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VMInfoDto {
	
	private int infoId;
	private String region;
	private int userLoad;
	private int throughput;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date;
	private String instanceType;
	private String vmType;
	private String scriptComplexity;
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getUserLoad() {
		return userLoad;
	}
	public void setUserLoad(Integer userLoad) {
		this.userLoad = userLoad;
	}
	public Integer getThroughput() {
		return throughput;
	}
	public void setThroughput(Integer throughput) {
		this.throughput = throughput;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getVmType() {
		return vmType;
	}
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
	public String getScriptComplexity() {
		return scriptComplexity;
	}
	public void setScriptComplexity(String scriptComplexity) {
		this.scriptComplexity = scriptComplexity;
	}
	
	@Override
	public String toString() {
		return "VMInfoDto [infoId=" + infoId + ", region=" + region + ", userLoad=" + userLoad + ", throughput="
				+ throughput + ", date=" + date + ", instanceType=" + instanceType + ", vmType=" + vmType
				+ ", scriptComplexity=" + scriptComplexity + "]";
	}
	
	
}
