package com.iisc.simulator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.iisc.simulator.fpunit.FPUnit;
import com.iisc.simulator.fpunit.FingerPrintUtil;

public class Main {

	private static final int CACHE_SIZE = 512;
	public static Integer compareOperations = 0;
	
	private CPUClock clock;
	private InstructionSet instructionSet;
	private MultivalueMemory multivalueMemory;
	private Cache<String, Block> cache; //Fully Associative cache (with LRU replacement Algo)
	private Loader loader;

	private FPUnit fpUnit = new FPUnit();
	// GUI
	public SimulatorForm simulatorUI = new SimulatorForm();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			Main main = new Main();
			main.initialize();
			
			main.executeInstructions();

			

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
					JOptionPane.PLAIN_MESSAGE);
		}

	}

	private void executeInstructions() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Instructions -
		//1 - Load R1 0
		Integer address = 0;
		double[] r1 = loader.loadTemplate(address, "R1");
		simulatorUI.nextInstruction();
		
		updateUI();
		
		
		//2 - Load R2 01140
		address = 0;
		double[] r2 = loader.loadTemplate(address, "R2");
		simulatorUI.nextInstruction();
		
		updateUI();
		
		compareOperations = 0;		
		//Compare
		Integer ret = fpUnit.compare();
		simulatorUI.nextInstruction();

		updateUI();
		
	}

	private void updateUI() {
		
		//Update Register1
		if (fpUnit.getRegisters1() != null) {
			double[] r1 = fpUnit.getRegisters1().getReisterContent();
			BufferedImage image = this.getFingerPrintImageDetail(r1);
			simulatorUI.updateR1Image(image);
			simulatorUI.updateR1Value(ConvertFingerPrintTemplateDoubleToString(r1));
		}
		
		//Update Register2
		if (fpUnit.getRegisters2() != null) {
			double[] r1 = fpUnit.getRegisters2().getReisterContent();
			BufferedImage image = this.getFingerPrintImageDetail(r1);
			simulatorUI.updateR2Image(image);
			simulatorUI.updateR2Value(ConvertFingerPrintTemplateDoubleToString(r1));
		}
		//Update cache contents
		simulatorUI.resetCache();
		for(Map.Entry<String, Block> entry : cache.getCacheMap().entrySet()) {
			simulatorUI.addCacheValue(entry.getKey(), entry.getValue().toString());
		}
		
		//Update Status field
		simulatorUI.updateStatusValue(fpUnit.getStatus().toString());
		
		//Update Statistics
		simulatorUI.resetStatistics();
		simulatorUI.addStatisticsValue("Cache", "");
		simulatorUI.addStatisticsValue("Entries", new Integer(cache.getCacheMap().size()).toString());
		simulatorUI.addStatisticsValue("Hits", cache.getHits().toString());
		simulatorUI.addStatisticsValue("Misses", cache.getMisses().toString());
		simulatorUI.addStatisticsValue("Block Replacements", cache.getReplacements().toString());
		
		simulatorUI.addStatisticsValue("FP Unit", "");
		simulatorUI.addStatisticsValue("R1 - No of Operations", fpUnit.getRegisters1().getWordUsageCount().toString());
		simulatorUI.addStatisticsValue("R2 - No of Operations", fpUnit.getRegisters1().getWordUsageCount().toString());
		simulatorUI.addStatisticsValue("Compare Operations", compareOperations.toString());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}

	private void initialize() {

		initializeMultivalueMemory();
		initializeCache();
		loadInstructions();
		initializeRegisters();
		loader = new Loader(cache);
		loader.setFpUnit(fpUnit);
		


	}

	private void initializeRegisters() {
		
//		BufferedImage image = this
//				.getFingerPrintImageDetail(this.multivalueMemory.getValues()[0].value);
//		simulatorUI.updateR1Image(image);
//		simulatorUI.updateR1Value(ConvertFingerPrintTemplateDoubleToString(this.multivalueMemory.getValues()[0].value));
//	
//		image = this
//				.getFingerPrintImageDetail(this.multivalueMemory.getValues()[1].value);
//		simulatorUI.updateR2Image(image);
//		simulatorUI.updateR2Value(ConvertFingerPrintTemplateDoubleToString(this.multivalueMemory.getValues()[1].value));
		
	}

	private void loadInstructions() {
		simulatorUI.setVisible(true);
		
		List<String> instList = new ArrayList<String>();
		instList.add("Load R1 0000");
		instList.add("Load R2 1001");
		instList.add("Compare");
		instList.add("Extract c:\\image\\sample2.bmp");
		instList.add("Store R1 2001");

		instructionSet = new InstructionSet(instList);
		
		//Display in UI
		for (String inst : instList) {
			simulatorUI.addInstruction(inst);
		}
		
	}
	
	private void initializeMultivalueMemory() {
		

		// Load FingerPrint Template features
		double[] fp1 = ConvertFingerPrintTemplateStringToDouble("331.0;208.0;109.0;0.0;0.0;0.0;0.0;-162.0;-103.0;191.9713520294109;3.7079223505592465;1.0;270.0;-4.0;-99.0;99.08077512817509;4.67200690482019;1.0;180.0;-11.0;-93.0;93.64827814754524;4.594656405580596;1.0;0.0;29.0;-73.0;78.54934754662193;5.090531295707291;1.0;90.0;-22.0;-55.0;59.23681287847955;4.331882603272325;3.0;0.0;82.0;-52.0;97.09788875150684;5.718035867172826;3.0;0.0;-70.0;-50.0;86.02325267042627;3.7618421395726145;3.0;0.0;35.0;-47.0;58.60034129593445;5.352477133146436;1.0;180.0;27.0;-44.0;51.62363799656123;5.26277492806148;1.0;0.0;-121.0;-39.0;127.12985487288185;3.45339328759363;1.0;270.0;-18.0;-16.0;24.08318915758459;3.8682349942715186;1.0;0.0;44.0;-14.0;46.17358552246078;5.97513252615581;1.0;180.0;71.0;-10.0;71.7007670809734;6.1432606366054685;1.0;0.0;-7.0;-9.0;11.40175425099138;4.051345811534003;3.0;0.0;-43.0;3.0;43.104524124504614;-3.211247227318361;1.0;0.0;9.0;5.0;10.295630140987;0.507098504392337;1.0;0.0;-108.0;21.0;110.02272492535349;-3.333640670830523;3.0;0.0;-21.0;30.0;36.61966684720111;-4.101663015995481;1.0;180.0;-29.0;40.0;49.4064773081425;-4.085080788108927;3.0;0.0;-150.0;42.0;155.76905982896602;-3.4146013566765037;3.0;0.0;-13.0;53.0;54.57105459856901;-4.471854732438786;1.0;90.0;92.0;54.0;106.6770828247567;0.5307734972029069;3.0;0.0;81.0;61.0;101.40019723846694;0.6454734928233306;1.0;270.0;7.0;62.0;62.39390995922599;1.4583691959647065;3.0;0.0;-167.0;63.0;178.48809484108457;-3.502330534437468;1.0;270.0;-7.0;68.0;68.3593446428504;-4.609809127966395;3.0;0.0;38.0;84.0;92.19544457292888;1.1459641638755542;1.0;180.0;-166.0;93.0;190.2761151589973;-3.6522643944333453;1.0;0.0;47.0;104.0;114.12712210513327;1.1463443197211451;1.0;180.0;-77.0;112.0;135.9154148726332;-4.110101634249726;1.0;0.0;62.0;115.0;130.64838307457157;1.0763365546039732;1.0;270.0;-142.0;117.0;183.99184764548673;-3.8307638335074428;1.0;180.0;-29.0;118.0;121.51131634543344;-4.471402312020249;1.0;0.0;-165.0;127.0;208.21623375712088;-3.797581105591627;3.0;0.0;55.0;130.0;141.15594213493105;1.1705556697609225;1.0;0.0;62.0;130.0;144.02777509911067;1.1257801020393552;1.0;180.0;-142.0;131.0;193.19679086361657;-3.886719561118642;1.0;0.0;-71.0;135.0;152.5319638633162;-4.228216394849627;1.0;0.0;7.0;141.0;141.1736519326464;1.5211916629294417;3.0;0.0;-154.0;148.0;213.5883891975404;-3.9071258807360767;3.0;0.0;-59.0;149.0;160.25604512778918;-4.335358834924473;3.0;0.0;64.0;155.0;167.69317219254933;1.1792162052337265;1.0;0.0;-64.0;161.0;173.25414857947845;-4.334026222711335;1.0;0.0;-64.0;161.0;173.25414857947845;-4.334026222711335;3.0;0.0;98.0;162.0;189.33568073662187;1.0267536539728521;3.0;0.0;-138.0;172.0;220.51757299589528;-4.036231608513485;1.0;0.0;98.0;172.0;197.9595918363139;1.0529033447245055;3.0;0.0;94.0;181.0;203.9534260560484;1.0917990462391378;3.0;0.0;-150.0;198.0;248.4028985338134;-4.064056991297429;3.0;0.0;-75.0;199.0;212.66405432042342;-4.351967238416835;1.0;0.0;86.0;201.0;218.62525014279572;1.1665051410381975;3.0;0.0;-2.0;203.0;203.0098519776811;-4.702537082389688;1.0;0.0;-2.0;218.0;218.00917411888886;-4.70321492583958;1.0;0.0;-42.0;229.0;232.81967270830015;-4.530998793365375;1.0;180.0;55.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;");
		double[] fp2 = ConvertFingerPrintTemplateStringToDouble("313.0;244.0;145.0;0.0;0.0;0.0;0.0;-143.0;-138.0;198.72845795205075;3.9091991002130184;1.0;270.0;-30.0;-131.0;134.39121995130486;4.4872633042217664;3.0;0.0;7.0;-123.0;123.1990259701756;4.769238227717527;1.0;180.0;-21.0;-113.0;114.93476410555685;4.528644449645567;1.0;180.0;-190.0;-110.0;219.5449840010015;3.6663884252399006;1.0;180.0;-51.0;-110.0;121.24768039018313;4.2782530959353275;1.0;0.0;-32.0;-104.0;108.81176406988355;4.41389004879851;1.0;180.0;-44.0;-98.0;107.42439201596628;4.290383950482472;1.0;0.0;-200.0;-95.0;221.41589825484527;3.5850409900095315;3.0;0.0;-26.0;-70.0;74.67261881037788;4.35675309608393;1.0;180.0;-47.0;-56.0;73.10950690573696;4.014148091541525;3.0;0.0;8.0;-56.0;56.568542494923804;4.854286034988854;1.0;180.0;-1.0;-54.0;54.00925846556311;4.693872578316681;1.0;0.0;-93.0;-50.0;105.58882516630251;3.6348925857788723;3.0;0.0;-146.0;-37.0;150.6154042586614;3.3897919174978957;1.0;0.0;-212.0;-24.0;213.3541656495134;3.2543202651801706;1.0;270.0;21.0;-21.0;29.698484809834994;5.497787143782138;1.0;180.0;-32.0;-14.0;34.92849839314596;3.5540030951871806;1.0;0.0;-28.0;-11.0;30.083217912982647;3.515926269597377;3.0;0.0;-63.0;3.0;63.071388124885914;-3.1891757568667765;1.0;0.0;-7.0;5.0;8.602325267042627;-3.7618421395726145;1.0;90.0;-127.0;14.0;127.76932339180638;-3.2513855707438157;1.0;270.0;-201.0;22.0;202.20039564748632;-3.250611427294825;1.0;90.0;-39.0;30.0;49.20365840057018;-3.797288279831329;1.0;180.0;-47.0;39.0;61.07372593840988;-3.834234496799098;3.0;0.0;-170.0;49.0;176.92088627406318;-3.4222215009294383;3.0;0.0;-10.0;57.0;57.87054518492115;-4.538717782043116;3.0;0.0;-23.0;66.0;69.8927750200262;-4.377064599069678;3.0;0.0;-186.0;70.0;198.73600579663466;-3.501541179617199;1.0;270.0;26.0;79.0;83.16850365372699;1.2528480354436051;1.0;180.0;34.0;96.0;101.84301645179212;1.2304143881529896;1.0;180.0;-181.0;99.0;206.30559856678636;-3.6420999163349697;1.0;0.0;50.0;105.0;116.29703349613007;1.1263771168937977;1.0;180.0;-87.0;112.0;141.82030884185804;-4.05196424064502;1.0;0.0;-40.0;116.0;122.70289320142373;-4.380329512841804;1.0;0.0;47.0;122.0;130.74020039758238;1.2030733442125485;1.0;0.0;-156.0;123.0;198.65799757371965;-3.809258242183148;1.0;180.0;-227.0;129.0;261.0938528575501;-3.6583634753913565;3.0;0.0;-179.0;135.0;224.2008028531566;-3.787769937974586;3.0;0.0;-81.0;136.0;158.2940302096071;-4.175219739524513;1.0;0.0;-5.0;137.0;137.09121051329294;-4.67590882126174;3.0;0.0;-155.0;138.0;207.53072061745462;-3.8690353142286984;1.0;0.0;-155.0;138.0;207.53072061745462;-3.8690353142286984;3.0;0.0;60.0;147.0;158.7734234687909;1.1832725210146178;1.0;0.0;-69.0;149.0;164.20109622045769;-4.278705152763241;3.0;0.0;53.0;155.0;163.8108665504215;1.241323918156993;3.0;0.0;-163.0;158.0;227.00881040171106;-3.911415752535299;1.0;0.0;-72.0;160.0;175.45369759569047;-4.289535054251749;1.0;0.0;-108.0;166.0;198.04039991880444;-4.135590389793399;1.0;0.0;-149.0;179.0;232.89911979223967;-4.018200456743928;1.0;0.0;-85.0;201.0;218.23381956058049;-4.3123106346623885;1.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;0.0;");

		
		Map<String, Block> buff = new HashMap<String, Block>();
		Integer blockNo = 0;
		int blockWords = 0;
		Integer address = 0;
		for (Integer i = 0; i < 50; i++) {
			
			Block blk = new Block();
			blk.words = new Word[Block.BLK_SIZE];
			blockWords = 0;
			if(i > 0) {
				blockNo++;
			}
			
			address = blockNo*Block.BLK_SIZE + blockWords;
			buff.put(AddressCalculator.getAddress(address), blk);
			
			for (int j=0; j < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; j++) {
				
				//Load in blocks of size 601 Words (double value)
				if( j%Block.BLK_SIZE == 0 && j > 0) {
					
					//Create the next Block
					blk = new Block();
					blk.words = new Word[Block.BLK_SIZE];
					blockWords = 0;
					
					blockNo++;
					address = blockNo*Block.BLK_SIZE + blockWords;
					buff.put(AddressCalculator.getAddress(address), blk);
					
				}
				
				blk.words[blockWords] = new Word();
				blk.words[blockWords].setValue(0.0);
				blk.words[blockWords].convertToRawValue();
				blockWords++;
				
			}
			
			
		}
		
		//Load 2 fp values
		address = 0;
		blockNo = 0;
		blockWords = 0;
		Block blk = (Block)buff.get(AddressCalculator.getAddress(address));
		for (int j=0; j < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; j++) {
			
			//Load in blocks of size 601 Words (double value)
			if( j%Block.BLK_SIZE == 0 && j > 0) {
				blockNo++;
				blockWords=0;
				address = blockNo*Block.BLK_SIZE + blockWords;
				blk = (Block)buff.get(AddressCalculator.getAddress(address));
				
			}
			
			blk.words[blockWords].setValue(fp1[j]);
			blk.words[blockWords].convertToRawValue();
			blockWords++;
		}

		// Need to go to next block
		blockNo++;
		blockWords=0;
		address = blockNo*Block.BLK_SIZE + blockWords;
		blk = (Block)buff.get(AddressCalculator.getAddress(address));
		for (int j=0; j < FingerPrintUtil.FP_TEMPLATE_MAX_SIZE; j++) {
			
			//Load in blocks of size 601 Words (double value)
			if( j%Block.BLK_SIZE == 0 && j > 0) {
				blockNo++;
				blockWords=0;
				address = blockNo*Block.BLK_SIZE + blockWords;
				blk = (Block)buff.get(AddressCalculator.getAddress(address));
				
			}
			
			blk.words[blockWords].setValue(fp2[j]);
			blk.words[blockWords].convertToRawValue();
			blockWords++;
		}
		
		multivalueMemory = new MultivalueMemory(buff);
		
		//Display in UI
		for (Integer i = 0; i < buff.size()*Block.BLK_SIZE; i = i+Block.BLK_SIZE) {
		    simulatorUI.addMemoryValue(i.toString(),buff.get(AddressCalculator.getAddress(i)).toString());
		}
		
	}
	
	private void initializeCache() {

		cache = new Cache<String, Block>(CACHE_SIZE);
		cache.setMultivalueMemory(multivalueMemory);

	}

	public BufferedImage getFingerPrintImageDetail(double[] m_arr) {
		
		// set finger print image
		BufferedImage m_ImageBuffer = new BufferedImage(
				FingerPrintUtil.FP_IMAGE_WIDTH,
				FingerPrintUtil.FP_IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i <= FingerPrintUtil.FP_IMAGE_WIDTH - 1; i++) {
			for (int j = 0; j <= FingerPrintUtil.FP_IMAGE_HEIGHT - 1; j++) {
				// if (P[i][j] == 1)
				// m_ImageBuffer.setRGB(i,j,Color.blue.getRGB());
				// else
				m_ImageBuffer.setRGB(i, j, Color.white.getRGB());
			}
		}

//		int linelength = 5;
		// draw points
		Graphics2D gf = m_ImageBuffer.createGraphics();
		gf.setColor(Color.red);
		for (int i = 7; i <= m_arr[0] - 1; i = i + 6) {
			if (m_arr[i + 4] > 1) {
				gf.setColor(Color.red);
				gf.drawRect((int) m_arr[i] + (int) m_arr[1] - 3,
						(int) m_arr[i + 1] + (int) m_arr[2] - 2, 5, 5);
			} else if (m_arr[i + 4] == 1) {
				gf.setColor(Color.GREEN);
				gf.drawRect((int) m_arr[i] + (int) m_arr[1] - 3,
						(int) m_arr[i + 1] + (int) m_arr[2] - 2, 5, 5);
			}

		}// end for
		gf.setColor(Color.gray);
		// draws the origin
		gf.drawLine((int) m_arr[1] - 5, (int) m_arr[2], (int) m_arr[1] + 5,
				(int) m_arr[2]);
		gf.drawLine((int) m_arr[1], (int) m_arr[2] - 5, (int) m_arr[1],
				(int) m_arr[2] + 5);
		gf.drawImage(m_ImageBuffer, null, FingerPrintUtil.FP_IMAGE_WIDTH,
				FingerPrintUtil.FP_IMAGE_WIDTH);
		return m_ImageBuffer;
	}// end void

	public String ConvertFingerPrintTemplateDoubleToString(double[] finger) {
		String temp = "";
		for (int i = 0; i <= finger.length - 1; i++) {
			temp = temp + Double.toString(finger[i]) + ";";
		}
		return temp;
	}
	
	public String ConvertBlockToString(Block block) {
		String temp = "";
		
		for (Word word : block.words) {
			temp = temp + Double.toString(word.getValue()) + ";";
		}
		return temp;
	}

	public double[] ConvertFingerPrintTemplateStringToDouble(String finger) {
		double m_finger[] = new double[FingerPrintUtil.FP_TEMPLATE_MAX_SIZE];
		int c = -1;
		String m_double = "";
		String temp = "";
		for (int i = 0; i <= finger.length() - 1; i++) {
			temp = Character.toString(finger.charAt(i));
			if (temp.equals(";")) {
				
				m_finger[++c] = Double.parseDouble(m_double);
				m_double = "";
			} else {
				m_double = m_double + temp;
			}
		}
		return m_finger;
	}

}
