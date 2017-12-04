package evaluationPersonnel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreLogin extends JFrame {

	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Mon bouton");

	public void display() {
	    this.setTitle("Login");
	    this.setSize(400, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    JTextField textField = new JTextField();
	    pan.setLayout(new GridLayout(2, 1));
	    JPanel topContent = new JPanel();
	    topContent.setLayout(new GridLayout(1,2));
	    topContent.add(new JLabel("Votre id:"));
	    topContent.add(textField);
	    pan.add(topContent);
	    JPanel southContent = new JPanel();
	    southContent.add(new ConnectionButton("Ok",textField,this));
	    pan.add(southContent);
	    this.setContentPane(pan);
	    
	    this.setVisible(true);

	}
	
	public JPanel getLoginPanel() {
		return this.pan;
	}
	
	public static void main(String[] args) {
		FenetreLogin fl = new FenetreLogin();
		fl.display();
	}

}
