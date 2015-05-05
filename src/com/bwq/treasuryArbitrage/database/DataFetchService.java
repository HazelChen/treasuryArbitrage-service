package com.bwq.treasuryArbitrage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bwq.treasuryArbitrage.dataFetch.CThostFtdcDepthMarketDataField;
import com.bwq.treasuryArbitrage.modelsCalculation.SimpleArbitrage;

public class DataFetchService {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd HH:mm:ss");

	private static Database database = new Database();

	public static void insert(CThostFtdcDepthMarketDataField data) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sqlCreate = "create table if not exists " + data.InstrumentID
				+ "(" + "TradingDay varchar(20)," + "InstrumentID varchar(20),"
				+ "ExchangeID varchar(20)," + "ExchangeInstID varchar(20),"
				+ "LastPrice double," + "PreSettlementPrice double,"
				+ "PreClosePrice double," + "PreOpenInterest double,"
				+ "OpenPrice double," + "HighestPrice double,"
				+ "LowestPrice double," + "Volume double," + "Turnover double,"
				+ "OpenInterest double," + "ClosePrice double,"
				+ "SettlementPrice double," + "UpperLimitPrice double,"
				+ "LowerLimitPrice double," + "PreDelta double,"
				+ "CurrDelta double," + "UpdateTime varchar(20),"
				+ "UpdateMillisec double," + "BidPrice1 double,"
				+ "BidVolume1 double," + "AskPrice1 double,"
				+ "AskVolume1 double," + "BidPrice2 double,"
				+ "BidVolume2 double," + "AskPrice2 double,"
				+ "AskVolume2 double," + "BidPrice3 double,"
				+ "BidVolume3 double," + "AskPrice3 double,"
				+ "AskVolume3 double," + "BidPrice4 double,"
				+ "BidVolume4 double," + "AskPrice4 double,"
				+ "AskVolume4 double," + "BidPrice5 double,"
				+ "BidVolume5 double," + "AskPrice5 double,"
				+ "AskVolume5 double," + "AveragePrice double" + ");";

		String sqlInsert = "Insert into "
				+ data.InstrumentID
				+ "("
				+ "TradingDay ,"
				+ "InstrumentID ,"
				+ "ExchangeID ,"
				+ "ExchangeInstID ,"
				+ "LastPrice ,"
				+ "PreSettlementPrice ,"
				+ "PreClosePrice ,"
				+ "PreOpenInterest ,"
				+ "OpenPrice ,"
				+ "HighestPrice ,"
				+ "LowestPrice ,"
				+ "Volume ,"
				+ "Turnover ,"
				+ "OpenInterest ,"
				+ "ClosePrice ,"
				+ "SettlementPrice ,"
				+ "UpperLimitPrice ,"
				+ "LowerLimitPrice ,"
				+ "PreDelta ,"
				+ "CurrDelta ,"
				+ "UpdateTime ,"
				+ "UpdateMillisec ,"
				+ "BidPrice1 ,"
				+ "BidVolume1 ,"
				+ "AskPrice1 ,"
				+ "AskVolume1 ,"
				+ "BidPrice2 ,"
				+ "BidVolume2 ,"
				+ "AskPrice2 ,"
				+ "AskVolume2 ,"
				+ "BidPrice3 ,"
				+ "BidVolume3 ,"
				+ "AskPrice3 ,"
				+ "AskVolume3 ,"
				+ "BidPrice4 ,"
				+ "BidVolume4 ,"
				+ "AskPrice4 ,"
				+ "AskVolume4 ,"
				+ "BidPrice5 ,"
				+ "BidVolume5 ,"
				+ "AskPrice5 ,"
				+ "AskVolume5 ,"
				+ "AveragePrice )"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		connection = database.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sqlCreate);
			preparedStatement.execute();

			preparedStatement = connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, data.TradingDay);
			preparedStatement.setString(2, data.InstrumentID);
			preparedStatement.setString(3, data.ExchangeID + "");
			preparedStatement.setString(4, data.ExchangeInstID + "");
			preparedStatement.setDouble(5, data.LastPrice);
			preparedStatement.setDouble(6, data.PreSettlementPrice);
			preparedStatement.setDouble(7, data.PreClosePrice);
			preparedStatement.setDouble(8, data.PreOpenInterest);
			preparedStatement.setDouble(9, data.OpenPrice);
			preparedStatement.setDouble(10, data.HighestPrice);
			preparedStatement.setDouble(11, data.LowestPrice);
			preparedStatement.setDouble(12, data.Volume);
			preparedStatement.setDouble(13, data.Turnover);
			preparedStatement.setDouble(14, data.OpenInterest);
			preparedStatement.setDouble(15, data.ClosePrice);
			preparedStatement.setDouble(16, data.SettlementPrice);
			preparedStatement.setDouble(17, data.UpperLimitPrice);
			preparedStatement.setDouble(18, data.LowerLimitPrice);
			preparedStatement.setDouble(19, data.PreDelta);
			preparedStatement.setDouble(20, data.CurrDelta);
			preparedStatement.setString(21, data.UpdateTime);
			preparedStatement.setDouble(22, data.UpdateMillisec);
			preparedStatement.setDouble(23, data.BidPrice1);
			preparedStatement.setDouble(24, data.BidVolume1);
			preparedStatement.setDouble(25, data.AskPrice1);
			preparedStatement.setDouble(26, data.AskVolume1);
			preparedStatement.setDouble(27, data.BidPrice2);
			preparedStatement.setDouble(28, data.BidVolume2);
			preparedStatement.setDouble(29, data.AskPrice2);
			preparedStatement.setDouble(30, data.AskVolume2);
			preparedStatement.setDouble(31, data.BidPrice3);
			preparedStatement.setDouble(32, data.BidVolume3);
			preparedStatement.setDouble(33, data.AskPrice3);
			preparedStatement.setDouble(34, data.AskVolume3);
			preparedStatement.setDouble(35, data.BidPrice4);
			preparedStatement.setDouble(36, data.BidVolume4);
			preparedStatement.setDouble(37, data.AskPrice4);
			preparedStatement.setDouble(38, data.AskVolume4);
			preparedStatement.setDouble(39, data.BidPrice5);
			preparedStatement.setDouble(40, data.BidVolume5);
			preparedStatement.setDouble(41, data.AskPrice5);
			preparedStatement.setDouble(42, data.AskVolume5);
			preparedStatement.setDouble(43, data.AveragePrice);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		database.terminate(resultSet, preparedStatement, connection);
	}

	public static ArrayList<SimpleArbitrage> getHistoryArbitrages(String code) {
		ArrayList<SimpleArbitrage> result = new ArrayList<SimpleArbitrage>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select LastPrice,TradingDay,UpdateTime from " + code;

		connection = database.getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				double price = resultSet.getDouble(1);
				String day = resultSet.getString(2);
				String time = resultSet.getString(3);
				Date date = DATE_FORMAT.parse(day + " " + time);
				SimpleArbitrage tem = new SimpleArbitrage(price, date);
				result.add(tem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("没有找到记录");
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("格式转换有误");
		}
		database.terminate(resultSet, preparedStatement, connection);
		return result;
	}
}
