package eg.edu.alexu.csd.oop.jdbc.cs43.UI;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import eg.edu.alexu.csd.oop.db.cs43.DataBaseBufferPool;
import eg.edu.alexu.csd.oop.db.cs43.commandConcreteClasses.ClientCommand;
import eg.edu.alexu.csd.oop.db.cs43.concreteclass.CommandFactory;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class UIController {
	@FXML
	private TextField input;
	@FXML
	private TextArea output;
	@FXML
	private Button path;
	private TableView<List<String>> table;
	private String filePath;
	private Stage stage;
	private DriverInstance driverInstance;
	private boolean connected = false;

	public UIController() {
		driverInstance = new DriverInstance();

	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	public void enterCommand(KeyEvent e) throws Exception {
		if (e.getCode() == KeyCode.ENTER && connected) {
			driverInstance.handleRequest(input.getText());
		}

	}

	@FXML
	public void fileChooseOpen() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Open Resource File");
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = directoryChooser.showDialog(stage);

		if (file == null) {
			connected = false;
		} else {
			connected = true;
			driverInstance.StartConnection(file.getAbsolutePath(), output);
		}

	}

	public void Save() {
		driverInstance.closeConnections();
	}

}
