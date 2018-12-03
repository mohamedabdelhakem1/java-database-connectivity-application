package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;

public class test {

	public static void main(String[] args) {
		try {
			DriverManager.registerDriver(new MyDriver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	try {
		Driver driver = (Driver) DriverManager.getDriver("jdbc:xmldb://localhost");
		System.out.println("sss");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
