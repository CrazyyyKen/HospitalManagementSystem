import java.util.Scanner;

public class Patient {
	Scanner input = new Scanner(System.in);
	private String id;
	private String name;
	private String disease;
	private String sex;
	private String admitStatus;
	private int age;

	public Patient() {
	}

	public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
		this.id = id;
		this.name = name;
		this.disease = disease;
		this.sex = sex;
		this.admitStatus = admitStatus;
		this.age = age;
	}

	public void newPatient() {
		System.out.print("Enter patient's ID: ");
		id = input.nextLine();
		System.out.print("Enter patient's name: ");
		name = input.nextLine();
		System.out.print("Enter patient's disease: ");
		disease = input.nextLine();
		System.out.print("Enter patient's sex: ");
		sex = input.nextLine();
		System.out.print("Enter patient's admit status: ");
		admitStatus = input.nextLine();
		System.out.print("Enter patient's age: ");
		age = input.nextInt();
	}

	// Shows the information of doctor
	public void showPatientInfo() {
		String format = "%-5s %-20s %-20s %-10s %-15s %-5d";
		String output = String.format(format, id, name, disease, sex, admitStatus, age);
		System.out.println(output);
	}

}
