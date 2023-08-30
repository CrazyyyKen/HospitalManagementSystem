import java.util.Scanner;

public class Lab {
	private String lab;
	private int cost;

	public Lab() {};

	public Lab(String lab, int cost) {
		this.lab = lab;
		this.cost = cost;
	}
	
	public void newLab() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the facility: ");
		lab = input.nextLine();
		
		System.out.print("Enter the cost: ");
		cost = input.nextInt();
		
		input.close();
	}
	
	public void labList() {
		System.out.println(lab + "\t" + cost);
	}
}
