package databaseGroup;

public class Main {

	public static void main(String[] args) {
		DatabaseHandler db = new DatabaseHandler();
		db.GetFromDatabase("SHOW DATABASES");

	}

}
