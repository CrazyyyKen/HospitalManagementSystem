import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

public class Staff {

	// Data fields
	private String id;
	private String name;
	private String designation;
	private String sex;
	private int salary;
	private String errorMsg;

	// Default constructor
	public Staff() {
	}

	// Constructor
	public Staff(String id, String name, String designation, String sex, int salary) {
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.sex = sex;
		this.salary = salary;
	}

	// Staff main page
	public static Scene staffPage(Stage primaryStage) {

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
		backButton.setOnAction(e -> primaryStage.setScene(staffPage(primaryStage)));
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

		// Call newStaff method
		addButton.setOnAction(e -> {
			Staff staff = new Staff();
			primaryStage.setScene(staff.newStaff(primaryStage));
		});

		// Call showStaffPage method
		displayButton.setOnAction(e -> {
			if (HospitalManagement.staffs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Staff's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(showStaffPage(primaryStage));
			}
		});

		// Call removeDoctor method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.staffs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Staff's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(removeStaff(primaryStage));
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

	// Prompts user to enter new information of Staff
	public Scene newStaff(Stage primaryStage) {
		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(staffPage(primaryStage)));
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

		// Create text field objects
		TextField idTextField = new TextField();
		idTextField.setStyle(Style.getTextfieldStyle());
		idTextField.setPromptText("123");
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Soh How Ken");
		TextField salaryTextField = new TextField();
		salaryTextField.setStyle(Style.getTextfieldStyle());
		salaryTextField.setPromptText("3000");

		// Create combo box objects
		String[] designationArray = { "Doctor", "Nurse", "Pharmacist", "Human Resources Manager", "Finance Manager",
				"Nurse Manager", "Laboratory Manager", "Maintenance Staff" };
		ComboBox<String> designationComboBox = new ComboBox<>();
		designationComboBox.setStyle(Style.getTextStyle());
		designationComboBox.getItems().addAll(designationArray);
		designationComboBox.getSelectionModel().selectFirst();

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
			TextField[] textFieldArray = {idTextField, nameTextField, salaryTextField};
			
			// Combine String input into an array
			String[] inputArray = {idInput, nameInput, salaryInput, designationInput,sexInput};
			
			// Validate user input
			if(staffValidation(textFieldArray, inputArray, HospitalManagement.staffs)) {
				
				// Assign values to Staff's data field
				id = String.format("%03d", Integer.parseInt(idInput));
				name = nameInput;
				designation = designationInput;
				sex = sexInput;
				salary = Integer.parseInt(salaryInput);
				
				
				// Add Staff object to ArrayList
				HospitalManagement.staffs.add(this);
				
				// Add Staff to Database
				if(HospitalManagement.connect && HospitalManagement.storeData) {
					try {
						Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
						connection.createStatement().executeUpdate(
								"insert into Staff values('" + id + "', '"+ name + "', '"+ designation + "', '"+ sex + "', "+  salary + ")");
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
					primaryStage.setScene(staffPage(primaryStage));
				}

			} else {
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
	public String[] showStaffInfo() {
		String[] output = { id, name, designation, sex, salary + "" };
		return output;
	}
	
	// Show Staff's information page
	public static Scene showStaffPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);

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
		for (int i = 0; i < HospitalManagement.staffs.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 5; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(180);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.staffs.get(i).showStaffInfo()[j]);
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
		// Call StaffPage
		backButton.setOnAction(e -> primaryStage.setScene(staffPage(primaryStage)));
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

				for (int i = 0; i < HospitalManagement.staffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(HospitalManagement.staffs.get(i).showStaffInfo()[j]);
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
				ArrayList<Staff> copyStaffs = new ArrayList<Staff>(HospitalManagement.staffs);
				copyStaffs.sort((o1, o2) -> o1.id.compareTo(o2.id));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyStaffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyStaffs.get(i).showStaffInfo()[j]);
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
				ArrayList<Staff> copyStaffs = new ArrayList<Staff>(HospitalManagement.staffs);
				copyStaffs.sort((o1, o2) -> o1.name.compareTo(o2.name));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyStaffs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyStaffs.get(i).showStaffInfo()[j]);
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
				new Background(new BackgroundImage(new Image("/resources/staffInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}
	
	
	// Remove Staff page
	public static Scene removeStaff(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(staffPage(primaryStage)));
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Staff's ID:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> staffIdComboBox = new ComboBox<>();
		for (int i = 0; i < HospitalManagement.staffs.size(); i++) {
			staffIdComboBox.getItems().add(HospitalManagement.staffs.get(i).id);
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
		staffIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "ID", "Name", "Designation", "Sex", "Salary"  };
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
			for (int i = 0; i < HospitalManagement.staffs.size(); i++) {
				if (HospitalManagement.staffs.get(i).id.equals(staffIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Staff's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Staff's information
					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(HospitalManagement.staffs.get(i).showStaffInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove " + HospitalManagement.staffs.get(index).name + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							HospitalManagement.staffs.remove(index);
							
							// Remove item from database
							if(HospitalManagement.connect && HospitalManagement.storeData) {
								try {
									Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
									connection.createStatement().executeUpdate(
											"DELETE FROM Staff WHERE id = "+staffIdComboBox.getValue());
								} catch (SQLException e1) {
									// Exception Catch
									e1.printStackTrace();
								}
							}
							
							JOptionPane.showMessageDialog(null, "Successfully removed!", "Message",
									JOptionPane.INFORMATION_MESSAGE);

							int reply2 = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
									JOptionPane.YES_NO_OPTION);

							if (reply2 == JOptionPane.YES_OPTION) {
								primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
							} else {
								primaryStage.setScene(staffPage(primaryStage));
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


	// Input validation method
	private boolean staffValidation(TextField[] textFieldArray, String[] inputArray, ArrayList<Staff> arrayList) {
		
		// Check for empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				errorMsg = "Input cannot be empty.";
				return false;
			}
		}
		
		// Check if ID overflow
		if (inputArray[0].length() > 4) {
			textFieldArray[0].clear();
			errorMsg = "ID must be a non-negative number less than 10000.";
			return false;
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
		
		// Check if salary contains non-number value or negative number
		try {
			Integer.parseInt(inputArray[2]);
			if (Integer.parseInt(inputArray[2]) <= 0) {
				textFieldArray[2].clear();
				errorMsg = "Salary must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			textFieldArray[2].clear();
			this.errorMsg = "Salary must be a positive number without any spacing.";
			return false;
		}
		
		// Check if ID is duplicated
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).id.equals(String.format("%03d", Integer.parseInt(inputArray[0])))) {
				textFieldArray[0].clear();
				this.errorMsg = "ID already exists in record.";
				return false;
			}
		}
		return true;
	}
	
	// Getter
	public String getErrorMsg() {
		return errorMsg;
	}
}
