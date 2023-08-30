import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomScene {
	public static Scene welcomePage(Stage primaryStage) {

		// Create local date time object
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime time = LocalDateTime.now();

		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

		// Create text objects
		Text dateText = new Text(dateFormat.format(date));
		dateText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		Text timeText = new Text(timeFormat.format(time));
		timeText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		Text welcomeText = new Text("Welcome to Hospital Management System");
		welcomeText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		// Create button object
		Button startButton = new Button("Get started");

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		vBox.getChildren().addAll(dateText, timeText, welcomeText, startButton);
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for startButton
		startButton.setOnAction(e -> {
			primaryStage.setScene(mainMenuPage(primaryStage));
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}

	// Main menu page
	public static Scene mainMenuPage(Stage primaryStage) {

		// Create text object
		Text menuTitle = new Text("Main Menu");
		menuTitle.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		// Create button objects
		Button doctorButton = new Button("Doctors");
		Button patientButton = new Button("Patients");
		Button medicalButton = new Button("Medicals");
		Button labButton = new Button("Laboratories");
		Button facilityButton = new Button("Facilities");
		Button staffButton = new Button("Staffs");
		Button exitButton = new Button("Exit program");

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		vBox.getChildren().addAll(menuTitle, doctorButton, patientButton, medicalButton, labButton, facilityButton,
				staffButton, exitButton);
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for buttons
		doctorButton.setOnAction(e -> {
			primaryStage.setScene(doctorPage(primaryStage));
		});
		exitButton.setOnAction(e -> {
			System.exit(0);
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}

	// Doctor page
	public static Scene doctorPage(Stage primaryStage) {

		// Create text object
		Text menuTitle = new Text("Doctors");
		menuTitle.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 25));

		// Create button objects
		Button addButton = new Button("Add new doctor");
		Button displayButton = new Button("Display doctors' information");
		Button backButton = new Button("Back to main menu");

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create border pane object
		BorderPane borderPane = new BorderPane();

		// Arrange panes and objects
		vBox.getChildren().addAll(menuTitle, addButton, displayButton, backButton);
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for buttons
		addButton.setOnAction(e -> {
			Doctor doctor = new Doctor();
			primaryStage.setScene(doctor.newDoctor(primaryStage));
			HospitalManagement.doctors.add(doctor);
		});

		displayButton.setOnAction(e -> {
			Doctor doctor = new Doctor();

			primaryStage.setScene(CustomScene.showDoctorPage(primaryStage));

		});

		backButton.setOnAction(e -> {
			primaryStage.setScene(mainMenuPage(primaryStage));
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}

	public static Scene showDoctorPage(Stage primaryStage) {

		// Create VBox object
		VBox vBox = new VBox(10);

		// Create HBox object for column label
		String[] columnLabel = { "ID", "Name", "Specialist", "Work Time", "Qualification", "Room No." };
		HBox columnLabelHBox = new HBox(10);

		for (int i = 0; i < 6; i++) {
			StackPane stackPane = new StackPane();
			stackPane.setPrefWidth(150);
			stackPane.setPrefHeight(15);
			Text text = new Text(columnLabel[i]);
			stackPane.getChildren().add(text);
			columnLabelHBox.getChildren().add(stackPane);
		}
		columnLabelHBox.setAlignment(Pos.CENTER);

		// Insert HBox object of column label into VBox object
		vBox.getChildren().add(columnLabelHBox);

		// Create and insert HBox object of each doctor's info into VBox object
		for (int i = 0; i < HospitalManagement.doctors.size(); i++) {
			HBox hBox = new HBox(10);

			for (int j = 0; j < 6; j++) {
				StackPane stackPane = new StackPane();
				stackPane.setPrefWidth(150);
				stackPane.setPrefHeight(15);
				Text text = new Text(HospitalManagement.doctors.get(i).showDoctorInfo()[j]);
				stackPane.getChildren().add(text);
				hBox.getChildren().add(stackPane);
			}

			hBox.setAlignment(Pos.CENTER);
			vBox.getChildren().add(hBox);
		}

		// Create button object
		Button backButton = new Button("Back");

		// Arrange panes and objects
		vBox.getChildren().add(backButton);
		BorderPane borderPane = new BorderPane();
		vBox.setAlignment(Pos.CENTER);
		borderPane.setCenter(vBox);
		BorderPane.setAlignment(vBox, Pos.CENTER);

		// Create event handling for button
		backButton.setOnAction(e -> {
			primaryStage.setScene(doctorPage(primaryStage));
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}
}
