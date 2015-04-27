package test.database;

import com.bwq.treasuryArbitrage.database.DatabaseUtil;
import com.bwq.treasuryArbitrage.xyzCalculate.Xyz;

public class TestDatabaseUtil {
	
	public static void main(String[] args) {
		Xyz xyz = new Xyz(1, 2.0, 3.0, 4.0);
		DatabaseUtil.insert(xyz);
	}

}
