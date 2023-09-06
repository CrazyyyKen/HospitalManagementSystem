import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
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

	// Get Current working directory
	private static String databasePath = "jdbc:ucanaccess://" + System.getProperty("user.dir").replace("\\", "/")
			+ "/src/resources/HospitalDatabase.accdb";
	public static boolean connect = false;
	public static boolean fileExist = false;
	public static boolean storeData = false;

	public static String getDatabasePath() {
		return databasePath;
	}

	// Declaration of ArrayList
	public static ArrayList<Doctor> doctors = new ArrayList<Doctor>();
	public static ArrayList<Patient> patients = new ArrayList<Patient>();
	public static ArrayList<Staff> staffs = new ArrayList<Staff>();
	public static ArrayList<Medical> medicals = new ArrayList<Medical>();
	public static ArrayList<Lab> laboratories = new ArrayList<Lab>();
	public static ArrayList<Facility> facilities = new ArrayList<Facility>();

	// Stage for welcome page scene
	public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image("/resources/logo.png"));
		primaryStage.setTitle("Hospital Management System");
		primaryStage.setScene(welcomePage(primaryStage));
		primaryStage.setResizable(false);
		primaryStage.show();

		// Double Confirm if the user want to exit the program
		primaryStage.setOnCloseRequest(e -> {
			int option = JOptionPane.showConfirmDialog(null,
					"Thank you for using Hospital Management System!\n\n" + "Are you sure you want to exit?",
					"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			} else {
				// Prevent the window from closing
				e.consume();
			}
		});

	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		String filePath = System.getProperty("user.dir").replace("\\", "/") + "/src/resources/HospitalDatabase.accdb";

		// Create a File object representing the database file
		File accessFile = new File(filePath);

		// Check if the database file exists
		if (accessFile.exists() && !accessFile.isDirectory()) {
			fileExist = true;
		} else {
			fileExist = false;
			JOptionPane.showMessageDialog(null,
					"HospitalDatabase.accdb file cannot be found!"
							+ "\n\n Rest assured that you can still use our service without storage.",
					"Error", JOptionPane.WARNING_MESSAGE);
			
			// Check if the user want to create new file to store data
			int createFile = JOptionPane.showConfirmDialog(null,
					"Would you like to create a HospitalDatabase.accdb file to store data?", "File Creation",
					JOptionPane.YES_NO_OPTION);
			if (createFile == JOptionPane.YES_OPTION) {
				
				// Create a file to store data
				String fileName = "HospitalDatabase.accdb";
				String separator = System.getProperty("file.separator");
				String fileDirectory = System.getProperty("user.dir") + separator + "src" + separator + "resources"
						+ separator + fileName;
				File file = new File(fileDirectory);
				
				// change file format & Create a new Access database
				try {
					Database db = new DatabaseBuilder(file).setFileFormat(Database.FileFormat.V2000).create();
					db.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (file.exists() && !file.isDirectory()) {
					JOptionPane.showMessageDialog(null, "Successfully created HospitalDatabase.accdb file!",
							"File Created", JOptionPane.INFORMATION_MESSAGE);
					fileExist = true;
					storeData =  true;
				} else {
					
					// If fail to create file
					JOptionPane.showMessageDialog(null, "Fail to create HospitalDatabase.accdb file!", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					int option = JOptionPane.showConfirmDialog(null, "Would you like to continue using our service?",
							"Error", JOptionPane.YES_NO_OPTION);
					
					// Exit program
					if (option == JOptionPane.NO_OPTION) {
						System.exit(0);
					}
				}

			}
		}

		if (fileExist) {
			// Connect with .accdb file
			Connection connection = DriverManager.getConnection(databasePath);
			
			if (connection != null) { 
				// Connected to Hospital Database
				connect = true;
				JOptionPane.showMessageDialog(null, "Successfully connected to Hospital Database!", "Success Message",
						JOptionPane.INFORMATION_MESSAGE);
				
				// To confirm if the user want to store data to database
				if(!storeData) {
					int store = JOptionPane.showConfirmDialog(null,
							"Would you like to store your data in the database? \n\n"
									+ "*Note that you can continue using our service even without storage.",
							"Connected to Database", JOptionPane.YES_NO_OPTION);
					if (store == JOptionPane.YES_OPTION) {
						storeData = true;
					}
				}


			} else {
				connect = false;
				JOptionPane.showMessageDialog(null,
						"Fail to connect to Hospital Database! "
								+ "\nRest assured that you can still use our service without storage.",
						"Warning", JOptionPane.WARNING_MESSAGE);

			}

			if (connect && storeData) {
				// For creating a statement
				Statement statement = connection.createStatement();

				// Meta Data
				DatabaseMetaData datasetMetaData = connection.getMetaData();

				/* ============================ DOCTOR ============================ */
				// Check if the "Doctor" table exists
				ResultSet docTableCheck = datasetMetaData.getTables(null, null, "Doctor", null);

				if (!docTableCheck.next()) {// The "Doctor" table doesn't exist
					// Create Doctor Table
					statement.executeUpdate(
							"create table Doctor(id varchar(4),	name varchar(100), specialist varchar(30), workTime varchar(30), qualification varchar(50), room int)");
				}

				// Execute a query to check if any records exist in Doctor table
				ResultSet docCount = statement.executeQuery("SELECT COUNT(*) FROM Doctor");

				if (docCount.next()) {
					// Get the count of records
					int recordCount = docCount.getInt(1);

					// Empty Doctor Record
					if (recordCount == 0) {

						// Initialization of first 5 doctors
						statement.executeUpdate(
								"insert into Doctor values('687', 'Dr. Goh Ken How', 'Surgeon', '8-11AM', 'MBBS, MD', 6)");
						statement.executeUpdate(
								"insert into Doctor values('160', 'Dr. Ho Joe Ee', 'Physician', '8-11AM', 'MBBS, MD', 3)");
						statement.executeUpdate(
								"insert into Doctor values('223', 'Dr. Soh Wen Kai', 'Surgeon', '8-11AM', 'MBBS, MD', 69)");
						statement.executeUpdate(
								"insert into Doctor values('001', 'Dr. Soon Chun Hong', 'Surgeon', '8-11AM', 'MBBS, MD', 1)");
						statement.executeUpdate(
								"insert into Doctor values('999', 'Dr. Ong Kar Meng', 'Surgeon', '8-11AM', 'MBBS, MD', 99)");
					}
				}

				// Read the value from the table into array list
				ResultSet docResultSet = statement.executeQuery("select * from Doctor");
				while (docResultSet.next()) {
					Doctor doctor = new Doctor(docResultSet.getString(1), docResultSet.getString(2),
							docResultSet.getString(3), docResultSet.getString(4), docResultSet.getString(5),
							docResultSet.getInt(6));
					doctors.add(doctor);
				}

				/* ============================ PATIENT ============================ */
				// Check if the "Patient" table exists
				ResultSet patientTableCheck = datasetMetaData.getTables(null, null, "Patient", null);

				if (!patientTableCheck.next()) {// The "Patient" table doesn't exist
					// Create Patient Table
					statement.executeUpdate(
							"create table Patient(id varchar(4), name varchar(100), disease varchar(100), sex varchar(6), admitStatus varchar(50), age int)");
				}

				// Execute a query to check if any records exist in Patient table
				ResultSet patientCount = statement.executeQuery("SELECT COUNT(*) FROM Patient");

				if (patientCount.next()) {
					// Get the count of records
					int recordCount = patientCount.getInt(1);

					// The record is empty, add 5 records
					if (recordCount == 0) {
						// Initialization of first 5 patient
						statement.executeUpdate(
								"insert into Patient values('687', 'Goh Ken How', 'Fever', 'Male', 'Admitted', 34)");
						statement.executeUpdate(
								"insert into Patient values('160', 'Ho Joe Ee', 'Blood Disorder', 'Female', 'Outpatient', 20)");
						statement.executeUpdate(
								"insert into Patient values('223', 'Soh Wen Kai', 'MentalDisorder', 'Male', 'Outpatient', 69)");
						statement.executeUpdate(
								"insert into Patient values('001', 'Terry Soon', 'Stroke', 'Male', 'Emergency Admission', 90)");
						statement.executeUpdate(
								"insert into Patient values('999', 'Desmond Ong', 'Asthma', 'Female', 'Admitted', 45)");
					}
				}

				// Read the value from the table into array list
				ResultSet patientResultSet = statement.executeQuery("select * from Patient");
				while (patientResultSet.next()) {
					Patient patient = new Patient(patientResultSet.getString(1), patientResultSet.getString(2),
							patientResultSet.getString(3), patientResultSet.getString(4), patientResultSet.getString(5),
							patientResultSet.getInt(6));
					patients.add(patient);
				}

				/* ============================ STAFF ============================ */
				// Check if the "Staff" table exists
				ResultSet staffTableCheck = datasetMetaData.getTables(null, null, "Staff", null);

				if (!staffTableCheck.next()) {// The "Staff" table doesn't exist
					// Create Staff Table
					statement.executeUpdate(
							"create table Staff(id varchar(4), name varchar(100), designation varchar(100), sex varchar(6), salary int)");
				}

				// Execute a query to check if any records exist in Staff table
				ResultSet staffCount = statement.executeQuery("SELECT COUNT(*) FROM Staff");

				if (staffCount.next()) {
					// Get the count of records
					int recordCount = staffCount.getInt(1);

					// The record is empty, add 5 records
					if (recordCount == 0) {
						// Initialization of first 5 staffs
						statement.executeUpdate(
								"insert into Staff values('687', 'Goh Ken How', 'Doctor', 'Male', 12400)");
						statement.executeUpdate(
								"insert into Staff values('160', 'Ho Joe Ee', 'Doctor', 'Female', 22220)");
						statement.executeUpdate(
								"insert into Staff values('223', 'Soh Wen Kai', 'Nurse', 'Male', 19069)");
						statement.executeUpdate(
								"insert into Staff values('001', 'Terry Soon', 'Pharmacist', 'Male', 3000)");
						statement.executeUpdate(
								"insert into Staff values('999', 'Desmond Ong', 'Finance Manager', 'Male',4500)");
					}
				}

				// Read the value from the table into array list
				ResultSet staffResultSet = statement.executeQuery("select * from Staff");
				while (staffResultSet.next()) {
					Staff staff = new Staff(staffResultSet.getString(1), staffResultSet.getString(2),
							staffResultSet.getString(3), staffResultSet.getString(4), staffResultSet.getInt(5));
					staffs.add(staff);
				}

				/* ============================ MEDICAL ============================ */
				// Check if the "Medical" table exists
				ResultSet medTableCheck = datasetMetaData.getTables(null, null, "Medical", null);

				if (!medTableCheck.next()) {// The Medical table doesn't exist
					// Create Medical Table
					statement.executeUpdate(
							"create table Medical(name varchar(100), manufacturer varchar(100), expiryDate varchar(10), cost int, unit int)");
				}

				// Execute a query to check if any records exist in Medical table
				ResultSet medCount = statement.executeQuery("SELECT COUNT(*) FROM Medical");

				if (medCount.next()) {
					// Get the count of records
					int recordCount = medCount.getInt(1);

					// The record is empty, add 5 records
					if (recordCount == 0) {
						// Initialization of first 5 medicals
						statement.executeUpdate(
								"insert into Medical values('Antibiotics', 'Medtronic', '2024-09-06', 80, 100)");
						statement.executeUpdate(
								"insert into Medical values('Aspirin', 'Noripharma', '2024-09-06', 99, 100)");
						statement.executeUpdate(
								"insert into Medical values('Morphine', 'Prime Pharmaceutical', '2024-09-06', 69, 100)");
						statement.executeUpdate(
								"insert into Medical values('Metformin', 'Medtronic', '2024-09-06', 180, 100)");
						statement.executeUpdate(
								"insert into Medical values('Insulin', 'Noripharma', '2024-09-06', 67, 100)");
					}
				}

				// Read the value from the table into array list
				ResultSet medResultSet = statement.executeQuery("select * from Medical");
				while (medResultSet.next()) {
					Medical medical = new Medical(medResultSet.getString(1), medResultSet.getString(2),
							medResultSet.getString(3), medResultSet.getInt(4), medResultSet.getInt(5));
					medicals.add(medical);
				}
				/* ============================ LAB ============================ */
				// Check if the Lab table exists
				ResultSet labTableCheck = datasetMetaData.getTables(null, null, "Lab", null);

				if (!labTableCheck.next()) {// The Lab table doesn't exist
					// Create Lab Table
					statement.executeUpdate("create table Lab(lab varchar(100), cost int)");
				}

				// Execute a query to check if any records exist in Lab table
				ResultSet labCount = statement.executeQuery("SELECT COUNT(*) FROM Lab");

				if (labCount.next()) {
					// Get the count of records
					int recordCount = labCount.getInt(1);

					// The record is empty, add 5 records
					if (recordCount == 0) {
						// Initialization of first 5 Labs
						statement.executeUpdate("insert into Lab values('Clinical Laboratory',10000)");
						statement.executeUpdate("insert into Lab values('Pathology Laboratory',10000)");
						statement.executeUpdate("insert into Lab values('Cytology Laboratory',10000)");
						statement.executeUpdate("insert into Lab values('Blood Bank',10000)");
						statement.executeUpdate("insert into Lab values('Imaging Laboratory', 10000)");
					}
				}

				// Read the value from the table into array list
				ResultSet labResultSet = statement.executeQuery("select * from Lab");
				while (labResultSet.next()) {
					Lab lab = new Lab(labResultSet.getString(1), labResultSet.getInt(2));
					laboratories.add(lab);
				}

				/* ============================ FACILITY ============================ */
				// Check if the Facility table exists
				ResultSet facilityTableCheck = datasetMetaData.getTables(null, null, "Facility", null);

				if (!facilityTableCheck.next()) {// The Facility table doesn't exist
					// Create Facility Table
					statement.executeUpdate("create table Facility(facility varchar(100))");
				}

				// Execute a query to check if any records exist in Facility table
				ResultSet facilityCount = statement.executeQuery("SELECT COUNT(*) FROM Facility");

				if (facilityCount.next()) {
					// Get the count of records
					int recordCount = facilityCount.getInt(1);

					// The record is empty, add 5 records
					if (recordCount == 0) {
						// Initialization of first 5 Labs
						statement.executeUpdate("insert into Facility values('Microscopes')");
						statement.executeUpdate("insert into Facility values('Specimen')");
						statement.executeUpdate("insert into Facility values('Incubators')");
						statement.executeUpdate("insert into Facility values('Fume Hoods')");
						statement.executeUpdate("insert into Facility values('Cytogenetics')");
					}
				}

				// Read the value from the table into array list
				ResultSet facilityResultSet = statement.executeQuery("select * from Facility");
				while (facilityResultSet.next()) {
					Facility facility = new Facility(facilityResultSet.getString(1));
					facilities.add(facility);
				}
				connection.close();
			}
		}

		// Initialize array list if the connection to database cannot be establish
		if (!connect || !storeData) {
			// Initialization of first 5 doctors
			doctors.add(new Doctor("687", "Dr. Goh Ken How", "Surgeon", "8-11AM", "MBBS, MD", 6));
			doctors.add(new Doctor("160", "Dr. Ho Joe Ee", "Physician", "8-11AM", "MBBS, MD", 3));
			doctors.add(new Doctor("223", "Dr. Soh Wen Kai", "Surgeon", "8-11AM", "MBBS, MD", 69));
			doctors.add(new Doctor("001", "Dr. Soon Chun Hong", "Surgeon", "8-11AM", "MBBS, MD", 1));
			doctors.add(new Doctor("999", "Dr. Ong Kar Meng", "Dentist", "8-11AM", "MBBS, MD", 99));

			// Initialization of first 5 patients
			patients.add(new Patient("687", "Goh Ken How", "Fever", "Male", "Admitted", 34));
			patients.add(new Patient("160", "Ho Joe Ee", "Blood Disorder", "Female", "Outpatient", 20));
			patients.add(new Patient("223", "Soh Wen Kai", "Mental Disorder", "Male", "Outpatient", 69));
			patients.add(new Patient("001", "Terry Soon", "Stroke", "Male", "Emergency Admission", 90));
			patients.add(new Patient("999", "Desmond Ong", "Asthma", "Female", "Admitted", 45));

			// Initialization of first 5 staff
			staffs.add(new Staff("687", "Goh Ken How", "Doctor", "Male", 12400));
			staffs.add(new Staff("160", "Ho Joe Ee", "Doctor", "Female", 22220));
			staffs.add(new Staff("223", "Soh Wen Kai", "Nurse", "Male", 19069));
			staffs.add(new Staff("001", "Terry Soon", "Pharmacist", "Male", 3000));
			staffs.add(new Staff("999", "Desmond Ong", "Finance Manager", "Male", 4500));

			// Initialization of first 5 medicals
			medicals.add(new Medical("Antibiotics", "Medtronic", "2024-09-06", 80, 100));
			medicals.add(new Medical("Aspirin", "Noripharma", "2024-09-06", 99, 100));
			medicals.add(new Medical("Morphine", "Prime Pharmaceutical", "2024-09-06", 69, 100));
			medicals.add(new Medical("Metformin", "Medtronic", "2024-09-06", 180, 100));
			medicals.add(new Medical("Insulin", "Noripharma", "2024-09-06", 67, 100));

			// Initialization of first 5 labs
			laboratories.add(new Lab("Clinical Laboratory", 10000));
			laboratories.add(new Lab("Pathology Laboratory", 10000));
			laboratories.add(new Lab("Cytology Laboratory", 10000));
			laboratories.add(new Lab("Blood Bank", 10000));
			laboratories.add(new Lab("Imaging Laboratory", 10000));

			// Initialization of first 5 facilities
			facilities.add(new Facility("Microscopes"));
			facilities.add(new Facility("Specimen"));
			facilities.add(new Facility("Incubators"));
			facilities.add(new Facility("Fume Hoods"));
			facilities.add(new Facility("Cytogenetics"));

		}
		// Launch screen
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
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/welcome.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

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
		doctorButton.setOnMouseClicked(e -> primaryStage.setScene(Doctor.doctorPage(primaryStage))); // Go to doctor

		// Patient Button Object
		ImageView patientIcon = new ImageView(new Image("/resources/patientIcon.png"));
		Button patientButton = new Button("   Patients", patientIcon);
		patientButton.setAlignment(Pos.CENTER_LEFT);
		patientButton.setPrefSize(250, 50);
		patientButton.setStyle(Style.getIconButtonStyle());
		patientButton.setOnMouseEntered(e -> patientButton.setStyle(Style.getHoveredIconButtonStyle()));
		patientButton.setOnMouseExited(e -> patientButton.setStyle(Style.getIconButtonStyle()));
		patientButton.setOnMouseClicked(e -> primaryStage.setScene(Patient.patientPage(primaryStage))); // Go to patient

		// Staff Button
		ImageView staffIcon = new ImageView(new Image("/resources/staffIcon.png"));
		Button staffButton = new Button("   Staff", staffIcon);
		staffButton.setAlignment(Pos.CENTER_LEFT);
		staffButton.setPrefSize(250, 50);
		staffButton.setStyle(Style.getIconButtonStyle());
		staffButton.setOnMouseEntered(e -> staffButton.setStyle(Style.getHoveredIconButtonStyle()));
		staffButton.setOnMouseExited(e -> staffButton.setStyle(Style.getIconButtonStyle()));
		staffButton.setOnMouseClicked(e -> primaryStage.setScene(Staff.staffPage(primaryStage))); // Go to Staff

		// Medical Button
		ImageView medicalIcon = new ImageView(new Image("/resources/medIcon.png"));
		Button medicalButton = new Button("   Medicals", medicalIcon);
		medicalButton.setAlignment(Pos.CENTER_LEFT);
		medicalButton.setPrefSize(250, 50);
		medicalButton.setStyle(Style.getIconButtonStyle());
		medicalButton.setOnMouseEntered(e -> medicalButton.setStyle(Style.getHoveredIconButtonStyle()));
		medicalButton.setOnMouseExited(e -> medicalButton.setStyle(Style.getIconButtonStyle()));
		medicalButton.setOnMouseClicked(e -> primaryStage.setScene(Medical.medicalPage(primaryStage))); // Go to Staff

		// Lab Buttons
		ImageView labIcon = new ImageView(new Image("/resources/labIcon.png"));
		Button labButton = new Button("   Laboratories", labIcon);
		labButton.setAlignment(Pos.CENTER_LEFT);
		labButton.setPrefSize(250, 50);
		labButton.setStyle(Style.getIconButtonStyle());
		labButton.setOnMouseEntered(e -> labButton.setStyle(Style.getHoveredIconButtonStyle()));
		labButton.setOnMouseExited(e -> labButton.setStyle(Style.getIconButtonStyle()));
		labButton.setOnMouseClicked(e -> primaryStage.setScene(Lab.labPage(primaryStage))); // Go to Staff

		// Facilities Button
		ImageView facilityIcon = new ImageView(new Image("/resources/facilityIcon.png"));
		Button facilityButton = new Button("   Facilities", facilityIcon);
		facilityButton.setAlignment(Pos.CENTER_LEFT);
		facilityButton.setPrefSize(250, 50);
		facilityButton.setStyle(Style.getIconButtonStyle());
		facilityButton.setOnMouseEntered(e -> facilityButton.setStyle(Style.getHoveredIconButtonStyle()));
		facilityButton.setOnMouseExited(e -> facilityButton.setStyle(Style.getIconButtonStyle()));
		facilityButton.setOnMouseClicked(e -> primaryStage.setScene(Facility.facilityPage(primaryStage))); // Go to
																											// Staff

		// Exit Button
		ImageView exitIcon = new ImageView(new Image("/resources/exitBtn.png"));
		Button exitButton = new Button("   Exit", exitIcon);
		exitButton.setPrefSize(170, 50);
		exitButton.setLayoutX(590);
		exitButton.setLayoutY(600);
		exitButton.setStyle(Style.getIconButtonStyle());
		exitButton.setOnMouseEntered(e -> exitButton.setStyle(Style.getHoveredIconButtonStyle()));
		exitButton.setOnMouseExited(e -> exitButton.setStyle(Style.getIconButtonStyle()));
		// Exit Program
		exitButton.setOnMouseClicked(e -> {
			int option = JOptionPane.showConfirmDialog(null,
					"Thank you for using Hospital Management System!\n\n" + "Are you sure you want to exit?",
					"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});

		// Create VBox object
		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(20);

		// Create pane object
		Pane pane = new Pane();

		// Arrange panes and objects
		vBox1.getChildren().addAll(doctorButton, patientButton, staffButton);
		vBox1.setAlignment(Pos.CENTER);
		vBox1.setLayoutX(400);
		vBox1.setLayoutY(300);
		vBox2.getChildren().addAll(medicalButton, labButton, facilityButton);
		vBox2.setAlignment(Pos.CENTER);
		vBox2.setLayoutX(700);
		vBox2.setLayoutY(300);
		pane.getChildren().addAll(vBox1, vBox2, exitButton);

		// Set Background image
		pane.setBackground(
				new Background(new BackgroundImage(new Image("/resources/mainmenu.png"), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

		// Create scene object
		Scene scene = new Scene(pane, 1344, 756);
		return scene;
	}

}
