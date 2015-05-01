package test.xyzCalculate;

import com.bwq.treasuryArbitrage.modelsCalculation.calculator.XyzCalculator;

public class TestCalculate {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new XyzCalculator());
		thread.start();
	}

}
