package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.util.*;
import java.io.File;
import java.sql.Connection;
import java.sql.Driver;

import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.ConnectionGroupManager;

public class MyDriver implements Driver {

	public MyDriver() {
		
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
			
			File dir = (File) info.get("path");
			String path = dir.getAbsolutePath();
			ConnectionManager connectionManager = ConnectionManager.getInstance();
			return connectionManager.acquireConnection(path); 
		}
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		DriverPropertyInfo info2 = new DriverPropertyInfo("user", "admin");
		DriverPropertyInfo info3 = new DriverPropertyInfo("password", "admin");
		DriverPropertyInfo[] infos = new DriverPropertyInfo[] {info2,info3};
		return infos;
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
