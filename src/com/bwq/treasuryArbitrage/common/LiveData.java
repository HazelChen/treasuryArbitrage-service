package com.bwq.treasuryArbitrage.common;

import java.util.ArrayList;
import java.util.Date;

import com.bwq.treasuryArbitrage.modelsCalculation.SimpleArbitrage;

public class LiveData {

	private ArrayList<SimpleArbitrage> simpleArbs= new ArrayList<SimpleArbitrage>();
	private String[] codes = ArbitrageCodes.getCodes();
	private static LiveData self = new LiveData();
	
	private LiveData(){
		for(int i=0; i<codes.length; i++){
			simpleArbs.add(new SimpleArbitrage(0.0, new Date()));
		}
	}
	
	public synchronized static LiveData getInstance(){
		return self;
	}
	
	public SimpleArbitrage getData(String code){
		int index = 0;
		for(; index<codes.length; index++){
			if(codes[index].equals(code)){
				break;
			}
		}
		return simpleArbs.get(index);
	}
	
	public void register(String code,SimpleArbitrage simpleArb){
		int index = 0;
        for (; index < codes.length; index++) {
            if (codes[index].equals(code)) {
                break;
            }
        }
        System.out.println(simpleArb.getPrice());
        simpleArbs.set(index, simpleArb);
	}
}
