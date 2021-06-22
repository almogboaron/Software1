package il.ac.tau.cs.sw1.ex9.starfleet;

public class Officer extends CrewWoman{
		
	protected OfficerRank EnumRank;
	
	public Officer(String name, int age, int yearsInService, OfficerRank rank) {
		super(age,yearsInService,name);
		this.EnumRank = rank;
	
	}
	public OfficerRank getRank() {
		return EnumRank;
		
	}
	public String toString() {
		String str = super.toString() +
										"Rank="+getRank()+'\r'+'\t';
		return str;
	}
	
}
