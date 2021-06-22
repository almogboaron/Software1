package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public class CylonRaider extends myAbstractFightingShip{

	public CylonRaider(String name, int commissionYear, float maximalSpeed, Set<Cylon> crewMembers,
			List<Weapon> weapons) {
		super(name, commissionYear,maximalSpeed,crewMembers, weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return (int)(3500 + this.weaponMaintenanceCost + 500*this.CrewMembers.size() +this.maximalSpeed*1200) ;
	}
	public String toString() {
		return super.toString();
	}
	

}
