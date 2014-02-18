package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import databaseGroup.DatabaseHandler;

public class GUIframe extends JFrame implements ActionListener{




	private static final long serialVersionUID = 5717162655607497124L;

	private String username;
	private int status,id;
	private DatabaseHandler db;
	private CardLayout cl;

	//Panels
	private JPanel shell; 
	LoginPanel loginPanel;
	AdminPanel adminPanel;
	UserPanel userPanel;
	RegisterUserPanel registerUserPanel;
	RegisterPersonPanel registerPersonPanel;
	BannedPanel bannedPanel;


	public GUIframe(){
		db = new DatabaseHandler();
		cl = new CardLayout();
		shell = new JPanel(cl);
		setTitle("DatabasProjekt");
		setSize(600, 300);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(shell);

		this.setupPanels();
		this.addActionListeners();

	}

	private void setupPanels(){
		loginPanel = new LoginPanel();
		this.add(loginPanel, "loginPanel");
		adminPanel = new AdminPanel();
		this.add(adminPanel, "adminPanel");
		userPanel = new UserPanel();
		this.add(userPanel, "userPanel");
		registerUserPanel = new RegisterUserPanel();
		this.add(registerUserPanel, "registerUserPanel");
		registerPersonPanel = new RegisterPersonPanel();
		this.add(registerPersonPanel, "registerPersonPanel");
		bannedPanel = new BannedPanel();
		this.add(bannedPanel,"bannedPanel");
	}

	private void addActionListeners(){
		loginPanel.btn_login.addActionListener(this);
		adminPanel.bt_registerNewUser.addActionListener(this);
		adminPanel.bt_registerNewPerson.addActionListener(this);
		userPanel.bt_viewInfo.addActionListener(this);
		registerUserPanel.bt_register.addActionListener(this);
		registerPersonPanel.bt_registerPerson.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		String entered_un ="";
		String entered_pw="";
		switch (e.getActionCommand()){
		case "LOG IN":
			entered_un = ((LoginPanel)loginPanel).TV_userName.getText(); //entered user name
			entered_pw = new String(((LoginPanel)loginPanel).TV_password.getPassword()); //entered password
			//Log in to database
			if(db.setupConnection(entered_un,entered_pw)){
				System.out.println("LOGGED IN as: " + entered_un);
				username = entered_un;
				id = db.getID(username);
				status = db.getStatus(id);	
				
				if(status == db.BANNED){
					cl.show(shell, "bannedPanel");
				}
				else if(status == db.ADMIN){
					cl.show(shell, "adminPanel");
				}
				else {
					cl.show(shell, "userPanel");
				}

			}
			break;
		case "Register new User":

			entered_un = registerUserPanel.getUserName();

			if(registerUserPanel.passwordMatch()){
				entered_pw = registerUserPanel.getPW1();
				db.createNewUser(entered_un, entered_pw,registerUserPanel.getSelected());
			}
			else{
				System.err.println("Passwords do not match");
			}


			break;

		case "Register new Person Choice":
			cl.show(shell, "registerPersonPanel");
			break;

		case "Register new User Choice":
			registerUserPanel.fillComboBox(db.getPersonnrList());
			cl.show(shell, "registerUserPanel");
			break;

		case "Register new Person":

			String firstName = registerPersonPanel.getFirstName();
			String lastName = registerPersonPanel.getLastName();

			String email=registerPersonPanel.getEmail();
			String personnr=registerPersonPanel.getPersonNr();

			db.createNewPerson(firstName, lastName, email, personnr);

			break;
		case "View Info":
			userPanel.setText(db.getUserAdminInfo());

		}	
	}



}


