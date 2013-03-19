
package pl.agent;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

/**
 * 
 * @author wsm
 */
public class RunAgent{
	public static AgentController t1 = null;
	private static ContainerController controller;
	private static Runtime rt;
	private static Object reference=null;
	private static Object[] args=null;
	private static Profile p=null;

	public static void StartAgents(int scenario) {
		try{
				rt = Runtime.instance();
				 p = new ProfileImpl();
				controller = rt.createMainContainer(p);
				 reference = new Object();
				 args = new Object[2];
				args[0] = reference;
				args[1] =Integer.valueOf(scenario);
				 
				t1 = controller.createNewAgent(Const.LINE_AGENT_1,"pl.agent.LineAgent", args);
				t1.start();
				t1 = controller.createNewAgent(Const.LINE_AGENT_2,"pl.agent.LineAgent", args);
				t1.start();
				t1 = controller.createNewAgent(Const.LINE_AGENT_3,"pl.agent.LineAgent", args);
				t1.start();
				t1 = controller.createNewAgent(Const.LINE_AGENT_4,"pl.agent.LineAgent", args);
				t1.start();
				t1 = controller.createNewAgent(Const.LINE_AGENT_5,"pl.agent.LineAgent", args);
				
				
				t1.start();
				t1 = controller.createNewAgent(Const.MANAGER_AGENT,"pl.agent.ManagerAgent", args);
				t1.start();
				t1 = controller.createNewAgent(Const.SYMULATOR,"pl.agent.Symulator", args);
				t1.start();
				
				
			} catch (StaleProxyException ex) {
				System.out.println(ex.getMessage());
			}
		}


	public static void StopAgents() {
			try {
				t1.kill();
				System.out.println("++++++++++ Kill");
				
				controller.kill();
				rt.shutDown();
				System.out.println("-----------------Kill");
				
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
			rt=Runtime.instance();
			
	}
	}

