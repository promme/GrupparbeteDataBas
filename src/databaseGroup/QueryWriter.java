package databaseGroup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QueryWriter {

	String[] createNewUser(String username, String password){

		//Create a new user
		String[] returnData = new String[3]; 	
		try {
			returnData[0] = String.format("insert into user (Username,Password,IP_address,registry_date) values ('%s',password('%s'),'%s',CURDATE())",username,password,InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		returnData[1] =String.format("CREATE USER %s@localhost IDENTIFIED BY '%s'",username,password);
//		returnData[2] =String.format("GRANT SELECT, UPDATE (user.password), SELECT (user.*,admin.*) ON grupparbete TO %s",username); 
		returnData[2] =String.format("GRANT ALL ON grupparbete.* TO %s@localhost",username); 

		
		return returnData;
	}	

	String createNewPerson(String firstName,String lastName, String email, String personnr  ){
		//Create new Person
		return String.format("insert into persondata (firstName,lastName,Email,Personnr) values ('%s','%s','%s','%s')",firstName,lastName,email, personnr);
	}

	String userPerson(int id, String personnr){
		//Connect person to a user account
		return String.format("insert into userperson (User_id,Personnr) values (%d,'%s')",id,personnr);
	}

	String[] adminify(int id, String address,String username){
		//Make a user into an Admin
		String[] returnData = new String[2]; 	
		returnData[0] = String.format("insert into admin(admin_id,address) values(%d,'%s')",id,address);
		returnData[1] = String.format("GRANT ALL ON grupparbete.* TO %s@*",username);	
		return returnData;
	}

	String addMobileNr(String mobilenr,int id){
		//Add mobilenr to an admin
		return String.format("insert into admininfo(Mobile_nr,Admin_id) values('%s',%d)",mobilenr,id);
	}

	String banUser(int userId,int daysBanned){
		return String.format("insert into BannedUser(User_id,daysBanned) values(%d,%d)",userId,daysBanned);
		
	}

}
