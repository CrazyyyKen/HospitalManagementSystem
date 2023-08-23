import java.util.ArrayList;

public class HospitalManagement {
    public static void main(String[] args) throws Exception {

        // Declaration of ArrayList
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        ArrayList<Patient> patients = new ArrayList<Patient>();
        ArrayList<Staff> staffs = new ArrayList<Staff>();

        // Initialization of first 5 doctors
        doctors.add(new Doctor("687", "Dr. Goh Ken How", "Surgeon", "8-11AM", "MBSS, MD", 6));
        doctors.add(new Doctor("160", "Dr. Ho Joe Ee", "Physician", "8-11AM", "MBSS, MD", 3));
        doctors.add(new Doctor("223", "Dr. Soh Wen Kai", "Surgeon", "8-11AM", "MBSS, MD", 69));
        doctors.add(new Doctor("001", "Dr. Soon Chun Hong", "Surgeon", "8-11AM", "MBSS, MD", 1));
        doctors.add(new Doctor("999", "Dr. Desmond", "Dentist", "8-11AM", "MBSS, MD", 99));

        

        for (int i = 0; i < doctors.size(); i++) {
            doctors.get(i).showDoctorInfo();
        }
    }
}
