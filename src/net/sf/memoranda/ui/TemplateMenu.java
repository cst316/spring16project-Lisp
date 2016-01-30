package net.sf.memoranda.ui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.ComponentOrientation;

public class TemplateMenu extends JPopupMenu {
	public TemplateMenu() {
		
		JMenuItem add = new JMenuItem("Add Task");
		JMenuItem remove = new JMenuItem("Remove Task");
		JMenuItem rename = new JMenuItem("Rename");
		
		add(add);
		add(remove);
		add(rename);

	}

}
