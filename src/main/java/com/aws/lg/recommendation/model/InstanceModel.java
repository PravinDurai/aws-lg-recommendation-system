package com.aws.lg.recommendation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.format.annotation.DateTimeFormat;

public class InstanceModel {
	
	private Map<String, String> regions=new TreeMap<String, String>();
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date executionDate;
	private List<String> scriptType=new ArrayList<String>();
	private int userLoad;
	private int throughput;
	
	public InstanceModel() {
		super();
	}

	public InstanceModel(Map<String, String> regions, Date executionDate, int userLoad,
			int throughput) {
		super();
		this.regions = regions;
		this.executionDate = executionDate;
		this.scriptType=scriptType;
		this.userLoad = userLoad;
		this.throughput = throughput;
	}

	public Map<String, String> getRegions() {
		return regions;
	}

	public void setRegions(Map<String, String> regions) {
		this.regions = regions;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public List<String> getScriptType() {
		return scriptType;
	}

	public void setScriptType(List<String> scriptType) {
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
}
