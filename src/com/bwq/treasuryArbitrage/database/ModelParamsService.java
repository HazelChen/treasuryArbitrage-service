package com.bwq.treasuryArbitrage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bwq.treasuryArbitrage.xyzCalculate.Lambda;
import com.bwq.treasuryArbitrage.xyzCalculate.OptimalKT;
import com.bwq.treasuryArbitrage.xyzCalculate.Xyz;


public class ModelParamsService {
	private Database database = new Database();
	private DateFormat formater = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
	private final String FXY = "fxyArbParams";
	private final String WXY = "wxyArbParams";
	private final String DJ = "djArbParams";
	
	public static void main(String[] args) {
		ModelParamsService ms = new ModelParamsService();
		ms.InsertFXYParams(1, 1, 2, 3);
		ms.getFXYParamsByNum(1);
		
		ms.InsertWXYParams(1, 2, 3);
		ms.getWXYParamsByNum(1);
		
		ms.InsertDJParams(1, 2, 3);
		ms.getDJParamsByNum(1);
		
	}
	
	public ModelParamsService(){}
	
	//�������FXY/WXY/DJ=============================================================================================
	public boolean InsertFXYParams(int group, double x, double y, double k){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+FXY+" (time,groupNum,x,y,k) values(?,?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, group);
			preparedStatement.setDouble(3, x);
			preparedStatement.setDouble(4, y);
			preparedStatement.setDouble(5, k);
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("插入成功");
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			result = false;
			System.err.println("插入失败");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		return result;
	}
	
	public boolean InsertWXYParams(int group, double optimalLambda1, double optimalLambda2){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+WXY+" (time,groupNum,optimalLambda1,optimalLambda2) values(?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, group);
			preparedStatement.setDouble(3, optimalLambda1);
			preparedStatement.setDouble(4, optimalLambda2);
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("插入成功");
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			result = false;
			System.err.println("插入失败");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		return result;
	}
	
	public boolean InsertDJParams(int group, double k, double t){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+DJ+" (time,groupNum,k,t) values(?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, group);
			preparedStatement.setDouble(3, k);
			preparedStatement.setDouble(4, t);
			preparedStatement.executeUpdate();
			result = true;
			System.out.println("插入成功");
		} catch (SQLException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			result = false;
			System.err.println("插入失败");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		return result;
	}
	
	//��ȡ����FXY/WXY/DJ=============================================================================================
	public Xyz getFXYParamsByNum(int group){
		double[] params = new double[3];
		Date time = null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select time,x,y,k from "+FXY+" where groupNum = "+group+" order by id desc limit 1";

		connection = database.getConnection();
		try {
			preparedStatement =connection.prepareStatement(sql);
			//System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				time = formater.parse(resultSet.getString(1));
				params[0] = resultSet.getDouble(2);
				params[1] = resultSet.getDouble(3);
				params[2] = resultSet.getDouble(4);
			}
		} catch (SQLException | ParseException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			System.err.println("没有找到记录");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		//return params;
		Xyz resultXyz = new Xyz(group,time,params[0],params[1],params[2]);
		return resultXyz;
	}
	
	public Lambda getWXYParamsByNum(int group){
		double[] params = new double[3];
		Date time = null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select time,optimalLambda1,optimalLambda2 from "+WXY+" where groupNum = "+group+" order by id desc limit 1";

		connection = database.getConnection();
		try {
			preparedStatement =connection.prepareStatement(sql);
			//System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				time = formater.parse(resultSet.getString(1));
				params[0] = resultSet.getDouble(2);
				params[1] = resultSet.getDouble(3);
			}
		} catch (SQLException | ParseException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			System.err.println("没有找到记录");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		//return params;
		Lambda resutlLambda = new Lambda(group, time, params[0], params[1]);
		return resutlLambda;
	}
	
	public OptimalKT getDJParamsByNum(int group){
		double[] params = new double[3];
		Date time = null;
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "select time,k,t from "+DJ+" where groupNum = "+group+" order by id desc limit 1";

		connection = database.getConnection();
		try {
			preparedStatement =connection.prepareStatement(sql);
			//System.out.println(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				time = formater.parse(resultSet.getString(1));
				params[0] = resultSet.getDouble(2);
				params[1] = resultSet.getDouble(3);
			}
		} catch (SQLException | ParseException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
			System.err.println("没有找到记录");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		//return params;
		OptimalKT resultKt = new OptimalKT(group, time, params[0], params[1]);
		return resultKt;
	}
}
