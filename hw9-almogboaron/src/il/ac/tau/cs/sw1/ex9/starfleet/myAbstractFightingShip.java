package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.List;
import java.util.Set;

public abstract class myAbstractFightingShip extends myAbstractSpaceShip {

	protected int firePower;
	protected int weaponMaintenanceCost;
	protected List<Weapon> weapons;
	
	public myAbstractFightingShip(String name, int commissionYear, float maximalSpeed, Set<? extends CrewMember> crewMembers, List<Weapon> weapons) {
		super(name, commissionYear, maximalSpeed, crewMembers);
		this.weapons = weapons;
		this.firePower = setFirePower();
		this.weaponMaintenanceCost = setWeaponMaintenanceCost();
	}
	private int setFirePower() {
		return weapons.stream().map(x->x.getFirePower()).reduce(BasefirePower,Integer::sum);
	}
	private int setWeaponMaintenanceCost() {
		return weapons.stream().map(x->x.getAnnualMaintenanceCost()).reduce(0,Integer::sum);
	}
	public List<Weapon> getWeapon() {
		return weapons;
	}
	public int getFirePower() {
		return this.firePower;
	}
	public String toString() {
		String str =super.toString()+'\r'+'\t'+
						"Weapons="+this.weapons.toString();
		return str ;
	}
}
	
