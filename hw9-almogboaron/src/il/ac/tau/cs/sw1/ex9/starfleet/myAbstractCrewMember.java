package il.ac.tau.cs.sw1.ex9.starfleet;

public abstract class myAbstractCrewMember implements CrewMember {
	
	protected String name;
	protected int age ; 
	protected int yearsInService ; 
	
	public myAbstractCrewMember(int age,int yearsInService,String name) {
		this.name=name;
		this.age=age;
		this.yearsInService=yearsInService;
		
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public int getYearsInService() {
		return this.yearsInService;
	}
	public String toString() {
		String str = (this.getClass()+"") +'\r'+'\t'+
				"Name="+this.name+'\r'+'\t'+
				"Age="+this.age+'\r'+'\t'+
				"YearsInService="+this.yearsInService;
				return str;
	
		
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
		myAbstractCrewMember other = (myAbstractCrewMember) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
