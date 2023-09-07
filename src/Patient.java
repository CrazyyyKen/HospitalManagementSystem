// IMPORT libraries/files
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Style;

public class Patient extends Role implements PageManager {

	// Data fields
	private String disease;
	private String sex;
	private String admitStatus;
	private int age;

	// Default constructor
	public Patient() {
		super();
	}

	// Constructor
	public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
		setId(id);
		setName(name);
		this.disease = disease;
		this.sex = sex;
		this.admitStatus = admitStatus;
		this.age = age;
	}

	/* ========================== PATIENT MAIN PAGE ========================== */
	public Scene mainPage(Stage primaryStage) {

		// Add New Patient Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Patient", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(400, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Patient Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Patient's Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(400, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Patient Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Patient", removeIcon);
		removeButton.setAlignment(Pos.CENTER_LEFT);
		removeButton.setPrefSize(400, 80);
		removeButton.setStyle(Style.getIconButtonStyle());
		removeButton.setOnMouseEntered(e -> removeButton.setStyle(Style.getHoveredIconButtonStyle()));
		removeButton.setOnMouseExited(e -> removeButton.setStyle(Style.getIconButtonStyle()));

		// Back to Main Menu Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(70);

		// Create event handling for buttons

		// Call add method
		addButton.setOnAction(e -> {
			Patient Patient = new Patient();
			primaryStage.setScene(Patient.add(primaryStage));
		});

		// Call show method
		displayButton.setOnAction(e -> {
			if (DatabaseHandler.patients.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Patient's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(show(primaryStage));
			}
		});

		// Call remove method
		removeButton.setOnAction(e -> {
			if (DatabaseHandler.patients.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Patient's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(remove(primaryStage));
			}
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Arrange panes and objects
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(addButton, displayButton, removeButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setLayoutX(668);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().addAll(vBox, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/patient.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	/* ============================ ADD PATIENT ============================ */
	// Prompts user to enter information of Patient
	public Scene add(Stage primaryStage) {

		// Create BACK button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		backButton.setLayoutX(185);
		backButton.setLayoutY(100);

		// Create label objects
		Label idLabel = new Label("Enter Patient's ID : ");
		idLabel.setStyle(Style.getTextStyle());
		Label nameLabel = new Label("Enter Patient's Name : ");
		nameLabel.setStyle(Style.getTextStyle());
		Label diseaseLabel = new Label("Enter Patient's Disease : ");
		diseaseLabel.setStyle(Style.getTextStyle());
		Label sexLabel = new Label("Enter Patient's Sex : ");
		sexLabel.setStyle(Style.getTextStyle());
		Label admitStatusLabel = new Label("Enter Patient's Admit Status : ");
		admitStatusLabel.setStyle(Style.getTextStyle());
		Label ageLabel = new Label("Enter Patient's Age : ");
		ageLabel.setStyle(Style.getTextStyle());

		// Create text field objects for ID, name, disease
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextfieldStyle());
		idTextField.setPromptText("123");
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Goh Kai Wen");
		TextField diseaseTextField = new TextField();
		diseaseTextField.setStyle(Style.getTextfieldStyle());
		diseaseTextField.setPromptText("Fever");

		// Create combo box for Sex
		ComboBox<String> sexComboBox = new ComboBox<>();
		sexComboBox.setStyle(Style.getTextStyle());
		sexComboBox.getItems().addAll("Male", "Female");
		sexComboBox.getSelectionModel().selectFirst();
		sexComboBox.setPrefWidth(250);

		// Create combo box for admitStatus
		String[] admitStatusArray = { "Admitted", "Outpatient", "Emergency Admission", "Scheduled Admission",
				"Discharged", "Transferred" };
		ComboBox<String> admitStatusComboBox = new ComboBox<>();
		admitStatusComboBox.setStyle(Style.getTextStyle());
		admitStatusComboBox.getItems().addAll(admitStatusArray);
		admitStatusComboBox.getSelectionModel().selectFirst();
		admitStatusComboBox.setPrefWidth(250);

		// Create spinner objects for age
		Spinner<Integer> ageSpinner = new Spinner<>(0, 200, 20);
		ageSpinner.setEditable(true);
		ageSpinner.setStyle(Style.getTextStyle());
		ageSpinner.getEditor().setPrefWidth(250);

		// Create Add button object
		Button addButton = new Button("Add");
		addButton.setStyle(Style.getIdleButtonStyle());
		addButton.setLayoutX(620);
		addButton.setLayoutY(640);
		addButton.setPrefSize(150, 50);
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIdleButtonStyle()));

		// Create event handling for button
		addButton.setOnAction(e -> {

			// Initialization of data field
			String idInput = idTextField.getText();
			String nameInput = nameTextField.getText();
			String diseaseInput = diseaseTextField.getText();
			String sexInput = sexComboBox.getValue();
			String admitStatusInput = admitStatusComboBox.getValue();
			String ageInput = Integer.toString(ageSpinner.getValue());

			// Combine text field into TextField array
			TextField[] textFieldArray = { idTextField, nameTextField, diseaseTextField };

			// Combine String input into an array
			String[] inputArray = { idInput, nameInput, diseaseInput, sexInput, admitStatusInput, ageInput };

			// Validate user input
			if (validation(textFieldArray, inputArray, DatabaseHandler.patients)) {

				// Assign values to Patient's data field
				setId(String.format("%03d", Integer.parseInt(idInput)));
				setName(nameInput);
				disease = diseaseInput;
				sex = sexInput;
				admitStatus = admitStatusInput;
				age = Integer.parseInt(ageInput);

				// Add patient object to ArrayList
				DatabaseHandler.patients.add(this);

				// Add patient to Database
				DatabaseHandler.addDatabase("insert into Patient values('" + getId() + "', '" + getName() + "', '"
						+ disease + "', '" + sex + "', '" + admitStatus + "', " + age + ")");

				// Check if user would like to return to previous section or return to main menu
				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);

				int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
				} else {
					primaryStage.setScene(mainPage(primaryStage));
				}

			} else {
				// Show warning message when user enter wrong inputs
				JOptionPane.showMessageDialog(null, getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		// Create GridPane for Form
		GridPane grid = new GridPane();
		// Position
		grid.setLayoutX(430);
		grid.setLayoutY(248);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(idLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(diseaseLabel, 0, 2);
		grid.add(sexLabel, 0, 3);
		grid.add(admitStatusLabel, 0, 4);
		grid.add(ageLabel, 0, 5);
		// Column 1
		grid.add(idTextField, 1, 0);
		grid.add(nameTextField, 1, 1);
		grid.add(diseaseTextField, 1, 2);
		grid.add(sexComboBox, 1, 3);
		grid.add(admitStatusComboBox, 1, 4);
		grid.add(ageSpinner, 1, 5);

		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addPatient.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}

	/* ========================== DISPLAY PATIENT INFO ========================== */
	// Shows the information of Patient
	public String[] showInfo() {
		String[] output = { getId(), getName(), disease, sex, admitStatus, age + "" };
		return output;
	}

	// Show Patient's information page
	public Scene show(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Disease", "Sex", "Admit Status", "Age" };
		HBox columnLabelHBox = new HBox(10);
		columnLabelHBox.setStyle(Style.getHEADERStyle());
		columnLabelHBox.setPrefHeight(50);

		for (int i = 0; i < 6; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(160);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column label into VBox object
		vBox.getChildren().addAll(columnLabelHBox);

		// Create and insert HBox object of each patient's info into VBox object
		for (int i = 0; i < DatabaseHandler.patients.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(160);
				stackPane.setPrefHeight(15);
				Text text = new Text(DatabaseHandler.patients.get(i).showInfo()[j]);
				text.setStyle(Style.getTextStyle());
				stackPane.getChildren().add(text);
				hBox.getChildren().add(stackPane);
			}

			hBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(hBox);
		}

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn.png"));
		Button backButton = new Button("Back", backIcon);
		backButton.setStyle(Style.getIconButtonStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconButtonStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconButtonStyle()));

		// Create event handling for button to return to mainPage
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);

		/* ============================ SORTING FUNCTION ============================ */
		// Create combo box objects for sort function
		String[] sortArray = { "Sort By Default", "Sort by ID", "Sort by Name" };
		ComboBox<String> sortComboBox = new ComboBox<>();
		sortComboBox.setStyle(Style.getComboBox2Style());
		sortComboBox.getItems().addAll(sortArray);
		sortComboBox.getSelectionModel().selectFirst();
		sortComboBox.setPrefWidth(220);

		// Create HBox object for combo box
		HBox HSort = new HBox(15);
		HSort.getChildren().add(sortComboBox);
		HSort.setAlignment(Pos.CENTER);

		// Sort Function
		sortComboBox.setOnAction(e -> {
			// Sort by default function
			if (sortArray[0].equals(sortComboBox.getValue())) {
				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < DatabaseHandler.patients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(DatabaseHandler.patients.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			}
			// Sort by id function
			else if (sortArray[1].equals(sortComboBox.getValue())) {
				// Make a copy of ArrayList
				ArrayList<Patient> copyPatients = new ArrayList<Patient>(DatabaseHandler.patients);
				copyPatients.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyPatients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyPatients.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			}
			// Sort by name function
			else {
				// Make a copy of ArrayList
				ArrayList<Patient> copyPatients = new ArrayList<Patient>(DatabaseHandler.patients);
				copyPatients.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyPatients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyPatients.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			}
		});

		/* ============================ ARANGE PANE ============================ */
		// Create ScrollPane to handle data overflow
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vBox);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setStyle("-fx-background-color: transparent; -fx-background-radius: 20px");

		// Create objects to limit the height and width of the displayed information
		Pane heightLimit = new Pane();
		heightLimit.setPrefSize(1344, 230);
		heightLimit.getChildren().add(HSort);
		HSort.setLayoutX(940);
		HSort.setLayoutY(180);
		VBox rightLimit = new VBox();
		rightLimit.setPrefWidth(180);
		VBox leftLimit = new VBox();
		leftLimit.setPrefWidth(180);

		// Create borderpane to hold all pane
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(scrollPane);
		borderPane.setBottom(HBtn);
		borderPane.setTop(heightLimit);
		borderPane.setRight(rightLimit);
		borderPane.setLeft(leftLimit);
		BorderPane.setAlignment(vBox, Pos.CENTER);
		BorderPane.setAlignment(HBtn, Pos.CENTER);
		BorderPane.setAlignment(heightLimit, Pos.TOP_CENTER);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/patientsInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}

	/*
	 * ============================ REMOVE PATIENT PAGE ============================
	 */
	// Prompt the user to choose the patient the he would like to remove
	public Scene remove(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));

		// HBox to hold Back button
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Patient's ID:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object for choosing id
		ComboBox<String> PatientIdComboBox = new ComboBox<>();
		for (int i = 0; i < DatabaseHandler.patients.size(); i++) {
			PatientIdComboBox.getItems().add(DatabaseHandler.patients.get(i).getId());
		}
		PatientIdComboBox.getSelectionModel().select("Select ID --");
		PatientIdComboBox.setStyle(Style.getComboBoxStyle());

		// Create HBox object
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Create Remove button object
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("  Remove", removeIcon);
		removeButton.setLayoutX(620);
		removeButton.setLayoutY(640);
		removeButton.setAlignment(Pos.CENTER);
		removeButton.setPrefSize(220, 50);
		removeButton.setStyle(Style.getIdleButtonStyle());
		removeButton.setOnMouseEntered(e -> removeButton.setStyle(Style.getHoveredButtonStyle()));
		removeButton.setOnMouseExited(e -> removeButton.setStyle(Style.getIdleButtonStyle()));

		// Create event handling
		PatientIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "ID", "Name", "Disease", "Sex", "Admit Status", "Age" };
			HBox columnLabelHBox = new HBox(10);
			columnLabelHBox.setAlignment(Pos.CENTER);
			columnLabelHBox.setStyle(Style.getHEADERStyle());
			columnLabelHBox.setPrefSize(800, 50);

			for (int i = 0; i < 6; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected Patient's information
			for (int i = 0; i < DatabaseHandler.patients.size(); i++) {
				if (DatabaseHandler.patients.get(i).getId().equals(PatientIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Patient's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Patient's information
					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(DatabaseHandler.patients.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(
								null, "Are you sure you want to remove "
										+ DatabaseHandler.patients.get(index).getName() + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							// Remove item from array list
							DatabaseHandler.patients.remove(index);

							// Remove item from database
							DatabaseHandler.removeDatabase("DELETE FROM Patient WHERE id = " + PatientIdComboBox.getValue());

							JOptionPane.showMessageDialog(null, "Successfully removed!", "Message",
									JOptionPane.INFORMATION_MESSAGE);

							// Check if user want to return to main menu
							int reply2 = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
									JOptionPane.YES_NO_OPTION);

							if (reply2 == JOptionPane.YES_OPTION) {
								primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
							} else {
								primaryStage.setScene(mainPage(primaryStage));
							}
						}
					});
				}
			}
			vBox.getChildren().add(infoVBox);
		});

		// Create VBox to limit the position of information
		VBox leftLimit = new VBox();
		leftLimit.setPrefWidth(210);
		VBox rightLimit = new VBox();
		rightLimit.setPrefWidth(210);

		// Arrange panes and objects
		hBox.getChildren().addAll(label, PatientIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removePatient.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}

	/* ============================ INPUT VALIDATIOn ============================ */
	private boolean validation(TextField[] textFieldArray, String[] inputArray, ArrayList<Patient> arrayList) {

		// Check for empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				setErrorMsg("Input cannot be empty.");
				return false;
			}
		}

		// Check if ID overflow
		if (inputArray[0].length() > 4) {
			textFieldArray[0].clear();
			setErrorMsg("ID must be a non-negative number less than 10000.");
			return false;
		}

		// Check if ID consists of positive numbers only or not
		try {
			Integer.parseInt(inputArray[0]);
			if (Integer.parseInt(inputArray[0]) <= 0) {
				textFieldArray[0].clear();
				setErrorMsg("ID must be a positive number without any spacing.");
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[0].clear();
			setErrorMsg("ID must be a positive number without any spacing.");
			return false;
		}
		return true;
	}

}
