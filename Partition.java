package pictoralLoopInv;

public class Partition {
	public static int partition (int [] arr, int left, int right) {
		
		int pivot = left;
		int l_spot = left + 1, r_spot = right - 1;
		
		
		while (r_spot != l_spot) {
			while (arr[l_spot] < arr[pivot] && l_spot < r_spot) {
				l_spot++;
			}
			
			while (arr[r_spot] > arr[pivot] && l_spot < r_spot) {
				r_spot--;
			}
			
			int temp = arr[l_spot];
			arr[l_spot] = arr[r_spot];
			arr[r_spot] = temp;
		}
		
		int temp = arr[l_spot-1];
		arr[l_spot-1] = arr[pivot];
		arr[pivot] = temp;
		System.out.print("{");
		for (int i : arr) {
			System.out.print("," + i);	
		}
		System.out.println("}");
		return l_spot;
	}
	public static void main(String[] args) {
		int [] arr = {2,-21, 0, 22, 100, -9, 3, 5};
		
		System.out.println(Partition.partition(arr, 0, 8));

	}
}
