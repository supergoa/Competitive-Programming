import java.util.Arrays;

public class PriorityQ {
	public static void main(String[] args) {
		int Arr[ ] = {0,1,4,3,7,8,9,10};
		int N = Arr.length-1;
		build_maxheap(Arr, N);
		System.out.println(Arrays.toString(Arr));
	}
	static void build_maxheap (int Arr[ ], int N)
    {
        for(int i = N/2 ; i >= 1 ; i-- )
        {
            max_heapify (Arr, i, N) ;
        }
    }
	static void max_heapify (int Arr[ ], int i, int N)
    {
        int left = 2*i;                   //left child
        int right = 2*i +1;          //right child
        int largest =0;
        if(left<= N && Arr[left] > Arr[i] )
              largest = left;
        else
             largest = i;
        if(right <= N && Arr[right] > Arr[largest] )
            largest = right;
        if(largest != i )
        {
            int temp = Arr[i];
            Arr[i]=Arr[largest];
            Arr[largest] = temp;
            
            System.out.println(Arrays.toString(Arr));
            max_heapify (Arr, largest,N);
        } 
     }
}
