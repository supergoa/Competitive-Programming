import java.util.Arrays;

public class insertion {
	public static void main(String[] args) {
		int a[] = {5,2,4,6,1,3};
		int b[] = {5,2,4,6,1,3};
		
		insertion_sort1(a, 6);
		System.out.println();
		insertion_sort2(b, 6);
		System.out.println();
		System.out.println();
		System.out.println(Arrays.toString(a) +"\n"+Arrays.toString(b));
	}
	static void insertion_sort1 ( int A[ ] , int n) 
	{
	     for( int i = 0 ;i < n ; i++ ) {
	    /*storing current element whose left side is checked for its 
	             correct position .*/

	      int temp = A[ i ];    
	      int j = i;

	       /* check whether the adjacent element in left side is greater or
	            less than the current element. */

	          while(  j > 0  && temp < A[ j -1]) {

	           // moving the left side element to one position forward.
	                A[ j ] = A[ j-1];   
	                j= j - 1;
	                
	           }
	          
	         // moving current element to its  correct position.
	           A[ j ] = temp;  
	           System.out.println(Arrays.toString(A));
	     }  
	}
	
	static void insertion_sort2 ( int A[ ] , int n) 
	{
		for(int j=1; j<n; j++) {
			int i=0;
			while(A[j] > A[i]) {
				i += 1;
			}
			int temp = A[j];
			for(int k=0; k<j-i; k++) {
				A[j-k] = A[j-k-1];
				
			}
			
			A[i] = temp;
			System.out.println(Arrays.toString(A));
		}
	       
	}
}
