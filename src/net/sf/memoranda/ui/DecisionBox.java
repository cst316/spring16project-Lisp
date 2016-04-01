package net.sf.memoranda.ui;

/*
 * Author: Jason Rice
 * Description: a simple decision box with yes and no buttons
 * Date: Mar 19, 2016
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import net.sf.memoranda.TaskJson;
import java.awt.BorderLayout;

public class DecisionBox {
	private JDialog panel;
	private JButton yes;
	private JButton no;
	private String Jtitle;
	JPanel panel_1;
	JPanel panel_2;
	JPanel textPanel;
	JLabel questionLabel;
	
	/*
	 * public constructor to initialize a new decision box
	 * Requirements: Dialog owner = the parent panel denoted by "this"
	 * 				 Title = title of the panel its self
	 * 				 question = the yes or no question being asked
	 */
	public DecisionBox(JDialog jd, String title, String question, String id) {
		
		panel = new JDialog(jd);
		
		textPanel = new JPanel();
		panel_1 = new JPanel();
		panel_2 = new JPanel();
		
		questionLabel = new JLabel();
		
		yes = new JButton();
		no = new JButton();
		
		//set specifics
		panel.setMinimumSize(new Dimension(390, 150));
		panel.setPreferredSize(new Dimension(390, 150));
		panel.setLocation(jd.getLocation());
		panel.setTitle(title);
		panel.setModal(true);
		panel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		panel.getContentPane().setVisible(true);
		panel.setSize(new Dimension(390, 150));
		panel.setResizable(false);
		panel.toFront(); 
		
		setJtitle(title); 
		panel.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setBounds(6, 6, 378, 54);
		
		//panel_1
		panel_1.add(questionLabel);
		questionLabel.setText(question);
		questionLabel.setVisible(true);

		//panel_2
		panel_2.setBounds(6, 72, 378, 50);
		panel.getContentPane().add(panel_2);
		
		//Yes Button
		panel_2.add(yes);
		yes.setText("Yes");
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskJson tj = null;
				try {
					tj = new TaskJson("template.json","tasks");
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}
				try {
					tj.deleteNode(id);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//dispose this panel
				panel.dispose();
			    //dispose the original dialog
				jd.dispose();
			}
		});
		
		//No Button
		panel_2.add(no);
		no.setText("No");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.dispose();
			}
		});
		
		panel.setVisible(true);
	}
	
	//gets the panel title
	public String getJtitle() {
		return Jtitle;
	}
	
	//sets the panel title
	public void setJtitle(String jtitle) {
		if(!jtitle.isEmpty()) {
			Jtitle = jtitle;
		}
		else {
			Jtitle = "no question";
		}
		
	}
}