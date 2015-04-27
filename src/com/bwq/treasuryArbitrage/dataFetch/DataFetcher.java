package com.bwq.treasuryArbitrage.dataFetch;

import com.bwq.treasuryArbitrage.common.ArbitrageCodes;
import com.bwq.treasuryArbitrage.util.FileOperater;

public class DataFetcher implements Runnable{
	private static final String USER_CONFIG_FILE = "config/ctp-data-user";
	private static final String CODE_CONFIG_SPLITER = ";";
	
	public static callBack_String callStr = new callBack_String();
	public static callBack_Double callDou = new callBack_Double();
	
	private String username;
	private String password;
	
	@Override
	public void run() {
		readUserFromConfig();
		fetchData();
	}

	private void fetchData() {
		System.loadLibrary("./DLL/TestJNA");
		TestJNA.INSTANCE.callBack_String(callStr);
		TestJNA.INSTANCE.callBack_Double(callDou);
		
		String[] arbitrageCodes = ArbitrageCodes.getCodes();
		TestJNA.INSTANCE.initial(arbitrageCodes, 
				arbitrageCodes.length, username, password);
	}

	private void readUserFromConfig() {
		String userLine = FileOperater.read(USER_CONFIG_FILE).trim();
		String[] user = userLine.split(CODE_CONFIG_SPLITER);
		
		if (user == null || user.length != 2) {
			throw new RuntimeException(
					"There are some error in config file" + 
			        USER_CONFIG_FILE);
		}
		
		username = user[0];
		password = user[1];
	}

}
