package com.bwq.treasuryArbitrage.dataFetch;
import com.sun.jna.Callback;

public class callBack_String implements Callback{
	
	public void setString(String val,int loc){
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);
	}
	
}
