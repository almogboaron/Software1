package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class Bomber extends myAbstractFightingShip{

	private int numberOfTechnichians;

	public Bomber(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons, int numberOfTechnicians){
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);
		this.numberOfTechnichians = numberOfTechnicians;
	
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 5000 + (int)Math.round(this.weaponMaintenanceCost*(1-0.1*this.numberOfTechnichians));
	}
	public int getNumberOfTechnicians() {
		return this.numberOfTechnichians;
	}
	public String toString() {
		String str = super.toString() +'\r'+'\t'+
										"NumberOfTechnichians="+getNumberOfTechnicians();
		return str;
	}

}
