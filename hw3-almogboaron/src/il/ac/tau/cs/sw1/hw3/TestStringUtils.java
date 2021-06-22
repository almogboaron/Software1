package il.ac.tau.cs.sw1.hw3;

public class TestStringUtils {
	public static void main(String[] args) {
		System.out.println( StringUtils.findSortedSequence("my mind is an empty zoo"));
		System.out.println( StringUtils.findSortedSequence("to be or not to be").equals("not to"));
		System.out.println( StringUtils.findSortedSequence("").equals(""));
		System.out.println( StringUtils.findSortedSequence("andy bought candy"));
		System.out.println( StringUtils.findSortedSequence("life is not not not fair").equals("is not not not"));
		
		System.out.println(StringUtils.isAnagram("mothEr in law","hitlEr woman"));
		System.out.println(StringUtils.isAnagram("software","jeans"));
		System.out.println(StringUtils.isAnagram("Funeral","real Fun"));
		System.out.println(StringUtils.isEditDistanceOne("dog","god"));
		System.out.println(StringUtils.isEditDistanceOne("x","x"));
		System.out.println(StringUtils.isEditDistanceOne("ab","cab"));
	}

}
