class Selection extends Sorter {
    void sort() {
        int n = A.length; 
  
        for (int i = 0; i < n-1; i++){
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (lt(A[j],A[min_idx])){ 
                    min_idx = j; 
                }
            int temp = A[min_idx]; 
            swap(min_idx,i); 
            A[i] = temp; 
        } 
    }
    
    String algorithmName() {
        return "selection";
    }
}