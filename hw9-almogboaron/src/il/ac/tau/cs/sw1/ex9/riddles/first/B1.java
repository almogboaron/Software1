package il.ac.tau.cs.sw1.ex9.riddles.first;

public class B1 extends A1 {
	protected C1 c =  new C1();
	@Override
	protected boolean foo() {
		return !c.foo();
		
	}
	
}
