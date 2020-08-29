package com.aws.lg.recommendation.algorithm;

import java.util.Map;
import java.util.TreeMap;

public class OptimisedResult {
	/*
	private int totalUserLoadLinux;
	private int totalUserLoadWindows;
	
	double totalCostLinux;
	double totalCostWindows;
	*/
	private Map<Double, ECInstance> topTOptResultLinux=new TreeMap<Double, ECInstance>();
	private Map<Double, ECInstance> topTOptResultWindows=new TreeMap<Double, ECInstance>();
	private Map<Double, ECInstance> sortedOrderLinux=new TreeMap<Double, ECInstance>();
	private Map<Double, ECInstance> sortedOrderWindows=new TreeMap<Double, ECInstance>();
	
	public OptimisedResult() {
		super();
	}

	public OptimisedResult(Map<Double, ECInstance> topTLinux,Map<Double, ECInstance> topTWindows,Map<Double, ECInstance> sortedOrderLinux, Map<Double, ECInstance> sortedOrderWindows) {
		super();
		this.topTOptResultLinux=topTLinux;
		this.topTOptResultWindows=topTWindows;
		this.sortedOrderLinux = sortedOrderLinux;
		this.sortedOrderWindows = sortedOrderWindows;
	}

	/*
	public OptimisedResult(int totalUserLoadLinux, int totalUserLoadWindows, double totalCostLinux,
			double totalCostWindows, Map<Double, ECInstance> sortedOrderLinux,
			Map<Double, ECInstance> sortedOrderWindows) {
		super();
		this.totalUserLoadLinux = totalUserLoadLinux;
		this.totalUserLoadWindows = totalUserLoadWindows;
		this.totalCostLinux = totalCostLinux;
		this.totalCostWindows = totalCostWindows;
		this.sortedOrderLinux = sortedOrderLinux;
		this.sortedOrderWindows = sortedOrderWindows;
	}

	public int getTotalUserLoadLinux() {
		return totalUserLoadLinux;
	}
	public void setTotalUserLoadLinux(int totalUserLoadLinux) {
		this.totalUserLoadLinux = totalUserLoadLinux;
	}
	public int getTotalUserLoadWindows() {
		return totalUserLoadWindows;
	}
	public void setTotalUserLoadWindows(int totalUserLoadWindows) {
		this.totalUserLoadWindows = totalUserLoadWindows;
	}
	public double getTotalCostLinux() {
		return totalCostLinux;
	}
	public void setTotalCostLinux(double totalCostLinux) {
		this.totalCostLinux = totalCostLinux;
	}
	public double getTotalCostWindows() {
		return totalCostWindows;
	}
	public void setTotalCostWindows(double totalCostWindows) {
		this.totalCostWindows = totalCostWindows;
	}
	*/

	public Map<Double, ECInstance> getSortedOrderLinux() {
		return sortedOrderLinux;
	}
	public Map<Double, ECInstance> getTopTOptResultLinux() {
		return topTOptResultLinux;
	}

	public void setTopTOptResultLinux(Map<Double, ECInstance> topTOptResultLinux) {
		this.topTOptResultLinux = topTOptResultLinux;
	}

	public Map<Double, ECInstance> getTopTOptResultWindows() {
		return topTOptResultWindows;
	}

	public void setTopTOptResultWindows(Map<Double, ECInstance> topTOptResultWindows) {
		this.topTOptResultWindows = topTOptResultWindows;
	}

	public void setSortedOrderLinux(Map<Double, ECInstance> sortedOrderLinux) {
		this.sortedOrderLinux = sortedOrderLinux;
	}
	public Map<Double, ECInstance> getSortedOrderWindows() {
		return sortedOrderWindows;
	}
	public void setSortedOrderWindows(Map<Double, ECInstance> sortedOrderWindows) {
		this.sortedOrderWindows = sortedOrderWindows;
	}

	@Override
	public String toString() {
		return "OptimisedResult [topTOptResultLinux=" + topTOptResultLinux + ", topTOptResultWindows="
				+ topTOptResultWindows + "]";
	}
}
