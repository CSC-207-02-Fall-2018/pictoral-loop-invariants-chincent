package pictorialLoopInvariants;
/**
 * This class contains the partition, median, select, and quickSort methods
 * @author Chase Snodgrass and Vincent Noh
 *
 */
public class Partition {

	/**
	 * partition determines the sorted location of a random element of the array passed to it
	 * in which all elements behind the random element are smaller than it and vice-versa
	 * This function only applies within the bounds of left and right with left being inclusive,
	 * and right being exclusive, so the sorted position of the random element may not be absolute within
	 * the passed array
	 * @param arr An array of ints
	 * @param left an int: 0 <= left < right
	 * @param right an int: 0 < right <= arr.length
	 * @return an int indicating the position of the random element
	 */
	public static int partition (int [] arr, int left, int right) {

		int pivot = left;

		//makes the pivot a random value in the array
		swap(arr, pivot, left + ((int) Math.floor(Math.random()*(right - left))));

		int l_spot = left + 1, r_spot = right - 1;

		while (r_spot >= l_spot) {
			while (l_spot <= r_spot && arr[l_spot] < arr[pivot]) {
				l_spot++;
			}

			while (l_spot <= r_spot && arr[r_spot] > arr[pivot]) {
				r_spot--;
			}

			if(r_spot >= l_spot) {
				swap(arr, l_spot, r_spot);
			}
		}

		//puts the pivot in its place
		swap(arr, r_spot, pivot);
		return r_spot;
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
	 * this method finds the kth largest element of the array a
	 * @param a an array of ints
	 * @param k an int
	 * @return an int giving the kth largest element
	 */
	public static int select(int[]a, int k) {
		int index = 0;
		int left = 0;
		int right = a.length;
		
	k = a.length - k + 1;
		index = partition(a, left, right);
		
		while(index + 1 != k) {
			System.out.println("Looping");
			if(index + 1 < k) {
				left = index;
				index = partition(a, left + 1, right);

			}
			else if(index + 1 > k) {
				right = index;
				index = partition (a, left, right);
			}
		}
		return a[index];
	}

	/**
	 * the method returns the median element of the array a
	 * @param a an array of ints
	 * @return an int that is the median of a
	 */
	public static int median(int[]a) {
		return select(a, (int) Math.ceil(a.length / 2.0));

	}

	/**
	 * this method sorts an array of ints by utilizing the partition method. This is
	 * the quick sort sorting method
	 * @param arr an array of ints
	 */
	public static void quickSort(int []arr) {

		kernel(arr, 0, arr.length);
	}
	
	/**
	 * this method is the kernel of the quickSort method
	 * @param arr an array of ints
	 * @param l an int indicating an index of the array
	 * @param r an int: 0 < r <= arr.length
	 */
	private static void kernel(int []arr, int l, int r) {
		if(l < r) {
			int index = partition(arr, l, r);
			if(r-l >= 2 ) {
				kernel(arr, l, index);
				kernel(arr, index+1, r);
			}
			else {
				partition(arr, l, r);
			}
		}
	}

	public static void main(String[] args) {
		int [] test = {5,4,3,2,1};
		int [] arr = {2,-21, 0, 22 ,100, -9, -1, 3, 5, 4, -1000};

		System.out.println(median(arr));
		quickSort(arr);
		System.out.println("\n\n QuickSort");
		System.out.print("{");
		for (int i : arr) {
			System.out.print(" " + i);
		}
		System.out.println ("}");

		//System.out.println(median(arr));

		//



	}
}
