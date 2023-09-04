import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	// Lab main page
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
		vBox.setLayoutX(730);
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
				// primaryStage.setScene(showLabPage(primaryStage));
			}
		});

		// Call removeLab method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.laboratories.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lab's record is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				// primaryStage.setScene(removeLab(primaryStage));
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

	// Prompts user to enter information of Lab
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
				
				// Check if user would like to return to previous section or return to main menu
				JOptionPane.showMessageDialog(null, "Successfully added!", "Message", JOptionPane.INFORMATION_MESSAGE);
	
				int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
						JOptionPane.YES_NO_OPTION);
	
				if (reply == JOptionPane.YES_OPTION) {
					primaryStage.setScene(HospitalManagement.mainMenuPage(primaryStage));
				} else {
					primaryStage.setScene(labPage(primaryStage));
				}
			}
			else {
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

	public void labList() {
		System.out.println(lab + "\t" + cost);
	}
	
	// Input validation method
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
