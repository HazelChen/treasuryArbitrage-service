package com.bwq.treasuryArbitrage.dataFetch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bwq.treasuryArbitrage.modelsCalculation.SimpleArbitrage;

public class CThostFtdcDepthMarketDataField{
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd HH:mm:ss");
	
	private static CThostFtdcDepthMarketDataField self;
	
	///交易日
	public String	TradingDay;
	///合约代码
	public String	InstrumentID;
	///交易所代码
	public String	ExchangeID;
	///合约在交易所的代码
	public String	ExchangeInstID;
	///最新价
	public double	LastPrice;
	///上次结算价
	public double	PreSettlementPrice;
	///昨收盘
	public double	PreClosePrice;
	///昨持仓量
	public double	PreOpenInterest;
	///今开盘
	public double	OpenPrice;
	///最高价
	public double	HighestPrice;
	///最低价
	public double	LowestPrice;
	///数量
	public double	Volume;
	///成交金额
	public double	Turnover;
	///持仓量
	public double	OpenInterest;
	///今收盘
	public double	ClosePrice;
	///本次结算价
	public double	SettlementPrice;
	///涨停板价
	public double	UpperLimitPrice;
	///跌停板价
	public double	LowerLimitPrice;
	///昨虚实度
	public double	PreDelta;
	///今虚实度
	public double	CurrDelta;
	///最后修改时间
	public String	UpdateTime;
	///最后修改毫秒
	public double	UpdateMillisec;
	///申买价一
	public double	BidPrice1;
	///������һ
	public double	BidVolume1;
	///������һ
	public double	AskPrice1;
	///������һ
	public double	AskVolume1;
	///����۶�
	public double	BidPrice2;
	///��������
	public double	BidVolume2;
	///�����۶�
	public double	AskPrice2;
	///��������
	public double	AskVolume2;
	///�������
	public double	BidPrice3;
	///��������
	public double	BidVolume3;
	///��������
	public double	AskPrice3;
	///��������
	public double	AskVolume3;
	///�������
	public double	BidPrice4;
	///��������
	public double	BidVolume4;
	///��������
	public double	AskPrice4;
	///��������
	public double	AskVolume4;
	///�������
	public double	BidPrice5;
	///��������
	public double	BidVolume5;
	///��������
	public double	AskPrice5;
	///��������
	public double	AskVolume5;
	///���վ��
	public double	AveragePrice;
	
	public void setData(String val,int loc){
		switch (loc){
		case 1:
			self.TradingDay = val;
			break;
		case 2:
			self.InstrumentID = val;
			break;
		case 3:
			self.ExchangeID = val;
			break;
		case 4:
			self.ExchangeInstID = val;
			break;
		case 21:
			self.UpdateTime = val;
			break;
		default:
			System.err.println("Error!");
		}
	}
	
	public void setData(double val,int loc){
		switch (loc){
		case 5:
			self.LastPrice = val;
			break;
		case 6:
			self.PreSettlementPrice = val;
			break;
		case 7:
			self.PreClosePrice = val;
			break;
		case 8:
			self.PreOpenInterest = val;
			break;
		case 9:
			self.OpenPrice = val;
			break;
		case 10:
			self.HighestPrice = val;
			break;
		case 11:
			self.LowestPrice = val;
			break;
		case 12:
			self.Volume = val;
			break;
		case 13:
			self.Turnover = val;
			break;
		case 14:
			self.OpenInterest = val;
			break;
		case 15:
			self.ClosePrice = val;
			break;
		case 16:
			self.SettlementPrice = val;
			break;
		case 17:
			self.UpperLimitPrice = val;
			break;
		case 18:
			self.LowerLimitPrice = val;
			break;
		case 19:
			self.PreDelta = val;
			break;
		case 20:
			self.CurrDelta = val;
			break;
		case 22:
			self.UpdateMillisec = val;
			break;
		case 23:
			self.BidPrice1 = val;
			break;
		case 24:
			self.BidVolume1 = val;
			break;
		case 25:
			self.AskPrice1 = val;
			break;
		case 26:
			self.AskVolume1 = val;
			break;
		case 27:
			self.BidPrice2 = val;
			break;
		case 28:
			self.BidVolume2 = val;
			break;
		case 29:
			self.AskPrice2 = val;
			break;
		case 30:
			self.AskVolume2 = val;
			break;
		case 31:
			self.BidPrice3 = val;
			break;
		case 32:
			self.BidVolume3 = val;
			break;
		case 33:
			self.AskPrice3 = val;
			break;
		case 34:
			self.AskVolume3 = val;
			break;
		case 35:
			self.BidPrice4 = val;
			break;
		case 36:
			self.BidVolume4 = val;
			break;
		case 37:
			self.AskPrice4 = val;
			break;
		case 38:
			self.AskVolume4 = val;
			break;
		case 39:
			self.BidPrice5 = val;
			break;
		case 40:
			self.BidVolume5 = val;
			break;
		case 41:
			self.AskPrice5 = val;
			break;
		case 42:
			self.AskVolume5 = val;
			break;
		case 43:
			self.AveragePrice = val;
			break;
		default:
			System.err.println("Error!");
		}
	}
	
	private CThostFtdcDepthMarketDataField(){}
	
	public static CThostFtdcDepthMarketDataField getInstance(){
		if(self == null){
			self = new CThostFtdcDepthMarketDataField();
		}
		return self;
	}
	
	
	public SimpleArbitrage getSnapshot(){
		double price = self.LastPrice;
		Date date = null;
		try {
			date = DATE_FORMAT.parse(self.TradingDay + " " + self.UpdateTime);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		SimpleArbitrage simpleArb = new SimpleArbitrage(price,date);
		return simpleArb;
	}
//	public CThostFtdcDepthMarketDataField clone(){
//		CThostFtdcDepthMarketDataField tem = self.clone();
//		return tem;
//	}
}
