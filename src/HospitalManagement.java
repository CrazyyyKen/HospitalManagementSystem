import java.util.ArrayList;

public class HospitalManagement {
    public static void main(String[] args) throws Exception {
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        Staff staff = new Staff();
        staff.newStaff();
        staffs.add( staff);
        for(int i = 0;i<staffs.size();i++){
            staffs.get(i).showStaffInfo();
        }
    }
}
