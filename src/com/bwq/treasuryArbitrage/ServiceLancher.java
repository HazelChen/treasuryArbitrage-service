package com.bwq.treasuryArbitrage;

import com.bwq.treasuryArbitrage.dataFetch.DataFetcher;
import com.bwq.treasuryArbitrage.xyzCalculate.XyzCalculator;

public class ServiceLancher {
	
	public static void main(String[] args) {
		fetchData();
		//calculateXyz();
	}

	private static void fetchData() {
		Thread thread = new Thread(new DataFetcher());
		thread.start();
	}
	
	private static void calculateXyz() {
		Thread calculateXyzThread = new Thread(new XyzCalculator());
		calculateXyzThread.start();
	}


}
