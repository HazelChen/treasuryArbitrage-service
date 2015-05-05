package com.bwq.treasuryArbitrage.dataFetch;

import com.bwq.treasuryArbitrage.common.LiveData;
import com.bwq.treasuryArbitrage.database.DataFetchService;
import com.sun.jna.Callback;

public class callBack_Double implements Callback {

	public void setDouble(double val, int loc) {
		CThostFtdcDepthMarketDataField.getInstance().setData(val, loc);
		if (loc == 43) {
			System.out.println("Start Insert");
			DataFetchService.insert(CThostFtdcDepthMarketDataField
					.getInstance());
			LiveData.getInstance().register(
					CThostFtdcDepthMarketDataField.getInstance().InstrumentID,
					CThostFtdcDepthMarketDataField.getInstance().getSnapshot());
		}
	}

}
