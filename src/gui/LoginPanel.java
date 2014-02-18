package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel{


	private static final long serialVersionUID = 224373932785858248L;
	JTextField TV_userName;
	JPasswordField TV_password;

	JButton btn_login;

	public LoginPanel(){
		btn_login = new JButton("Log In");
		btn_login.setActionCommand("LOG IN");
		
		TV_userName = new JTextField(10);
		TV_password = new JPasswordField(10);
				
		this.add(TV_userName);
		this.add(TV_password);
		
		this.add(new JLabel("Username"));
		this.add(TV_userName);
		this.add(new JLabel("Password"));
		this.add(TV_password);
		this.add(btn_login);
	}


	
}
