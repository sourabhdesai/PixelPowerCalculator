//UIUC CS125 SPRING 2013 MP. File: SelectionSort.java, CS125 Project: Challenge7-RecursiveKnight, Version: 2013-04-05T22:19:25-0500.794714000
/**
 * @author svdesai2
 *
 */
public class SelectionSort {
	/**
	 * Sorts the entire array using selection sort
	 * This is just a wrapper method that calls the 
	 * recursive method with the correct parameter lo,hi values.
	 * @param data
	 */
	public static void sort(double[] data) {
		sort(data,0,data.length-1);	
	}
	
	public static void sort(int[] data) {
		sort(data,0,data.length-1);	
	}

	/** Recursively sorts the sub array lo...hi using selection sort algorithm.*/
	public static void sort(double[] data, int lo, int hi) {
		
		if(lo==hi) return;
		int min=findMin(data,lo,hi);
		swap(data, lo, min);
		sort(data,lo+1,hi);
	
	}
	
	public static void sort(int[] data, int lo, int hi) {
		
		if(lo==hi) return;
		int min=findMin(data,lo,hi);
		swap(data, lo, min);
		sort(data,lo+1,hi);
	
	}

	/** Helper method for selection sort: Swaps values at indices i and j*/
	public static void swap(double[] data, int i, int j) {
		double i1=data[i];
		double j1=data[j];
		data[i]=j1;
		data[j]=i1;
	}
	
	public static void swap(int[] data, int i, int j) {
		int i1=data[i];
		int j1=data[j];
		data[i]=j1;
		data[j]=i1;
	}

	/**
	 * Recursively finds the position of the smallest value of the values lo...hi (inclusive). 
	 * @param data
	 * @param lo
	 * @param hi
	 * @return
	 */
	public static int findMin(double[] data, int lo, int hi) {
		if(lo==hi) return lo;
		if(data[lo]>data[hi]) return findMin(data,lo+1,hi);
		if(data[lo]<data[hi]) return findMin(data,lo,hi-1);
		return 0;
	
	}
	
	public static int findMin(int[] data, int lo, int hi) {
		if(lo==hi) return lo;
		if(data[lo]>data[hi]) return findMin(data,lo+1,hi);
		if(data[lo]<data[hi]) return findMin(data,lo,hi-1);
		return 0;
	
	}
}
