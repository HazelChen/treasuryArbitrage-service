package com.bwq.treasuryArbitrage.dataFetch;
import com.bwq.treasuryArbitrage.database.DatabaseUtil;
import com.sun.jna.Callback;

public class callBack_Double implements Callback{
	
	public void setDouble(double val,int loc){
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);
		if(loc == 43){
			System.out.println("Start Insert");
			DatabaseUtil.insert(CThostFtdcDepthMarketDataField.getInstance());
		}
	}
	
}
