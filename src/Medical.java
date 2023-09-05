import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

public class Medical {
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int unit;
	private String errorMsg; // Only used for input validation

	public Medical() {
	};

	public Medical(String name, String manufacturer, String expiryDate, int cost, int unit) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.expiryDate = expiryDate;
		this.cost = cost;
		this.unit = unit;
	}

	// Medical main page
	public static Scene medicalPage(Stage primaryStage) {

		// Add New Medical Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Medical", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(410, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Medical's Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Medicals' Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(410, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Medical Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Medical", removeIcon);
		removeButton.setAlignment(Pos.CENTER_LEFT);
		removeButton.setPrefSize(410, 80);
		removeButton.setStyle(Style.getIconButtonStyle());
		removeButton.setOnMouseEntered(e -> removeButton.setStyle(Style.getHoveredIconButtonStyle()));
		removeButton.setOnMouseExited(e -> removeButton.setStyle(Style.getIconButtonStyle()));

		// Back to Main Menu Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(medicalPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(70);

		// Arrange panes and objects
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(addButton, displayButton, removeButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setLayoutX(655);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().addAll(vBox, backButton);

		// Create event handling for buttons

		// Call newMedical method
		addButton.setOnAction(e -> {
			Medical Medical = new Medical();
			primaryStage.setScene(Medical.newMedical(primaryStage));
		});

		// Call showmedicalPage method
		displayButton.setOnAction(e -> {
			if (HospitalManagement.medicals.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Medical's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(showMedicalPage(primaryStage));
			}
		});

		// Call removeMedical method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.medicals.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Medical's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(removeMedical(primaryStage));
			}
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/medicals.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	// Prompts user to enter information of Medical
	public Scene newMedical(Stage primaryStage) {

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(medicalPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(100);

		// Create label objects
		Label nameLabel = new Label("Enter Medical's name : ");
		nameLabel.setStyle(Style.getTextStyle());
		Label manufacturerLabel = new Label("Enter the manufacturer : ");
		manufacturerLabel.setStyle(Style.getTextStyle());
		Label expiryDateLabel = new Label("Enter the Expiry Date : ");
		expiryDateLabel.setStyle(Style.getTextStyle());
		Label costLabel = new Label("Enter the cost (RM) : ");
		costLabel.setStyle(Style.getTextStyle());
		Label unitLabel = new Label("Enter the number of units : ");
		unitLabel.setStyle(Style.getTextStyle());

		// Create text field objects
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Antibiotic");
		TextField manufacturerTextField = new TextField();
		manufacturerTextField.setStyle(Style.getTextfieldStyle());
		manufacturerTextField.setPromptText("Medtronic");
		TextField costTextField = new TextField();
		costTextField.setStyle(Style.getTextfieldStyle());
		costTextField.setPromptText("100");

		// Create spinner objects (UNIT)
		Spinner<Integer> unit = new Spinner<>(0, 100000, 5);
		unit.setEditable(true);
		unit.setStyle(Style.getTextStyle());
		unit.getEditor().setPrefWidth(100);
		unit.setPrefWidth(230);

		// DatePicker
		DatePicker datePicker = new DatePicker();
		datePicker.setStyle(Style.getTextStyle());
		// Set today's date as the default value
		datePicker.setValue(LocalDate.now());
		datePicker.setShowWeekNumbers(true);	// show week numbers
		
		// To get the date picker value
		// LocalDate date = datePicker.getValue();
		// TO DO: FOR OUTPUT
		
		// Create button object
		Button addButton = new Button("Add");
		addButton.setLayoutX(620);
		addButton.setLayoutY(640);
		addButton.setPrefSize(150, 50);
		addButton.setStyle(Style.getIdleButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIdleButtonStyle()));

		// Create event handling for button
		addButton.setOnAction(e -> {
			// Initialization of data field
			String nameInput = nameTextField.getText();
			String manufacturerInput = manufacturerTextField.getText();
			String costInput = costTextField.getText();

			// Combine text fields into TextField array
			TextField textFieldArray[] = { nameTextField, manufacturerTextField, costTextField };

			// Combine String input into an array
			String inputArray[] = { nameInput, manufacturerInput, costInput };

			// Validate user input
			if (medicalValidation(textFieldArray, inputArray, HospitalManagement.medicals)) {
				// Assign value to data field
				name = nameInput;
				manufacturer = manufacturerInput;
				LocalDate selectedDate = datePicker.getValue();
				expiryDate = selectedDate.toString();
				cost = Integer.parseInt(costInput);
				this.unit = unit.getValue();

				// Add facility object to ArrayList
				HospitalManagement.medicals.add(this);
				
				// Add Medical to Database
				if(HospitalManagement.connect && HospitalManagement.storeData) {
					try {
						Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
						connection.createStatement().executeUpdate(
								"insert into Medical values('" + name + "', '"+ manufacturer + "', '"+ expiryDate + "', "+ cost + ", "+  this.unit + ")");
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
					primaryStage.setScene(medicalPage(primaryStage));
				}
			} else {
				JOptionPane.showMessageDialog(null, getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		// Create gridPane for Form
		GridPane grid = new GridPane();
		// Position
		grid.setLayoutX(445);
		grid.setLayoutY(280);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(nameLabel, 0, 0);
		grid.add(manufacturerLabel, 0, 1);
		grid.add(expiryDateLabel, 0, 2);
		grid.add(costLabel, 0, 3);
		grid.add(unitLabel, 0, 4);
		// Column 1
		grid.add(nameTextField, 1, 0);
		grid.add(manufacturerTextField, 1, 1);
		grid.add(datePicker, 1, 2);
		grid.add(costTextField, 1, 3);
		grid.add(unit, 1, 4);

		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addMedicals.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}
	
	
	// Shows the information of Medical
	public String[] showMedicalInfo() {
		String[] output = { name, manufacturer, expiryDate, cost + "", unit + "" };
		return output;
	}
	
	// Show Medical's information page
	public static Scene showMedicalPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);

		// Create HBox object for column label
		String[] columnLabel = { "Name", "Manufacturer", "Expiry Date", "Cost", "Unit" };
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

		// Create and insert HBox object of each Medical's info into VBox object
		for (int i = 0; i < HospitalManagement.medicals.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 5; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(180);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.medicals.get(i).showMedicalInfo()[j]);
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
		// Call MedicalPage
		backButton.setOnAction(e -> primaryStage.setScene(medicalPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);

		// Create combo box objects for sort function
		String[] sortArray = { "Sort By Default", "Sort by Name", "Sort by Unit" };
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

				for (int i = 0; i < HospitalManagement.medicals.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(HospitalManagement.medicals.get(i).showMedicalInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			} // Sort by name function
			else if (sortArray[1].equals(sortComboBox.getValue())) {
				// Make a copy of ArrayList
				ArrayList<Medical> copyMedicals = new ArrayList<Medical>(HospitalManagement.medicals);
				copyMedicals.sort((o1, o2) -> o1.name.compareTo(o2.name));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyMedicals.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyMedicals.get(i).showMedicalInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			}
			// Sort by Unit function
			else {
				// Make a copy of ArrayList
				ArrayList<Medical> copyMedicals = new ArrayList<Medical>(HospitalManagement.medicals);
				copyMedicals.sort((o1, o2) -> Integer.toString(o1.unit).compareTo(Integer.toString(o2.unit)));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyMedicals.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(180);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyMedicals.get(i).showMedicalInfo()[j]);
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
				new Background(new BackgroundImage(new Image("/resources/medicalInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}
	
	
	// Remove Medical page
	public static Scene removeMedical(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(medicalPage(primaryStage)));
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Medical's Name:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> medIdComboBox = new ComboBox<>();
		for (int i = 0; i < HospitalManagement.medicals.size(); i++) {
			medIdComboBox.getItems().add(HospitalManagement.medicals.get(i).name);
		}
		medIdComboBox.getSelectionModel().select("Select Name --");
		medIdComboBox.setStyle(Style.getComboBoxStyle());

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
		medIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "Name", "Manufacturer", "Expiry Date", "Cost", "Unit" };
			HBox columnLabelHBox = new HBox(10);
			columnLabelHBox.setAlignment(Pos.CENTER);
			columnLabelHBox.setStyle(Style.getHEADERStyle());
			columnLabelHBox.setPrefSize(800, 50);
			
			for (int i = 0; i < 5; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(250);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected Medical's information
			for (int i = 0; i < HospitalManagement.medicals.size(); i++) {
				if (HospitalManagement.medicals.get(i).name.equals(medIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Medical's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Medical's information
					for (int j = 0; j < 5; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(250);
						Text text = new Text(HospitalManagement.medicals.get(i).showMedicalInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove " + HospitalManagement.medicals.get(index).name + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							HospitalManagement.medicals.remove(index);
							
							// Remove item from database
							if(HospitalManagement.connect && HospitalManagement.storeData) {
								try {
									Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
									connection.createStatement().executeUpdate(
											"DELETE FROM Medical WHERE name = '"+ medIdComboBox.getValue()+ "'");
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
								primaryStage.setScene(medicalPage(primaryStage));
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
		hBox.getChildren().addAll(label, medIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removeMedical.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}
	

	// Input validation method
	private boolean medicalValidation(TextField[] textField, String[] inputArray, ArrayList<Medical> arrayList) {

		// Check empty input
		for (int i = 0; i < inputArray.length; i++) {
			if (inputArray[i].isEmpty()) {
				errorMsg = "Input cannot be empty.";
				return false;
			}
		}

		// Check if cost contains non-number value or negative number
		try {
			Integer.parseInt(inputArray[2]);
			if (Integer.parseInt(inputArray[2]) <= 0) {
				textField[2].clear();
				errorMsg = "Cost must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			textField[2].clear();
			this.errorMsg = "Cost must be a positive number without any spacing.";
			return false;
		}

		return true;
	}

	// Getter
	public String getErrorMsg() {
		return errorMsg;
	}
}
