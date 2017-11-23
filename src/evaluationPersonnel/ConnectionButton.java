package evaluationPersonnel;

import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ConnectionButton extends JButton implements MouseListener {
	
	private String name;
	private JTextField textField;
	private FenetreLogin fl;
	private JPanel loginPanel;
	
	public ConnectionButton(String str,JTextField textField, FenetreLogin fl){
	    super(str);
	    this.name = str;
	    this.addMouseListener(this);
	    this.textField = textField;
	    this.fl = fl;
	    this.loginPanel = fl.getLoginPanel();
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int saisie = Integer.parseInt(textField.getText());
		try {
			Personne personne = PersonneMapper.getInstance().getPersonne(saisie);
			if(personne!=null) {
				JPanel pan = new JPanel();
				JPanel topInfo = new JPanel();
				JPanel bottomInfo = new JPanel();
				JPanel gridInfos = new JPanel();
				JPanel panelBoutonAnnuler = new JPanel();

				//String infoPere = "Votre père : " + personne.getLePere().getIdentite();
				//JLabel affichageInfosPersonne = new JLabel(infoPersonne + "\n" + infoPere);
				pan.setLayout(new BorderLayout());
				pan.add(topInfo, BorderLayout.NORTH);
				topInfo.setLayout(new BorderLayout());
				gridInfos.setBorder(new EmptyBorder(5, 5, 0, 0));
				topInfo.add(gridInfos,BorderLayout.CENTER);
				topInfo.add(panelBoutonAnnuler,BorderLayout.EAST);
				JButton annuler = new JButton("Annuler");
				annuler.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						fl.setContentPane(loginPanel);
						SwingUtilities.updateComponentTreeUI(fl);
					}
				});
				panelBoutonAnnuler.add(new JButton("Annuler"));
				gridInfos.setLayout(new GridLayout(3,0));
				gridInfos.add(new JLabel("Vous : " + personne.getIdentite()));
				gridInfos.add(new JLabel("Votre père : " + personne.getLePere().getIdentite()));
				gridInfos.add(new JLabel("Votre évaluation : " + personne.getEvaluation()));
				
				pan.add(bottomInfo,BorderLayout.CENTER);
			    //pan.add(affichageInfosPersonne);
			    //pan.add(new JButton("Annuler"));
			    //pan.add(new JTextField("Vos fils"));
			    //pan.add(new JLabel("toto"));
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
