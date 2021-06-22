package il.ac.tau.cs.sw1.ex7;
import java.util.*;


public class Graph implements Greedy<Graph.Edge>{
    List<Edge> lst; //Graph is represented in Edge-List. It is undirected. Assumed to be connected.
    int n; //nodes are in [0,...,n-1]
    private int[] parent;

    Graph(int n1, List<Edge> lst1){
        lst = lst1;
        n = n1+1;
        parent = new int[n];
        initializeParent();
    }
//---------------------------------------------------------------------------------------------------
    public static class Edge implements Comparable<Edge>{
        int node1, node2;
        double weight;

        Edge(int n1, int n2, double w) {
            node1 = n1;
            node2 = n2;
            weight = w;
        }

        @Override
        public String toString() {
            return "{" + "(" + node1 + "," + node2 + "), weight=" + weight + '}';
        }

		@Override
		public int compareTo(Edge o) {
			double w=Double.compare(this.weight, o.weight);
			if(w==0) {
				double node1=Double.compare(this.node1,o.node1);
				if(node1==0) {
					return Double.compare(this.node2,o.node2);
					}
				return (int)node1;
				}
			return (int)w;
		}
    }

    
    @Override
    public Iterator<Edge> selection() {
    	Collections.sort(lst);
    	return lst.iterator();
    }

    @Override 
    public boolean feasibility(List<Edge> candidates_lst, Edge element) {
    	
        return !isCycle(candidates_lst,element);
    }
    private void initializeParent() {
    	for(int i=0;i<this.n;i++) {
    		parent[i]=-1;
    	}
    }
    //helper function to find subset of an element i
    private int find(int i ) {
    	if(parent[i]==-1) {
    		return i;
    	}
    	return find(parent[i]);
    }
    
    private void union(int x,int y) {
    	parent[x]=y;
    }
    private boolean isCycle(List<Edge> candidates_lst,Edge element) {
    	
           int x = find(element.node1);
           int y = find(element.node2);
           if (x == y) {
                return true;
           }
       	union(x, y);
        return false;
    }
    @Override
    public void assign(List<Edge> candidates_lst, Edge element) {
    	candidates_lst.add(element);
    }

    @Override
    public boolean solution(List<Edge> candidates_lst) {
    	if (candidates_lst.size()==this.n-1) {
    		return true;
    	}
        return false;
    }
}
