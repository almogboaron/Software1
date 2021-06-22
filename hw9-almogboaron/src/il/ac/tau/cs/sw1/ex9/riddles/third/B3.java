package il.ac.tau.cs.sw1.ex9.riddles.third;

public class B3 extends A3{
	public B3(String str) {
		super(str);
	}
	public String getMessage() {
		return this.s;
	}
	@Override
	public void foo(String s) throws Exception{
		if (s.equals(this.s)){
			throw this;
		}
		}
}