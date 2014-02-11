package databaseGroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseHandler {

	Statement queryCaller = null;
	Connection con = null;
	ResultSet rs = null;
	MysqlDataSource ds = null;

	DatabaseHandler() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName("localhost");
		ds.setPort(3306);
		ds.setDatabaseName("grupparbete");

		//		try {
		//			con = ds.getConnection("swag", "yolo");
		//		} catch (SQLException e) {
		//			System.out.println("error" + e.getMessage());
		//		}
		//		System.out.println("de funka");
		//		try {
		//			queryCaller = con.createStatement();
		//		} catch (SQLException e) {
		//			System.out.println("error: " + e.getMessage());
		//		}

		// TODO:lägg in när programmet stängs
		// if (con != null) {
		// try {
		// con.close();
		// } catch (SQLException e) {
		// System.out.println("warning, cant close the connection:"
		// + e.getMessage());
		// }

	}

	Boolean setupConnection(String password, String username){
		ds = new MysqlDataSource();
		ds.setServerName("localhost");
		ds.setPort(3306);
		ds.setDatabaseName("grupparbete");




		try {
			con = ds.getConnection(password, username);
			try {
				queryCaller = con.createStatement();
			} catch (SQLException e) {
				System.out.println("error: " + e.getMessage());
			}
			return true;
		} catch (SQLException e) {
			System.out.println("Error, cannot connect " + e.getMessage());
			return false;
		}



	}

	ResultSet getFromDatabase(String Query) {
		int nCols = 0;
		try {
			rs = queryCaller.executeQuery(Query);
			rs.beforeFirst();
			try {
				ResultSetMetaData resultinfo = rs.getMetaData();
				nCols = resultinfo.getColumnCount();
				for (int i = 1; i < nCols + 1; i++) {
					System.out.print(resultinfo.getColumnLabel(i) + " ");
				}
				System.out.println();
			} catch (SQLException e1) {
				System.out.println("ERROR: " + e1.getMessage());
				e1.printStackTrace();
			}
			while (rs.next()) {
				for (int i = 1; i < nCols + 1; i++) {
					System.out.print(rs.getString(i) + " ");

				}
				System.out.println();
				// System.out.println(rs.getMetaData());
				// System.out.println(rs.getString("last_name"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR:" + e.getMessage());
		}
		return rs;

	}

	void writeToDatabase(String Query) {

		try {
			int affectedrows = queryCaller.executeUpdate(Query);
			System.out.println("Affected rows: " + affectedrows);
			System.out.println("Query Sent:" + Query);
		} catch (SQLException e) {
			System.out.println("error: " + e.getMessage());
		}
	}

	void writeToDatabase(String query[]) {
		try {
			for(int i=0;i<query.length;i++){

				queryCaller.addBatch(query[i]);
				System.out.println("Addin batch "+ query[i]);
			}
			queryCaller.executeBatch();

		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
	}

}
