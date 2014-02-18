package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegisterPersonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6092458272494332981L;
	JTextField jt_email;
	JTextField jt_firstname;
	JTextField jt_lastname;
	JTextField jt_personnr;
	JButton bt_registerPerson;

	public RegisterPersonPanel(){
		jt_firstname = new JTextField(10);
		jt_lastname = new JTextField(10);
		jt_email = new JTextField(20);
		jt_personnr = new JTextField(10);

		bt_registerPerson = new JButton("Register");
		bt_registerPerson.setActionCommand("Register new Person");

		add(new JLabel("First name"));
		add(jt_firstname);
		add(new JLabel("Last name"));
		add(jt_lastname);
		add(new JLabel("Person nmbr"));
		add(jt_personnr);
		add(new JLabel("Email"));
		add(jt_email);
		add(bt_registerPerson);
	}

	public String getFirstName(){
		return jt_firstname.getText();
	}
	public String getLastName(){
		return jt_lastname.getText();
	}
	public String getEmail(){
		return jt_email.getText();
	}
	public String getPersonNr(){
		return jt_personnr.getText();
	}
	



}
