package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public abstract class myAbstractSpaceShip implements Spaceship {
	
	protected String name;
	protected int commisionYear;
	protected float maximalSpeed;
	protected static int BasefirePower = 10;
	protected Set<? extends CrewMember> CrewMembers;
	
	public myAbstractSpaceShip(String name, int commissionYear, float maximalSpeed,
									Set<? extends CrewMember> crewMembers) {
		this.name =name;
		this.commisionYear = commissionYear;
		this.maximalSpeed = maximalSpeed;
		this.CrewMembers = crewMembers;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getCommissionYear() {
		return this.commisionYear;
	}

	@Override
	public float getMaximalSpeed() {
		return this.maximalSpeed;
	}

	@Override
	public Set<? extends CrewMember> getCrewMembers() {
		return this.CrewMembers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		myAbstractSpaceShip other = (myAbstractSpaceShip) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString() {
		String str = (this.getClass().getSimpleName()) +'\r'+'\t'+
				"Name="+this.name+'\r'+'\t'+
				"CommissionYear="+this.commisionYear+'\r'+'\t'+
				"MaximalSpeed="+this.maximalSpeed+'\r'+'\t' +
				"FirePower="+this.getFirePower()+'\r'+'\t' +
				"CrewMembers="+this.CrewMembers.size()+'\r'+'\t'+
				"AnnualMaintenanceCost="+this.getAnnualMaintenanceCost();
				return str;
		
	}

}
