import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
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

public class Doctor {

	// Data fields
	private String id;
	private String name;
	private String specialist;
	private String workTime;
	private String qualification;
	private int room;
	private String errorMsg;

	// Default constructor
	public Doctor() {
	}

	// Constructor
	public Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
		this.id = id;
		this.name = name;
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.room = room;
	}

	// Prompts user to enter information of doctor
	public Scene newDoctor(Stage primaryStage) {

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(doctorPage(primaryStage)));
		backButton.setLayoutX(200);
		backButton.setLayoutY(100);

		// Create label objects
		Label idLabel = new Label("Enter doctor's id: ");
		idLabel.setStyle(Style.getTextStyle());
		Label nameLabel = new Label("Enter doctor's name: ");
		nameLabel.setStyle(Style.getTextStyle());
		Label specialistLabel = new Label("Enter doctor's specialization: ");
		specialistLabel.setStyle(Style.getTextStyle());
		Label workTimeLabel = new Label("Enter doctor's work time: ");
		workTimeLabel.setStyle(Style.getTextStyle());
		Label qualificationLabel = new Label("Enter doctor's qualification: ");
		qualificationLabel.setStyle(Style.getTextStyle());
		Label roomLabel = new Label("Enter doctor's room number: ");
		roomLabel.setStyle(Style.getTextStyle());
		Label dashLabel = new Label("-");
		dashLabel.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, 20));

		// Create text field objects
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextStyle());
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextStyle());
		TextField qualificationTextField = new TextField();
		qualificationTextField.setStyle(Style.getTextStyle());
		TextField roomTextField = new TextField();
		roomTextField.setStyle(Style.getTextStyle());

		// Create spinner objects
		Spinner<Integer> startTime = new Spinner<>(1, 12, 8);
		startTime.setEditable(true);
		startTime.setStyle(Style.getTextStyle());
		startTime.getEditor().setPrefWidth(100);
		Spinner<Integer> endTime = new Spinner<>(1, 12, 11);
		endTime.setEditable(true);
		endTime.setStyle(Style.getTextStyle());
		endTime.getEditor().setPrefWidth(100);

		// Create combo box objects
		String[] specialistArray = { "Physician", "Surgeon", "Pathologist", "Pediatrician", "Dermatologist" };
		ComboBox<String> specialistComboBox = new ComboBox<>();
		specialistComboBox.setStyle(Style.getTextStyle());
		specialistComboBox.getItems().addAll(specialistArray);
		specialistComboBox.getSelectionModel().selectFirst();

		ComboBox<String> workTimeComboBox = new ComboBox<>();
		workTimeComboBox.setStyle(Style.getTextStyle());
		workTimeComboBox.getItems().addAll("AM", "PM");
		workTimeComboBox.getSelectionModel().selectFirst();

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
				id = String.format("%03d", Integer.parseInt(idInput));
				name = "Dr. " + nameInput;
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

		// Create GridPane for Form
		GridPane grid = new GridPane();
		// Position
		grid.setLayoutX(380);
		grid.setLayoutY(260);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(idLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(specialistLabel, 0, 2);
		grid.add(workTimeLabel, 0, 3);
		grid.add(qualificationLabel, 0, 4);
		grid.add(roomLabel, 0, 5);
		// Column 1
		grid.add(idTextField, 1, 0, 4, 1);
		grid.add(nameTextField, 1, 1, 4, 1);
		grid.add(specialistComboBox, 1, 2, 4, 1);
		grid.add(startTime, 1, 3);
		grid.add(dashLabel, 2, 3);
		grid.add(endTime, 3, 3);
		grid.add(workTimeComboBox, 4, 3);
		grid.add(qualificationTextField, 1, 4, 4, 1);
		grid.add(roomTextField, 1, 5, 4, 1);

		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addDoctors.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}

	// Shows the information of doctor
	public String[] showDoctorInfo() {
		String[] output = { id, name, specialist, workTime, qualification, room + "" };
		return output;
	}

	// Doctor main page
	public static Scene doctorPage(Stage primaryStage) {

		// Add New Doctor Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Doctor", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(400, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Doctor's Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Doctors' Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(400, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Display Doctor's Information Button
		Button removeButton = new Button("Remove Doctor");

		// Back to Main Menu Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn.png"));
		Button backButton = new Button("   Back to Main Menu", backIcon);
		backButton.setAlignment(Pos.CENTER_LEFT);
		backButton.setPrefSize(400, 80);
		backButton.setStyle(Style.getIconButtonStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconButtonStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconButtonStyle()));

		// Arrange panes and objects
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(addButton, displayButton, removeButton, backButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setLayoutX(730);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().add(vBox);

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

		// Call removeDoctor method
		removeButton.setOnAction(e -> {
			primaryStage.setScene(removeDoctor(primaryStage));
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/doctors.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	// Show doctor's information page
	public static Scene showDoctorPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
		HBox columnLabelHBox = new HBox(10);
		HBox line = new HBox();

		for (int i = 0; i < 6; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(150);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			text.setFont(Font.font("Courier", FontWeight.BOLD, 17));
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		line.setAlignment(Pos.CENTER);
		line.getChildren().add(new Text(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));

		// Insert HBox object of column label into VBox object
		vBox.getChildren().addAll(columnLabelHBox, line);

		// Create and insert HBox object of each doctor's info into VBox object
		for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.doctors.get(i).showDoctorInfo()[j]);
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
		// Call doctorPage
		backButton.setOnAction(e -> primaryStage.setScene(doctorPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);

		// Arrange panes and objects
		BorderPane borderPane = new BorderPane();
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		borderPane.setBottom(HBtn);
		BorderPane.setAlignment(vBox, Pos.CENTER);
		BorderPane.setAlignment(HBtn, Pos.CENTER);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/doctorsInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}

	// Remove doctor page
	public static Scene removeDoctor(Stage primaryStage) {

		// Create label object
		Label label = new Label("Enter doctor's ID: ");

		// Create combo box object
		ComboBox<String> doctorIdComboBox = new ComboBox<>();
		for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
			doctorIdComboBox.getItems().add(HospitalManagement.doctors.get(i).id);
		}
		doctorIdComboBox.getSelectionModel().select("Select ID --");
		;

		// Create HBox object
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Create event handling
		doctorIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(15);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
			HBox columnLabelHBox = new HBox(10);
			columnLabelHBox.setAlignment(Pos.CENTER);
			for (int i = 0; i < 6; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				stackPane.setPrefHeight(15);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 17));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected doctor's information
			for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
				if (HospitalManagement.doctors.get(i).id.equals(doctorIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for doctor's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);

					// Create button object
					Button removeButton = new Button("Remove");

					// Get doctor's information
					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						stackPane.setPrefHeight(15);
						Text text = new Text(HospitalManagement.doctors.get(i).showDoctorInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove " + HospitalManagement.doctors.get(index).name + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							HospitalManagement.doctors.remove(index);
							JOptionPane.showMessageDialog(null, "Successfully removed!", "Message",
									JOptionPane.INFORMATION_MESSAGE);

							int reply2 = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
									JOptionPane.YES_NO_OPTION);

							if (reply2 == JOptionPane.YES_OPTION) {
								primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
							} else {
								primaryStage.setScene(doctorPage(primaryStage));
							}
						}
					});
				}
			}
			vBox.getChildren().add(infoVBox);
		});

		// Arrange panes and objects
		hBox.getChildren().addAll(label, doctorIdComboBox);
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
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
			if (arrayList.get(i).id.equals(String.format("%03d", Integer.parseInt(idInput)))) {
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
