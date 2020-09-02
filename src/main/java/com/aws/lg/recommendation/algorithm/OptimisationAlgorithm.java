package com.aws.lg.recommendation.algorithm;

public class OptimisationAlgorithm {
	
	public ECInstance randomInstanceBasedOnCost(ECInstance maxUserPerVM,
		InstanceCost instanceCost, int userLoad) {
		ECInstance randomInstance = new ECInstance();
		int sum = 0;
		double micro = 0;
		double small = 0;
		double medium = 0;
		double large = 0;
		double xtraLarge = 0;
		int microIncCount = 0;
		int smallIncCount = 0;
		int mediumIncCount = 0;
		int largeIncCount = 0;
		int xLargeIncCount = 0;
		do {
			
			microIncCount+=(int) (Math.random()*10);
			micro=microIncCount*instanceCost.getMicro();
			while(micro>(smallIncCount*instanceCost.getSmall())) {
				microIncCount--;
				smallIncCount++;
				micro-=instanceCost.getMicro();
				small+=instanceCost.getSmall();
				sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
				if(sum>=userLoad) {
					break;
				}
				int randomSmallCount=(int) (Math.random()*10);
				smallIncCount+=randomSmallCount;
				small+=randomSmallCount*instanceCost.getSmall();
			}
			sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
			if(sum>=userLoad) {
				break;
			}
			//System.out.println("Micro :\t" + microIncCount + "\tCost \t" + microIncCount*instanceCost.getMicro());
			while(small>(mediumIncCount*instanceCost.getMedium())) {
				smallIncCount--;
				mediumIncCount++;
				small-=instanceCost.getSmall();
				medium+=instanceCost.getMedium();
				sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
				if(sum>=userLoad) {
					break;
				}
				int randomMediumCount=(int) (Math.random()*10);
				mediumIncCount+=randomMediumCount;
				medium+=randomMediumCount*instanceCost.getMedium();
			}
			sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
			if(sum>=userLoad) {
				break;
			}
			//System.out.println("Small :\t" + smallIncCount + "\tCost \t" + smallIncCount*instanceCost.getSmall());
			while(medium>(largeIncCount*instanceCost.getLarge())) {
				mediumIncCount--;
				largeIncCount++;
				medium-=instanceCost.getMedium();
				large+=instanceCost.getLarge();
				sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
				if(sum>=userLoad) {
					break;
				}
				int randomLargeCount=(int) (Math.random()*10);
				largeIncCount+=randomLargeCount;
				large+=randomLargeCount*instanceCost.getLarge();
			}
			sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
			if(sum>=userLoad) {
				break;
			}
			//System.out.println("Medium :\t" + mediumIncCount + "\tCost \t" + mediumIncCount*instanceCost.getMedium());
			while(large>(xLargeIncCount*instanceCost.getXtraLarge())) {
				largeIncCount--;
				xLargeIncCount++;
				large-=instanceCost.getLarge();
				xtraLarge+=instanceCost.getXtraLarge();
				sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
				if(sum>=userLoad) {
					break;
				}
				int randomXLargeCount=(int) (Math.random()*10);
				xLargeIncCount+=randomXLargeCount;
				xtraLarge+=randomXLargeCount*instanceCost.getXtraLarge();
			}
			//System.out.println("Large :\t" + largeIncCount + "\tCost \t" + largeIncCount*instanceCost.getLarge());
			//System.out.println("Xtra Large :\t" + xLargeIncCount + "\tCost \t" + xLargeIncCount*instanceCost.getXtraLarge());
			sum=microIncCount*maxUserPerVM.getMicro() + smallIncCount*maxUserPerVM.getSmall() + mediumIncCount*maxUserPerVM.getMedium() + largeIncCount*maxUserPerVM.getLarge() + xLargeIncCount*maxUserPerVM.getXtraLarge();
		} while (sum <= userLoad);
		randomInstance.setMicro(microIncCount);
		randomInstance.setSmall(smallIncCount);
		randomInstance.setMedium(mediumIncCount);
		randomInstance.setLarge(largeIncCount);
		randomInstance.setXtraLarge(xLargeIncCount);
		//System.out.println(randomInstance.toString());
		return (randomInstance);
	}

}
