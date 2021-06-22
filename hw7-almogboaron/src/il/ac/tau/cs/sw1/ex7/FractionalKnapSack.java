package il.ac.tau.cs.sw1.ex7;
import java.util.*;

public class FractionalKnapSack implements Greedy<FractionalKnapSack.Item>{
    int capacity;
    private double currCapacity;
    List<Item> lst;
    //Initialising 
    FractionalKnapSack(int c, List<Item> lst1){
        capacity = c;
        lst = lst1;
        currCapacity = 0;
    }
    //Item Inner Class
    public static class Item implements Comparable<Item>   {
        double weight, value;
        
        Item(double w, double v) {
            weight = w;
            value = v;
        }

        @Override
        public String toString() {
            return "{" + "weight=" + weight + ", value=" + value + '}';
        }

		@Override
		public int compareTo(Item o) {
			return -Double.compare((double)(this.value/this.weight),(double)(o.value/o.weight));
		}
    }//end of Item (Comparable)

    @Override
    public Iterator<Item> selection() {
    	Collections.sort(lst);
        return lst.iterator();
    }


    @Override
    public boolean feasibility(List<Item> candidates_lst, Item element) {
        return (this.capacity-this.currCapacity)>0;
    }

    @Override
    public void assign(List<Item> candidates_lst, Item element) {
    	double capleft = this.capacity-this.currCapacity;
    	if (capleft >=element.weight) {
    		this.currCapacity = this.currCapacity + element.weight; 
    		candidates_lst.add(element);
    	}else {
    			this.currCapacity = this.capacity;
    			candidates_lst.add(new Item(capleft,element.value));
    	}
    }

    @Override
    public boolean solution(List<Item> candidates_lst) {
        return (this.capacity-this.currCapacity)==0;
    }
}
