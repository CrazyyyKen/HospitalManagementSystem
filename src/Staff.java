import java.util.Scanner;

public class Staff extends Person {

    Scanner input = new Scanner(System.in);

    // Data fields
    private String designation;
    private String sex;
    private int salary;

    // Default constructor
    public Staff() {
        super();
    }

    // Constructor
    public Staff(String id, String name, String designation, String sex, int salary) {
        super(id, name);
        this.designation = designation;
        this.sex = sex;
        this.salary = salary;
    }

    // Accessors and mutators
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public String getSex() {
        return sex;
    }

    public int getSalary() {
        return salary;
    }

    // Prompts user to enter information of staff
    public void newStaff() {

        System.out.print("Enter staff's ID: ");
        super.setId(input.nextLine());
        System.out.print("Enter staff's name: ");
        super.setName(input.nextLine());
        System.out.print("Enter staff's designation: ");
        designation = input.nextLine();
        System.out.print("Enter staff's sex: ");
        sex = input.nextLine();
        System.out.print("Enter staff's salary: ");
        salary = input.nextInt();

    }

    // Shows the information of staff
    public void showStaffInfo() {
        System.out.println(getId() + "\t\t" + getName() + "\t\t" + designation + "\t\t" + sex + "\t\t" + salary);
    }

}
