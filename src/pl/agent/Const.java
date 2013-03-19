package pl.agent;

import java.util.HashMap;
import java.util.Map;

public class Const {
	
		public static String MANAGER_AGENT = "Manager_agent";
		public static String LINE_AGENT_1 = "Line_Agent 1";
		public static String LINE_AGENT_2 = "Line_Agent 2";
		public static String LINE_AGENT_3 = "Line_Agent 3";
		public static String LINE_AGENT_4 = "Line_Agent 4";
		public static String LINE_AGENT_5 = "Line_Agent 5";
		
		
		public static String SYMULATOR= "Symulator";
		
		public static String TASK_LINE="1";
		public static String I_GET_YOU_TASK="biore zadanie";
	
		public static final String spilt=":";
	
		public static final String Waga_100= "100";
		public static final String Waga_150= "150";
		public static final String Waga_200= "200";
		public static final String Waga_300= "300";
		public static final String Waga_400= "400";
		public static final String Waga_500= "500";

//Massages 
		public static final String ARE_YOU_FREE = "are_you_free";
		public static final String I_FREE="i_am_free";
		public static final String I_BUSY="i busy";
		
		public static final String HOW_MENY_TIME="Ile jeszcze czasu zostalo";
		public static final String YOU_ARE_FOULD="TY SIE ZEPSULAS";
		public static final String TASK="dostajesz zadanie";
//Coffee Names
		public static final String FAMILY ="Family";
		public static final String EXCLUSIVE ="Exclusive";
		public static final String GALA ="Gala";

//Service Name
		
		public static final String SERVICE_MANAGER="service_manager";
		public static final String SERVICE_LINE="service_line";
//Conwersacion Id
		public static final String ID_LINE_FREE="Id line is free";
		public static Map<String, Long> coffeMap;
		
		
		static{
			//1000 =1s 1min = 60000
			coffeMap = new HashMap<String, Long>();
			coffeMap.put(GALA, 700l);
			coffeMap.put(EXCLUSIVE, 900l);
			coffeMap.put(FAMILY,800l);
		}
}
