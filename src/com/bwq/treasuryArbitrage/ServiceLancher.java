package com.bwq.treasuryArbitrage;

import com.bwq.treasuryArbitrage.dataFetch.DataFetcher;
import com.bwq.treasuryArbitrage.xyzCalculate.LambdaCalculator;
import com.bwq.treasuryArbitrage.xyzCalculate.OptimalKTCalculator;
import com.bwq.treasuryArbitrage.xyzCalculate.XyzCalculator;

public class ServiceLancher {
	
	public static void main(String[] args) {
		fetchData();
		//calculateXyz();
		//calculateLambda();
		//calculateOptimalKT();
	}

	private static void fetchData() {
		Thread thread = new Thread(new DataFetcher());
		thread.start();
	}
	
	private static void calculateXyz() {
		Thread calculateXyzThread = new Thread(new XyzCalculator());
		calculateXyzThread.start();
	}
	
	private static void calculateLambda() {
		Thread calculateLambdaThread = new Thread(new LambdaCalculator());
		calculateLambdaThread.start();
	}
	
	private static void calculateOptimalKT() {
		Thread calculateOptimalKTThread = new Thread(new OptimalKTCalculator());
		calculateOptimalKTThread.start();
	}


}
