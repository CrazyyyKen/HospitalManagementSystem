import java.util.Scanner;

public class Facility {
	
	private String facility;
	
	public Facility() {};
	
	public Facility(String facility) {
		this.facility = facility;
	}
	
	public void newFacility() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the facility: ");
		facility = input.nextLine();
		
		input.close();
	}
	
	public void showFacility() {
		System.out.println(facility);
	}
}
