package databaseGroup;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements ActionListener  {

	JPanel shell = new JPanel(new CardLayout());
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
		JTextField TV_userName = new JTextField(10);
		
		JPasswordField TV_password = new JPasswordField(10);
		
		JButton btn_login = new JButton("login");
		btn_login.setActionCommand("LOGIN");
		btn_login.addActionListener(this);
		JPanel jp_loginPanel = new JPanel();
		jp_loginPanel.add(TV_userName);
		jp_loginPanel.add(TV_password);
		jp_loginPanel.add(btn_login);

		shell.add(jp_loginPanel, "LOGIN");
		setContentPane(shell);
	}
	public void startScreen(){
		setTitle("DatabasProjekt");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton knapp = new JButton();

		JPanel jp_startScreen = new JPanel();
		jp_startScreen.add(knapp);
		jp_startScreen.setVisible(true);
		
		
		shell.add(jp_startScreen, "STARTSCREEN");
		
	}
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(shell.getLayout());
		switch (e.getActionCommand()){
		case "LOGIN": 
		cl.next(shell);
		break;
		}
		
	}
}
