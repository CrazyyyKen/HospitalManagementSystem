import java.util.ArrayList;
import javafx.application.Application;
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
		primaryStage.setScene(CustomScene.welcomePage(primaryStage));
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

}
