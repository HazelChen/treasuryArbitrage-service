package test.database;

import com.bwq.treasuryArbitrage.database.DataForAnalyzer;

public class testAnalyzerData {
	public static void main(String[] args) {
		System.out.println(DataForAnalyzer.getDataToday(0).size());
		System.out.println(DataForAnalyzer.getCurData(0).getPrice());
		System.out.println(DataForAnalyzer.getCurData(0).getPrice());
	}
}
