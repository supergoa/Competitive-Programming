//ALWAYS USE RANGE QUERY AND RANGE UPDATE, NEVER A POINT
class SuperBIT {
  long[] dataMul, dataAdd;
  SuperBIT(int n) {
    dataMul = new long[n];
    dataAdd = new long[n];
  }
  void update(int left, int right, long val) {
    internalUpdate(left, val, -val * (left-1));
    internalUpdate(right, -val, val * right);
  }
  void internalUpdate(int at, long mul, long add) {
    while (at < dataMul.length) {
      dataMul[at] += mul;
      dataAdd[at] += add;
      at |= (at + 1);
    }
  }
  long query(int at) {
    long mul = 0;
    long add = 0;
    int start = at;
    while(at >= 0) {
      mul += dataMul[at];
      add += dataAdd[at];
      at = (at & (at + 1)) -1;
    }
    return mul * start + add;
  }
  long query(int left, int right) {
    if (left > right) {
      int temp = left;
      left = right;
      right = temp;
    }
    return query(right) - (left > 0 ? query(left-1) : 0);
  }
  long[] indices; // Used for compressed BIT
  // Compressed BIT constructor
  // A BIT that only stores the values that will be updated. 
  // indices is a sorted array of all the unique indices 
  // that would be used for this BIT.
  public SuperBIT(long[] indices) {
    this.indices = indices;
    dataMul = new long[indices.length];
    dataAdd = new long[indices.length];
  }
 
  // Search for the index in the array. If the index was not found, 
  // return the first index lower than the search index.
  int binSearch(int ind) {
    int low = 0;
    int high = dataMul.length-1;
    while(low < high) {
      int mid = (low + high+1)/2;
      if(indices[mid] == ind)
        return mid;
      else if(indices[mid] < ind) 
        low = mid;
      else if(indices[mid] > ind) 
        high = mid-1;
    }
    if(indices[low] > ind)
      --low;
    return low;
  }
  
  // Read the largest index less than or equal to the given index.
  long queryCompr(int index) {
    return query(binSearch(index));
  }
  long queryCompr(int left, int right) {
    return query(binSearch(left), binSearch(right));
  }
  // Update a specific index by a value. If the index is not in this 
  // compressed BIT, the index below will be updated.
  void updateCompr(int index, long val) {
    int ind = binSearch(index);
    update(ind, ind, val);
  }
  void updateCompr(int left, int right, long val) {
    update(binSearch(left), binSearch(right), val);
  } 
}