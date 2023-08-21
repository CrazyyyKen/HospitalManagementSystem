import java.util.Scanner;

public class Staff {

    Scanner input = new Scanner(System.in);

    // Data fields
    private String id;
    private String name;
    private String designation;
    private String sex;
    private int salary;

    // Constructor
    public Staff(String id, String name, String designation, String sex, int salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.sex = sex;
        this.salary = salary;
    }

    // Accessors and mutators
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void newStaff() {

        System.out.print("Enter staff's ID: ");
        System.out.print("Enter staff's name: ");
        System.out.print("Enter staff's designation: ");
        System.out.print("Enter staff's sex: ");
        System.out.print("Enter staff's salary: ");

    }
}
