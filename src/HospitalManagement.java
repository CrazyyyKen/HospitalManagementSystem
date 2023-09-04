import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Style;

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

		primaryStage.setTitle("Hospital Management System");
		primaryStage.setScene(welcomePage(primaryStage));
		primaryStage.setResizable(false);
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
		
		Text dateText = new Text(588, 445, dateFormat.format(date));
		dateText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 30));

		Text timeText = new Text(608, 510, timeFormat.format(time));
		timeText.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 30));
		
		// Create button object
		Button startButton = new Button("Get Started");
		// Button Style
		startButton.setStyle(Style.getIdleButtonStyle());
		startButton.setOnMouseEntered(e -> startButton.setStyle(Style.getHoveredButtonStyle()));
        startButton.setOnMouseExited(e -> startButton.setStyle(Style.getIdleButtonStyle()));
		// Button Position
        startButton.setPrefSize(200, 70);
		startButton.setLayoutX(570);
		startButton.setLayoutY(550);
		
		// Create event handling for startButton
		// Call mainMenuPage method
		startButton.setOnAction(e -> {
			primaryStage.setScene(mainMenuPage(primaryStage));
			primaryStage.setResizable(false);
		});

		// Root Pane
		Pane pane = new Pane();
		pane.getChildren().addAll(dateText, timeText, startButton);
		
		// Set Background image
		pane.setBackground(new Background(
                new BackgroundImage(
                        new Image("/resources/welcome.png"),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                ))
             );
		

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		
		return scene;
	}

	// Main menu page
	public static Scene mainMenuPage(Stage primaryStage) {

		// Doctor Button Object
		Image docImage = new Image("/resources/docIcon.png");
		ImageView docIcon = new ImageView(docImage);
		Button doctorButton = new Button("   Doctors", docIcon);
		doctorButton.setAlignment(Pos.CENTER_LEFT);
		doctorButton.setPrefSize(250, 50);
		doctorButton.setStyle(Style.getIdleButtonStyle());
		doctorButton.setOnMouseEntered(e -> doctorButton.setStyle(Style.getHoveredIconButtonStyle()));
		doctorButton.setOnMouseExited(e -> doctorButton.setStyle(Style.getIconButtonStyle()));
		doctorButton.setOnMouseClicked(e ->primaryStage.setScene(Doctor.doctorPage(primaryStage)));	// Go to doctor
        
		// Patient Button Object
		ImageView patientIcon = new ImageView(new Image("/resources/patientIcon.png"));
		Button patientButton = new Button("   Patients", patientIcon);
		patientButton.setAlignment(Pos.CENTER_LEFT);
		patientButton.setPrefSize(250, 50);
		patientButton.setStyle(Style.getIconButtonStyle());
		patientButton.setOnMouseEntered(e -> patientButton.setStyle(Style.getHoveredIconButtonStyle()));
		patientButton.setOnMouseExited(e -> patientButton.setStyle(Style.getIconButtonStyle()));
		patientButton.setOnMouseClicked(e ->primaryStage.setScene(Patient.patientPage(primaryStage)));	// Go to patient
        
		// Staff Button
		ImageView staffIcon = new ImageView(new Image("/resources/staffIcon.png"));
		Button staffButton = new Button("   Staffs", staffIcon);
		staffButton.setAlignment(Pos.CENTER_LEFT);
		staffButton.setPrefSize(250, 50);
		staffButton.setStyle(Style.getIconButtonStyle());
		staffButton.setOnMouseEntered(e -> staffButton.setStyle(Style.getHoveredIconButtonStyle()));
		staffButton.setOnMouseExited(e -> staffButton.setStyle(Style.getIconButtonStyle()));
		staffButton.setOnMouseClicked(e ->primaryStage.setScene(Staff.staffPage(primaryStage)));	// Go to Staff
		
		// Medical Button
		ImageView medicalIcon = new ImageView(new Image("/resources/medIcon.png"));
		Button medicalButton = new Button("   Medicals", medicalIcon);
		medicalButton.setAlignment(Pos.CENTER_LEFT);
		medicalButton.setPrefSize(250, 50);
		medicalButton.setStyle(Style.getIconButtonStyle());
		medicalButton.setOnMouseEntered(e -> medicalButton.setStyle(Style.getHoveredIconButtonStyle()));
		medicalButton.setOnMouseExited(e -> medicalButton.setStyle(Style.getIconButtonStyle()));
		medicalButton.setOnMouseClicked(e ->primaryStage.setScene(Medical.medicalPage(primaryStage)));	// Go to Staff
		
		// Lab Buttons
		ImageView labIcon = new ImageView(new Image("/resources/labIcon.png"));
		Button labButton = new Button("   Laboratories", labIcon);
		labButton.setAlignment(Pos.CENTER_LEFT);
		labButton.setPrefSize(250, 50);
		labButton.setStyle(Style.getIconButtonStyle());
		labButton.setOnMouseEntered(e -> labButton.setStyle(Style.getHoveredIconButtonStyle()));
		labButton.setOnMouseExited(e -> labButton.setStyle(Style.getIconButtonStyle()));
		labButton.setOnMouseClicked(e ->primaryStage.setScene(Lab.labPage(primaryStage)));	// Go to Staff
		
		// Facilities Button
		ImageView facilityIcon = new ImageView(new Image("/resources/facilityIcon.png"));
		Button facilityButton = new Button("   Facilities", facilityIcon);
		facilityButton.setAlignment(Pos.CENTER_LEFT);
		facilityButton.setPrefSize(250, 50);
		facilityButton.setStyle(Style.getIconButtonStyle());
		facilityButton.setOnMouseEntered(e -> facilityButton.setStyle(Style.getHoveredIconButtonStyle()));
		facilityButton.setOnMouseExited(e -> facilityButton.setStyle(Style.getIconButtonStyle()));
		facilityButton.setOnMouseClicked(e ->primaryStage.setScene(Facility.facilityPage(primaryStage)));	// Go to Staff
		
		
		// Exit Button
		ImageView exitIcon = new ImageView( new Image("/resources/exitBtn.png"));
		Button exitButton = new Button("   Exit",exitIcon);
		exitButton.setPrefSize(170, 50);
		exitButton.setLayoutX(590);
        exitButton.setLayoutY(600);
		exitButton.setStyle(Style.getIconButtonStyle());
		exitButton.setOnMouseEntered(e -> exitButton.setStyle(Style.getHoveredIconButtonStyle()));
        exitButton.setOnMouseExited(e -> exitButton.setStyle(Style.getIconButtonStyle()));
        exitButton.setOnMouseClicked(e ->System.exit(0));	// Exit Program
        

		// Create VBox object
		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(20);

		// Create pane object
		Pane pane = new Pane();

		// Arrange panes and objects
		vBox1.getChildren().addAll(doctorButton, patientButton,staffButton);
		vBox1.setAlignment(Pos.CENTER);
		vBox1.setLayoutX(400);
		vBox1.setLayoutY(300);
		vBox2.getChildren().addAll(medicalButton, labButton, facilityButton);
		vBox2.setAlignment(Pos.CENTER);
		vBox2.setLayoutX(700);
		vBox2.setLayoutY(300);
		pane.getChildren().addAll(vBox1,vBox2, exitButton);

		// Set Background image
		pane.setBackground(new Background(
                new BackgroundImage(
                        new Image("/resources/mainmenu.png"),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                ))
             );
		
		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);

		return scene;
	}

}
