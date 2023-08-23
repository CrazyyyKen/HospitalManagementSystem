import java.util.Scanner;

public class Doctor extends Person {
    Scanner input = new Scanner(System.in);

    // Data fields
    private String specialist;
    private String workTime;
    private String qualification;
    private int room;

    // Default constructor
    public Doctor() {
        super();
    }

    // Constructor
    public Doctor(String id, String name, String specialist, String workTime, String qualification, int room) {
        super(id, name);
        this.specialist = specialist;
        this.workTime = workTime;
        this.qualification = qualification;
        this.room = room;
    }

    // Accessors and mutators
    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getWorkTime() {
        return workTime;
    }

    public String getQualification() {
        return qualification;
    }

    public int getRoom() {
        return room;
    }

    // Prompts user to enter information of doctor
    public void newDoctor() {
        System.out.print("Enter doctor's ID: ");
        super.setId(input.nextLine());
        System.out.print("Enter doctor's name: ");
        super.setName(input.nextLine());
        System.out.print("Enter doctor's specialization: ");
        specialist = input.nextLine();
        System.out.print("Enter doctor's work time: ");
        workTime = input.nextLine();
        System.out.print("Enter doctor's work time: ");
        workTime = input.nextLine();
        System.out.print("Enter doctor's room number: ");
        room = input.nextInt();
    }

    // Shows the information of doctor
    public void showDoctorInfo() {
        String format = "%-5s %-20s %-15s %-10s %-15s %-5d";
        String output = String.format(format, getId(), getName(), specialist, workTime, qualification, room);
        System.out.println(output);
    }
}
