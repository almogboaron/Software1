package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;

public class B4 implements Iterator<String> {
	protected String[] strings;
	protected int k;
	protected int i=0;
	protected int limit;
	
	public B4(String[] strings,int k) {
		this.strings = new String[strings.length*k];
		for(int j=0;j<k;j++) {
			for(int s =0;s<strings.length;s++) {
				this.strings[s+j]=strings[s];
			}
		}
		this.k = k;
		this.limit = strings.length*k;
	}
	@Override
	public boolean hasNext() {
		return (i<limit);
	}

	@Override
	public String next() {
		String str= strings[i];
		i++;
		return str;
	}
	
}
