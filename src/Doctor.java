import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Doctor extends Person {

	// Data fields
	private String specialist;
	private String workTime;
	private String qualification;
	private int room;
	private String errorMsg;

	// Default constructor
	public Doctor() {
		super();
	}

	// Constructor
	public Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
		super(id, name);
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.room = room;
	}

	public String getId() {
		return super.getId();
	}

	public String getName() {
		return super.getName();
	}

	public String getSpecialist() {
		return specialist;
	}

	public String getWorkTime() {
		return workTime;
	}

	public String getQualification() {
		return qualification;
	}

	public int getRoom() {
		return room;
	}

	// Prompts user to enter information of doctor
	public Scene newDoctor(Stage primaryStage) {

		// Create label objects
		Label idLabel = new Label("Enter doctor's id: ");
		Label nameLabel = new Label("Enter doctor's name: ");
		Label specialistLabel = new Label("Enter doctor's specialization: ");
		Label workTimeLabel = new Label("Enter doctor's work time: ");
		Label qualificationLabel = new Label("Enter doctor's qualification: ");
		Label roomLabel = new Label("Enter doctor's room number: ");
		Label dashLabel = new Label("-");

		// Create text field objects
		TextField idTextField = new TextField();
		TextField nameTextField = new TextField();
		TextField qualificationTextField = new TextField();
		TextField roomTextField = new TextField();

		// Create spinner objects
		Spinner<Integer> startTime = new Spinner<>(1, 12, 8);
		startTime.setEditable(true);
		Spinner<Integer> endTime = new Spinner<>(1, 12, 11);
		endTime.setEditable(true);

		// Create combo box objects
		String[] specialistArray = { "Physician", "Surgeon", "Pathologist", "Pediatrician", "Dermatologist" };
		ComboBox<String> specialistComboBox = new ComboBox<>();
		specialistComboBox.getItems().addAll(specialistArray);
		specialistComboBox.getSelectionModel().selectFirst();

		ComboBox<String> workTimeComboBox = new ComboBox<>();
		workTimeComboBox.getItems().addAll("AM", "PM");
		workTimeComboBox.getSelectionModel().selectFirst();

		// Create button object
		Button addButton = new Button("Add");

		// Create HBox objects
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		HBox hBox4 = new HBox();
		HBox hBox5 = new HBox();
		HBox hBox6 = new HBox();

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		hBox1.getChildren().addAll(idLabel, idTextField);
		hBox1.setAlignment(Pos.CENTER);

		hBox2.getChildren().addAll(nameLabel, nameTextField);
		hBox2.setAlignment(Pos.CENTER);

		hBox3.getChildren().addAll(specialistLabel, specialistComboBox);
		hBox3.setAlignment(Pos.CENTER);

		hBox4.getChildren().addAll(workTimeLabel, startTime, dashLabel, endTime, workTimeComboBox);
		hBox4.setAlignment(Pos.CENTER);

		hBox5.getChildren().addAll(qualificationLabel, qualificationTextField);
		hBox5.setAlignment(Pos.CENTER);

		hBox6.getChildren().addAll(roomLabel, roomTextField);
		hBox6.setAlignment(Pos.CENTER);

		vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, addButton);
		vBox.setAlignment(Pos.CENTER);

		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for button
		addButton.setOnAction(e -> {

			// Initialization of data fields
			String idInput = idTextField.getText();
			String nameInput = nameTextField.getText();
			String specialistInput = specialistComboBox.getValue();
			String workTimeInput = Integer.toString(startTime.getValue()) + "-" + Integer.toString(endTime.getValue())
					+ workTimeComboBox.getValue();
			String qualificationInput = qualificationTextField.getText();
			String roomInput = roomTextField.getText();

			// Create text field array
			TextField[] textFieldArray = { idTextField, nameTextField, qualificationTextField, roomTextField };

			// Create string array
			String[] inputArray = { idInput, nameInput, qualificationInput, roomInput };

			// Validate user inputs
			if (doctorValidation(textFieldArray, inputArray, HospitalManagement.doctors)) {

				// Assign value to doctor object's data fields
				super.setId(String.format("%03d", Integer.parseInt(idInput)));
				super.setName("Dr. " + nameInput);
				specialist = specialistInput;
				workTime = workTimeInput;
				qualification = qualificationInput;
				room = Integer.parseInt(roomInput);

				// Add doctor object to ArrayList
				HospitalManagement.doctors.add(this);

				// Check if user would like to return to previous section or return to main menu
				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);

				int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION) {
					primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
				} else {
					primaryStage.setScene(doctorPage(primaryStage));
				}

			} else {
				JOptionPane.showMessageDialog(null, getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
			}

		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;

	}

	// Shows the information of doctor
	public String[] showDoctorInfo() {
		String[] output = { getId(), getName(), specialist, workTime, qualification, room + "" };
		return output;
	}

	// Doctor main page
	public static Scene doctorPage(Stage primaryStage) {

		// Create text object
		Text menuTitle = new Text("Doctors");
		menuTitle.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		// Create button objects
		Button addButton = new Button("Add new doctor");
		Button displayButton = new Button("Display doctors' information");
		Button backButton = new Button("Back to main menu");

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		vBox.getChildren().addAll(menuTitle, addButton, displayButton, backButton);
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for buttons

		// Call newDoctor method
		addButton.setOnAction(e -> {
			Doctor doctor = new Doctor();
			primaryStage.setScene(doctor.newDoctor(primaryStage));
		});

		// Call showDoctorPage method
		displayButton.setOnAction(e -> {

			primaryStage.setScene(showDoctorPage(primaryStage));

		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}
	
	// Show doctor's information page
	public static Scene showDoctorPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
		HBox columnLabelHBox = new HBox(10);

		for (int i = 0; i < 6; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(150);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column label into VBox object
		vBox.getChildren().add(columnLabelHBox);

		// Create and insert HBox object of each doctor's info into VBox object
		for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.doctors.get(i).showDoctorInfo()[j]);
				stackPane.getChildren().add(text);
				hBox.getChildren().add(stackPane);
			}

			hBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(hBox);
		}

		// Create button object
		Button backButton = new Button("Back");

		// Arrange panes and objects
		vBox.getChildren().add(backButton);
		BorderPane borderPane = new BorderPane();
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for button
		// Call doctorPage
		backButton.setOnAction(e -> {
			primaryStage.setScene(doctorPage(primaryStage));
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}

	// Input validation
	public boolean doctorValidation(TextField[] textFieldArray, String[] inputArray, ArrayList<Doctor> arrayList) {

		// Initialization of data fields
		String idInput = inputArray[0];
		String nameInput = inputArray[1];
		String qualificationInput = inputArray[2];
		String roomInput = inputArray[3];

		// Check empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				this.errorMsg = "Input cannot be empty.";
				return false;
			}
		}

		// Check if ID consists of positive numbers only or not
		try {
			Integer.parseInt(idInput);
			if (Integer.parseInt(idInput) <= 0) {
				textFieldArray[3].clear();
				this.errorMsg = "ID must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[0].clear();
			this.errorMsg = "ID must be a positive number without any spacing.";
			return false;
		}

		// Check if room number consists of positive numbers only or not
		try {
			Integer.parseInt(roomInput);
			if (Integer.parseInt(roomInput) <= 0) {
				textFieldArray[3].clear();
				this.errorMsg = "Room number must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[3].clear();
			this.errorMsg = "Room number must be a positive number without any spacing.";
			return false;
		}

		// Check if ID and room number is duplicated or not
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(String.format("%03d", Integer.parseInt(idInput)))) {
				textFieldArray[0].clear();
				this.errorMsg = "ID already exists in record.";
				return false;
			}
			if (Integer.toString(arrayList.get(i).room).equals(inputArray[3])) {
				textFieldArray[3].clear();
				this.errorMsg = "This room is occupied.";
				return false;
			}
		}

		return true;

	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
