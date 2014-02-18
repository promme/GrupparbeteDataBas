package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterUserPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6359284649316029702L;
	JTextField jt_username;
	JPasswordField jt_password,jt_repeatpassword;
	JComboBox<String> jcb_Personlist;
	JButton bt_register;
	public RegisterUserPanel(){
		jt_username = new JTextField(10);
		jt_password = new JPasswordField(10);
		jt_repeatpassword = new JPasswordField(10);
		jcb_Personlist = new JComboBox<String>();
		bt_register = new JButton("Register");
		bt_register.setActionCommand("Register new User");

		add(new JLabel("Username"));
		add(jt_username);
		add(new JLabel("Password"));
		add(jt_password);
		add(new JLabel("Repeat password"));
		add(jt_repeatpassword);
		add(bt_register);
		add(new JLabel("Person nr"));
		add(jcb_Personlist);


	}

	public String getUserName(){
		return jt_username.getText();
	}
	public boolean passwordMatch(){
		return (getPW1().equals(getPW2()) && getPW1().length()>0 ); 
	}
	public String getPW1(){
		return 	new String( jt_password.getPassword() );
	}
	public String getPW2(){
		return 	new String( jt_repeatpassword.getPassword() );
	}
	public void fillComboBox(String[] data){
		jcb_Personlist.removeAll();
		for (String s : data) {
			jcb_Personlist.addItem(s);
		}
		jcb_Personlist.repaint();
	}
	public String getSelected(){
		return jcb_Personlist.getSelectedItem().toString();
	}




}
