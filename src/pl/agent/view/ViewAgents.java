/**
 * 
 */
package pl.agent.view;

import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import pl.agent.RunAgent;

/**
 * @author Piotr Prusinski
 *
 */
public class ViewAgents extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel panel = new Panel();

	
	public ViewAgents() throws HeadlessException {
		super("Symulator: Fabryka Kawy");
		setBounds(400, 50, 800, 800);
		displayPanel(this);
	}	
	
	public static void main(String[] args) {
		
		 EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
        		ViewAgents v =new ViewAgents();
				v.setVisible(true);
				v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
		
	}
	
	public void displayPanel(ViewAgents v){
		v.getContentPane().add(panel);
	}

}
