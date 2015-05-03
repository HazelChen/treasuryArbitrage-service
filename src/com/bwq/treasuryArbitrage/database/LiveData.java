package com.bwq.treasuryArbitrage.database;

import java.util.ArrayList;

import com.bwq.treasuryArbitrage.common.ArbitrageCodes;
import com.bwq.treasuryArbitrage.modelsCalculation.SimpleArbitrage;

public class LiveData {

	private ArrayList<SimpleArbitrage> simpleArbs= new ArrayList<SimpleArbitrage>();
	private String[] codes = ArbitrageCodes.getCodes();
	private static LiveData self = new LiveData();
	
	private LiveData(){}
	
	public static LiveData getInstance(){
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
        simpleArbs.set(index, simpleArb);
	}
}
