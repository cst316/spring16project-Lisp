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
import java.awt.Component;

public class DecisionBox {
	
	private JDialog panel;
	private JDialog jdialogParent;
	private Component componentParent;
	private JButton yes;
	private JButton no;
	private String Jtitle;
	private JPanel insideTopPanel;
	private JPanel insideBottomPanel;
	private JPanel textPanel;
	private JLabel questionLabel;
	private String title;
	private String question; 
	private String id;
	public boolean decision;
	
	/*
	 * public constructor to initialize a new decision box
	 * Requirements: Dialog owner = the parent panel denoted by "this"
	 * 				 Title = title of the panel its self
	 * 				 question = the yes or no question being asked
	 *               id = specific to Json files
	 */
	public DecisionBox(JDialog jdialogParent, String title, String question, String id) {
		
		this.jdialogParent = jdialogParent;
		this.title = title;
		this.question = question;
		this.id = id;
		yes = new JButton();
		no = new JButton();
		
		initCheckContainer();
		textPanel = new JPanel();
		insideTopPanel = new JPanel();
		insideBottomPanel = new JPanel();
		questionLabel = new JLabel();
		
		init();
		panel.setVisible(true);
	}
	
	/*
	 * public constructor to initialize a new decision box
	 * Requirements: Componant owner = the parent panel denoted by "this"
	 * 				 Title = title of the panel its self
	 * 				 question = the yes or no question being asked
	 */
	public DecisionBox(Component componentParent, String title, String question) {
		
		this.componentParent = componentParent;
		this.title = title;
		this.question = question;
		jdialogParent = null;
		id = "";
		decision = false;
		
		yes = new JButton();
		no = new JButton();
		
		initCheckContainer();
		textPanel = new JPanel();
		insideTopPanel = new JPanel();
		insideBottomPanel = new JPanel();
		questionLabel = new JLabel();
		
		init();
		panel.setVisible(true);
	}
	
	public void init() {
		
		setJtitle(title); 
	
		//set specifics
		panel.setMinimumSize(new Dimension(390, 150));
		panel.setPreferredSize(new Dimension(390, 150));
		panel.setTitle(title);
		panel.setModal(true);
		panel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		panel.getContentPane().setVisible(true);
		panel.setSize(new Dimension(390, 150));
		panel.setResizable(false);
		panel.toFront(); 
		
		panel.getContentPane().add(insideTopPanel, BorderLayout.NORTH);
		insideTopPanel.setBounds(6, 6, 378, 54);
		
		//panel_1
		insideTopPanel.add(questionLabel);
		questionLabel.setText(question);
		questionLabel.setVisible(true);

		//panel_2
		insideBottomPanel.setBounds(6, 72, 378, 50);
		panel.getContentPane().add(insideBottomPanel);
		
		//Yes Button
		insideBottomPanel.add(yes);
		yes.setText("Yes");
		
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jdialogParent != null) {
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
					jdialogParent.dispose();
				}
				else {
					decision = true;
					panel.dispose();
				}
			}
		});
		
		//No Button
		insideBottomPanel.add(no);
		no.setText("No");
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decision = false;
				panel.dispose();
			}
		});
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
	
	public boolean getDecision() {
		return decision;
	}
	
	//checks to see what type of container is called
	public void initCheckContainer() {
		if(jdialogParent == null) {
			panel = new JDialog();
			panel.setLocationRelativeTo(componentParent);
		}
		else {
			panel = new JDialog(jdialogParent);
			panel.setLocation(jdialogParent.getLocation());
		}
	}
}