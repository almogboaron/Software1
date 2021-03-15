package il.ac.tau.cs.sw1.ex1;

import java.util.Arrays;

public class Assignment1 {
	public static void main(String[] args){
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int	z = Integer.parseInt(args[2]);
		if (x>0 && y>0 && z>0) { 
		int new_args[] = {x,y,z};
		Arrays.sort(new_args);
		if (new_args[2] < new_args[0] + new_args[1]) {
			if (Math.pow(new_args[0], 2) + Math.pow(new_args[1], 2) == Math.pow(new_args[2], 2)){
				System.out.println("The input ("+x+", "+y+", "+z+") defines a valid right triangle!"); }
			else{System.out.println("The input (" + x + ", " + y + ", " + z + ") defines a valid triangle!");} }
		else{System.out.println("The input (" + x + ", " + y + ", " + z + ") does not define a valid triangle!");}
		}else {System.out.println("Invalid input!");}
	}
}