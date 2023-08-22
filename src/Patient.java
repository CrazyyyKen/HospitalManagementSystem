import java.util.Scanner;

public class Patient extends Person {
    Scanner input = new Scanner(System.in);

    private String disease;
    private String sex;
    private String admitStatus;
    private int age;

    public Patient() {
        super();
    }

    public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
        super(id, name);
        this.disease = disease;
        this.sex = sex;
        this.admitStatus = admitStatus;
        this.age = age;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAdmitStatus(String admitStatus) {
        this.admitStatus = admitStatus;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public String getSex() {
        return sex;
    }

    public String getAdmitStatus() {
        return admitStatus;
    }

    public int getAge() {
        return age;
    }

    public void newPatient() {
        System.out.print("Enter patient's ID: ");
        super.setId(input.nextLine());
        System.out.print("Enter patient's name: ");
        super.setName(input.nextLine());
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
        System.out.println(getId() + "\t\t" + getName() + "\t\t" + disease + "\t\t" + sex + "\t\t"
                + admitStatus + "\t\t" + age);
    }
}
