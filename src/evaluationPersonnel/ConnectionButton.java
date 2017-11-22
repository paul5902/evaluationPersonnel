package evaluationPersonnel;

import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionButton extends JButton implements MouseListener {
	
	private String name;
	private JTextField textField;
	
	public ConnectionButton(String str,JTextField textField){
	    super(str);
	    this.name = str;
	    this.addMouseListener(this);
	    this.textField = textField;
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int saisie = Integer.parseInt(textField.getText());
		PersonneVirtualProxy vp = new PersonneVirtualProxy(saisie);
		try {
			Personne personne = vp.getPersonne();
			System.out.println(personne.getPrenom());
		} catch (SQLException e) {
			System.out.println("ID Inexistant");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
