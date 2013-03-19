package pl.agent.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;

import pl.agent.Const;
import pl.agent.LineAgent;
import pl.agent.ManagerAgent;
import pl.agent.RunAgent;
import pl.agent.Task;

public class Panel extends JPanel {

	private final static String SCENARIUSZ1= "Scenariusz 1";
	private final static String SCENARIUSZ2= "Scenariusz 2";
	private final static String SCENARIUSZ3= "Scenariusz 3";
	private final static String STOP_SCEANARIO1= "Stop Scenariusz 1";
	private final static String STOP_SCEANARIO2= "Stop Scenariusz 2";
	private final static String STOP_SCEANARIO3= "Stop Scenariusz 3";
	
	public static JButton buttonScenario1 = new JButton(SCENARIUSZ1);
	public static JButton buttonScenario2 = new JButton(SCENARIUSZ2);
	public static JButton buttonScenario3 = new JButton(SCENARIUSZ3);
	public static JButton managerButton = new JButton("Manager");
	public static JLabel managerLabel = new JLabel("Manager");
	public static JButton linia_1 = new JButton("Zglos Awarie"); 
	public static JButton linia_2 = new JButton("Zglos Awarie"); 
	public static JButton linia_3 = new JButton("Zglos Awarie"); 
	public static JButton linia_4 = new JButton("Zglos Awarie"); 
	public static JButton linia_5 = new JButton("Zglos Awarie"); 
	public static JLabel linia_1_label = new JLabel("Linia 1");
	public static JLabel linia_2_label = new JLabel("Linia 2");
	public static JLabel linia_3_label = new JLabel("Linia 3");
	public static JLabel linia_4_label = new JLabel("Linia 4");
	public static JLabel linia_5_label = new JLabel("Linia 5");
	
	public static JTextArea console= new JTextArea();
	//public static 
	private static Graphics g2;
	
	public Panel() {
		setLayout(null);
		
		
		add(buttonScenario1).setBounds(100, 600, 200, 50);
		add(buttonScenario2).setBounds(300, 600, 200, 50);
		add(buttonScenario3).setBounds(500, 600, 200, 50);
		add(console).setBounds(20, 200, 300, 290);
		console.setBackground(Color.black);
		
		add(linia_1_label).setBounds(400, 280, 400, 20);
		add(linia_1).setBounds(500, 300 ,150, 20);
		add(linia_2_label).setBounds(400, 180, 400, 20);
		add(linia_2).setBounds(500, 200 ,150, 20);
		add(linia_3_label).setBounds(400, 80, 400, 20);
		add(linia_3).setBounds(500, 100 ,150, 20);
		add(linia_4_label).setBounds(400, 380, 400, 20);
		add(linia_4).setBounds(500, 400 ,150, 20);
		add(linia_5_label).setBounds(400, 480, 400, 20);
		add(linia_5).setBounds(500, 500 ,150, 20);
		
		add(managerLabel).setBounds(40, 100, 400, 20);
		add(managerButton).setBounds(49,150 ,150, 20);
		
		managerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getActionCommand().endsWith("Manager")){
					managerLabel.setText("Click");
				}
			}
		});
		
		linia_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LineAgent.lineIsFould(Const.LINE_AGENT_1);
			}
		});
		linia_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LineAgent.lineIsFould(Const.LINE_AGENT_2);
			}
		});
		linia_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LineAgent.lineIsFould(Const.LINE_AGENT_3);
			}
		});
		
		buttonScenario1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(buttonScenario1.getText().equals(SCENARIUSZ1)){
					RunAgent.StartAgents(1);	
					buttonScenario1.setText(STOP_SCEANARIO1);
					buttonScenario1.setBackground(Color.green);
					System.out.println("scenario 1 -------------- Start");
					
				}else if(buttonScenario1.getText().equals(STOP_SCEANARIO1)){
					buttonScenario1.setText(SCENARIUSZ1);
					buttonScenario1.setBackground(Color.gray);
					RunAgent.StopAgents();	
					System.out.println("scenario 1 -------------- Stop");
				}
			}
		});
buttonScenario3.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(buttonScenario3.getText().equals(SCENARIUSZ3)){
					RunAgent.StartAgents(3);	
					buttonScenario3.setText(STOP_SCEANARIO3);
					buttonScenario3.setBackground(Color.green);
					
				}else if(buttonScenario3.getText().equals(STOP_SCEANARIO3)){
					RunAgent.StopAgents();	
					buttonScenario3.setText(SCENARIUSZ3);
					buttonScenario3.setBackground(Color.gray);
					RunAgent.StopAgents();	
					
				}
			}
		});
buttonScenario2.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(buttonScenario2.getText().equals(SCENARIUSZ2)){
			RunAgent.StartAgents(2);	
			buttonScenario2.setText(STOP_SCEANARIO2);
			buttonScenario2.setBackground(Color.green);
			
		}else if(buttonScenario2.getText().equals(STOP_SCEANARIO2)){
			//RunAgent.StartAgents(2);	
			buttonScenario2.setText(SCENARIUSZ2);
			buttonScenario2.setBackground(Color.gray);
			RunAgent.StopAgents();	
			
		}
	}
});
	}
	
    @Override
    protected void paintComponent(Graphics g) {
       super.paintComponent(g); 
       setBackground(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        rysuj(g,null);
      //  repaint();
    }
    public static synchronized void rysuj(Graphics g,Decorator dec){
    	Graphics2D g3 = (Graphics2D) g;
    	if(dec!=null){
    	if(dec.getMyName().equals(Const.MANAGER_AGENT)){
    		managerButton.setBackground(dec.getColor());
    		managerLabel.setText(dec.getLabel());
    		if(!dec.getTaski().isEmpty()){
	    		console.setText("");
	    		console.setForeground(Color.green);
	    		int i=0;
	    		for (Task taask: dec.getTaski()){
	    			console.append(i+"  ");
	    			console.append(taask.toString()+"\n");
	    			i++;
	    		}
    		}
    	}
    	 if(dec.getMyName().equals(Const.LINE_AGENT_1)){
    		linia_1.setBackground(dec.getColor());
    		linia_1_label.setText(dec.getLabel());
    	 }
    	if(dec.getMyName().equals(Const.LINE_AGENT_2)){
    		linia_2.setBackground(dec.getColor());
    		linia_2_label.setText(dec.getLabel());
    	}
    	if(dec.getMyName().equals(Const.LINE_AGENT_3)){
    		linia_3.setBackground(dec.getColor());
    		linia_3_label.setText(dec.getLabel());
    	}
    	if(dec.getMyName().equals(Const.LINE_AGENT_4)){
    		linia_4.setBackground(dec.getColor());
    		linia_4_label.setText(dec.getLabel());
    	}
    	if(dec.getMyName().equals(Const.LINE_AGENT_5)){
    		linia_5.setBackground(dec.getColor());
    		linia_5_label.setText(dec.getLabel());
    	}
    }
    } 	
}
