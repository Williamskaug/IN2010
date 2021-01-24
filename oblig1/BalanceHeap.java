import java.util.*; 
  

class BalanceHeap {
        static Node midten;

        int finnMidten(PriorityQueue<Node> x){
            PriorityQueue<Node> midlertidig = x;

            Node a = null;
            int sentrum = x.size()/2;
            for(int i = 0; i < sentrum; i++){ //Midten +- 1?
                a = x.poll();
            }

            x = midlertidig;
            return a.verdi;
        }


        Node balanserArray(PriorityQueue<Node> x, int start, int slutt){ 
            if (start > slutt) { 
                return null; 
            } 
    
            int sentrum = (start + slutt)/2;
            Node node = new Node(finnMidten(x));

            node.hoyre = balanserArray(x, sentrum + 1, slutt); 
            node.venstre = balanserArray(x, start, sentrum - 1); 
            return node;
        } 

        //Skriver ut venstre nodes verdi først, så hoyres. Kaller rekursivt på neste node.
        void utskrift(Node node){ 
            if (node == null) { 
                return; 
            }
            System.out.println(node.verdi); 
            utskrift(node.venstre); 
            utskrift(node.hoyre); 
        } 
        

        public static void main(String[] args){
            PriorityQueue<Node> pq1 = new PriorityQueue<Node>();
            for(int i = 0; i < 11; i++){
                Node nyNode = new Node(i);
                pq1.offer(nyNode);
            }

            BalanceHeap tree = new BalanceHeap(); 
            //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            //int[] arr = in.lines().mapToInt(i -> Integer.parseInt(i)).toArray();
            int a = pq1.size(); 
            midten = tree.balanserArray(pq1, 0, a - 1); 
            tree.utskrift(midten); 
        }
} 