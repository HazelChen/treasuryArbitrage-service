package test.database;

import com.bwq.treasuryArbitrage.database.ModelCalculationService;
import com.bwq.treasuryArbitrage.modelsCalculation.model.Lambda;
import com.bwq.treasuryArbitrage.modelsCalculation.model.OptimalKT;
import com.bwq.treasuryArbitrage.modelsCalculation.model.Xyz;

public class TestDatabaseUtil {
	
	public static void main(String[] args) {
		ModelCalculationService ms = new ModelCalculationService();
		ms.InsertFXYParams(new Xyz(1, 1, 2, 3));
		ms.getFXYParamsByNum(1);

		ms.InsertWXYParams(new Lambda(1, 2, 3));
		ms.getWXYParamsByNum(1);

		ms.InsertDJParams(new OptimalKT(1, 2, 3));
		ms.getDJParamsByNum(1);
	}

}
