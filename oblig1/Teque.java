import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Teque {
    protected int lengde;
    protected Node start, slutt;

    public class Node {
        protected int verdi;
        protected Node neste;
        protected Node forrige;

        public Node(int x){
            this.verdi = x;
        }
    }

    public Teque(){
        lengde = 0;
        start = new Node(0);
        slutt = new Node(0);
        start.neste = slutt;
        slutt.forrige = start;
    }

    public void push_back(int x){
        Node ny = new Node(x);
        slutt.forrige.neste = ny;
        ny.forrige= slutt.forrige;
        ny.neste = slutt;
        slutt.forrige = ny;
        lengde++;
    }

    public void push_front(int x){
        Node ny = new Node(x);
        ny.neste = start.neste;
        ny.forrige = start;
        start.neste.forrige = ny;
        start.neste = ny;

        lengde++;
    }

    public void push_middle(int x){
        int midten = 0;

        midten = (lengde + 1)/2;
        
        Node a = start;
        for(int i=0; i<midten; i++){
            a = a.neste;
        }
        Node ny = new Node(x);
        ny.neste = a.neste;
        ny.forrige = a;

        a.neste = ny;
    
        a = ny.neste;
        a.forrige = ny;
        
        lengde++;

    }

    public int get(int pos){
        if(pos<lengde && pos>=0){
            Node a = start;
            for (int i=0;i<=pos;i++){
                a = a.neste;
            }
            return a.verdi;
        } else {
            throw new UgyldigListeIndeks(pos);
        }
    }

    public static void main(String[] args) {
        try {
            File nyfil = new File(args[0]);
            Scanner inn = new Scanner(nyfil);

            Teque liste = new Teque();
            String[] linje;
            int lengde = Integer.parseInt(inn.nextLine());
            String kommando;
            int verdi;

            //Kaller på liste-kommandoer
            for(int i = 0; i < lengde; i++){
                linje = inn.nextLine().split(" ", 2);
                kommando = linje[0].trim();
                verdi = Integer.parseInt(linje[1].trim());

                if(kommando.equals("push_back")){
                    liste.push_back(verdi);
                } 
                else if(kommando.equals("push_front")){
                    liste.push_front(verdi);
                }
                else if(kommando.equals("push_middle")){
                    liste.push_middle(verdi);
                }
                else if(kommando.equals("get")){
                    System.out.print(liste.get(verdi) + ",");
                }
                else {
                    System.out.println("Feil i kommando-innlesning");
                }
            }
        

        } catch(FileNotFoundException e) {
            System.out.println("Feil med innlesning"); 
        }
    }

}
