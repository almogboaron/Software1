package il.ac.tau.cs.sw1.ex9.starfleet;

public class Cylon extends myAbstractCrewMember {
	protected int modelNumber ;
	
	public Cylon(String name, int age, int yearsInService, int modelNumber) {
		super(age,yearsInService,name);
		this.modelNumber = modelNumber;
	}
	public int getModelNumber() {
		return this.modelNumber;
	}
	public String toString() {
		String str = super.toString() +
										"ModelNumber="+getModelNumber()+'\r'+'\t';
		return str;
	}
}
