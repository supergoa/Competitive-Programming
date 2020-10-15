import java.util.Scanner;

public class MergeSort {
	static long count = 0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = s.nextInt();
		}
		merge_sort(arr, 0, N-1);
		System.out.println(count);
		s.close();
		
	}
	public static  void merge(int A[ ] , int start, int mid, int end) {
		 //stores the starting position of both parts in temporary variables.
		int p = start ,q = mid+1;

		int[] Arr = new int[end-start+1];
		int k=0;

		for(int i = start ;i <= end ;i++) {
		    if(p > mid) {      //checks if first part comes to an end or not .
		       Arr[ k++ ] = A[ q++] ;
		    }

		   else if ( q > end)  { //checks if second part comes to an end or not
		       Arr[ k++ ] = A[ p++ ];
		       //count++;
		   }
		   else if( A[ p ] <= A[ q ])     //checks which part has smaller element.
		      Arr[ k++ ] = A[ p++ ];

		   else {
		      Arr[ k++ ] = A[ q++];
		      if(mid-p<0)
		    	  System.out.println("no");
		      
		      count+=mid-p+1;
		   }
		 }
		
		  for (int p1=0 ; p1< k ;p1 ++) {
		   /* Now the real array has elements in sorted manner including both 
		        parts.*/
		     A[ start++ ] = Arr[ p1 ] ;                          
		  }
		  //System.out.println("List: " +Arrays.toString(Arr));
	}
	public static void merge_sort (int A[ ] , int start , int end )
	   {
	           if( start < end ) {
	           int mid = (start + end ) / 2 ;           // defines the current array in 2 parts .
	           merge_sort (A, start , mid ) ;                 // sort the 1st part of array .
	           merge_sort (A,mid+1 , end ) ;              // sort the 2nd part of array.

	         // merge the both parts by comparing elements of both the parts.
	          merge(A,start , mid , end );   
	   }                    
	}
}
