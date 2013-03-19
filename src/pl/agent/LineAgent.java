package pl.agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import pl.agent.view.Decorator;
import pl.agent.view.Panel;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class LineAgent extends Agent {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean stop=false;
	private boolean FREE = true;
	private boolean IS_GETING_TASK=false;
	private long agentErrorSleapTime= 600l;
	private long agentProductionTime= 0;
	private String volumen="";
	private long timeKgOfCoffe;
	private long timeWait=4000;
	public String MY_NAME;
	private static DateTime time;
	private static Set<String> agentWithError;
	private int counterGlobal = 0;
	private boolean SERVICE_ERROR=false;
	private int volumentInt = 0; 
	

	protected void setup() {
		MY_NAME=getLocalName();
		try {
			DFAgentDescription dfAgentDescription = new DFAgentDescription();
			dfAgentDescription.setName(this.getAID());

			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setName(Const.SERVICE_LINE);
			serviceDescription.setType(Const.SERVICE_LINE);

			dfAgentDescription.addServices(serviceDescription);
			DFService.register(this, dfAgentDescription);
			
					this.addBehaviour(new SendMessage());
					this.addBehaviour(new GetTask());
					this.addBehaviour(new Work());
					this.addBehaviour(new GetFould());
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	protected void takeDown() {
		System.out.println(getLocalName() + " Agent LINE takedown");
		
	}

	public boolean isFREE() {
		return FREE;
	}

	public void setFREE(boolean FREE) {
		this.FREE = FREE;
	}

	public class SendMessage extends CyclicBehaviour{

		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			if(!IS_GETING_TASK ){
			try {
				Panel.rysuj(null, Decorator.decorateView("Linia Wolna ", Color.GRAY, myAgent.getLocalName()));
				ACLMessage acl = myAgent.receive();
				if(acl!=null){
					
					ACLMessage aclReply = acl.createReply();
					if (FREE) {
						Panel.rysuj(null, Decorator.decorateView("Oodbieram zapaytanie i odsylam ¿e jestem Wolna", Color.GREEN, myAgent.getLocalName()));
						doWait(timeWait);
						System.out.println(getLocalName() + " wysyla wiadomosc ze jest wolna");
						this.myAgent.send(aclReply);
						IS_GETING_TASK=true;
						acl= new ACLMessage();
						//myAgent.clean(true);
					}	
				}
				else{
					block();
				}
			} catch (NullPointerException e) {
			}
		}
		}
	}

	public class GetTask extends CyclicBehaviour{
		
		private static final long serialVersionUID = 1L;
		
		@Override
		public void action() {
			if(isFREE()&& IS_GETING_TASK){
				try{
					ACLMessage acl = myAgent.receive();
					if(acl!=null){
						if(acl.getConversationId().equals(Const.TASK_LINE)){
							String []taski;
							taski= acl.getContent().split(Const.spilt);
							volumen=taski[1];
							Panel.rysuj(null, Decorator.decorateView("Odebrane:  +"+volumen+ " kg ", Color.GREEN, myAgent.getLocalName()));
							doWait(timeWait);
							System.out.println(getLocalName() + " odbiera task " + volumen + "+kg+ " + " " +agentProductionTime +" s" );
							setFREE(false);
						}else{
							System.out.println(getLocalName() + " Task !=" +Const.TASK_LINE + acl.getContent());
							
						}
					}else{
						if(counterGlobal>0){
							Panel.rysuj(null, Decorator.decorateView("Nie Dostalam Zadania Moge Le¿ec!", Color.GRAY, myAgent.getLocalName()));
						}
							counterGlobal++;
						if(counterGlobal>2){
							clearAll();
						}
						block();
						System.out.println(getLocalName() + " Counter " + counterGlobal );
						
						
					}
				}catch(NullPointerException e){
					System.out.println(getLocalName() + " Null  kiedy bierze zadanie");
					block(10000);
				}
			}
		}

		private void  getTimeProduction(String coffeName, String volumen) {
			agentProductionTime=(long)Long.valueOf(volumen)*timeKgOfCoffe;
			volumentInt= Integer.valueOf(volumen);
		}
	}
		
	
public class Work extends CyclicBehaviour{
		
		private static final long serialVersionUID = 1L;
		public int counterError=0;
		
		@Override
		public void action() {
			if(stop){
				Panel.rysuj(null, Decorator.decorateView("Wyprodukowane ", Color.GRAY, myAgent.getLocalName()));
				myAgent.doWait(900000);
			}
			
			counterError=0;
			int ms= time.getMillisOfDay();
				if(agentProductionTime!=0){
					setFREE(false);
					while(volumentInt>0){
						if((ms%timeKgOfCoffe)<4){
						volumentInt-=20;
						System.out.println(getLocalName() + volumentInt);
						}
						ms++;
						doWait(2);
						//System.out.println(getLocalName() +  ms);
						Panel.rysuj(null, Decorator.decorateView("Trwa produkcja ", Color.WHITE, myAgent.getLocalName()));
						try{		
							if(!agentWithError.isEmpty()){	
								String name=null;
								for(String nameAgentWithError : agentWithError){
									if(nameAgentWithError.equals(MY_NAME)){
										name=nameAgentWithError;
									}
								}	
								if(name!=null && counterError<1){
									Panel.rysuj(null, Decorator.decorateView("Zepsulam sie :( ", Color.RED, myAgent.getLocalName()));
									doWait(900);
									Panel.rysuj(null, Decorator.decorateView("Naprawa bedzie trwala +" +agentErrorSleapTime, Color.RED, myAgent.getLocalName()));
									ACLMessage acl = new ACLMessage(ACLMessage.FAILURE);
									acl.setContent(Const.spilt+String.valueOf(volumentInt));
									acl.addReceiver(new AID(Const.MANAGER_AGENT, AID.ISLOCALNAME));
									send(acl);
									counterError++;
									volumentInt=0;
									SERVICE_ERROR=true;
								}
							}	
						} catch (NullPointerException e) {
							System.out.println(getLocalName() + " Null Work");
					}
					}	
					if(!SERVICE_ERROR){
						clearAll();
						System.out.println(getLocalName() + " Po wszystkim ");
						Panel.rysuj(null, Decorator.decorateView("Wyprodukowane ", Color.GRAY, myAgent.getLocalName()));
						doWait(timeWait);
						
					}	
				}	
		}
	}

public class GetFould extends CyclicBehaviour{

	private static final long serialVersionUID = 1L;
	

	@Override
	public void action() {
		if(SERVICE_ERROR){
		try {
			setFREE(false);
			
			ACLMessage acl = myAgent.receive();
			if(acl!=null){
				if (acl.getContent().equals(Const.I_GET_YOU_TASK)) {
					Panel.rysuj(null, Decorator.decorateView("Naprawa trwa...", Color.RED, myAgent.getLocalName()));
					System.out.println(getLocalName()+ " Trwa naprawa  do wait");
					myAgent.doWait(agentErrorSleapTime);
					int counter=0;
					while(counter<agentErrorSleapTime){
						counter++;
						doWait(1);
					}
					SERVICE_ERROR=false;
					clearAll();
					
				}
			}else{
				System.out.println(getLocalName()+ " Trwa naprawa  ktos wyslal Ale servis wylaczamy");
				myAgent.doWait(agentErrorSleapTime);
				block();	
			}
		} catch (NullPointerException e) {
			System.out.println(getLocalName()+ " Trwa naprawa  null ale spimy i tak i tak");
			block(agentErrorSleapTime);
		}
	}
	}
}
public static void lineIsFould(String name){
	agentWithError.add(name);
}
public void clearAll(){
	FREE = true;
	IS_GETING_TASK=false;
	agentProductionTime= 0;
	volumen="";
	timeKgOfCoffe=0l;
	counterGlobal= 0;
	agentWithError.remove(getLocalName());
	
}
}