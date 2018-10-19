/*****************************************************************
 * Vincent Noh and Chase Snodgrass                               *
 * 4341 and 4553                                                 *
 * Program for CSC 207                                           *
 *   Pictoral Loop Invariants                                    *
 * Assignment for Friday, October 18                             *
 *****************************************************************/


/* ***************************************************************
 * Academic honesty certification:                               *
 *   Written/online sources used:                                *
 *     Pictoral Loop Invariants Lab                              *
 *   Help obtained                                               *
 *     none                                                      *
 *   My signature below confirms that the above list of sources  *
 *   is complete AND that I have not talked to anyone else       *
 *   [e.g., CSC 161 students] about the solution to this problem *
 *                                                               *
 *   Signature: Vincent Noh & Chase Snodgrass                    *
 *****************************************************************/

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
     * the passed array.
     * 
     * Note that this method will pass an exception on an empty array.
     * 
     * @param arr An array of ints
     * @param left an int: 0 <= left < right
     * @param right an int: 0 < right <= arr.length
     * @return an int indicating the position of the random element
     */
    public static int partition (int [] arr, int left, int right) {

        int pivot = left;

        // makes the pivot a random value in the array
        swap (arr, pivot, left + 
                ((int) Math.floor (Math.random() * (right - left))));

        int l_spot = left + 1, r_spot = right - 1;

        while (r_spot >= l_spot) {
            while (l_spot <= r_spot && arr[l_spot] < arr[pivot]) {
                l_spot++;
            }

            while (l_spot <= r_spot && arr[r_spot] > arr[pivot]) {
                r_spot--;
            }

            if (r_spot >= l_spot) {
                swap (arr, l_spot, r_spot);
            }
        }

        //puts the pivot in its place
        swap (arr, r_spot, pivot);
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
     * Prints the contents of an array of ints
     * 
     * @param arr   Array of ints
     */
    public static void printArray (int arr []) {
        System.out.print("{");
        for (int i : arr) {
            System.out.print(" " + i);  
        }
        System.out.println("}");
    }

    /**
     * this method finds the kth largest element of the array a
     * @param a an array of ints
     * @param k an int
     * @return an int giving the kth largest element
     */
    public static int select (int [] a, int k) {
        int index = 0;
        int left = 0;
        int right = a.length;

        k = a.length - k + 1;
        index = partition (a, left, right);

        while (index + 1 != k) {
            if (index + 1 < k) {
                left = index;
                index = partition (a, left + 1, right);

            }
            else if (index + 1 > k) {
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
    public static int median(int [] a) {
        return select (a, (int) Math.ceil(a.length / 2.0));

    }

    /**
     * this method sorts an array of ints by utilizing the partition method. This is
     * the quick sort sorting method
     * @param arr an array of ints
     */
    public static void quickSort (int [] arr) {

        kernel (arr, 0, arr.length);
    }

    /**
     * this method is the kernel of the quickSort method
     * @param arr an array of ints
     * @param l an int indicating an index of the array
     * @param r an int: 0 < r <= arr.length
     */
    private static void kernel (int [] arr, int l, int r) {
        if(l < r) {
            int index = partition (arr, l, r);
            
            if (r - l >= 2 ) {
                kernel (arr, l, index);
                kernel (arr, index + 1, r);
            }
            else {
                partition (arr, l, r);
            }
        }
    }

    public static void main(String[] args) {
        int [] inOrder = {1, 2, 3, 4, 5, 6, 7, 8};
        int [] inOrder2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] inOrder3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] inOrder4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int [] revOrder = {8, 7, 6, 5, 4, 3, 2, 1};
        int [] revOrder2 = {8, 7, 6, 5, 4, 3, 2, 1};
        int [] arr = {2,-21, 0, 22 , 100, -9, -1, 3, 5, 4, -1000};
        int [] arr1 = {2,-21, 0, 22 , 100, -9, -1, 3, 5, 4, -1000};
        int [] arr2 = {2,-21, 0, 22 , 100, -9, -1, 3, 5, 4, -1000};
        int [] smallFirst = {1, 2};
        int [] bigFirst = {2, 1};
        int [] bigFirst2 = {2, 1};
        int [] bigFirst3 = {2, 1};
        int [] oneEl = {1};
        int [] empty = {};

        // Test median on even and odd sized arrays
        System.out.println ("Testing median on even and odd sized arrays");
        System.out.println (median (inOrder));
        System.out.println (median (inOrder2));
        System.out.println ();
        
        // Test select
        System.out.println ("Testing select");
        System.out.println (select (inOrder, 1));
        System.out.println (select (inOrder, 8));
        System.out.println (select (arr1, 3));
        System.out.println ();
        
        // Test Partition
        System.out.println ("Testing partition");
        System.out.println (partition (inOrder3, 0, inOrder2.length));
        Partition.printArray (inOrder3);
        System.out.println ();
        
        System.out.println (partition (revOrder, 0, revOrder.length));
        Partition.printArray (revOrder);
        System.out.println ();
        
        System.out.println (partition (arr, 3, 6));
        Partition.printArray (arr);
        System.out.println ();
        
        System.out.println (partition (bigFirst, 0, bigFirst.length));
        Partition.printArray (bigFirst);
        System.out.println ();
        
        System.out.println (partition (bigFirst2, 0, 1));
        Partition.printArray (bigFirst2);
        System.out.println ();
        
        try {
            partition (empty, 0, 0);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println ("IndexOutOfBoundsException passed");
        }
        System.out.println ();
        
        // Test Quicksort
        System.out.println ("Testing quickSort");
        quickSort (inOrder4);
        quickSort (revOrder2);
        quickSort (arr2);
        quickSort (smallFirst);
        quickSort (bigFirst3);
        quickSort (oneEl);
        
        Partition.printArray (inOrder4);
        Partition.printArray (revOrder2);
        Partition.printArray (arr2);
        Partition.printArray (smallFirst);
        Partition.printArray (bigFirst3);
        Partition.printArray (oneEl);
        
        try {
            quickSort (empty);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println ("IndexOutOfBoundsException passed");
        }
    }
}
