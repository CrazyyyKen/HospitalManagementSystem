
/* ============================ PROPERTY CLASS ============================ */
// For lab cost and medical cost
public class Property extends Hospital {
	private int cost;
	
	public Property() {
		super();
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
