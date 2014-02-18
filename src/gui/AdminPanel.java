package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AdminPanel extends JPanel {


	private static final long serialVersionUID = -8738038684253299318L;
	JButton bt_registerNewUser;
	JButton bt_registerNewPerson;
	public AdminPanel(){
	    bt_registerNewUser = new JButton("Register new user");
		bt_registerNewUser.setActionCommand("Register new User Choice");
		add(bt_registerNewUser);
	    bt_registerNewPerson = new JButton("Register new person");
		bt_registerNewPerson.setActionCommand("Register new Person Choice");		
		add(bt_registerNewPerson);
	}
}
