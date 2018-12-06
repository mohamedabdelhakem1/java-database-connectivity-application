package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ConnectionManager implements ConnectionPool {
	private static ConnectionManager connectionManager;
	private static Map<String, Connection> ConnectionsPool;
	private static Map<String, Connection> UnusedConnectionsPool;
	private Connection CurrentConnection;

	private ConnectionManager() {
		ConnectionsPool = new HashMap<>();
		UnusedConnectionsPool = new HashMap<>();
	}

	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	@Override
	public Connection acquireConnection(String path) {
		
		for (Entry<String, Connection> e : ConnectionsPool.entrySet()) {
			
			if (e.getKey().equalsIgnoreCase(path)) {
				CurrentConnection = e.getValue();
				
				return e.getValue();
			}
		}
		for (Entry<String, Connection> e : UnusedConnectionsPool.entrySet()) {
			
			if (e.getKey().equalsIgnoreCase(path)) {
				CurrentConnection = e.getValue();
				Connection connection = UnusedConnectionsPool.remove(e.getKey());
				ConnectionsPool.put(path, connection);
				
				return e.getValue();
			}
		}
		Connection connection = new MyConnection(path, "jdbc:xmldb://localhost");
		CurrentConnection = connection;
		ConnectionsPool.put(path, connection);
		return connection;
	}

	@Override
	public void releaseConnection(String path) {
		UnusedConnectionsPool.put(path, ConnectionsPool.remove(path));
	}

	@Override
	public void CloseConnection(String path) {
		ConnectionsPool.remove(path);
	}

	@Override
	public String[] getAllUsedConnections() {
		String[] paths = new String[ConnectionsPool.size()];
		int i = 0;
		for (Entry<String, Connection> e : ConnectionsPool.entrySet()) {
			paths[i] = e.getKey();
			i++;
		}
		return paths;
	}

	@Override
	public Connection getCurrentConnection() {
		return CurrentConnection;

	}
}
