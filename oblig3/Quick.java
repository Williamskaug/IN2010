class Quick extends Sorter {

    void sort() {
        int lavest = 0;
        int hoyest = A.length-1;

        if (lt(lavest,hoyest)){ 
            int pi = partition(A, lavest, hoyest); 
            sort2(A, lavest, pi-1);
            sort2(A, pi+1, hoyest); 
        } 
    }

    void sort2(int A[], int lavest, int hoyest){
        if (lt(lavest,hoyest)){ 
            int pi = partition(A, lavest, hoyest); 
            sort2(A, lavest, pi-1);
            sort2(A, pi+1, hoyest); 
        } 
    }

    int partition(int A[], int lavest, int hoyest){ 
        int pivot = A[hoyest];  
        int i = (lavest-1); 
        for (int j=lavest; j<hoyest; j++){ 
            if (lt(A[j],pivot)) 
            { 
                i++; 
                int temp = A[i]; 
                swap(i,j);
                A[j] = temp; 
            } 
        } 

        int temp = A[i+1]; 
        swap(i+1, hoyest);
        A[hoyest] = temp; 

        return i+1; 
    } 

    String algorithmName() {
        return "quick";
    }
}
