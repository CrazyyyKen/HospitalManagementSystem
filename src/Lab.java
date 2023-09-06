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

public class Lab {
	private String lab;
	private int cost;
	private String errorMsg; // Only used for input validation

	public Lab() {
	};

	public Lab(String lab, int cost) {
		this.lab = lab;
		this.cost = cost;
	}

	/* ============================ LAB MAIN PAGE ============================ */
	public static Scene labPage(Stage primaryStage) {

		// Add New Lab Button
		ImageView addIcon = new ImageView(new Image("/resources/add.png"));
		Button addButton = new Button("   Add New Lab", addIcon);
		addButton.setAlignment(Pos.CENTER_LEFT);
		addButton.setPrefSize(400, 80);
		addButton.setStyle(Style.getIconButtonStyle());
		addButton.setOnMouseEntered(e -> addButton.setStyle(Style.getHoveredIconButtonStyle()));
		addButton.setOnMouseExited(e -> addButton.setStyle(Style.getIconButtonStyle()));

		// Display Lab's Information Button
		ImageView displayIcon = new ImageView(new Image("/resources/info.png"));
		Button displayButton = new Button("   Display Labs' Information", displayIcon);
		displayButton.setAlignment(Pos.CENTER_LEFT);
		displayButton.setPrefSize(400, 80);
		displayButton.setStyle(Style.getIconButtonStyle());
		displayButton.setOnMouseEntered(e -> displayButton.setStyle(Style.getHoveredIconButtonStyle()));
		displayButton.setOnMouseExited(e -> displayButton.setStyle(Style.getIconButtonStyle()));

		// Remove Lab Button
		ImageView removeIcon = new ImageView(new Image("/resources/delete.png"));
		Button removeButton = new Button("   Remove Lab", removeIcon);
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
		backButton.setOnAction(e -> primaryStage.setScene(labPage(primaryStage)));
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

		// Call newLab method
		addButton.setOnAction(e -> {
			Lab Lab = new Lab();
			primaryStage.setScene(Lab.newLab(primaryStage));
		});

		// Call showLabPage method
		displayButton.setOnAction(e -> {
			if (HospitalManagement.laboratories.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lab's record is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(showLabPage(primaryStage));
			}
		});

		// Call removeLab method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.laboratories.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lab's record is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				primaryStage.setScene(removeLab(primaryStage));
			}
		});

		// Call mainMenuPage method
		backButton.setOnAction(e -> {
			primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
		});

		// Set Background image
		pane.setBackground(new Background(new BackgroundImage(new Image("/resources/labs.png"), BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

	/* ============================ ADD LAB ============================ */
	// Prompts user to enter new information of LAB
	public Scene newLab(Stage primaryStage) {

		// Create button object
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(labPage(primaryStage)));
		backButton.setLayoutX(150);
		backButton.setLayoutY(100);

		// Create label objects
		Label nameLabel = new Label("Enter Lab's name : ");
		nameLabel.setStyle(Style.getTextStyle());
		Label costLabel = new Label("Enter the cost (RM) : ");
		costLabel.setStyle(Style.getTextStyle());

		// Create text field objects
		TextField nameTextField = new TextField();
		nameTextField.setStyle(Style.getTextfieldStyle());
		nameTextField.setPromptText("Lab 01");
		TextField costTextField = new TextField();
		costTextField.setStyle(Style.getTextfieldStyle());
		costTextField.setPromptText("100");

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
			String labNameInput = nameTextField.getText();
			String costInput = costTextField.getText();
			
			// Validate user input
			if(labValidation(nameTextField, costTextField, labNameInput, costInput, HospitalManagement.laboratories)) {
				// Assign value to facility object's data field
				lab = labNameInput;
				cost = Integer.parseInt(costInput);
			
				// Add facility object to ArrayList
				HospitalManagement.laboratories.add(this);
				
				// Add Lab to Database
				if(HospitalManagement.connect && HospitalManagement.storeData) {
					try {
						Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
						connection.createStatement().executeUpdate(
								"insert into Lab values('" + lab + "', " +  cost + ")");
						connection.close();
					} catch (SQLException e1) {
						// Exception Catch
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);
				
				// Check if user would like to return to previous section or return to main menu
				int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
						JOptionPane.YES_NO_OPTION);
	
				if (reply == JOptionPane.YES_OPTION) {
					primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
				} else {
					primaryStage.setScene(labPage(primaryStage));
				}
			}
			else {
				// Show warning message when user enter wrong inputs
				JOptionPane.showMessageDialog(null, getErrorMsg(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});

		// Create gridPane for Form
		GridPane grid = new GridPane();
		// Position
		grid.setLayoutX(470);
		grid.setLayoutY(330);
		grid.setHgap(20);
		grid.setVgap(20);
		// Column 0
		grid.add(nameLabel, 0, 0);
		grid.add(costLabel, 0, 1);

		// Column 1
		grid.add(nameTextField, 1, 0);
		grid.add(costTextField, 1, 1);


		// Create pane object
		Pane pane = new Pane();
		pane.getChildren().addAll(grid, addButton, backButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/addLab.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;

	}

	// Shows the information of Lab
	public String[] showLabInfo() {
		String[] output = { lab, cost + "" };
		return output;
	}
	
	/* ========================== DISPLAY LAB INFO ========================== */
	// Show lab's information page
	public static Scene showLabPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(15);
		vBox.setAlignment(Pos.CENTER);

		// Create HBox object for column label
		String[] columnLabel = { "Lab", "Cost"};
		HBox columnLabelHBox = new HBox(10);
		columnLabelHBox.setStyle(Style.getHEADERStyle());
		columnLabelHBox.setPrefHeight(50);

		for (int i = 0; i < 2; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(200);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column label into VBox object
		vBox.getChildren().addAll(columnLabelHBox);

		// Create and insert HBox object of each Lab's info into VBox object
		for (int i = 0; i < HospitalManagement.laboratories.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 2; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(200);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.laboratories.get(i).showLabInfo()[j]);
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
		// Call LabPage
		backButton.setOnAction(e -> primaryStage.setScene(labPage(primaryStage)));
		HBox HBtn = new HBox();
		HBtn.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(20));
		HBtn.setAlignment(Pos.CENTER);
		
		/* ============================ SORTING FUNCTION ============================ */
		// Create combo box objects for sort function
		String[] sortArray = { "Sort By Default", "Sort by Name", "Sort by Cost" };
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

				for (int i = 0; i < HospitalManagement.laboratories.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 2; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(200);
						stackPane.setPrefHeight(15);
						Text text = new Text(HospitalManagement.laboratories.get(i).showLabInfo()[j]);
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
				ArrayList<Lab> copyLabs = new ArrayList<Lab>(HospitalManagement.laboratories);
				copyLabs.sort((o1, o2) -> o1.lab.compareTo(o2.lab));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyLabs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 2; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(200);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyLabs.get(i).showLabInfo()[j]);
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
				ArrayList<Lab> copyLabs = new ArrayList<Lab>(HospitalManagement.laboratories);
				copyLabs.sort((o1, o2) -> Integer.toString(o1.cost).compareTo(Integer.toString(o2.cost)));

				vBox.getChildren().clear();
				vBox.getChildren().addAll(columnLabelHBox);

				for (int i = 0; i < copyLabs.size(); i++) {
					HBox hBox = new HBox(10);

					for (int j = 0; j < 2; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(200);
						stackPane.setPrefHeight(15);
						Text text = new Text(copyLabs.get(i).showLabInfo()[j]);
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
				new Background(new BackgroundImage(new Image("/resources/labInfo.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);

		return scene;
	}
	
	/* ============================ REMOVE LAB PAGE ============================ */
	// Prompt the user to choose the LAB the he would like to remove
	public static Scene removeLab(Stage primaryStage) {

		// Back Button
		ImageView backIcon = new ImageView(new Image("/resources/backBtn2.png"));
		Button backButton = new Button("  Back", backIcon);
		backButton.setStyle(Style.getIconStyle());
		backButton.setOnMouseEntered(e -> backButton.setStyle(Style.getHoveredIconStyle()));
		backButton.setOnMouseExited(e -> backButton.setStyle(Style.getIconStyle()));
		backButton.setOnAction(e -> primaryStage.setScene(labPage(primaryStage)));
		HBox BtnHbox = new HBox();
		BtnHbox.getChildren().add(backButton);
		HBox.setMargin(backButton, new Insets(70, 0, 0, 75));

		// Create label object
		Label label = new Label("Enter Lab's Name:       ");
		label.setStyle(Style.getTextStyle());

		// Create combo box object
		ComboBox<String> LabIdComboBox = new ComboBox<>();
		for (int i = 0; i < HospitalManagement.laboratories.size(); i++) {
			LabIdComboBox.getItems().add(HospitalManagement.laboratories.get(i).lab);
		}
		LabIdComboBox.getSelectionModel().select("Select Lab --");
		LabIdComboBox.setStyle(Style.getComboBoxStyle());

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
		LabIdComboBox.setOnAction(e -> {

			// To ensure that there is only one infoVBox in the main vBox
			if (vBox.getChildren().size() == 2) {
				vBox.getChildren().remove(1);
			}

			// Create VBox object
			VBox infoVBox = new VBox(20);
			infoVBox.setAlignment(Pos.CENTER);

			// Create HBox object for column label
			String[] columnLabel = { "Lab", "Cost" };
			HBox columnLabelHBox = new HBox(10);
			columnLabelHBox.setAlignment(Pos.CENTER);
			columnLabelHBox.setStyle(Style.getHEADERStyle());
			columnLabelHBox.setPrefSize(800, 50);
			
			for (int i = 0; i < 2; i++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(250);
				Text text = new Text(columnLabel[i]);
				text.setFont(Font.font("Courier", FontWeight.BOLD, 20));
				stackPane.getChildren().add(text);
				columnLabelHBox.getChildren().add(stackPane);
			}

			// Display selected Lab's information
			for (int i = 0; i < HospitalManagement.laboratories.size(); i++) {
				if (HospitalManagement.laboratories.get(i).lab.equals(LabIdComboBox.getValue())) {

					int index = i;

					// Create HBox object for Lab's information
					HBox infoHBox = new HBox(10);
					infoHBox.setAlignment(Pos.CENTER);
					infoHBox.setPadding(new Insets(0, 0, 20, 0));

					// Get Lab's information
					for (int j = 0; j < 2; j++) {
						StackPane stackPane = new StackPane();
						stackPane.setPrefWidth(250);
						Text text = new Text(HospitalManagement.laboratories.get(i).showLabInfo()[j]);
						text.setStyle(Style.getTextStyle());
						stackPane.getChildren().add(text);
						infoHBox.getChildren().add(stackPane);
					}

					infoVBox.getChildren().addAll(columnLabelHBox, infoHBox, removeButton);

					// Check if user would like to remove the item
					removeButton.setOnAction(e2 -> {
						int reply = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove " + HospitalManagement.laboratories.get(index).lab + "?",
								"Select an Option", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							// Remove item from array list
							HospitalManagement.laboratories.remove(index);
							
							// Remove item from database
							if(HospitalManagement.connect && HospitalManagement.storeData) {
								try {
									Connection connection = DriverManager.getConnection(HospitalManagement.getDatabasePath());
									connection.createStatement().executeUpdate(
											"DELETE FROM Lab WHERE lab = '"+LabIdComboBox.getValue()+ "'");
								} catch (SQLException e1) {
									// Exception Catch
									e1.printStackTrace();
								}
							}
							
							JOptionPane.showMessageDialog(null, "Successfully removed!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
							
							// Check if user want to return to main menu
							int reply2 = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
									JOptionPane.YES_NO_OPTION);

							if (reply2 == JOptionPane.YES_OPTION) {
								primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
							} else {
								primaryStage.setScene(labPage(primaryStage));
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
		hBox.getChildren().addAll(label, LabIdComboBox);
		hBox.setPadding(new Insets(0, 0, 20, 0));
		vBox.getChildren().add(hBox);
		borderPane.setCenter(vBox);
		borderPane.setTop(BtnHbox);
		borderPane.setLeft(leftLimit);
		borderPane.setRight(rightLimit);
		BorderPane.setAlignment(BtnHbox, Pos.TOP_LEFT);

		// Set Background image
		borderPane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/removeLab.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(borderPane, 1344, 756);
		return scene;
	}
	
	
	/* ============================ INPUT VALIDATIOn ============================ */
	private boolean labValidation(TextField nameTextField, TextField costTextField, String lab, String cost, ArrayList<Lab> arrayList) {
		// Check for empty input
		if(lab.isEmpty() || cost.isEmpty()) {
			errorMsg = "Input cannot be empty";
			return false;
		}
		
		// Check if cost contains non-number value or negative number
		try {
			Integer.parseInt(cost);
			if (Integer.parseInt(cost) <= 0) {
				costTextField.clear();
				errorMsg = "Cost must be a positive number without any spacing.";
				return false;
			}

		} catch (NumberFormatException e) {
			costTextField.clear();
			this.errorMsg = "Cost must be a positive number without any spacing.";
			return false;
		}
		
		// Check if laboratory name is duplicated
		for(int i = 0; i < arrayList.size(); ++i) {
			if(arrayList.get(i).lab.equals(lab)) {
				nameTextField.clear();
				errorMsg = "Laboratory already exists in record";
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
