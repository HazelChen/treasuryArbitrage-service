package com.bwq.treasuryArbitrage;

import com.bwq.treasuryArbitrage.common.LiveData;
import com.bwq.treasuryArbitrage.dataFetch.DataFetcher;
import com.bwq.treasuryArbitrage.modelsCalculation.calculator.LambdaCalculator;
import com.bwq.treasuryArbitrage.modelsCalculation.calculator.OptimalKTCalculator;
import com.bwq.treasuryArbitrage.modelsCalculation.calculator.XyzCalculator;

public class ServiceLancher {
	
	public static void main(String[] args) {
		fetchData();
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		System.err.println("==========================");
//		System.err.println(LiveData.getInstance().getData("TF1506").getPrice());
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
