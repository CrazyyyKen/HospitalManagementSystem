import java.time.LocalDate;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import resources.Style;

public class Medical {
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int count;

	public Medical() {
	};

	public Medical(String name, String manufacturer, String expiryDate, int cost, int count) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.expiryDate = expiryDate;
		this.cost = cost;
		this.count = count;
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
				// primaryStage.setScene(showmedicalPage(primaryStage));
			}
		});

		// Call removeMedical method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.medicals.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Medical's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				// primaryStage.setScene(removeMedical(primaryStage));
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
        datePicker.setOnAction( e -> {
            // get the date picker value
            LocalDate date = datePicker.getValue();
            // TO DO: FOR OUTPUT 
        });
        // show week numbers
        datePicker.setShowWeekNumbers(true);
        

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
			// ACTION
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

	public void findMedical() {
		System.out.println(name + "\t" + manufacturer + "\t" + expiryDate + "\t" + cost);
	}
}
