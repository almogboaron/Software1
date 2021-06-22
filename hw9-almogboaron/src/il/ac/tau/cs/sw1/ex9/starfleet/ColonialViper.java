package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ColonialViper extends myAbstractFightingShip  {

	public ColonialViper(String name, int commissionYear, float maximalSpeed, Set<CrewWoman> crewMembers,
			List<Weapon> weapons) {
		super(name,commissionYear,maximalSpeed,crewMembers,weapons);
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return (int) (4000 + this.weaponMaintenanceCost + 500*this.CrewMembers.size() + 500*this.maximalSpeed);
	}
	public String toString() {
		return super.toString();
	}

}
