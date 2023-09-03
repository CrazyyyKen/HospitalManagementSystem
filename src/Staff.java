import java.util.Scanner;

import javax.swing.JOptionPane;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.Style;

public class Staff {

	Scanner input = new Scanner(System.in);

	// Data fields
	private String id;
	private String name;
	private String designation;
	private String sex;
	private int salary;

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
				//primaryStage.setScene(showStaffPage(primaryStage));
			}
		});

		// Call removeDoctor method
		removeButton.setOnAction(e -> {
			if (HospitalManagement.staffs.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Staff's record is empty!", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				//primaryStage.setScene(removeStaff(primaryStage));
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

	// Prompts user to enter information of Staff
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
		salaryTextField.setPromptText("3000.00");

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

			// action

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

	// Shows the information of staff
	public void showStaffInfo() {
		String format = "%-5s %-20s %-15s %-10s %-5d";
		String output = String.format(format, id, name, designation, sex, salary);
		System.out.println(output);
	}

}
