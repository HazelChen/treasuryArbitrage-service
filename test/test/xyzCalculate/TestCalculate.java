package test.xyzCalculate;

import com.bwq.treasuryArbitrage.modelsCalculation.calculator.LambdaCalculator;
import com.bwq.treasuryArbitrage.modelsCalculation.calculator.OptimalKTCalculator;
import com.bwq.treasuryArbitrage.modelsCalculation.calculator.XyzCalculator;

public class TestCalculate {
	
	public static void main(String[] args) {
		Thread threadxyk = new Thread(new XyzCalculator());
		Thread threadkt = new Thread(new OptimalKTCalculator());
		Thread threadLambdaThread = new Thread(new LambdaCalculator());
		threadxyk.start();
//		threadkt.start();
//		threadLambdaThread.start();
	}

}
