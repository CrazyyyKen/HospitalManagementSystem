import java.util.Scanner;

public class Medical {
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int count;
	
	public Medical() {};
	
	public Medical(String name, String manufacturer, String expiryDate, int cost, int count) {
		this.name = name;
		this.manufacturer = manufacturer;
		this.expiryDate = expiryDate;
		this.cost = cost;
		this.count = count;
	}
	
	public void newMedical() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the name: ");
		name = input.nextLine();
		
		System.out.print("Enter the manufacturer: ");
		manufacturer = input.nextLine();
		
		System.out.print("Enter the expiry date: ");
		expiryDate = input.nextLine();
		
		System.out.print("Enter the cost: ");
		cost = input.nextInt();
		
		System.out.print("Enter the number of units: ");
		count = input.nextInt();
		
		input.close();
	}
	
	public void findMedical() {
		System.out.println(name + "\t" + manufacturer + "\t" + expiryDate + "\t" + cost);
	}
}
