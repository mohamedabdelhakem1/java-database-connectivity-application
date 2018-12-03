package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.sql.Connection;
import java.sql.Driver;

import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class MyDriver implements Driver {
	private Properties properties;
	
	public MyDriver() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		if (url.equals("jdbc:xmldb://localhost")) {
			return true;
		}
		return false;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if (acceptsURL(url)) {
			properties = info;
			Connection connection = new MyConnection();
		}
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		
		return null;
	}

	@Override
	public int getMajorVersion() {
		throw new UnsupportedOperationException();

	}

	@Override
	public int getMinorVersion() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean jdbcCompliant() {
		throw new UnsupportedOperationException();
	}

}
