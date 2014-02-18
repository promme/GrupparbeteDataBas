package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BannedPanel extends JPanel{

	JTextArea jta_banned;
	public BannedPanel(){
		jta_banned= new JTextArea();
		jta_banned.setEditable(false);
		jta_banned.setText("YOU ARE BANNED");
		add(jta_banned);
	}
}
