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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javafx.scene.layout.Border;

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
				panelBoutonAnnuler.add(annuler);
				gridInfos.setLayout(new GridLayout(3,0));
				gridInfos.add(new JLabel("Vous : " + personne.getIdentite()));
				if(personne.getLePere()!=null) {
					gridInfos.add(new JLabel("Votre pere : " + personne.getLePere().getIdentite()));
				}
				
				gridInfos.add(new JLabel("Votre Evaluation : " + personne.getEvaluation()));
				
				pan.add(bottomInfo,BorderLayout.CENTER);
				bottomInfo.setLayout(new GridLayout(3,0));
				
				
				
			    
				final List<Personne> lesFils = personne.getLesFils();
				final JList jlistFils = new JList(lesFils.toArray());
				
				bottomInfo.add(new JLabel("Vos fils :"));
				JPanel listeFilsEtEval = new JPanel();
				listeFilsEtEval.setLayout(new GridLayout(0,2));
				listeFilsEtEval.add(jlistFils);
				JPanel eval = new JPanel();
				eval.setLayout(new GridLayout(3,0));
				eval.add(new JLabel("Evaluation de"));
				final JLabel nomFilsSelection = new JLabel("");
				eval.add(nomFilsSelection);
				final JTextField saisieEvalFils = new JTextField();
				eval.add(saisieEvalFils);
				listeFilsEtEval.add(eval);
				bottomInfo.add(listeFilsEtEval);
				JButton valider = new JButton("Valider");
				valider.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						for(Personne p : lesFils) {
							String selection = jlistFils.getSelectedValue().toString();
							String[] parties = selection.split(" ");
							System.out.println(p.getNom()+p.getPrenom());
							if(p.getNom().equals(parties[0]) && p.getPrenom().equals(parties[1])) {
								System.out.println("CORRESPOND");
								p.setEvaluation(saisieEvalFils.getText());
							}
						}
						UnitOfWork.getInstance().commit();
					}
				});
				
				bottomInfo.add(valider);
				jlistFils.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting() == false) {

					        if (jlistFils.getSelectedIndex() == -1) {
					            

					        } else {
					        	nomFilsSelection.setText(jlistFils.getSelectedValue().toString());
					        }
					    }
						
					}
				});
				
				
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
