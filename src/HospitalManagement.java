import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HospitalManagement extends Application {

	// Declaration of ArrayList
	public static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	public static ArrayList<Patient> patients = new ArrayList<Patient>();
	public static ArrayList<Staff> staffs = new ArrayList<Staff>();
	public static ArrayList<Medical> medicals = new ArrayList<Medical>();
	public static ArrayList<Lab> laboratories = new ArrayList<Lab>();
	public static ArrayList<Facility> facilities = new ArrayList<Facility>();

	// Stage for welcome page scene
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Java FX Test 7");
		primaryStage.setScene(welcomePage(primaryStage));
		primaryStage.show();

	}

	public static void main(String[] args) {

		// Initialization of first 5 doctors
		doctors.add(new Doctor("687", "Dr. Goh Ken How", "Surgeon", "8-11AM", "MBSS, MD", 6));
		doctors.add(new Doctor("160", "Dr. Ho Joe Ee", "Physician", "8-11AM", "MBSS, MD", 3));
		doctors.add(new Doctor("223", "Dr. Soh Wen Kai", "Surgeon", "8-11AM", "MBSS, MD", 69));
		doctors.add(new Doctor("001", "Dr. Soon Chun Hong", "Surgeon", "8-11AM", "MBSS, MD", 1));
		doctors.add(new Doctor("999", "Dr. Desmond", "Dentist", "8-11AM", "MBSS, MD", 99));

		launch(args);
	}
	
	// Welcome page
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
		
		// Call mainMenuPage method
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
		
		// Call doctorPage method
		doctorButton.setOnAction(e -> {
			primaryStage.setScene(Doctor.doctorPage(primaryStage));
		});
		
		// Exit program
		exitButton.setOnAction(e -> {
			System.exit(0);
		});

		// Create scene object
		Scene scene = new Scene(borderPane, 1200, 800);
		return scene;
	}

}
