package com.bwq.treasuryArbitrage.modelsCalculation.calculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.bwq.treasuryArbitrage.common.ArbitrageCodes;
import com.bwq.treasuryArbitrage.database.DataFetchService;
import com.bwq.treasuryArbitrage.database.ModelCalculationService;
import com.bwq.treasuryArbitrage.modelsCalculation.MatlabCaller;
import com.bwq.treasuryArbitrage.modelsCalculation.SimpleArbitrage;
import com.bwq.treasuryArbitrage.modelsCalculation.model.Lambda;

public class LambdaCalculator implements Runnable {
	double buyprice, saleprice,TradeCost,stop_loss,stop_profit;
	Date now;
	int now_hour, now_min;
	boolean todayRun, sleepPF;
	MatlabCaller dm;
	Object[] result;
	ModelCalculationService ms;
	
	public LambdaCalculator(){
		buyprice = 0;
		saleprice = 0;
		TradeCost = 0;
		stop_loss = -1;
		stop_profit = 1;
		now = new Date();
		todayRun = false;// Arbitrage_Main run?
		sleepPF = true;
		dm = new MatlabCaller();
		ms=new ModelCalculationService();
		result = null;
	}
	
	public void run() {
			Calendar calendar = Calendar.getInstance();
			System.out.println("Construction Done...");
			while (true) {
				now = new Date();
				calendar.setTime(now);
				now_hour = calendar.get(Calendar.HOUR_OF_DAY);
				now_min = calendar.get(Calendar.MINUTE);
				if (now_hour == 0 && now_min < 2) {
					todayRun = false;
				}
				// 
				if (!todayRun && now_hour >= 0 && now_min >= 2) {
					todayRun = true;
					sleepPF = true;
					System.out.println("Thread run...");
					String p1 = null, p2 = null;
					for (int p = 1; p < 4; p++) // p������
					{
						String[] codes = ArbitrageCodes.getCodes();
						// 
						switch (p) {
						case 1:
							p1 = codes[0];
							p2 = codes[1];
							break;
						case 2:
							p1 = codes[0];
							p2 = codes[2];
							break;
						case 3:
							p1 = codes[1];
							p2 = codes[2];
							break;
						default:
							p1 = codes[0];
							p2 = codes[1];
							break;
						}

						ArrayList<SimpleArbitrage> lf1 = DataFetchService.getHistoryArbitrages(p1);// �������
						ArrayList<SimpleArbitrage> lf2 = DataFetchService.getHistoryArbitrages(p2);// �������
						// 
						for (int i = 0; i < lf1.size() - 1;) {
							if (lf1.get(i).getDate().getTime() / 60000 == lf1.get(i + 1).getDate()
									.getTime() / 60000) {
								lf1.remove(i);
							} else {
								i++;
							}
						}
						for (int i = 0; i < lf2.size() - 1;) {
							if (lf2.get(i).getDate().getTime() / 60000 == lf2.get(i + 1).getDate()
									.getTime() / 60000) {
								lf2.remove(i);
							} else {
								i++;
							}
						}
						// 
						int i1 = lf1.size() - 1, i2 = lf2.size() - 1, maxdt = 2;
						// Double lf2lp=lf2.get(i2).price,lf1lp=lf1.get(i1).price;
						Date dt1 = lf1.get(i1).getDate(), dt10 = lf1.get(0).getDate(), dt2 = lf2
								.get(i2).getDate(), dt20 = lf2.get(0).getDate(), nt = lf2
								.get(i2).getDate();
						if (dt1.getTime() > dt2.getTime()) {
							nt = dt1;
							maxdt = 1;
						} else {
						}
						long l = min(nt.getTime() / 1000 / 60 - dt10.getTime()
								/ 1000 / 60,
								nt.getTime() / 1000 / 60 - dt20.getTime() / 1000
										/ 60) + 1;
						System.out.println((dt10.getTime()) / 1000 / 60 + " "
								+ (dt20.getTime()) / 1000 / 60);
						System.out.println((dt1.getTime()) / 1000 / 60 + " "
								+ (dt2.getTime()) / 1000 / 60);
						// System.out.println((nt.getTime())/1000/60);
						System.out
								.println((dt1.getTime() / 1000 / 60 - dt10
										.getTime() / 1000 / 60)
										+ " "
										+ (dt2.getTime() / 1000 / 60 - dt20
												.getTime() / 1000 / 60));
						System.out.println("Data Fix begin...l:" + l + "; lf1.size"
								+ lf1.size() + "; lf2.size" + lf2.size());
						// System.out.println("i1:" + i1 + "; i2:"+i2);
						// -----------------Debugging----OK!!!
						System.out.println("nt:" + nt.getTime() / 60000);
						long st = nt.getTime() - 60000 * l, tmpt;
						while (nt.getTime() / 60000 >= st / 60000) {
							// if(maxdt==1){i1--;}
							// else if(maxdt==2){i2--;}
							if (i1 >= 0
									&& lf1.get(i1).getDate().getTime() / 60000 < nt
											.getTime() / 60000) {
								tmpt = lf1.get(i1).getDate().getTime();
								// if(i1+2<lf1.size())System.out.println(" l("+i1+"):"+lf1.get(i1).date.getTime()/60000
								// +" l("+(i1+1)+"):"+lf1.get(i1+1).date.getTime()/60000
								// +" l("+(i1+2)+"):"+lf1.get(i1+2).date.getTime()/60000);
								lf1.add(i1, lf1.get(i1));
								// System.out.println("insert i:"+i1 +
								// " nt:"+nt.getTime()/60000
								// +" l("+i1+"):"+lf1.get(i1).date.getTime()/60000
								// +" l("+(i1+1)+"):"+lf1.get(i1+1).date.getTime()/60000);
								SimpleArbitrage tmp = new SimpleArbitrage(
										lf1.get(i1).getPrice(), lf1.get(i1).getDate());
								tmp.getDate().setTime(nt.getTime());
								lf1.set(i1 + 1, tmp);
								SimpleArbitrage tmp2 = new SimpleArbitrage(
										lf1.get(i1).getPrice(), lf1.get(i1).getDate());
								Date tt = new Date();
								tt.setTime(tmpt);
								tmp2.setDate(tt);
								lf1.set(i1, tmp2);
								// System.out.println("after insert fix i:"+i1
								// +" l("+i1+"):"+lf1.get(i1).date.getTime()/60000
								// +" l("+(i1+1)+"):"+lf1.get(i1+1).date.getTime()/60000);
							} else if (i1 > 0) // if(lf1.get(i1).date.getTime()/60000>=nt.getTime()/60000)
							{
								i1--;
							}
							if (i2 >= 0
									&& lf2.get(i2).getDate().getTime() / 60000 < nt
											.getTime() / 60000) {
								tmpt = lf2.get(i2).getDate().getTime();
								// if(i2+2<lf2.size())System.out.println(" l("+i2+"):"+lf2.get(i2).date.getTime()/60000
								// +" l("+(i2+1)+"):"+lf2.get(i2+1).date.getTime()/60000
								// +" l("+(i2+2)+"):"+lf2.get(i2+2).date.getTime()/60000);
								lf2.add(i2, lf2.get(i2));
								SimpleArbitrage tmp20 = new SimpleArbitrage(
										lf2.get(i2).getPrice(), lf2.get(i2).getDate());
								tmp20.getDate().setTime(nt.getTime());
								lf2.set(i2 + 1, tmp20);
								SimpleArbitrage tmp22 = new SimpleArbitrage(
										lf2.get(i2).getPrice(), lf2.get(i2).getDate());
								Date tt2 = new Date();
								tt2.setTime(tmpt);
								tmp22.setDate(tt2);
								lf2.set(i2, tmp22);
								// System.out.println("after insert fix i:"+i2
								// +" l("+i2+"):"+lf2.get(i2).date.getTime()/60000
								// +" l("+(i2+1)+"):"+lf2.get(i2+1).date.getTime()/60000);
							} else if (i2 > 0) // if(lf2.get(i2).date.getTime()/60000>=nt.getTime()/60000)
							{
								i2--;
							}
							// 
							nt.setTime(nt.getTime() - 60 * 1000);
							// System.out.println("i1:"+i1+" i2:"+i2+" nt:"+nt.getTime()/60000);
						}
						// fix bug
						if (maxdt == 2) {
							SimpleArbitrage ftmp22 = new SimpleArbitrage(
									lf2.get(lf2.size() - 1).getPrice(), dt2);
							Date ftt2 = new Date();
							ftt2.setTime(st + (l) * 60000);
							ftmp22.setDate(ftt2);
							lf2.set(lf2.size() - 1, ftmp22);
							System.out.println(dt2.getTime() / 60000);
							System.out.println(ftmp22.getDate().getTime() / 60000);
						} else if (maxdt == 1) {
							SimpleArbitrage ftmp11 = new SimpleArbitrage(
									lf1.get(lf1.size() - 1).getPrice(), dt1);
							Date ftt1 = new Date();
							ftt1.setTime(st + (l) * 60000);
							ftmp11.setDate(ftt1);
							lf1.set(lf1.size() - 1, ftmp11);
						}
						System.out.println(" nt:" + nt.getTime() / 60000 + " st:"
								+ st / 60000);
						System.out.println("l1s:" + lf1.size() + " l2s:"
								+ lf2.size());
						System.out.println(lf1.get(lf1.size() - 1).getDate().getTime()
								/ 60000 + " "
								+ lf2.get(lf2.size() - 1).getDate().getTime() / 60000);
						System.out.println(lf1.get(0).getDate().getTime() / 60000 + " "
								+ lf2.get(0).getDate().getTime() / 60000);
						System.out.println(lf1.get(lf1.size() - 1).getDate().getTime()
								/ 60000 - lf1.get(0).getDate().getTime() / 60000);
						System.out.println(lf2.get(lf2.size() - 1).getDate().getTime()
								/ 60000 - lf2.get(0).getDate().getTime() / 60000);
						// 
						if (lf1.size() < lf2.size()) {
							for (int j = 0; j < lf2.size() - lf1.size();) {
								lf2.remove(j);
							}
						} else if (lf1.size() > lf2.size()) {
							for (int j = 0; j < lf1.size() - lf2.size();) {
								lf1.remove(j);
							}
						}
						if (lf1.size() != lf2.size()) {
							System.out.println("Data Fix problem...lf1.size"
									+ lf1.size() + "; lf2.size" + lf2.size());
						} else {
							System.out.println("Data Ready...");
							System.out.println("Data Count:" + lf1.size() + " "
									+ lf2.size());
							// get TradeCost
							
							ArrayList<Double> plf1 = new ArrayList<Double>(), plf2 = new ArrayList<Double>();
							for (int i = 0; i < lf1.size(); i++) {
								plf1.add(lf1.get(i).getPrice());
								plf2.add(lf2.get(i).getPrice());
							}
							// 
							System.out.println("Data " + p + " Calculate...");
							result = dm.Arbitrage_Main1(plf1, plf2, stop_loss,
									stop_profit, TradeCost);
							System.out.println("Calculate Done...");
							// result = dm.Open(lf1, lf2, 91, 92,0,0,93); test
							if (result != null) {
								double l1,l2;
								l1 = Double.valueOf(String.valueOf(result[0]));
								l2 = Double.valueOf(String.valueOf(result[1]));	
								//insert into sql
								Lambda newLambda = new Lambda(p, l1, l2);
								ms.InsertWXYParams(newLambda);
							} else {
								// todayRun=false;
								System.out
										.println("Result is empty.Thread will sleep...");
							}
						}

					}// for()

					/*
					 * File parafile =new File("para_xyk"); if(!parafile.exists()){
					 * try { parafile.createNewFile(); } catch (IOException e) {
					 * e.printStackTrace(); } } FileWriter fileWritter; try {
					 * fileWritter = new FileWriter(parafile.getName());
					 * BufferedWriter bufferWritter = new
					 * BufferedWriter(fileWritter); bufferWritter.write(x + "\t" +
					 * y+ "\t" + k); bufferWritter.close(); } catch (IOException e)
					 * { e.printStackTrace(); }
					 */
				} else {// 
					try {
						if (sleepPF) {
							System.out.println(now_hour + ":" + now_min
									+ "THread sleeping...");
							sleepPF = false;
						}
						Thread.sleep(60 * 1000);// 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}// end of if()
			}// end of while(true)
		}

		private long min(long a, long b) {
			if (a <= b)
				return a;
			else
				return b;
		}

		public double getBuyprice() {
			return buyprice;
		}

		public double getSaleprice() {
			return saleprice;
		}
}
