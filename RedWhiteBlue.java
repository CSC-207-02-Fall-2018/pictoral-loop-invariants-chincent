/**
 * This class implements two static dutch flag methods corresponding to invariant A and C in
 * the lab. It also implements various helper methods.
 * 
 * @author Chase Snodgrass and Vincent Noh
 *
 */
public class RedWhiteBlue {
	/**
	 * Enumerates colors in order
	 */
	enum color {
		RED, WHITE, BLUE;
	}
	
	/**
	 * Populates array with 0s, 1s, and 2s (corresponds to our colors) after running.
	 * 
	 * @param arr		Array of ints to be populated
	 */
	public static void generateArray(int arr[]) {
		for( int i = 0; i < arr.length; i++) {
			arr[i] = (int) Math.floor (3 * Math.random ());
		}
	}
	
	/**
	 * Swaps two elements within an array. After running, the value at index1
	 * will be that of index2 and vice-versa.
	 * 
	 * @param arr		Array of ints in which elements will be swapped
	 * @param index1	Index of the first element to be swapped
	 * @param index2	Index of the second element to be swapped
	 */
	public static void swap (int arr [], int index1, int index2) {
		int temp = arr[index1];
		
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * Prints the contents of an array of ints
	 * 
	 * @param arr	Array of ints
	 */
	public static void printArray (int arr []) {
		System.out.print("{");
		for (int i : arr) {
			System.out.print(" " + i);	
		}
		System.out.println("}");
	}
	
	/**
	 * Implements Invariant A version of the Dutch Flag problem from our lab.
	 * The given array will be sorted for all 0s to come first, followed by
	 * 1s then 2s originally in the array. This is completed in O(n) time.
	 * 
	 * @param arr		Array of ints with values of only 0s, 1s, and 2s
	 */
	public static void dutchFlagA (int arr []) {
		System.out.println("This array be like");
		printArray (arr);
		
		// Denote the index of end of the portion of the array containing that color. 
		// This index is exclusive (ie the value at that index will not necessarily be the color).
		// Also note that index also keeps track of the end of the blue portion.
		int red = 0, white = 0;
		int index = 0;
		
		// Loop Invariant: red will be an unprocessed index right after the end of the red portion
		//	white will be an unprocessed index right after the end of the white portion
		//	index will be an unprocessed index right after the end of the blue portion
		while (index < arr.length) {
			if (arr[index] == color.WHITE.ordinal () ) {
				swap (arr, index, white);
				
				white++;
			}
			else if (arr[index] == color.RED.ordinal ()) {
				swap (arr, index, white);
				swap (arr, white, red);
				
				red++;
				white++;
			}
			
			index++;

		}
		System.out.println("It is sorted into:");
		RedWhiteBlue.printArray (arr);
		System.out.println();
	}
	
	/**
	 * Implements Invariant C version of the Dutch Flag problem from our lab.
	 * The given array will be sorted for all 0s to come first, followed by
	 * 1s then 2s originally in the array. This is completed in O(n) time.
	 * 
	 * @param arr		Array of ints with values of only 0s, 1s, and 2s
	 */
	public static void dutchFlagC (int arr []) {
		System.out.println("This array begin as:");
		printArray (arr);
		
		// Blue denotes the index of beginning of the blue portion of the array. 
		// Red denotes the index of the end of the red portion of the array.
		// These indexes are exclusive (IE the value at that index will not necessarily be the color).
		// Also note that index also keeps track of the end of the red portion.
		int red = 0, blue = arr.length - 1;
		int index = arr.length - 1;
		
		// Loop Invariant: red will be an unprocessed index right after the end of the red portion
		//	blue will be an unprocessed index right before the beginning of the blue portion
		//	index will be an unprocessed index right before the beginning of the white portion
		while (index >= red) {
			if (arr[index] == color.BLUE.ordinal ()) {
				swap (arr, index, blue);
				blue--;
				index--;
			}
			else if (arr[index] == color.WHITE.ordinal ()) {
				index--;
			}
			else if (arr[index] == color.RED.ordinal ()) {
				swap (arr, index, red);
				red++;
			}
		}
		
		System.out.println("It is sorted into:");
		RedWhiteBlue.printArray (arr);
		System.out.println();
	}
	
	
	public static void main (String [] args) {
		int [] colors = new int [50];
		int [] colors2 = new int [50];
		int [] onlyBlue = new int [50];
		int [] notBlue = new int [50];
		int [] smallArr = {2};
		
		RedWhiteBlue.generateArray (colors);
		RedWhiteBlue.generateArray (colors2);
		
		for (int i = 0; i < onlyBlue.length; i++) {
			onlyBlue[i] = 2;
			notBlue[i] = (int) Math.floor (2 * Math.random ());
		}
		// Test dutchFlagA
		System.out.println ("Testing dutchFlagA");
		RedWhiteBlue.dutchFlagA (colors);
		RedWhiteBlue.dutchFlagA (colors2);
		RedWhiteBlue.dutchFlagA (onlyBlue);
		RedWhiteBlue.dutchFlagA (notBlue);
		RedWhiteBlue.dutchFlagA (smallArr);
		
		RedWhiteBlue.generateArray (colors);
		RedWhiteBlue.generateArray (colors2);
		
		for (int i = 0; i < onlyBlue.length; i++) {
			onlyBlue[i] = 2;
			notBlue[i] = (int) Math.floor (2 * Math.random ());
		}
		
		// Test dutchFlagC
		System.out.println ("Testing dutchFlagC");
		RedWhiteBlue.dutchFlagC (colors);
		RedWhiteBlue.dutchFlagC (colors2);
		RedWhiteBlue.dutchFlagC (onlyBlue);
		RedWhiteBlue.dutchFlagC (notBlue);
		RedWhiteBlue.dutchFlagC (smallArr);
	}
}
