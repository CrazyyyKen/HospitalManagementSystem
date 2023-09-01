import java.util.Scanner;

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

	// Prompts user to enter information of staff
	public void newStaff() {

		System.out.print("Enter staff's ID: ");
		id = input.nextLine();
		System.out.print("Enter staff's name: ");
		name = input.nextLine();
		System.out.print("Enter staff's designation: ");
		designation = input.nextLine();
		System.out.print("Enter staff's sex: ");
		sex = input.nextLine();
		System.out.print("Enter staff's salary: ");
		salary = input.nextInt();

	}

	// Shows the information of staff
	public void showStaffInfo() {
		String format = "%-5s %-20s %-15s %-10s %-5d";
		String output = String.format(format, id, name, designation, sex, salary);
		System.out.println(output);
	}

}
