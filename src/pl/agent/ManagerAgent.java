package pl.agent;

import java.awt.Color;
import java.util.Stack;
import pl.agent.view.Decorator;
import pl.agent.view.Panel;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class ManagerAgent extends Agent {
	
	private Stack<Task> bufforTask;
	private static final long serialVersionUID = 1L;
	private boolean I_SEND_MESSAGE_FOR_ALL=false;
	private long timeWait=3000;
	private int counterTask=0;
			
	protected void setup() {
				try {
					System.out.println(getLocalName() + " Agent Men Start setup");
					
					DFAgentDescription dfAgentDescription = new DFAgentDescription();
					dfAgentDescription.setName(this.getAID());
					
					ServiceDescription serviceDescription = new ServiceDescription();
					serviceDescription.setType(Const.SERVICE_MANAGER);
					serviceDescription.setName(Const.SERVICE_MANAGER);
					dfAgentDescription.addServices(serviceDescription);
					dfAgentDescription.setName(getAID());
					DFService.register(this, dfAgentDescription);
					
						this.addBehaviour(new SerchFreeLineBehavior());
						this.addBehaviour(new GiveTask());
						this.addBehaviour(new ErrorServices());
					
						System.out.println(getLocalName() + " Dodany behavior");
				} catch (FIPAException e){
					System.out.println(getLocalName() + " exception Agent M ");
					e.printStackTrace();
				}
				
			}
			
			protected void takeDown() {
				System.out.println(getLocalName() + " Agent M take Down");
			}
			
			public class SerchFreeLineBehavior extends CyclicBehaviour{
			
				private static final long serialVersionUID = 1L;
				
				@Override
				public void action() {
					if(!I_SEND_MESSAGE_FOR_ALL){
					try {
						System.out.println(getLocalName() + " Agent Men start behavior sprawdz czy sa wolne linie");
						DFAgentDescription template = new DFAgentDescription();
						ServiceDescription serviceDescription = new ServiceDescription();
						serviceDescription.setType(Const.SERVICE_LINE);
						template.addServices(serviceDescription);
						
							DFAgentDescription[] dfAgentDescription = DFService.search(myAgent,template);
							if(dfAgentDescription!=null){
								Panel.rysuj(null, Decorator.decorateView("Szukam Wolnej lini", Color.BLUE,Const.MANAGER_AGENT));
								doWait(timeWait);
								ACLMessage acl = new ACLMessage();
								acl.setReplyWith("acl " + System.currentTimeMillis());
								System.out.println(getLocalName() + " Wysylanie wiadomosci +" +dfAgentDescription.length+" agentów");
								for (int i=0; i<dfAgentDescription.length; i++){
									I_SEND_MESSAGE_FOR_ALL=true;
									acl.addReceiver(dfAgentDescription[i].getName());
									myAgent.send(acl);
									counterTask++;
								}
							}else{
								System.out.println(getLocalName() + " Men nie znalazl odbiorcy");
							}	
					}catch (FIPAException e) {
						System.out.println(getLocalName() + " MEn fipa exception");
						e.printStackTrace();
					}catch(NullPointerException e){
						System.out.println(getLocalName() + " Men Null point exception");
					}
					}
				}
			}
				
				
				public class GiveTask extends CyclicBehaviour {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public void action() {
						
							if (counterTask>0) {
								try {
									System.out.println(getLocalName() + " Agent przydziela zadanie lini");
									ACLMessage acl = myAgent.receive();
									if (acl != null) {
				
										if (acl.getContent() != null) {
											System.out.println(getLocalName() + " Men Linia jest wolna");
											ACLMessage aclReply = acl.createReply();
											
										
											aclReply.setConversationId(Const.TASK_LINE);
											
											
											
											counterTask--;
											if(counterTask<1){
												I_SEND_MESSAGE_FOR_ALL=false;
											}
										}else{
											if(acl.getPerformative() == ACLMessage.FAILURE){
												servicessErrorMethod(myAgent,acl);
											}
										}
										
									} else {
										System.out.println(getLocalName() + " Stoje i nie przydzielam countr " + counterTask);
										block(10000);
									}
								} catch (NullPointerException e) {
									System.out.println(getLocalName() + " Men Null point exception");
								}
							}
						}
				}

				
			public class ErrorServices extends CyclicBehaviour{
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public void action() {
						try {
								ACLMessage acl= myAgent.receive();
								if(acl!=null){
									if(acl.getPerformative() == ACLMessage.FAILURE){
										servicessErrorMethod(myAgent,acl);
									}else{
										System.out.println(getLocalName() + " To nie jest Failure");	
									}
								
							}else{
								block(1000);
							}
						}catch(NullPointerException e){
							System.out.println(getLocalName() + " Men Null point exception");
						}
					}
				}
				

				public void setBufforTask(Stack<Task> bufforTask) {
					this.bufforTask = bufforTask;
				}	
				
				private void servicessErrorMethod(Agent myAgent, ACLMessage acl){
					Panel.rysuj(null, Decorator.decorateView("Obsluguje Zepsuta linie", Color.RED,Const.MANAGER_AGENT));
					
					doWait(timeWait);
					String []taski;
					taski= acl.getContent().split(Const.spilt);
					System.out.println(getLocalName() + " Zepsuta linia obslugujemy ja---- " + taski[0] + " " + taski[1]);	
					Task task=Task.createTask(taski[0], taski[1]);

					I_SEND_MESSAGE_FOR_ALL=false;
					ACLMessage acl2 = acl.createReply();
					acl2.setContent(Const.I_GET_YOU_TASK);
					myAgent.send(acl2);
					doWait(timeWait-500);
				}
	}		