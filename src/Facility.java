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

public class Facility extends Hospital implements PageManager {

	// Default constructor
	public Facility() {
		super();
	};

	// Constructor with argument
	public Facility(String name) {
		setName(name);
	}

	// =========================== FACILITY MAIN PAGE ===========================
	public Scene mainPage(Stage primaryStage) {

		// Add New Facility Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Facility", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(400, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Facility's Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display facilities' Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(400, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Facility Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Facility", removeIcon);
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

		// Arrange panes and objects
		VBox vBox = new VBox(30);
		vBox.getChildren().addAll(addButton, displayButton, removeButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setLayoutX(727);
		vBox.setLayoutY(310);
		Pane pane = new Pane();
		pane.getChildren().addAll(vBox, backButton);

		// Create event handling for buttons

		// Call add method
		addButton.setOnAction(e -> {
			Facility Facility = new Facility();
			primaryStage.setScene(Facility.add(primaryStage));
		});

		// Call show method
		displayButton.setOnAction(e -> {
			if (DatabaseHandler.facilities.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Facility's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(show(primaryStage));
			}
		});

		// Call remove method
		removeButton.setOnAction(e -> {
			if (DatabaseHandler.facilities.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Facility's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
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
				new Background(new BackgroundImage(new Image("/resources/facilities.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	// =========================== ADD Facility ===========================
	// Prompts user to enter new information of Facility
	public Scene add(Stage primaryStage) {

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		backButton.setLayoutX(130);
		backButton.setLayoutY(100);

		// Create Facility objects
		Label nameLabel = new Label("Enter Facility's name : ");
		nameLabel.setStyle(Style.getTextfieldStyle());

		// Create text field objects
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Facility 01");

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
			String facilityInput = nameTextField.getText();

			// Validate user input
			if (validation(nameTextField, facilityInput, DatabaseHandler.facilities)) {
				// Assign value to facility object's data field
				setName(facilityInput);

				// Add facility object to ArrayList
				DatabaseHandler.facilities.add(this);

				// Add Facility to Database
				DatabaseHandler.addDatabase("insert into Facility values('" + getName() + "')");

				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);

				// Check if user would like to return to previous section or return to main menu
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

		// Create gridPane for Form
		GridPane grid = new GridPane();
		// Position
		grid.setLayoutX(470);
		grid.setLayoutY(350);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(nameLabel, 0, 0);
		// Column 1
		grid.add(nameTextField, 1, 0);

		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addFacility.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}

	// Shows the information of Facility
	public String[] showInfo() {
		String[] output = { getName() };
		return output;
	}

	/* ========================== DISPLAY DOCTOR INFO ========================== */
	// Show facility's information page
	public Scene show(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create HBox object for column Facility
		String[] columnFacility = { "Facility" };
		HBox columnFacilityHBox = new HBox(10);
		columnFacilityHBox.setStyle(Style.getHEADERStyle());
		columnFacilityHBox.setPrefHeight(50);

		for (int i = 0; i < 1; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(300);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnFacility[i]);
			text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
			stackPane.getChildren().add(text);
			columnFacilityHBox.getChildren().add(stackPane);
		}
		columnFacilityHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column Facility into VBox object
		vBox.getChildren().addAll(columnFacilityHBox);

		// Create and insert HBox object of each Facility's info into VBox object
		for (int i = 0; i < DatabaseHandler.facilities.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 1; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(300);
				stackPane.setPrefHeight(15);
				Text text = new Text(DatabaseHandler.facilities.get(i).showInfo()[j]);
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
		// Call mainPage
		backButton.setOnAction(e -> primaryStage.setScene(mainPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);

		/* ============================ SORTING FUNCTION ============================ */
		// Create combo box objects for sort function
		String[] sortArray = { "Sort By Default", "Sort by Facility" };
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
				vBox.getChildren().addAll(columnFacilityHBox);

				for (int i = 0; i < DatabaseHandler.facilities.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 1; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(400);
						stackPane.setPrefHeight(15);
						Text text = new Text(DatabaseHandler.facilities.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						hBox.getChildren().add(stackPane);
					}

					hBox.setAlignment(Pos.CENTER);
					vBox.getChildren().add(hBox);
				}
			} // Sort by facility function
			else {
				// Make a copy of ArrayList
				ArrayList<Facility> copyfacilities = new ArrayList<Facility>(DatabaseHandler.facilities);
				copyfacilities.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnFacilityHBox);

				for (int i = 0; i < copyfacilities.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 1; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(300);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyfacilities.get(i).showInfo()[j]);
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
		HSort.setLayoutX(864);
		HSort.setLayoutY(180);
		VBox rightLimit = new VBox();
		rightLimit.setPrefWidth(260);
		VBox leftLimit = new VBox();
		leftLimit.setPrefWidth(260);

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
				new Background(new BackgroundImage(new Image("/resources/facilityInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}

	/*
	 * ============================ REMOVE FACILITY PAGE
	 * ============================
	 */
	// Prompt the user to choose the facility the he would like to remove
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
		Label label = new Label("Enter Facility:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> FacilityIdComboBox = new ComboBox<>();
		for (int i = 0; i < DatabaseHandler.facilities.size(); i++) {
			FacilityIdComboBox.getItems().add(DatabaseHandler.facilities.get(i).getName());
		}
		FacilityIdComboBox.getSelectionModel().select("Select Facility --");
		FacilityIdComboBox.setStyle(Style.getComboBoxStyle());

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
		FacilityIdComboBox.setOnAction(e -> {

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

			for (int i = 0; i < 1; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(400);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected Facility's information
			for (int i = 0; i < DatabaseHandler.facilities.size(); i++) {
				if (DatabaseHandler.facilities.get(i).getName().equals(FacilityIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Facility's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Facility's information
					for (int j = 0; j < 1; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(150);
						Text text = new Text(DatabaseHandler.facilities.get(i).showInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(
								null, "Are you sure you want to remove "
										+ DatabaseHandler.facilities.get(index).getName() + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							// Remove item from array list
							DatabaseHandler.facilities.remove(index);

							// Remove item from database
							DatabaseHandler.removeDatabase("DELETE FROM Facility WHERE Facility = '"
									+ FacilityIdComboBox.getValue() + "'");

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
		hBox.getChildren().addAll(label, FacilityIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removeFacility.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}

	/* ============================ INPUT VALIDATIOn ============================ */
	private boolean validation(TextField textField, String input, ArrayList<Facility> arrayList) {
		// Check for empty input
		if (input.isEmpty()) {
			setErrorMsg("Input cannot be empty");
			return false;
		}

		// Check if facility name is duplicated
		for (int i = 0; i < arrayList.size(); ++i) {
			if (arrayList.get(i).getName().equals(input)) {
				textField.clear();
				setErrorMsg("Facility already exists in record");
				return false;
			}
		}

		return true;
	}

}
