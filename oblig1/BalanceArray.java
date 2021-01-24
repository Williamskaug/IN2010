import java.io.*;

//Klasse for noder
class Node { 
    int verdi; 
    Node venstre, hoyre; 
      
    Node(int x) { 
        verdi = x; 
        venstre = null;
        hoyre = null; 
    } 
} 


class BalanceArray { 
    static Node midten; 

    Node balanserArray(int arr[], int start, int slutt) { 
        if (start > slutt) { 
            return null; 
        } 

        int sentrum = (start + slutt) / 2; 
        Node node = new Node(arr[sentrum]); 
        node.hoyre = balanserArray(arr, sentrum + 1, slutt); 
        node.venstre = balanserArray(arr, start, sentrum - 1); 
        return node;
    } 
  

    //Skriver ut venstre nodes verdi først, så hoyres. Kaller rekursivt på neste node.
    void utskrift(Node node) { 
        if (node == null) { 
            return; 
        }
        System.out.println(node.verdi); 
        utskrift(node.hoyre); 
        utskrift(node.venstre); 
    } 
      
    //Oppretter et binærtre, leser inn inputarrayet, og kaller på balanserArray()
    public static void main(String[] args) { 
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9,10};
        Binaertre tree = new Binaertre(); 
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //int[] arr = in.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
        int a = arr.length; 
        midten = tree.balanserArray(arr, 0, a - 1); 
        tree.utskrift(midten); 
    } 
} 
