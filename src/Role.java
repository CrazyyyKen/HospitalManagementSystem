
/* ============================ HOSPITAL ID ROLE CLASS ============================ */
// For doctor, patient, staff
public class Role extends Hospital {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role() {
		super();
	}

}
