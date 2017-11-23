package evaluationPersonnel;

import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ConnectionButton extends JButton implements MouseListener {
	
	private String name;
	private JTextField textField;
	private FenetreLogin fl;
	
	public ConnectionButton(String str,JTextField textField, FenetreLogin fl){
	    super(str);
	    this.name = str;
	    this.addMouseListener(this);
	    this.textField = textField;
	    this.fl = fl;
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int saisie = Integer.parseInt(textField.getText());
		try {
			Personne personne = PersonneMapper.getInstance().getPersonne(saisie);
			if(personne!=null) {
				JPanel pan = new JPanel();
				JTextField newText = new JTextField();
				pan.setLayout(new BorderLayout());
			    pan.add(newText, BorderLayout.SOUTH);
				fl.setContentPane(pan);
				SwingUtilities.updateComponentTreeUI(fl);
			}
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
