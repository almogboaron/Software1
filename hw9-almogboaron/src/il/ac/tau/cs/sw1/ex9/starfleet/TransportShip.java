package il.ac.tau.cs.sw1.ex9.starfleet;

import java.util.Set;

public class TransportShip extends myAbstractSpaceShip{

	
	private int cargoCapacity;
	private int passengerCapacity;

	public TransportShip(String name, int commissionYear, float maximalSpeed,
							Set<CrewMember> crewMembers, int cargoCapacity, int passengerCapacity){
		super(name,commissionYear,maximalSpeed,crewMembers);
		this.cargoCapacity = cargoCapacity;
		this.passengerCapacity = passengerCapacity;
	}
	
	@Override
	public int getFirePower() {
		return BasefirePower;
	}

	@Override
	public int getAnnualMaintenanceCost() {
		return 3000 + 5*cargoCapacity +3*passengerCapacity;
	}

	public int getCargoCapacity() {
		return cargoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}
	public String toString() {
		String str = super.toString() +'\r'+'\t'+
										"CargoCapacity="+getCargoCapacity()+'\r'+'\t'+
										"PassengerCapacity"+getPassengerCapacity();
										
		return str;
	}
}
