package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;	
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import eg.edu.alexu.csd.oop.db.cs43.ExecuteStructureQuerys;


public class test {

	public static void main(String[] args) throws SQLException {
		
		Driver driver = new JDBCDriver();
		Properties info =new Properties();
		info.put("path",new File("sample" + System.getProperty("file.separator") +"database1"));
		Connection connection = driver.connect("jdbc:xmldb://localhost", info);
		Statement statement = connection.createStatement();
		statement.execute("create database dbas");
		statement.execute("drop database dbas ");
		statement.execute("create table table1 ( column1 int )");
		
	}
	
}
