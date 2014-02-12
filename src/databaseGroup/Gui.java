package databaseGroup;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Gui extends JFrame implements ActionListener  {

	private static final long serialVersionUID = -965752350624413102L;
	JPanel shell = new JPanel(new CardLayout());
	DatabaseHandler db = new DatabaseHandler();
	JTextField TV_userName;
	JPasswordField TV_password;

	JTextField jt_username;
	JPasswordField jt_password;
	JPasswordField jt_repeatpassword;

	JTextField jt_email;
	JTextField jt_firstname;
	JTextField jt_lastname;
	JTextField jt_personnr;

	JTextArea jta_UserInfo;

	JComboBox<String> jcb_Personlist;


	// constructor and calls setupLayout
	public Gui() {
		setupLayout();

		//		DatabaseHandler db = new DatabaseHandler();
		//		db.GetFromDatabase("SELECT * FROM film");

	}
	public void setupLayout(){
		setTitle("DatabasProjekt");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		TV_userName = new JTextField(10);
		TV_password = new JPasswordField(10);
		
		
		JButton btn_login = new JButton("login");
		btn_login.setActionCommand("LOGIN");
		btn_login.addActionListener(this);
		JPanel jp_loginPanel = new JPanel();
		jp_loginPanel.add(new JLabel("Username"));
		jp_loginPanel.add(TV_userName);
		jp_loginPanel.add(new JLabel("Password"));
		jp_loginPanel.add(TV_password);
		jp_loginPanel.add(btn_login);

		shell.add(jp_loginPanel, "LOGIN");
		setContentPane(shell);

		setTitle("DatabasProjekt");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton knapp = new JButton();

		JPanel jp_startScreen = new JPanel();
		jp_startScreen.add(knapp);
		jp_startScreen.setVisible(true);


		shell.add(jp_startScreen, "STARTSCREEN");

		JPanel jp_Admin = new JPanel();
		JButton bt_registerNewUser = new JButton("Register new user");
		bt_registerNewUser.addActionListener(this);
		bt_registerNewUser.setActionCommand("Register new user");
		jp_Admin.add(bt_registerNewUser);
		JButton bt_registerNewPerson = new JButton("Register new person");
		bt_registerNewPerson.addActionListener(this);
		bt_registerNewPerson.setActionCommand("Register new person");		
		jp_Admin.add(bt_registerNewPerson);
		shell.add(jp_Admin, "Admin");

		JPanel jp_UserScreen = new JPanel();
		JButton bt_viewInfo = new JButton("View Info");
		jta_UserInfo = new JTextArea();
		jta_UserInfo.setEditable(false);
		jta_UserInfo.setText("   ");
		bt_viewInfo.addActionListener(this);
		bt_viewInfo.setActionCommand("View Info");
		jp_UserScreen.add(bt_viewInfo);
		jp_UserScreen.add(jta_UserInfo);

		shell.add(jp_UserScreen, "Userscreen");


		JPanel jp_registration = new JPanel();
		jt_username = new JTextField(10);
		jt_password = new JPasswordField(10);
		jt_repeatpassword = new JPasswordField(10);
		jcb_Personlist = new JComboBox<String>(); 


		//		jt_email = new JTextField(10);
		//		jt_firstname = new JTextField(10);
		//		jt_lastname = new JTextField(10);
		//		jt_personnr = new JTextField(10);

		JButton bt_register = new JButton("Register");
		bt_register.addActionListener(this);
		bt_register.setActionCommand("Register");
		jp_registration.add(new JLabel("Username"));
		jp_registration.add(jt_username);
		jp_registration.add(new JLabel("Password"));
		jp_registration.add(jt_password);
		jp_registration.add(new JLabel("Repeat password"));
		jp_registration.add(jt_repeatpassword);
		jp_registration.add(bt_register);
		jp_registration.add(new JLabel("Person nr"));
		jp_registration.add(jcb_Personlist);
		shell.add(jp_registration, "REGISTER");


		JPanel jp_registerPerson = new JPanel();


		jt_firstname = new JTextField(10);
		jt_lastname = new JTextField(10);
		jt_email = new JTextField(10);
		jt_personnr = new JTextField(10);

		JButton bt_registerPerson = new JButton("Register person");
		bt_registerPerson.addActionListener(this);
		bt_registerPerson.setActionCommand("Register person");
		jp_registerPerson.add(jt_firstname);
		jp_registerPerson.add(jt_lastname);
		jp_registerPerson.add(jt_personnr);
		jp_registerPerson.add(jt_email);
		jp_registerPerson.add(bt_registerPerson);


		shell.add(jp_registerPerson, "Register new person");




	}
	public void actionPerformed(ActionEvent e) {
		//		System.out.println(e.getActionCommand());
		CardLayout cl = (CardLayout)(shell.getLayout());
		switch (e.getActionCommand()){
		case "LOGIN": 
			if (db.setupConnection(TV_userName.getText(),new String(TV_password.getPassword()))){

				ResultSet rs = db.getFromDatabase("select * from admin where Admin_id In(Select user_id from user where username='" +TV_userName.getText()+"')");
				try {
					rs.beforeFirst();

					if(rs.next()){
						cl.show(shell, "Admin");	
					}
					else{
						cl.show(shell,"Userscreen");
						System.out.println("Not an admin");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}			
			break;
		case "Register new user":
			this.fillComboBox();
			cl.show(shell, "REGISTER");	

			break;


		case "Register new person":
			cl.show(shell, "Register new person");	

			break;



		case "Register":
			String username = jt_username.getText();
			if(new String(jt_password.getPassword()).equals(new String(jt_repeatpassword.getPassword()))){
				String password = new String(jt_password.getPassword());
				QueryWriter qw = new QueryWriter();
				String userQuery[] = qw.createNewUser(username, password);
				db.writeToDatabase(userQuery);
				try {
					ResultSet rs = db.getFromDatabase(qw.getID(username));
					rs.first();
					int id = rs.getInt(1);
					rs.beforeFirst();				
					//					System.out.println(id);
					String personnr = (String) jcb_Personlist.getSelectedItem();
					System.out.println(personnr);
					db.writeToDatabase(qw.userPerson(id, personnr));;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}




			}
			else{
				System.err.println("Passwords do not match");
			}



			break;

		case "Register person":
			//			System.out.println("Knappen funkar");
			String firstName = jt_firstname.getText();
			String lastName = jt_lastname.getText();

			String email=jt_email.getText();
			String personnr=jt_personnr.getText();

			QueryWriter qw = new QueryWriter();

			String personQuery = qw.createNewPerson(firstName, lastName, email, personnr);
			db.writeToDatabase(personQuery);
			break;
		case "View Info":
			jta_UserInfo.setText("");
			ResultSet rs = db.getFromDatabase("select Username,Address from admin right outer join user on (admin_id = user_id) order by username");
			String userInfo = "Username\tAddress\n";

			int nCols = 2;
			try {
				rs.beforeFirst();
				while (rs.next()) {
					for (int i = 1; i <= nCols; i++) {
						userInfo+= rs.getString(i) + "\t";

					}
					userInfo+="\n";
				}
				userInfo = userInfo.replace("null", "");
				jta_UserInfo.append(userInfo);

			} catch (SQLException e1) {
				System.out.println("ERROR:" + e1.getMessage());
			}





			break;
		}


	}

	public void fillComboBox(){
		jcb_Personlist.removeAllItems();
		ResultSet rs = db.getFromDatabase("select personnr from persondata");
		try {
			rs.beforeFirst();

			
			while(rs.next()){
				jcb_Personlist.addItem(String.format("%s", rs.getString(1)));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jcb_Personlist.repaint();

	}

}
