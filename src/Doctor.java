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

public class Doctor extends Role implements PageManager {

	// Data fields
	private String specialist;
	private String workTime;
	private String qualification;
	private int room;

	// Default constructor
	public Doctor() {
		super();
	}

	// Constructor
	public Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
		setId(id);
		setName(name);
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.room = room;
	}

	/* ============================ DOCTOR MAIN PAGE ============================ */
	@Override
	public Scene mainPage(Stage primaryStage) {

		// Add New Doctor Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Doctor", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(400, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Doctors' Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Doctors' Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(400, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Doctor Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Doctor", removeIcon);
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
			Doctor doctor = new Doctor();
			primaryStage.setScene(doctor.add(primaryStage));
		});

		// Call show method
		displayButton.setOnAction(e -> {
			if (DatabaseHandler.doctors.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Doctor's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(show(primaryStage));
			}
		});

		// Call remove method
		removeButton.setOnAction(e -> {
			if (DatabaseHandler.doctors.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Doctor's record is empty!", "Warning",
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
		vBox.setLayoutX(728);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().addAll(vBox, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/doctors.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	/* ============================ ADD DOCTOR ============================ */
	// Prompts user to enter new information of doctor
	public Scene add(Stage primaryStage) {

		// Create BACK button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(100);

		// Create Label objects
		Label idLabel = new Label("Enter doctor's id : ");
		idLabel.setStyle(Style.getTextStyle());
		Label nameLabel = new Label("Enter doctor's name : ");
		nameLabel.setStyle(Style.getTextStyle());
		Label specialistLabel = new Label("Enter doctor's specialization : ");
		specialistLabel.setStyle(Style.getTextStyle());
		Label workTimeLabel = new Label("Enter doctor's work time : ");
		workTimeLabel.setStyle(Style.getTextStyle());
		Label qualificationLabel = new Label("Enter doctor's qualification : ");
		qualificationLabel.setStyle(Style.getTextStyle());
		Label roomLabel = new Label("Enter doctor's room number : ");
		roomLabel.setStyle(Style.getTextStyle());
		Label dashLabel = new Label("-");
		dashLabel.setFont(Font.font("Courier", FontWeight.EXTRA_BOLD, 20));

		// Create text field objects for ID, Name, Qualification and Room Number
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextfieldStyle());
		idTextField.setPromptText("123");
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Goh Kai Wen");
		TextField qualificationTextField = new TextField();
		qualificationTextField.setStyle(Style.getTextfieldStyle());
		qualificationTextField.setPromptText("MBBS, MD");
		TextField roomTextField = new TextField();
		roomTextField.setStyle(Style.getTextfieldStyle());
		roomTextField.setPromptText("001");

		// Create spinner objects for WorkTime
		Spinner<Integer> startTime = new Spinner<>(1, 12, 8);
		startTime.setEditable(true);
		startTime.setStyle(Style.getTextStyle());
		startTime.getEditor().setPrefWidth(100);
		Spinner<Integer> endTime = new Spinner<>(1, 12, 11);
		endTime.setEditable(true);
		endTime.setStyle(Style.getTextStyle());
		endTime.getEditor().setPrefWidth(100);

		// Create combo box objects for Specialist
		String[] specialistArray = { "Physician", "Surgeon", "Pathologist", "Pediatrician", "Dermatologist" };
		ComboBox<String> specialistComboBox = new ComboBox<>();
		specialistComboBox.setStyle(Style.getTextStyle());
		specialistComboBox.getItems().addAll(specialistArray);
		specialistComboBox.getSelectionModel().selectFirst();
		specialistComboBox.setPrefWidth(350);
		// Combo box for workTime (AM & PM)
		ComboBox<String> workTimeComboBox = new ComboBox<>();
		workTimeComboBox.setStyle(Style.getTextStyle());
		workTimeComboBox.getItems().addAll("AM", "PM");
		workTimeComboBox.getSelectionModel().selectFirst();

		// Create Add button object
		Button addButton = new Button("Add");
		addButton.setLayoutX(620);
		addButton.setLayoutY(640);
		addButton.setPrefSize(150, 50);
		addButton.setStyle(Style.getIdleButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIdleButtonStyle()));

		// Create event handling for Add button
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
			if (validation(textFieldArray, inputArray, DatabaseHandler.doctors)) {

				// Assign value to doctor object's data fields
				setId(String.format("%03d", Integer.parseInt(idInput)));
				setName("Dr. " + nameInput);
				specialist = specialistInput;
				workTime = workTimeInput;
				qualification = qualificationInput;
				room = Integer.parseInt(roomInput);

				// Add doctor object to ArrayList
				DatabaseHandler.doctors.add(this);

				// Add doctor to Database
				DatabaseHandler.addDatabase("insert into Doctor values('" + getId() + "', '" + getName()
						+ "', '" + specialist + "', '" + workTime + "', '" + qualification + "', " + room + ")");
				

				// Check if user would like to return to previous section or return to main menu
				JOptionPane.showMessageDialog(null, "Successfully added!", "Success Message",
						JOptionPane.INFORMATION_MESSAGE);

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
	public String[] showInfo() {
		String[] output = { getId(), getName(), specialist, workTime, qualification, room + "" };
		return output;
	}

	/* ========================== DISPLAY DOCTOR INFO ========================== */
	// Show doctor's information page
	public Scene show(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
		HBox columnLabelHBox = new HBox(10);
		columnLabelHBox.setStyle(Style.getHEADERStyle());
		columnLabelHBox.setPrefHeight(50);

		// Insert columnLabel into HBox
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

		// Create and insert HBox object of each doctor's info into VBox object
		for (int i = 0; i < DatabaseHandler.doctors.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(160);
				stackPane.setPrefHeight(15);
				Text text = new Text(DatabaseHandler.doctors.get(i).showInfo()[j]);
				text.setStyle(Style.getTextStyle());
				stackPane.getChildren().add(text);
				hBox.getChildren().add(stackPane);
			}

			hBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(hBox);
		}

		// Create Back button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn.png"));
		Button backButton = new Button(" Back", backIcon);
		backButton.setStyle(Style.getIconButtonStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconButtonStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconButtonStyle()));

		// Create event handling for Back button to return to doctor main page
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

				for (int i = 0; i < DatabaseHandler.doctors.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						stackPane.setPrefHeight(15);
						Text text = new Text(DatabaseHandler.doctors.get(i).showInfo()[j]);
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
				ArrayList<Doctor> copyDoctors = new ArrayList<Doctor>(DatabaseHandler.doctors);
				copyDoctors.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyDoctors.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyDoctors.get(i).showInfo()[j]);
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
				ArrayList<Doctor> copyDoctors = new ArrayList<Doctor>(DatabaseHandler.doctors);
				copyDoctors.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyDoctors.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyDoctors.get(i).showInfo()[j]);
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
				new Background(new BackgroundImage(new Image("/resources/doctorsInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}

	/*
	 * ============================ REMOVE DOCTOR PAGE ============================
	 */
	// Prompt the user to choose the doctor the he would like to remove
	public Scene remove(Stage primaryStage) {

		// Create Back Button
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
		Label label = new Label("Enter doctor's ID:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object for choosing id
		ComboBox<String> doctorIdComboBox = new ComboBox<>();
		for (int i = 0; i < DatabaseHandler.doctors.size(); i++) {
			doctorIdComboBox.getItems().add(DatabaseHandler.doctors.get(i).getId());
		}
		doctorIdComboBox.getSelectionModel().select("Select ID --");
		doctorIdComboBox.setStyle(Style.getComboBoxStyle());

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
		doctorIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
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

			// Display selected doctor's information
			for (int i = 0; i < DatabaseHandler.doctors.size(); i++) {
				if (DatabaseHandler.doctors.get(i).getId().equals(doctorIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for doctor's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get doctor's information
					for (int j = 0; j < 6; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(DatabaseHandler.doctors.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(
								null, "Are you sure you want to remove "
										+ DatabaseHandler.doctors.get(index).getName() + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							// Remove item from array list
							DatabaseHandler.doctors.remove(index);

							// Remove item from database
							DatabaseHandler.removeDatabase("DELETE FROM Doctor WHERE id = " + doctorIdComboBox.getValue());
							
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
		hBox.getChildren().addAll(label, doctorIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removeDoctor.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}

	/* ============================ INPUT VALIDATIOn ============================ */
	private boolean validation(TextField[] textFieldArray, String[] inputArray, ArrayList<Doctor> arrayList) {

		// Initialization of data fields
		String idInput = inputArray[0];
		String roomInput = inputArray[3];

		// Check empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				setErrorMsg("Input cannot be empty.");
				return false;
			}
		}
		// Check if ID overflow
		if (idInput.length() > 4) {
			textFieldArray[0].clear();
			setErrorMsg("ID must be a non-negative number less than 10000.");
			return false;
		}
		// Check if Room Number overflow
		if (roomInput.length() > 4) {
			textFieldArray[3].clear();
			setErrorMsg("Room number must be a non-negative number less than 10000.");
			return false;
		}

		// Check if ID consists of positive numbers only or not
		try {
			Integer.parseInt(idInput);
			if (Integer.parseInt(idInput) <= 0) {
				textFieldArray[0].clear();
				setErrorMsg("ID must be a non-negative number without any spacing.");
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[0].clear();
			setErrorMsg("ID must be a non-negative number without any spacing.");
			return false;
		}

		// Check if room number consists of positive numbers only or not
		try {
			Integer.parseInt(roomInput);
			if (Integer.parseInt(roomInput) <= 0) {
				textFieldArray[3].clear();
				setErrorMsg("Room number must be a non-negative number without any spacing.");
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[3].clear();
			setErrorMsg("Room number must be a non-negative number without any spacing.");
			return false;
		}

		// Check if ID and room number is duplicated or not
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(String.format("%03d", Integer.parseInt(idInput)))) {
				textFieldArray[0].clear();
				setErrorMsg("ID already exists in record.");
				return false;
			}
			if (Integer.toString(arrayList.get(i).room).equals(inputArray[3])) {
				textFieldArray[3].clear();
				setErrorMsg("This room is occupied.");
				return false;
			}
		}

		return true;

	}

}