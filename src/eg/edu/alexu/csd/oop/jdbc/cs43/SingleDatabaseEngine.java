package eg.edu.alexu.csd.oop.jdbc.cs43;

import java.io.File;
import java.sql.SQLException;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs43.DataBaseBufferPool;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.MyDatabase;

public class SingleDatabaseEngine {
	private Database database;

	public SingleDatabaseEngine() {
		database = MyDatabase.getInstance();
	}

	public boolean CreateDatabase(String databaseName) {
		File file = new File(database.createDatabase(databaseName, false));
		return file.exists();
	}

	public boolean executeStructure(String sql) throws SQLException {
		return database.executeStructureQuery(sql);
	}

	public Object[][] executeQuery(String sql) throws SQLException {
		return database.executeQuery(sql);
	}

	public int executeUpdateQuery(String sql) throws SQLException {
		return database.executeUpdateQuery(sql);
	}

	public void closeEngine() {
		DataBaseBufferPool pool = DataBaseBufferPool.getInstance();
		try {
			pool.unloadCache();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.destroy();
	}
	

}
