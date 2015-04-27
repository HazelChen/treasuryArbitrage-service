package com.bwq.treasuryArbitrage.common;

import com.bwq.treasuryArbitrage.util.FileOperater;

public class ArbitrageCodes {
	public static final String CODE_CONFIG_FILE = "config/futures-codes";
	public static final String CODE_CONFIG_SPLITER = ";";
	
	private static String[] codes;
	
	public static String[] getCodes() {
		if (codes == null) {
			readCodesFromConfig();
		}
		
		return codes;
	}
	
	private static void readCodesFromConfig() {
		String codesLine = FileOperater.read(CODE_CONFIG_FILE).trim();
		codes = codesLine.split(CODE_CONFIG_SPLITER);
		
		if(codes == null || codes.length != 3) {
			throw new RuntimeException(
					"There are some errors in config file: "
					+ CODE_CONFIG_FILE);
		}
		
	}

}
