package databaseGroup;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseHandler {

	//Database parameters:
	private final int PORT = 3306;
	private final String DATABASENAME = "grupparbete";
	private final String SERVERNAME = "localhost";

	//DIFFERENT USER STATUSES
	public final int BANNED = 1;
	public final int ADMIN = 2;
	public final int NORMAL = 3;

	Statement queryCaller = null;
	Connection con = null;
	ResultSet rs = null;
	MysqlDataSource ds = null;
	PreparedStatement preparedStm = null;

	public DatabaseHandler() {
		ds = new MysqlDataSource();
		ds.setServerName(SERVERNAME);
		ds.setPort(PORT);
		ds.setDatabaseName(DATABASENAME);
	}

	public boolean setupConnection(String username,String password){
		boolean login = false;
		username=username.replaceAll(";","");
		password=password.replaceAll(";","");

		try {
			con = ds.getConnection(username,password);
			try {
				queryCaller = con.createStatement();
				login = true;
			} catch (SQLException e) {
				System.err.println("Error: " + e.getMessage());
			}
		} catch (SQLException e) {
			System.err.println("Error, cannot connect " + e.getMessage());
		}
		return login;
	}

	public int getID(String username){
		try {
			rs = queryCaller.executeQuery("Select user_id from user where username='" + username+"'");
			rs.first();
			return rs.getInt(1);
		} catch (SQLException e) {
			System.err.println("Unable to retrieve ID " + e.getMessage());
			return -1;
		}		
	}


	public int getStatus(int id){
		try{
			rs = queryCaller.executeQuery("select * from BannedUser where user_id ="+id);
			if(rs.first()){
				System.out.println("Logged in as banned user");
				return BANNED;
			}
			rs = queryCaller.executeQuery("select * from Admin where admin_id ="+id);

			if(rs.first()){
				System.out.println("Logged in as admin");
				return ADMIN;
			}
			System.out.println("Logged in as normal user");
			return NORMAL;
		} catch (SQLException e) {
			System.out.println("Error, cannot connect " + e.getMessage());
			return 0;
		}
	}

	public void createNewUser(String username, String password, String personnr){
		//Create a new user

		//Remove semicolons
		username=username.replaceAll(";","");
		password=password.replaceAll(";","");

		String[] statements = new String[5]; 	
		try {
			statements[0] = String.format("insert into user (Username,Password,IP_address,registry_date) values ('%s',password('%s'),'%s',CURDATE())",username,password,InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		statements[1] = String.format("CREATE USER %s@%s IDENTIFIED BY '%s'",username,SERVERNAME,password);
		statements[2] = String.format("Grant SELECT on User TO %s@%s",username,SERVERNAME); 
		statements[3] = String.format("Grant SELECT on Admin TO %s@%s",username,SERVERNAME); 
		statements[4] = String.format("Grant SELECT on BAnnedUser TO %s@%s",username,SERVERNAME); 

		writeToDatabase(statements);

		int id = getID(username);
		writeToDatabase(String.format("insert into userperson (User_id,Personnr) values (%d,'%s')",id,personnr));


	}	

	public void createNewPerson(String firstName,String lastName, String email, String personnr  ){
		firstName=firstName.replaceAll(";","");
		lastName=lastName.replaceAll(";","");
		email=email.replaceAll(";","");
		personnr=personnr.replaceAll(";","");
		writeToDatabase(String.format("insert into persondata (firstName,lastName,Email,Personnr) values ('%s','%s','%s','%s')",firstName,lastName,email, personnr));
	}

	public String[] getPersonnrList(){
		rs = getFromDatabase("select personnr from persondata");
		ArrayList<String> personnrList =  new ArrayList<String>();
		try {
			rs.beforeFirst();
			while(rs.next()){
				personnrList.add(rs.getString(1));
			}
			return personnrList.toArray(new String[personnrList.size()]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getUserAdminInfo(){
		rs = getFromDatabase("select Username,Address from admin right outer join user on (admin_id = user_id) order by username");
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
			return userInfo;
		} 
		catch (SQLException e1) {
			System.out.println("ERROR:" + e1.getMessage());
			return null;
		}
	}


	public ResultSet getFromDatabase(String Query) {
		try {
			rs = queryCaller.executeQuery(Query);
		} 
		catch (SQLException e) {
			System.err.println("ERROR reading from database:" + e.getMessage());
		}
		return rs;
	}

	private void writeToDatabase(String Query) {
		try {
			queryCaller.executeUpdate(Query);
		} catch (SQLException e) {
			System.err.println("Error writing to database: " + e.getMessage());
		}
	}

	private void writeToDatabase(String query[]) {
		try {
			for(int i=0;i<query.length;i++){

				queryCaller.addBatch(query[i]);
				//System.out.println("Adding query "+ query[i]);
			}
			queryCaller.executeBatch();

		} catch (SQLException e) {
			System.err.println("Error writing to database: " + e.getMessage());
		}
	}
}
