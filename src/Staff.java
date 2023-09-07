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

/* ============================ STAFF CLASS ============================ */
public class Staff extends Role implements PageManager {

	// Data fields
	private String designation;
	private String sex;
	private int salary;

	// Default constructor
	public Staff() {
		super();
	}

	// Constructor
	public Staff(String id, String name, String designation, String sex, int salary) {
		setId(id);
		setName(name);
		this.designation = designation;
		this.sex = sex;
		this.salary = salary;
	}

	/* ============================ STAFF MAIN PAGE ============================ */
	public Scene mainPage(Stage primaryStage) {

		// Add New Staff Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Staff", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(380, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Add New Doctor Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Staff's Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(380, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Doctor Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Staff", removeIcon);
		removeButton.setAlignment(Pos.CENTER_LEFT);
		removeButton.setPrefSize(380, 80);
		removeButton.setStyle(Style.getIconButtonStyle());
		removeButton.setOnMouseEntered(e -> removeButton.setStyle(Style.getHoveredIconButtonStyle()));
		removeButton.setOnMouseExited(e -> removeButton.setStyle(Style.getIconButtonStyle()));

		// Back to Main Menu Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
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

		// Call add method
		addButton.setOnAction(e -> {
			Staff staff = new Staff();
			primaryStage.setScene(staff.add(primaryStage));
		});

		// Call show method
		displayButton.setOnAction(e -> {
			if (DatabaseHandler.staffs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Staff's record is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(show(primaryStage));
			}
		});

		// Call removeDoctor method
		removeButton.setOnAction(e -> {
			if (DatabaseHandler.staffs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Staff's record is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(remove(primaryStage));
			}
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/staff.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	/* ============================ ADD STAFF ============================ */
	// Prompts user to enter new information of Staff
	public Scene add(Stage primaryStage) {

		// Create back button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		backButton.setLayoutX(190);
		backButton.setLayoutY(100);

		// Create label objects
		Label idLabel = new Label("Enter staff's id : ");
		idLabel.setStyle(Style.getTextStyle());
		Label nameLabel = new Label("Enter staff's name : ");
		nameLabel.setStyle(Style.getTextStyle());
		Label designationLabel = new Label("Enter staff's designation : ");
		designationLabel.setStyle(Style.getTextStyle());
		Label sexLabel = new Label("Enter staff's sex : ");
		sexLabel.setStyle(Style.getTextStyle());
		Label salaryLabel = new Label("Enter staff's salary (RM) : ");
		salaryLabel.setStyle(Style.getTextStyle());

		// Create text field objects for id, name, salary
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextfieldStyle());
		idTextField.setPromptText("123");
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Soh How Ken");
		TextField salaryTextField = new TextField();
		salaryTextField.setStyle(Style.getTextfieldStyle());
		salaryTextField.setPromptText("3000");

		// Create combo box objects for designation
		String[] designationArray = { "Doctor", "Nurse", "Pharmacist", "Human Resources Manager", "Finance Manager",
				"Nurse Manager", "Laboratory Manager", "Maintenance Staff" };
		ComboBox<String> designationComboBox = new ComboBox<>();
		designationComboBox.setStyle(Style.getTextStyle());
		designationComboBox.getItems().addAll(designationArray);
		designationComboBox.getSelectionModel().selectFirst();
		// Create combo box objects for sex
		ComboBox<String> sexComboBox = new ComboBox<>();
		sexComboBox.setStyle(Style.getTextStyle());
		sexComboBox.getItems().addAll("Male", "Female");
		sexComboBox.getSelectionModel().selectFirst();
		sexComboBox.setPrefWidth(250);

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
			String salaryInput = salaryTextField.getText();
			String designationInput = designationComboBox.getValue();
			String sexInput = sexComboBox.getValue();

			// Combine text field into TextField array
			TextField[] textFieldArray = { idTextField, nameTextField, salaryTextField };

			// Combine String input into an array
			String[] inputArray = { idInput, nameInput, salaryInput, designationInput, sexInput };

			// Validate user input
			if (validation(textFieldArray, inputArray, DatabaseHandler.staffs)) {

				// Assign values to Staff's data field
				setId(String.format("%03d", Integer.parseInt(idInput)));
				setName(nameInput);
				designation = designationInput;
				sex = sexInput;
				salary = Integer.parseInt(salaryInput);

				// Add Staff object to ArrayList
				DatabaseHandler.staffs.add(this);

				// Add Staff to Database
				DatabaseHandler.addDatabase("insert into Staff values('" + getId() + "', '"
						+ getName() + "', '" + designation + "', '" + sex + "', " + salary + ")");

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
		grid.setLayoutY(280);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(idLabel, 0, 0);
		grid.add(nameLabel, 0, 1);
		grid.add(designationLabel, 0, 2);
		grid.add(sexLabel, 0, 3);
		grid.add(salaryLabel, 0, 4);
		// Column 1
		grid.add(idTextField, 1, 0);
		grid.add(nameTextField, 1, 1);
		grid.add(designationComboBox, 1, 2);
		grid.add(sexComboBox, 1, 3);
		grid.add(salaryTextField, 1, 4);

		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addStaff.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}

	// Shows the information of Staff
	public String[] showInfo() {
		String[] output = { getId(), getName(), designation, sex, salary + "" };
		return output;
	}

	/* ========================== DISPLAY STAFF INFO ========================== */
	// Show Staff's information page
	public Scene show(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Designation", "Sex", "Salary" };
		HBox columnLabelHBox = new HBox(10);
		columnLabelHBox.setStyle(Style.getHEADERStyle());
		columnLabelHBox.setPrefHeight(50);

		for (int i = 0; i < 5; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(180);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column label into VBox object
		vBox.getChildren().addAll(columnLabelHBox);

		// Create and insert HBox object of each staff's info into VBox object
		for (int i = 0; i < DatabaseHandler.staffs.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 5; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(180);
				stackPane.setPrefHeight(15);
				Text text = new Text(DatabaseHandler.staffs.get(i).showInfo()[j]);
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

		// Create event handling for button to return to Staff page
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

				for (int i = 0; i < DatabaseHandler.staffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(DatabaseHandler.staffs.get(i).showInfo()[j]);
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
				ArrayList<Staff> copyStaffs = new ArrayList<Staff>(DatabaseHandler.staffs);
				copyStaffs.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyStaffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyStaffs.get(i).showInfo()[j]);
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
				ArrayList<Staff> copyStaffs = new ArrayList<Staff>(DatabaseHandler.staffs);
				copyStaffs.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyStaffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyStaffs.get(i).showInfo()[j]);
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
				new Background(new BackgroundImage(new Image("/resources/staffInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}

	/*
	 * ============================ REMOVE STAFF PAGE ============================
	 */
	// Prompt the user to choose the staff the he would like to remove
	public Scene remove(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Staff's ID:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> staffIdComboBox = new ComboBox<>();
		for (int i = 0; i < DatabaseHandler.staffs.size(); i++) {
			staffIdComboBox.getItems().add(DatabaseHandler.staffs.get(i).getId());
		}
		staffIdComboBox.getSelectionModel().select("Select ID --");
		staffIdComboBox.setStyle(Style.getComboBoxStyle());

		// Create HBox object
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Create delete button object
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
		staffIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "ID", "Name", "Designation", "Sex", "Salary" };
			HBox columnLabelHBox = new HBox(10);
			columnLabelHBox.setAlignment(Pos.CENTER);
			columnLabelHBox.setStyle(Style.getHEADERStyle());
			columnLabelHBox.setPrefSize(800, 50);

			for (int i = 0; i < 5; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected Staff's information
			for (int i = 0; i < DatabaseHandler.staffs.size(); i++) {
				if (DatabaseHandler.staffs.get(i).getId().equals(staffIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Staff's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Staff's information
					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(DatabaseHandler.staffs.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(
								null, "Are you sure you want to remove "
										+ DatabaseHandler.staffs.get(index).getName() + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							
							// Remove item from array list
							DatabaseHandler.staffs.remove(index);

							// Remove item from database
							DatabaseHandler.removeDatabase("DELETE FROM Staff WHERE id = " + staffIdComboBox.getValue());

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
		hBox.getChildren().addAll(label, staffIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removeStaff.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}

	/* ============================ INPUT VALIDATIOn ============================ */
	private boolean validation(TextField[] textFieldArray, String[] inputArray, ArrayList<Staff> arrayList) {

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

		// Check if salary contains non-number value or negative number
		try {
			Integer.parseInt(inputArray[2]);
			if (Integer.parseInt(inputArray[2]) <= 0) {
				textFieldArray[2].clear();
				setErrorMsg("Salary must be a positive number without any spacing.");
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[2].clear();
			setErrorMsg("Salary must be a positive number without any spacing.");
			return false;
		}

		// Check if ID is duplicated
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getId().equals(String.format("%03d", Integer.parseInt(inputArray[0])))) {
				textFieldArray[0].clear();
				setErrorMsg("ID already exists in record.");
				return false;
			}
		}
		return true;
	}

}
