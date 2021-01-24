import java.io.File;
import java.util.Scanner;
import java.io.IOException;

class Oblig2 {
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        Scanner in = new Scanner(new File(filename));

        int n = in.nextInt();
        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(i + 1);
        }

        for (int i = 0; i < n; i++) {
            int id = in.nextInt();
            Task task = tasks[id - 1];
            task.name = in.next();
            task.time = in.nextInt();
            task.staff = in.nextInt();

            while (true) {
                int dep = in.nextInt();
                if (dep == 0) {
                    break;
                }
                tasks[dep - 1].addEdge(task);
                tasks[id - 1].cntPredecessors++;
            }
        }
        in.close();
        Scanner inn2 = new Scanner(new File(filename));
        String forste = inn2.nextLine();
        String andre = inn2.nextLine();
        for(int i=0;i<n;i++){
            String linje = inn2.nextLine();
            String[] part = linje.split("\\s+");
            for(int j=4; Integer.parseInt(part[j])!=0;j++){
                tasks[i].addDependencies(tasks[Integer.parseInt(part[j])-1]);
            }
        }
        Graf graf = new Graf(tasks);
    }
}
