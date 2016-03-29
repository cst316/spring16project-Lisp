package net.sf.memoranda.ui;

/*
 * Author: Jason Rice
 * Description: a simple decision box with yes and no buttons
 * Date: Mar 19, 2016
 */
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import org.json.simple.parser.ParseException;

import net.sf.memoranda.TaskJson;
import java.awt.BorderLayout;

public class DecisionBox {
	private JDialog panel;
	private JButton yes;
	private JButton no;
	private String Jquestion;
	private String Jtitle;
	
	
	/*
	 * public constructor to initialize a new decision box
	 * Requirements: Dialog owner = the parent panel denoted by "this"
	 * 				 Title = title of the panel its self
	 * 				 question = the yes or no question being asked
	 */
	public DecisionBox(JDialog jd, String title, String question, String id) {
		
		panel = new JDialog(jd);
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
		
		JPanel textPanel = new JPanel();
		
		//set the title
		setJtitle(title); 
		JPanel panel_1 = new JPanel();
		panel.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setBounds(6, 6, 378, 54);
		//create the label for the question
		JLabel q = new JLabel();
		panel_1.add(q);
		q.setText(question);
		q.setVisible(true);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 72, 378, 50);
		panel.getContentPane().add(panel_2);
		
		//create the buttons
		yes = new JButton(); 
		panel_2.add(yes);
		yes.setText("Yes");
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskJson tj = null;
				try {
					tj = new TaskJson("template.json","tasks");
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					int tmpid = Integer.parseInt(id);
					String id = "" + tmpid;
					tj.deleteNode(id);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel.dispose();
				jd.dispose();
			}
		});
		no = new JButton();
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
		if(!jtitle.isEmpty())
		Jtitle = jtitle;
		else
		Jtitle = "no question";
	}
	
	//return the yes button to create action listeners
	public JButton getYesButton() {
		return yes;
	}
	//return the no button to create action listeners
	public JButton getNoButton() {
		return no;
	}
}
