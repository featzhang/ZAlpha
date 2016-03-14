package algorithm.mogls;

public class MOGLS_Main {
	public static void main(String[] args) {

		MOGLS mogls = new MOGLS();
		mogls.setMaxGeneration(5);
		mogls.setCurrentSetNum(20);
		mogls.setTemporaryElitePopulationNum(5);
		mogls.init();
		mogls.evoluation();
	}
}