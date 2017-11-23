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
	    JTextField textField = new JTextField();
	    pan.setLayout(new BorderLayout());
	    pan.add(textField, BorderLayout.NORTH);
	    pan.add(new ConnectionButton("Connexion",textField,this), BorderLayout.SOUTH);
	    this.setContentPane(pan);
	    
	    
	    this.setVisible(true);

	}
	
	public static void main(String[] args) {
		FenetreLogin fl = new FenetreLogin();
		fl.display();
	}

}
