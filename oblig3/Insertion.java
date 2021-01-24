class Insertion extends Sorter {
    void sort() {
        int n = A.length; 

        for (int i = 1; i < n; ++i) {
            int var1 = A[i]; 
            int j = i - 1; 

            while (geq(j, 0) && gt(A[j], var1)) { 
                swap(j + 1, j); 
                j = j - 1; 
            } 
            A[j + 1] = var1; 
        } 
    }

    String algorithmName() {
        return "insertion";
    }
}
