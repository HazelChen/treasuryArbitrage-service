package com.bwq.treasuryArbitrage.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bwq.treasuryArbitrage.modelsCalculation.model.Lambda;
import com.bwq.treasuryArbitrage.modelsCalculation.model.OptimalKT;
import com.bwq.treasuryArbitrage.modelsCalculation.model.Xyz;


public class ModelCalculationService {
	private Database database = new Database();
	private DateFormat formater = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
	private final String FXY = "fxyArbParams";
	private final String WXY = "wxyArbParams";
	private final String DJ = "djArbParams";
	
	public ModelCalculationService(){}
	
	//�������FXY/WXY/DJ=============================================================================================
	public boolean InsertFXYParams(Xyz xyz){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+FXY+" (time,groupNum,x,y,k) values(?,?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, xyz.getGroup());
			preparedStatement.setDouble(3, xyz.getX());
			preparedStatement.setDouble(4, xyz.getY());
			preparedStatement.setDouble(5, xyz.getZ());
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
	
	public boolean InsertWXYParams(Lambda lambda){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+WXY+" (time,groupNum,optimalLambda1,optimalLambda2) values(?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, lambda.getGroup());
			preparedStatement.setDouble(3, lambda.getLambda1());
			preparedStatement.setDouble(4, lambda.getLambda2());
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
	
	public boolean InsertDJParams(OptimalKT optimalKT){
		boolean result = false;
		
		Connection connection=database.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet =null;
		String sql = "Insert into "+DJ+" (time,groupNum,k,t) values(?,?,?,?)";
		String time = formater.format(new Date());
		
		try {
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, time);
			preparedStatement.setInt(2, optimalKT.getGroup());
			preparedStatement.setDouble(3, optimalKT.getOptimalK());
			preparedStatement.setDouble(4, optimalKT.getOptimalT());
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
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("没有找到记录");
		} catch (ParseException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("没有找到记录");
		} catch (ParseException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("没有找到记录");
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("没有找到记录");
		}
		database.terminate(resultSet,preparedStatement,connection);
		
		//return params;
		OptimalKT resultKt = new OptimalKT(group, time, params[0], params[1]);
		return resultKt;
	}
}
