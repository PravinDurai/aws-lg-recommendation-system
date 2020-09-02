package com.aws.lg.recommendation.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="aws_vm_instance_info")
//@Table(name="aws_vm_instance_info",schema="db_mapping")
public class VMInfoEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="instance_info_id")
	private int infoId;
	
	@Column(name="region")
	private String region;
	
	@Column(name="user_load")
	private int userLoad;
	
	@Column(name="throughput")
	private int throughput;
	
	@Column(name="date")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	@Column(name="instance_type")
	private String instanceType;
	
	@Column(name="vm_type")
	private String vmType;
	
	@Column(name="script_complexity")
	private String scriptComplexity;

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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
}
