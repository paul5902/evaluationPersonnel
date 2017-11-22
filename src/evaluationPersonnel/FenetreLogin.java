package evaluationPersonnel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreLogin extends JFrame {

	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Mon bouton");

	public void display() {
	    this.setTitle("Login");
	    this.setSize(600, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    //Ajout du bouton à notre content pane
	    JTextField textField = new JTextField();
	    this.getContentPane().add(textField, BorderLayout.NORTH);
	    this.getContentPane().add(new ConnectionButton("Connexion",textField), BorderLayout.SOUTH);
	    
	    
	    this.setVisible(true);

	}
	
	public static void main(String[] args) {
		FenetreLogin fl = new FenetreLogin();
		fl.display();
	}

}
