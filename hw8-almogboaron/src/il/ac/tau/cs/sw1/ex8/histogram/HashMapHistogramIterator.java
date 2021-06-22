package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
							//Fields In iterator
	
	
	private List<Entry<T, Integer>> Entrylist = new ArrayList<Entry<T, Integer>>();
	private int Size=-1;
	private int current = 0;
	
	
							//Inner Class Comparator
	public class HashMapHistogramComparator implements Comparator<Map.Entry<T,Integer>>{

		@Override
		public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2) {
			if(o1.getValue()==o2.getValue()) {
				return o1.getKey().compareTo(o2.getKey());
			}
			return -Integer.compare(o1.getValue(), o2.getValue());
		}
	}
							//End Of Inner Classs
	
	
							// Iterator Builder
	public HashMapHistogramIterator(Set<Entry<T,Integer>> EntrySet){
		Entrylist.addAll(EntrySet);
		Collections.sort(this.Entrylist,new HashMapHistogramComparator());
		this.Size = this.Entrylist.size();
							
		
	}
	
	@Override
	public boolean hasNext() {
		return current<Size; //replace this with the actual returned value
	}

	@Override
	public T next() {
		Entry<T,Integer> Elem = this.Entrylist.get(current);
		this.current++;
		return  Elem.getKey(); //replace this with the actual returned value
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
