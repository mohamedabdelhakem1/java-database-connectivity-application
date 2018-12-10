package eg.edu.alexu.csd.oop.jdbc.cs43.UI;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import eg.edu.alexu.csd.oop.jdbc.cs43.JDBCDriver;

public class DriverInstance {
	private Driver driver;
	private TableView<List<String>> table;
	private String url;
	private Statement statement;
	private Connection connection;
	private TextArea output;

	public DriverInstance() {
		driver = new JDBCDriver();
		url = "jdbc:xmldb://localhost";
	}

	public void StartConnection(String path, TextArea area) {
		
		this.output = area;
		Properties info = new Properties();
		info.put("path", new File(path));
		try {
			connection = driver.connect(url, info);
			output.setText("connected to the choosed folder successfully");
			statement = connection.createStatement();
			try {
				statement.setQueryTimeout(10);
			} catch (SQLException e) {
			}
		} catch (SQLException e) {
			output.setText("connection failed");
		}
	}

	public void handleRequest(String request) {

		String pattern = "(^\\s+)|(\\s*([(]{1})\\s*)|([\\s*,\\s*]{1,})|(([)]{1})\\s*)";
		Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		String[] strs = pat.split(request.trim()); // removed trailing and leading spaces

		if (strs[0].equalsIgnoreCase("create") || strs[0].equalsIgnoreCase("drop")) {
			try {
				boolean x = statement.execute(request);
				if (x) {
					output.setText("structure query succeeded");
				} else {
					output.setText("structure query failed");
				}
			} catch (SQLException e) {
				output.setText("structure query failed");
			}
		} else if (strs[0].equalsIgnoreCase("delete") || strs[0].equalsIgnoreCase("insert")
				|| (strs[0].equalsIgnoreCase("update"))) {
			try {
				int x = statement.executeUpdate(request);
				output.setText("number of rows affected = " + x);
			} catch (SQLException e) {
				output.setText("structure query failed");
			}
		} else if (strs[0].equalsIgnoreCase("select")) {
			output.setText("");

			try {
				ResultSet resultSet = statement.executeQuery(request);
				ResultSetMetaData data = resultSet.getMetaData();
				int columnNumber = data.getColumnCount();
				for (int i = 0; i < columnNumber; i++) {
					output.appendText(data.getColumnName(i + 1));
					output.appendText("\t\t");
				}
				output.appendText("\n");
				while (resultSet.next()) {
					for (int i = 1; i <= columnNumber; i++) {
						output.appendText(String.valueOf(resultSet.getObject(i)));
						output.appendText("\t\t");
					}
					output.appendText("\n");
				}
				/*
				 * ObservableList<List<String>> tabledata = FXCollections.observableArrayList();
				 * 
				 * List<String> row ; while (resultSet.next()) { row = new LinkedList<>(); for
				 * (int i = 1; i <= columnNumber; i++) {
				 * row.add(String.valueOf(resultSet.getObject(i))); } tabledata.add(row); } for
				 * (int i = 1; i <= columnNumber; i++) { int s = i-1;
				 * TableColumn<List<String>,String> column = new
				 * TableColumn<>(data.getColumnName(i)); column.setCellValueFactory(new
				 * Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
				 * public ObservableValue<String> call(CellDataFeatures<List<String>, String> p)
				 * { try { return new
				 * ReadOnlyObjectWrapper(p.getValue(),data.getColumnTypeName(s)); } catch
				 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
				 * return null; } });
				 * 
				 * table.getColumns().add(column); }
				 * 
				 * table.setItems(tabledata);
				 */
			} catch (SQLException e) {
				output.setText("select query failed");
			}

		}

	}

	public void closeConnections() {
		try {
			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {

		}

	}
}
