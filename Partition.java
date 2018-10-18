package pictorialLoopInvariants;

public class Partition {
	public static int partition (int [] arr, int left, int right) {
		System.out.println("Partition");
		int pivot = left;
		int l_spot = left+1, r_spot = right - 1;
		boolean hasLooped = false;

		if(right - left == 2) {
			if(arr[pivot] > arr[right-1]) {
				int temp = arr[pivot];
				arr[pivot] = arr[right-1];
				arr[right-1] = temp;
				return right - 1;
			}
		}

		if(l_spot == right) {
			System.out.println("Same");
			return left;
		}
		while (r_spot != l_spot) {
			hasLooped = true;
			System.out.print("{");
			for (int i : arr) {
				System.out.print("," + i);	
			}
			System.out.println("}");
			System.out.println("Main" + r_spot + ", " + l_spot);
			while (arr[l_spot] < arr[pivot] && l_spot < r_spot) {
				System.out.println("Sub1");
				l_spot++;
			}

			while (arr[r_spot] > arr[pivot] && l_spot < r_spot) {
				System.out.println("Sub2");
				r_spot--;
			}

			int temp = arr[l_spot];
			arr[l_spot] = arr[r_spot];
			arr[r_spot] = temp;
		}

		if(l_spot == right -1 && hasLooped) {
			l_spot++;
		}

		int temp = arr[l_spot-1];
		arr[l_spot-1] = arr[pivot];
		arr[pivot] = temp;

		System.out.print("{");
		for(int i = left; i < right; i++)
		{
			System.out.print("," + arr[i]);	
		}
		System.out.println("}");
		return l_spot - 1;
	}

	public static int select(int[]a, int k) {
		int index = 0;
		int left = 0;
		int right = a.length;
		index = partition(a, left, right);
		while(index+1 != k) {
			System.out.println("Looping");
			if(index+1 < k) {
				left = index;
				index = partition(a, left+1, right);

			}
			else if(index+1 > k) {
				right = index;
				index = partition(a, left, right);
			}


		}

		return a[index];

	}

	public static int median(int[]a) {
		return select(a, a.length/2);

	}

	public static void quickSort(int []arr) {

		kernel(arr, 0, arr.length);
	}
	private static void kernel(int []arr, int l, int r) {
		if(l < r) {
			int index = partition(arr, l, r);
			if(r-l > 2) {
				kernel(arr, l, index);
				kernel(arr, index+1, r);
			}
			else {
				partition(arr, l, r);
			}
		}
	}
	public static void main(String[] args) {
		int [] arr = {2,-21, 0, 22 ,100, -9, 3, 5, 4, -1000};

		//System.out.println(Partition.partition(arr, 0, arr.length));
		//int [] arr2 = {2};
		//	System.out.println(Partition.partition(arr2, 0,1));

		quickSort(arr);
		System.out.print("{");
		for (int i : arr) {
			System.out.print("," + i);	
		}
		System.out.println("}");

		//System.out.println(median(arr));





	}
}
