package com.aws.lg.recommendation.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.lg.recommendation.algorithm.ECInstance;
import com.aws.lg.recommendation.algorithm.InstanceCost;
import com.aws.lg.recommendation.algorithm.OptimisationAlgorithm;
import com.aws.lg.recommendation.algorithm.OptimisedResult;
import com.aws.lg.recommendation.dto.AWSPriceListDto;
import com.aws.lg.recommendation.dto.VMInfoDto;
import com.aws.lg.recommendation.entity.AWSPriceListEntity;
import com.aws.lg.recommendation.entity.VMInfoEntity;
import com.aws.lg.recommendation.model.InstanceModel;
import com.aws.lg.recommendation.service.AwsPriceListDao;
import com.aws.lg.recommendation.service.HomeService;
import com.aws.lg.recommendation.service.VMInstanceDao;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private VMInstanceDao vMInstanceDao;

	@Autowired
	private AwsPriceListDao awsPriceListDao;

	@Override
	public Set<String> getAllRegions() {
		List<AWSPriceListEntity> priceListEntity = awsPriceListDao.findAll();
		Set<String> regions = new HashSet<String>();
		priceListEntity.forEach(temp -> {
			regions.add(temp.getRegion());
		});
		return (regions);
	}

	@Override
	public Set<String> getAllScriptComplexity() {
		List<VMInfoEntity> vMinfoEntity = vMInstanceDao.findAll();
		Set<String> scriptComplexitySet = new HashSet<String>();
		vMinfoEntity.forEach(temp -> {
			scriptComplexitySet.add(temp.getScriptComplexity());
		});
		return (scriptComplexitySet);
	}

	@Override
	public OptimisedResult calculateUserLoad(InstanceModel model) {
		// Initializint the user
		int userLoad = model.getUserLoad();

		// Get the value for users by region and script complexity from database
		ECInstance maxVMInstance = getUserLoad(model);

		InstanceCost instanceCostLinux = getInstanceCost(model, "linux");
		InstanceCost instanceCostWindows = getInstanceCost(model, "windows");
		
		OptimisationAlgorithm algo = new OptimisationAlgorithm();

		// calculating the maximum possible VM individually required to run the given
		// user load
		ECInstance possibleVMInstance = new ECInstance(userLoad / maxVMInstance.getMicro(),
				userLoad / maxVMInstance.getSmall(), userLoad / maxVMInstance.getMedium(),
				userLoad / maxVMInstance.getLarge(), userLoad / maxVMInstance.getXtraLarge());

		// Initial Values before calculation
		System.out.println("Maximum Users that each VM can accomodate \n" + maxVMInstance.toString());
		System.out.println("Cost of each VM (Linux)\n" + instanceCostLinux.toString());
		System.out.println("Cost of each VM (Windows)\n" + instanceCostWindows.toString());
		System.out.println("Maximum number of VM that can individually satisfy the given user Load\n"
				+ possibleVMInstance.toString());

		System.out.println("Max VM User Load \n" + maxVMInstance.toString());

		// Trying to generate n Random instance Values that match the userLoad
		List<ECInstance> instanceList = new ArrayList<ECInstance>();
		List<ECInstance> randomInstanceListLinux = new ArrayList<ECInstance>();
		List<ECInstance> randomInstanceListWindows = new ArrayList<ECInstance>();
		
		//generating Random solution that meets the target
		generateRandomSolution(maxVMInstance, instanceCostLinux, instanceCostWindows, userLoad,randomInstanceListLinux ,randomInstanceListWindows, 1000);
		
		//My Fitness Function
		Map<Double, ECInstance> sortedOrderLinux = new TreeMap<Double, ECInstance>();
		fitnessFunction(sortedOrderLinux, randomInstanceListLinux, instanceCostLinux);
		
		Map<Double, ECInstance> sortedOrderWindows = new TreeMap<Double, ECInstance>();
		fitnessFunction(sortedOrderWindows, randomInstanceListWindows, instanceCostWindows);
		
		// calculating the number close to the target user load with minimum cost
		// (Similar to fitness function)for Linux
		Map<Double, ECInstance> sortedOrderTTLinux = new TreeMap<Double, ECInstance>();
		Map<Double, ECInstance> sortedOrderTTWindows = new TreeMap<Double, ECInstance>();
		int totalUserLoadLinux=0;
		double totalCostLinux=0;
		calculateCost(sortedOrderLinux, maxVMInstance, instanceCostLinux, totalUserLoadLinux, totalCostLinux);
		
		//for Windows
		int totalUserLoadWindows=0;
		double totalCostWindows=0;
		calculateCost(sortedOrderWindows, maxVMInstance, instanceCostWindows, totalUserLoadWindows, totalCostWindows);
		int i=0;
		ECInstance optLinux=new ECInstance();
		ECInstance optWindows=new ECInstance();
		for(Double temp:sortedOrderLinux.keySet()) {
			if(i==0) {
				optLinux.setMicro(sortedOrderLinux.get(temp).getMicro());
				optLinux.setSmall(sortedOrderLinux.get(temp).getSmall());
				optLinux.setMedium(sortedOrderLinux.get(temp).getMedium());
				optLinux.setLarge(sortedOrderLinux.get(temp).getLarge());
				optLinux.setXtraLarge(sortedOrderLinux.get(temp).getXtraLarge());
			}
			if(i>19) {
				break;
			}
			sortedOrderTTLinux.put(temp, sortedOrderLinux.get(temp));
			i++;
		}
		i=0;
		for(Double temp:sortedOrderWindows.keySet()) {	
			if(i==0) {
				optWindows.setMicro(sortedOrderWindows.get(temp).getMicro());
				optWindows.setSmall(sortedOrderWindows.get(temp).getSmall());
				optWindows.setMedium(sortedOrderWindows.get(temp).getMedium());
				optWindows.setLarge(sortedOrderWindows.get(temp).getLarge());
				optWindows.setXtraLarge(sortedOrderWindows.get(temp).getXtraLarge());
			}
			if(i>19) {
				break;
			}
			sortedOrderTTWindows.put(temp, sortedOrderWindows.get(temp));
			i++;
		}
		OptimisedResult opResult=new OptimisedResult(optLinux,optWindows,sortedOrderTTLinux, sortedOrderTTWindows,sortedOrderLinux, sortedOrderWindows );
		return (opResult);
	}
	
	@Override
	public OptimisedResult calculateThroughput(InstanceModel model) {
		
		List<VMInfoEntity> vMInstanceEntityList=vMInstanceDao.findByRegionAndScriptComplexity(model.getRegion(), model.getScriptComplexity());
		double throughputPerUser=0;
		int i=1;
		for(VMInfoEntity temp:vMInstanceEntityList) {
			throughputPerUser+=temp.getThroughput()/temp.getUserLoad();
			System.out.println("Throughput :\t"+throughputPerUser);
			i++;
		}
		throughputPerUser/=i;
		i=1;
		
		while(true) {
			if(i*throughputPerUser>=model.getThroughput()) {
				break;
			}
			i++;
		}
		InstanceModel instanceModel=new InstanceModel();
		instanceModel.setThroughput(0);
		instanceModel.setExecutionDate(model.getExecutionDate());
		instanceModel.setRegion(model.getRegion());
		instanceModel.setScriptComplexity(model.getScriptComplexity());
		instanceModel.setScriptType(model.getScriptType());
		instanceModel.setUserLoad(i);
		OptimisedResult optimisedResult=calculateUserLoad(instanceModel);
		System.out.println("Calculated User Load for the given throughput is :\t"+instanceModel.getUserLoad());
		return(optimisedResult);
	}
	
	private void calculateCost(Map<Double, ECInstance> sortedOrder,ECInstance maxVMInstance, InstanceCost instanceCost, int totalUserLoad, double totalCost) {
		for (Double temp : sortedOrder.keySet()) {
			totalUserLoad = sortedOrder.get(temp).getMicro() * maxVMInstance.getMicro()
					+ sortedOrder.get(temp).getSmall() * maxVMInstance.getSmall()
					+ sortedOrder.get(temp).getMedium() * maxVMInstance.getMedium()
					+ sortedOrder.get(temp).getLarge() * maxVMInstance.getLarge()
					+ sortedOrder.get(temp).getXtraLarge() * maxVMInstance.getXtraLarge();
			totalCost = sortedOrder.get(temp).getMicro() * instanceCost.getMicro()
					+ sortedOrder.get(temp).getSmall() * instanceCost.getSmall()
					+ sortedOrder.get(temp).getMedium() * instanceCost.getMedium()
					+ sortedOrder.get(temp).getLarge() * instanceCost.getLarge()
					+ sortedOrder.get(temp).getXtraLarge() * instanceCost.getXtraLarge();
			sortedOrder.get(temp).setTotalCost(totalCost);
			sortedOrder.get(temp).setTotalUsers(totalUserLoad);
//			System.out.println(sortedOrder.get(temp).toString());
//			System.out.println("User Load :\t" + totalUserLoad + "\tTotal Cost :\t" + totalCost);
		}
	}
	
	private void fitnessFunction(Map<Double, ECInstance> sortedOrder , List<ECInstance> randomInstanceList, InstanceCost instanceCost) {
//		System.out.println("Random Instance Based On Cost\n");
		randomInstanceList.forEach(temp -> {
			double totalCost = temp.getMicro() * instanceCost.getMicro()
					+ temp.getSmall() * instanceCost.getSmall() + temp.getMedium() * instanceCost.getMedium()
					+ temp.getLarge() * instanceCost.getLarge()
					+ temp.getXtraLarge() * instanceCost.getXtraLarge();
			sortedOrder.put(totalCost, temp);
		});
	}
	
	private void generateRandomSolution(ECInstance maxVMInstance,InstanceCost instanceCostLinux,InstanceCost instanceCostWindows,int userLoad, List<ECInstance> randomInstanceListLinux,List<ECInstance> randomInstanceListWindows, int iteration) {
		// Try to find all the possible solution that meet the required user load and
		// put them in a list
		OptimisationAlgorithm algo=new OptimisationAlgorithm();
		for (int j = 0; j < 2; j++) {
			if (j == 0) {
				for (int i = 0; i < 1000; i++) {
					randomInstanceListLinux
							.add(algo.randomInstanceBasedOnCost(maxVMInstance, instanceCostLinux, userLoad));
				}
			} else {
				for (int i = 0; i < 1000; i++) {
					randomInstanceListWindows
							.add(algo.randomInstanceBasedOnCost(maxVMInstance, instanceCostWindows, userLoad));
				}
			}
		}
	}

	private InstanceCost getInstanceCost(InstanceModel model, String machine) {

		// Get the value of VM instance price based on the region and time when they are
		// planning to run the test
		List<AWSPriceListEntity> priceEntityList = awsPriceListDao.findByRegion(model.getRegion());

		double micro = 0;
		double small = 0;
		double medium = 0;
		double large = 0;
		double xtraLarge = 0;

		System.out.println("Machine : "+machine);
		
		for (AWSPriceListEntity temp : priceEntityList) {
			System.out.println(temp.toString());
			if (machine.equalsIgnoreCase("linux")) {
				if (temp.getInstanceType().equals("micro")) {
					micro = temp.getLinuxPrice();
				}
				if (temp.getInstanceType().equals("small")) {
					small = temp.getLinuxPrice();
				}
				if (temp.getInstanceType().equals("medium")) {
					medium = temp.getLinuxPrice();
				}
				if (temp.getInstanceType().equals("large")) {
					large = temp.getLinuxPrice();
				}
				if (temp.getInstanceType().equals("xtra large")) {
					xtraLarge = temp.getLinuxPrice();
				}
			} else {
				if (temp.getInstanceType().equals("micro")) {
					micro = temp.getWindowsPrice();
				}
				if (temp.getInstanceType().equals("small")) {
					small = temp.getWindowsPrice();
				}
				if (temp.getInstanceType().equals("medium")) {
					medium = temp.getWindowsPrice();
				}
				if (temp.getInstanceType().equals("large")) {
					large = temp.getWindowsPrice();
				}
				if (temp.getInstanceType().equals("xtra large")) {
					xtraLarge = temp.getWindowsPrice();
				}
			}
		}
		InstanceCost newInstance = new InstanceCost(micro, small, medium, large, xtraLarge);
		return (newInstance);
	}

	private ECInstance getUserLoad(InstanceModel model) {
		int micro = 0;
		int small = 0;
		int medium = 0;
		int large = 0;
		int xtraLarge = 0;

		List<VMInfoEntity> vmInfoList = vMInstanceDao.findByRegionAndScriptComplexity(model.getRegion(),
				model.getScriptComplexity());

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<VMInfoDto> vmInfoDtoList = new ArrayList<VMInfoDto>();
		vmInfoList.forEach(temp -> {
			VMInfoDto t = modelMapper.map(temp, VMInfoDto.class);
			vmInfoDtoList.add(t);
			
		});

		for (VMInfoDto temp : vmInfoDtoList) {
			System.out.println(temp.toString());
			if (temp.getInstanceType().equals("micro")) {
				micro = temp.getUserLoad();
			}
			if (temp.getInstanceType().equals("small")) {
				small = temp.getUserLoad();
			}
			if (temp.getInstanceType().equals("medium")) {
				medium = temp.getUserLoad();
			}
			if (temp.getInstanceType().equals("large")) {
				large = temp.getUserLoad();
			}
			if (temp.getInstanceType().equals("xtra large")) {
				xtraLarge = temp.getUserLoad();
			}
		}
		ECInstance newInstance = new ECInstance(micro, small, medium, large, xtraLarge);
		System.out.println(newInstance.toString());
		return (newInstance);
	}
}
