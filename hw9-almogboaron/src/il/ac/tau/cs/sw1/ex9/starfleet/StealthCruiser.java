package il.ac.tau.cs.sw1.ex9.starfleet;


import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StealthCruiser extends Fighter{
	
	protected static int numOfStealthEngines = 0;
	
	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers, List<Weapon> weapons) {
		super(name,commissionYear, maximalSpeed, crewMembers, weapons);
		numOfStealthEngines++;
	}

	public StealthCruiser(String name, int commissionYear, float maximalSpeed, Set<CrewMember> crewMembers){
		this(name, commissionYear, maximalSpeed, crewMembers ,Collections.singletonList(new Weapon("Laser Cannons",10,100)));
	}
	public int getAnnualMaintenanceCost() {
		return (super.getAnnualMaintenanceCost() + 50*numOfStealthEngines);
	}
	
	public String toString() {
		return super.toString();
	}
	
}
