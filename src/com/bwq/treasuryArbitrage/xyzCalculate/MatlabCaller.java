package com.bwq.treasuryArbitrage.xyzCalculate;

import java.util.ArrayList;

import Arbitrage_Main.*;

import com.mathworks.toolbox.javabuilder.*;
//����java�Դ�Matlab��ذ�
//�������Ա�Լ���matlab�����jar��

public class MatlabCaller {
	ArrayList<Double> Lmarket_condition = null;
	public double opt_x, opt_y, opt_k;
	ArrayList<Double> Llambda = null;
	Arbitrage_Main m;

	public MatlabCaller() {
		try {
			m = new Arbitrage_Main();
		} catch (MWException e3) {

			e3.printStackTrace();
		}

	}

	/*
	 * resultΪ���ض������飬��Ԫ�طֱ�Ϊx,y,k
	 */
	public Object[] Arbitrage_Main(ArrayList<Double> Lf1,
			ArrayList<Double> Lf2, double stop_loss, double stop_profit) {
		Object[] result = null;
		MWNumericArray f1 = null, f2 = null;
		if (Lf1.size() != Lf2.size()) {
			System.out.println("History data size problem");
			return result;
		}
		int[] dims1 = { Lf1.size(), 1 };
		f1 = MWNumericArray.newInstance(dims1, MWClassID.DOUBLE,
				MWComplexity.REAL);
		for (int i = 1; i <= Lf1.size(); i++) {
			f1.set(i, Double.valueOf(Lf1.get(i - 1)));
		}
		int[] dims2 = { Lf2.size(), 1 };
		f2 = MWNumericArray.newInstance(dims2, MWClassID.DOUBLE,
				MWComplexity.REAL);
		for (int i = 1; i <= Lf2.size(); i++) {
			f2.set(i, Double.valueOf(Lf2.get(i - 1)));
		}
		try {
			// Arbitrage_Main��������ʾ��
			// ��һ���������ֱ�ʾ��Ҫ��õĽ�������������3����ʾ���������x,y,k;���д2,��ֻ�ܵõ�x,y;
			// ��ͬ
			result = m.Arbitrage_Main(3, f1, f2, stop_loss, stop_profit);
			// optimization_x,optimization_y,optimization_k
			// double x,y,k;
			// x = Double.valueOf(String.valueOf(result[0]));
			// y = Double.valueOf(String.valueOf(result[1]));
			// k = Double.valueOf(String.valueOf(result[2]));
			// File parafile =new File("para_xyk");
			// if(!parafile.exists()){
			// try {
			// parafile.createNewFile();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
			// FileWriter fileWriter;
			// try {
			// fileWriter = new FileWriter(parafile.getName());
			// BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			// bufferWriter.write(x + "\t" + y+ "\t" + k);
			// bufferWriter.close();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		} catch (Exception e) {
			System.out.println("Arbitrage_MAin Exception catched!");
			System.out.println(e.toString());
		}
		MWArray.disposeArray(f1);
		MWArray.disposeArray(f2);
		return result;
	}

}
