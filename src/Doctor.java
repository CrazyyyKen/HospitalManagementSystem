import javax.swing.JOptionPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Doctor extends Person {

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
		super(id, name);
		this.specialist = specialist;
		this.workTime = workTime;
		this.qualification = qualification;
		this.room = room;
	}

	// Prompts user to enter information of doctor
	public Scene newDoctor(Stage primaryStage) {

		// Create label objects
		Label idLabel = new Label("Enter doctor's id: ");
		Label nameLabel = new Label("Enter doctor's name: ");
		Label specialistLabel = new Label("Enter doctor's specialization: ");
		Label workTimeLabel = new Label("Enter doctor's work time: ");
		Label qualificationLabel = new Label("Enter doctor's qualification: ");
		Label roomLabel = new Label("Enter doctor's room number: ");

		// Create text field objects
		TextField idInput = new TextField();
		TextField nameInput = new TextField();
		TextField specialistInput = new TextField();
		TextField workTimeInput = new TextField();
		TextField qualificationInput = new TextField();
		TextField roomInput = new TextField();

		// Create button object
		Button addButton = new Button("Add");

		// Create HBox objects
		HBox hBox1 = new HBox();
		HBox hBox2 = new HBox();
		HBox hBox3 = new HBox();
		HBox hBox4 = new HBox();
		HBox hBox5 = new HBox();
		HBox hBox6 = new HBox();

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		hBox1.getChildren().addAll(idLabel, idInput);
		hBox1.setAlignment(Pos.CENTER);

		hBox2.getChildren().addAll(nameLabel, nameInput);
		hBox2.setAlignment(Pos.CENTER);

		hBox3.getChildren().addAll(specialistLabel, specialistInput);
		hBox3.setAlignment(Pos.CENTER);

		hBox4.getChildren().addAll(workTimeLabel, workTimeInput);
		hBox4.setAlignment(Pos.CENTER);

		hBox5.getChildren().addAll(qualificationLabel, qualificationInput);
		hBox5.setAlignment(Pos.CENTER);

		hBox6.getChildren().addAll(roomLabel, roomInput);
		hBox6.setAlignment(Pos.CENTER);

		vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, addButton);
		vBox.setAlignment(Pos.CENTER);

		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for button
		addButton.setOnAction(e -> {

			// Input validation
			super.setId(idInput.getText());
			super.setName(nameInput.getText());
			specialist = specialistInput.getText();
			workTime = workTimeInput.getText();
			qualification = qualificationInput.getText();
			room = Integer.parseInt(roomInput.getText());

			// Check if user would like to return to previous section or return to main menu
			JOptionPane.showMessageDialog(null, "Successfully added!", "InfoBox: " + "Message",
					JOptionPane.INFORMATION_MESSAGE);

			int reply = JOptionPane.showConfirmDialog(null, "Return to main menu?", "Select an Option",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				primaryStage.setScene(CustomScene.mainMenuPage(primaryStage));
			} else {
				primaryStage.setScene(CustomScene.doctorPage(primaryStage));
			}

		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;

	}

	// Shows the information of doctor
	public String[] showDoctorInfo() {
		String[] output = { getId(), getName(), specialist, workTime, qualification, room + "" };
		return output;
	}
}
