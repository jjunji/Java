import java.util.*;

public class ArraysEx {
	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4};
		int[][] arr2D = { {11,12,13}, {21,22,23} };
		
		System.out.println("arr = " + Arrays.toString(arr));
		System.out.println("arr2D = " + Arrays.deepToString(arr2D));
		
		System.out.println("========================");
		
		int[] arr2 = Arrays.copyOf(arr, arr.length);
		int[] arr3 = Arrays.copyOf(arr, 3);
		int[] arr4 = Arrays.copyOf(arr, 7);
		int[] arr5 = Arrays.copyOfRange(arr, 2, 4); // 23
		int[] arr6 = Arrays.copyOfRange(arr, 0, 7);
		
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr3));
		System.out.println(Arrays.toString(arr4));
		System.out.println(Arrays.toString(arr5));
		System.out.println(Arrays.toString(arr6));
		
		System.out.println("=========================");
		
		int[] arr7 = new int[5];
		Arrays.fill(arr7, 9);
		System.out.println(Arrays.toString(arr7));
		
		Arrays.setAll(arr7, i -> (int) (Math.random()*6)+1);
		System.out.println(Arrays.toString(arr7));
		
		for(int i : arr7){
			char[] graph = new char[i];
			Arrays.fill(graph, '*');
			System.out.println(new String(graph) + i);
		}
		
		System.out.println("========================");
		
		char[] chArr = { 'D', 'C', 'A', 'B', 'E' };
		
		int idx = Arrays.binarySearch(chArr, 'B');
		System.out.println(Arrays.toString(chArr));
		System.out.println(idx);
		Arrays.sort(chArr);
		int idx2 = Arrays.binarySearch(chArr, 'B');
		System.out.println(Arrays.toString(chArr));
		System.out.println(idx2);
	}
}