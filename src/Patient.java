import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
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

public class Patient {
	Scanner input = new Scanner(System.in);
	private String id;
	private String name;
	private String disease;
	private String sex;
	private String admitStatus;
	private int age;
	private String errorMsg;

	public Patient() {
	}

	public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
		this.id = id;
		this.name = name;
		this.disease = disease;
		this.sex = sex;
		this.admitStatus = admitStatus;
		this.age = age;
	}

	// Patient main page
	public static Scene patientPage(Stage primaryStage) {

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
		backButton.setOnAction(e -> primaryStage.setScene(patientPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(70);

		// Arrange panes and objects
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(addButton, displayButton, removeButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setLayoutX(668);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().addAll(vBox, backButton);

		// Create event handling for buttons

		// Call newPatient method
		addButton.setOnAction(e -> {
			Patient Patient = new Patient();
			primaryStage.setScene(Patient.newPatient(primaryStage));
		});

		// Call showPatientPage method
		displayButton.setOnAction(e -> {
			if (HospitalManagement.patients.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Patient's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(showPatientPage(primaryStage));
			}
		});

		// Call removePatient method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.patients.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Patient's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(removePatient(primaryStage));
			}
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/patient.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	// Prompts user to enter information of Patient
	public Scene newPatient(Stage primaryStage) {

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(patientPage(primaryStage)));
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

		// Create text field objects
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextfieldStyle());
		idTextField.setPromptText("123");
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Goh Kai Wen");
		TextField diseaseTextField = new TextField();
		diseaseTextField.setStyle(Style.getTextfieldStyle());
		diseaseTextField.setPromptText("Fever");
		
		// Sex
		ComboBox<String> sexComboBox = new ComboBox<>();
		sexComboBox.setStyle(Style.getTextStyle());
		sexComboBox.getItems().addAll("Male", "Female");
		sexComboBox.getSelectionModel().selectFirst();
		sexComboBox.setPrefWidth(250);
		
		// Create combo box objects
		String[] admitStatusArray = { "Admitted", "Outpatient", "Emergency Admission", "Scheduled Admission", "Discharged", "Transferred"};
		ComboBox<String> admitStatusComboBox = new ComboBox<>();
		admitStatusComboBox.setStyle(Style.getTextStyle());
		admitStatusComboBox.getItems().addAll(admitStatusArray);
		admitStatusComboBox.getSelectionModel().selectFirst();
		admitStatusComboBox.setPrefWidth(250);
		
		// Create spinner objects
		Spinner<Integer> ageSpinner = new Spinner<>(0, 100, 20);
		ageSpinner.setEditable(true);
		ageSpinner.setStyle(Style.getTextStyle());
		ageSpinner.getEditor().setPrefWidth(250);

		// Create button object
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
			TextField[] textFieldArray = {idTextField, nameTextField, diseaseTextField};
			
			// Combine String input into an array
			String[] inputArray = {idInput, nameInput, diseaseInput, sexInput, admitStatusInput, ageInput};
			
			// Validate user input
			if(patientValidation(textFieldArray, inputArray, HospitalManagement.patients)) {
				
				// Assign values to Patient's data field
				id = String.format("%03d", Integer.parseInt(idInput));
				name = nameInput;
				disease = diseaseInput;
				sex = sexInput;
				admitStatus = admitStatusInput;
				age = Integer.parseInt(ageInput);
				
				// Add patient object to ArrayList
				HospitalManagement.patients.add(this);
				
				// Add patient to Database
				if(HospitalManagement.connect && HospitalManagement.storeData) {
					try {
						Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
						connection.createStatement().executeUpdate(
								"insert into Patient values('" + id + "', '"+ name + "', '"+ disease + "', '"+ sex + "', '"+ admitStatus + "', "+ age + ")");
						connection.close();
					} catch (SQLException e1) {
						// Exception Catch
						e1.printStackTrace();
					}
					
				}

				// Check if user would like to return to previous section or return to main menu
				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);

				int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
				} else {
					primaryStage.setScene(patientPage(primaryStage));
				}

			} else {
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

	// Shows the information of Patient
	public String[] showPatientInfo() {
		String[] output = { id, name, disease, sex, admitStatus, age + "" };
		return output;
	}
	
	// Show Patient's information page
	public static Scene showPatientPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);

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
		for (int i = 0; i < HospitalManagement.patients.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(160);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.patients.get(i).showPatientInfo()[j]);
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
		// Create event handling for button
		// Call patientPage
		backButton.setOnAction(e -> primaryStage.setScene(patientPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);

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

				for (int i = 0; i < HospitalManagement.patients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(HospitalManagement.patients.get(i).showPatientInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			} // Sort by id function
			else if (sortArray[1].equals(sortComboBox.getValue())) {
				// Make a copy of ArrayList
				ArrayList<Patient> copyPatients = new ArrayList<Patient>(HospitalManagement.patients);
				copyPatients.sort((o1, o2) -> o1.id.compareTo(o2.id));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyPatients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyPatients.get(i).showPatientInfo()[j]);
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
				ArrayList<Patient> copyPatients = new ArrayList<Patient>(HospitalManagement.patients);
				copyPatients.sort((o1, o2) -> o1.name.compareTo(o2.name));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyPatients.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(160);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyPatients.get(i).showPatientInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			}
		});

		// Arrange panes and objects
		vBox.setAlignment(Pos.CENTER);
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
	
	
	// Remove Patient page
	public static Scene removePatient(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(patientPage(primaryStage)));
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Patient's ID:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> PatientIdComboBox = new ComboBox<>();
		for (int i = 0; i < HospitalManagement.patients.size(); i++) {
			PatientIdComboBox.getItems().add(HospitalManagement.patients.get(i).id);
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

		// Create button object
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
			String[] columnLabel = { "ID", "Name", "Disease", "Sex", "Admit Status", "Age"  };
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
			for (int i = 0; i < HospitalManagement.patients.size(); i++) {
				if (HospitalManagement.patients.get(i).id.equals(PatientIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Patient's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Patient's information
					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(HospitalManagement.patients.get(i).showPatientInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove " + HospitalManagement.patients.get(index).name + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							HospitalManagement.patients.remove(index);
							JOptionPane.showMessageDialog(null, "Successfully removed!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
							
							// Remove item from database
							if(HospitalManagement.connect && HospitalManagement.storeData) {
								try {
									Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
									connection.createStatement().executeUpdate(
											"DELETE FROM Patient WHERE id = "+PatientIdComboBox.getValue());
								} catch (SQLException e1) {
									// Exception Catch
									e1.printStackTrace();
								}
							}
							

							int reply2 = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
									JOptionPane.YES_NO_OPTION);

							if (reply2 == JOptionPane.YES_OPTION) {
								primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
							} else {
								primaryStage.setScene(patientPage(primaryStage));
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


	// Input validation method
	private boolean patientValidation(TextField[] textFieldArray, String[] inputArray, ArrayList<Patient> arrayList) {
		
		// Check for empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				errorMsg = "Input cannot be empty.";
				return false;
			}
		}
		
		// Check if ID consists of positive numbers only or not
		try {
			Integer.parseInt(inputArray[0]);
			if (Integer.parseInt(inputArray[0]) <= 0) {
				textFieldArray[0].clear();
				this.errorMsg = "ID must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[0].clear();
			this.errorMsg = "ID must be a positive number without any spacing.";
			return false;
		}		
		return true;
	}
	
	// Getter
	public String getErrorMsg() {
		return errorMsg;
	}
}
