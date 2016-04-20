package net.sf.memoranda.ui;

/*
 * Author: Jason Rice
 * Description: Notification pop up, could be something like save complete
 * or it could be used as a warning notification.
 */

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificationPopUp {
	private Component parent;
	private JDialog dialog;
	private String notification;
	private String pTitle;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JLabel questionLabel;
	private JButton okButton;
	
	/*
	 * Author: Jason Rice
	 * Description: Constructor takes in the parents panel for location and display purposes,
	 * also takes in the title of the box and the notification and returns a notification
	 * that displays in a pop up and has an ok button to click when the user has seen it.
	 */
	public NotificationPopUp(Component parent, String pTitle, String notification){
		dialog = new JDialog();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		okButton = new JButton("Ok");
		questionLabel = new JLabel();
		
		this.pTitle = pTitle;
		this.parent = parent;
		this.notification = notification;
		
		init();
	}
	
	/*
	 * Author: Jason Rice
	 * Description: init function initializes the pop up and builds the pop up.
	 */
	public void init() {
		String tmp;
		
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setSize(new Dimension (400, 100));
		dialog.setMinimumSize(new Dimension(400, 100));
		dialog.setPreferredSize(new Dimension(400, 100));
		dialog.setResizable(false);
		dialog.getContentPane().add(topPanel, BorderLayout.NORTH);
		dialog.getContentPane().add(bottomPanel, BorderLayout.CENTER);
		dialog.toFront();
		
		topPanel.add(questionLabel);
		bottomPanel.add(okButton);
		
		tmp = getPanelTitle();
		dialog.setTitle(tmp);
		
		tmp = getNotification();
		questionLabel.setText(tmp);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		
		topPanel.add(questionLabel);
		bottomPanel.add(okButton);
		
		Component comp = getParent();
		dialog.setLocationRelativeTo(comp);
		
		dialog.getContentPane().setVisible(true);
		dialog.setVisible(true);
		
	}
	
	/*
	 * Author: Jason Rice
	 * Description: private getters and setters.
	 */
	private String getNotification() {
		return notification;
	}
	
	private String getPanelTitle() {
		return pTitle;
	}
	
	private Component getParent() {
		return parent;
	}
}
