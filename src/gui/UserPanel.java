package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 221487266701963502L;
	private JTextArea jta_UserInfo;
	JButton bt_viewInfo;
	public UserPanel(){
		bt_viewInfo = new JButton("View Info");
		jta_UserInfo = new JTextArea();
		jta_UserInfo.setEditable(false);
		setText("   ");
		bt_viewInfo.setActionCommand("View Info");
		add(bt_viewInfo);
		add(jta_UserInfo);
	}
	
	public void setText(String text){
		jta_UserInfo.setText(text);
	}
	
	
	
}
