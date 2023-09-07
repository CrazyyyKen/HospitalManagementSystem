
/* ============================ HOSPITAL ID ROLE CLASS ============================ */
// For doctor, patient, staff
public class Role extends Hospital {
	private String id;
	
	// Constructor
	public Role() {
		super();
	}

	// Getter and setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
