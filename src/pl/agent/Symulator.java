/**
 * 
 */
package pl.agent;

import java.util.Stack;

import pl.agent.ManagerAgent.ErrorServices;
import pl.agent.ManagerAgent.GiveTask;
import pl.agent.ManagerAgent.SerchFreeLineBehavior;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

/**
 * @author Piotr Prusinski
 *
 */
public class Symulator extends Agent {
	
	private static final long serialVersionUID = 1L;
	public static Stack<Task> bufforTask;
	
		protected void setup() {
			try {
				Object[] argument = getArguments();
				System.out.println(getLocalName() + " Agent Men Start setup");
				
				DFAgentDescription dfAgentDescription = new DFAgentDescription();
				dfAgentDescription.setName(this.getAID());
				
				ServiceDescription serviceDescription = new ServiceDescription();
				serviceDescription.setType(Const.SYMULATOR);
				serviceDescription.setName(Const.SYMULATOR);
				dfAgentDescription.addServices(serviceDescription);
				dfAgentDescription.setName(getAID());
				DFService.register(this, dfAgentDescription);
				
				if(argument[1]!=null){
					if(argument[1].equals(Integer.valueOf(1))){
						System.out.println("Odpalono symulacjie -1");
						addBehaviour(new Symulator1());
					}

					if(argument[1].equals(Integer.valueOf(2))){
						System.out.println("Odpalono symulacjie -2");
						addBehaviour(new Symulator2());
					}

					if(argument[1].equals(Integer.valueOf(3))){
						System.out.println("Odpalono symulacjie -3");
						addBehaviour(new Symulator3());
					}
				}
				
//					this.addBehaviour(new ErrorServices());
					//this.addBehaviour(new SendErrorServices());
				
					System.out.println(getLocalName() + " Dodany behavior");
			} catch (FIPAException e){
				System.out.println(getLocalName() + " exception Agent M ");
				e.printStackTrace();
			}
			
		}
		public class Symulator1 extends OneShotBehaviour{
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void action() {
				try {
					
				}catch(NullPointerException e){
					System.out.println(getLocalName() + " Nuill");
				}
			}
		}
		
		public class Symulator2 extends OneShotBehaviour{
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void action() {
				try {
					doWait(9000);
					LineAgent.lineIsFould(Const.LINE_AGENT_1);
					doWait(1000);
					LineAgent.lineIsFould(Const.LINE_AGENT_2);
					doWait(1000);
					LineAgent.lineIsFould(Const.LINE_AGENT_3);
					doWait(1000);
					LineAgent.lineIsFould(Const.LINE_AGENT_4);
					doWait(1000);
					LineAgent.lineIsFould(Const.LINE_AGENT_5);
						
				}catch(NullPointerException e){	
					System.out.println(getLocalName() + " Nuill");
				}
			}
		}
		
		public class Symulator3 extends OneShotBehaviour{
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void action() {
				try {
					
					doWait(9000);
					LineAgent.lineIsFould(Const.LINE_AGENT_1);
					doWait(1000);
					LineAgent.lineIsFould(Const.LINE_AGENT_2);
				}catch(NullPointerException e){
					System.out.println(getLocalName() + " Nuill");
				}
			}
		}
		
		


		protected void takeDown() {
			System.out.println(getLocalName() + " Agent M take Down");
		}
		
		  public Symulator() {  }
		     
		      public static Stack<Task> getBufforStack(){
		        bufforTask = new Stack<Task>();
		        Task task= Task.createTask(Const.GALA, Const.Waga_200);
		        bufforTask.push(task);
		 
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.GALA, Const.Waga_150);
		         bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_500);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_150);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.GALA, Const.Waga_150);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_500);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_150);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.FAMILY, Const.Waga_300);
		        bufforTask.push(task);
		        task= Task.createTask(Const.EXCLUSIVE, Const.Waga_300);
		        bufforTask.push(task);
		  
		  
		        return bufforTask; 
		
		
			
		}
		
}


