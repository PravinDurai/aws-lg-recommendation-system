package com.aws.lg.recommendation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.format.annotation.DateTimeFormat;

public class InstanceModel {

	private Set<String> regionList = new HashSet<String>();
	private String region;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime executionDate;
	private String scriptComplexity;
	private Set<String> scriptType = new HashSet<String>();
	private int userLoad;
	private int throughput;

	public InstanceModel() {
		super();
	}

	public InstanceModel(Set<String> regionList, Set<String> scriptTypeList) {
		super();
		this.regionList = regionList;
		this.scriptType = scriptTypeList;
	}

	public Set<String> getRegionList() {
		return regionList;
	}

	public void setRegionList(Set<String> regionList) {
		this.regionList = regionList;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	/*
	 * public Date getExecutionDate() { return executionDate; }
	 * 
	 * public void setExecutionDate(Date executionDate) { this.executionDate =
	 * executionDate; }
	 */

	public LocalDateTime getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(LocalDateTime executionDate) {
		this.executionDate = executionDate;
	}

	public String getScriptComplexity() {
		return scriptComplexity;
	}

	public void setScriptComplexity(String scriptComplexity) {
		this.scriptComplexity = scriptComplexity;
	}

	public Set<String> getScriptType() {
		return scriptType;
	}

	public void setScriptType(Set<String> scriptType) {
		this.scriptType = scriptType;
	}

	public int getUserLoad() {
		return userLoad;
	}

	public void setUserLoad(int userLoad) {
		this.userLoad = userLoad;
	}

	public int getThroughput() {
		return throughput;
	}

	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}

	@Override
	public String toString() {
		return "InstanceModel [region=" + region + ", executionDate=" + executionDate + ", scriptComplexity="
				+ scriptComplexity + ", userLoad=" + userLoad + ", throughput=" + throughput + "]";
	}

}
