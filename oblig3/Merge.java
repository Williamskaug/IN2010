class Merge extends Sorter {
    void sort(){
        int left = 0;
        int right = A.length-1;
        sort2(A, left, right);
    }

    void sort2(int arr[], int left, int right){
        if (lt(left,right)) {
            int m = (left + right) / 2;
 
            sort2(arr, left, m);
            sort2(arr, m + 1, right);
 
            merge(arr, left, m, right);
        }
    }

    void merge(int arr[], int left, int m, int right){
        int n1 = m - left + 1;
        int n2 = right - m;

        int L[] = new int[n1];
        int R[] = new int[n2];
 
        for (int i = 0; i < n1; ++i)
            swap(i, left+1);
        for (int j = 0; j < n2; ++j)
            swap(j, m + 1 + j);
 
        int i = 0, j = 0;
 
        int k = left;
        while (lt(i,n1) && lt(j,n2)) {
            if (leq(L[i], R[j])){
                arr[k] = L[i];
                i++;    
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (lt(i, n1)) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        while (lt(j, n2)) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    String algorithmName() {
        return "merge";
    }
}