package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{
	
	protected HashMap<T, Integer> map; 

	public HashMapHistogram() {
		this.map = new HashMap<T, Integer>();
		
	}
	
	@Override
	public Iterator<T> iterator() {
		return new HashMapHistogramIterator<T>(map.entrySet());
	}
	@Override
	public void addItem(T item) {
		if (this.map.putIfAbsent(item,0)==null) {
			this.map.put(item, 1);
		}else {
			this.map.put(item, this.map.get(item)+1);
		}
		}
		

	@Override
	public void removeItem(T item) throws IllegalItemException {
		//Protective Coding
		if(!this.map.containsKey(item)) {
			throw new IllegalItemException();
		}
		//Code
		int i = this.map.get(item);
		if(i==1) {
			this.map.remove(item);
		}else {
		this.map.put(item, this.map.get(item)-1);
		}
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		//Protective Coding
		if(k<1) {throw new IllegalKValueException(k);}
		// Code
		for(int i=0;i<k;i++) {
			this.addItem(item);
		}
		}
		
	

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		//Protective Coding 
		if(!this.map.containsKey(item)) 		{throw new IllegalItemException();}
		if((this.map.get(item)<k)||(k<1)) 		{throw new IllegalKValueException(k);}
		//Code
		this.map.put(item, this.map.get(item)-k);
	}
	
	@Override
	public int getCountForItem(T item) {
		if(this.map.containsKey(item)) {
			return this.map.get(item);
		}
		return 0; //replace this with the actual returned value
	}

	@Override
	public void addAll(Collection<T> items) {
		Iterator<T> iter = items.iterator();
		while(iter.hasNext()) {
			this.addItem(iter.next());
		}
		
	}

	@Override
	public void clear() {
		this.map.clear();
		
	}

	@Override
	public Set<T> getItemsSet() {
		return this.map.keySet(); //replace this with the actual returned value
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for(T item: anotherHistogram.getItemsSet()) {
			int k = anotherHistogram.getCountForItem(item);
			try {
				this.addItemKTimes(item, k);
			} catch (IllegalKValueException e) {
				e.printStackTrace();
			}
	}

	
}
}