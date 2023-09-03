import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
				//primaryStage.setScene(showPatientPage(primaryStage));
			}
		});

		// Call removePatient method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.patients.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Patient's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				//primaryStage.setScene(removePatient(primaryStage));
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
			// Action

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

	public void newPatient() {
		System.out.print("Enter patient's ID: ");
		id = input.nextLine();
		System.out.print("Enter patient's name: ");
		name = input.nextLine();
		System.out.print("Enter patient's disease: ");
		disease = input.nextLine();
		System.out.print("Enter patient's sex: ");
		sex = input.nextLine();
		System.out.print("Enter patient's admit status: ");
		admitStatus = input.nextLine();
		System.out.print("Enter patient's age: ");
		age = input.nextInt();
	}

	// Shows the information of Patient
	public void showPatientInfo() {
		String format = "%-5s %-20s %-20s %-10s %-15s %-5d";
		String output = String.format(format, id, name, disease, sex, admitStatus, age);
		System.out.println(output);
	}

}
