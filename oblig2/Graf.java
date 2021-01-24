import java.util.Stack;

public class Graf {
    private Task[] tasks, output;
    public int tid;

    //Topologisk sorteringsalgoritme som vi har hentet fra forelesningslide.
    public Graf(Task[] tasks) {
        this.tasks = tasks;
        Stack < Task > S = new Stack < > ();
        int[] inCount = new int[tasks.length];
        output = new Task[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            inCount[i] = tasks[i].cntPredecessors;
            if (inCount[i] == 0) {
                S.push(tasks[i]);
                tasks[i].visit();
            }
        }
        int i = 0;
        while (S.empty() == false) {
            Task currentTask = S.pop();
            output[i] = currentTask;
            currentTask.visit();
            i++;
            for (Task thisTask: tasks) {
                if (thisTask.isVisited() == false) {
                    if (thisTask.getDependencies().contains(currentTask)) {
                        inCount[thisTask.getId() - 1] = inCount[thisTask.getId() - 1] - 1;
                    }
                    if (inCount[thisTask.getId() - 1] == 0) {
                        S.push(thisTask);
                        thisTask.visit();
                    }
                }
            }
        }
        if (i > tasks.length - 1) {
            System.out.println("Grafen er ferdig uten en sykel.");
            System.out.println();
            setInfo();
            printInfo();
            run();

        } else {
            isCycle();
        }
    }

    //Metode som starter simuleringen.
    public void run() {
        System.out.println();
        System.out.println("Tasks startet etter tid:");
        int arbeidskraft = 0;

        for (int i = 0; i <= tid; i++) {
            boolean delta = false;

            for (Task task: output) {
                if (task.earliestStart == i) {
                    if (!delta) {
                        System.out.println("");
                        System.out.println("Tid: " + i);
                        delta = true;
                    }
                    System.out.println("Start: " + task.id);
                    arbeidskraft += task.staff;
                } else if ((task.earliestStart + task.time) == i) {
                    if (!delta) {
                        System.out.println("");
                        System.out.println("Tid: " + i);
                        delta = true;
                    }
                    System.out.println("Ferdig: " + task.id);
                    arbeidskraft -= task.staff;
                }
            }

            if (delta) {
                System.out.println("Naavaerende ansatte: " + arbeidskraft);
            }
        }
        System.out.println("");
        System.out.println("Total tid: " + tid);
    }

    //Setter verdier til task som vi printer ut senere.
    public void setInfo() {
        int i = 0;
        for (Task task: output) {
            task.setEarliestStart();
            task.setLatestStart();
            i = task.getTime();
        }
        tid = i;
        for (int j = output.length - 1; j >= 0; j--) {
            Task task = output[j];
            if (task.getEdges().size() == 0) {
                task.latestFinish = tid;
                task.latestStart = tid - task.time;
            } else {
                task.setLatestStart();
                task.latestStart = task.latestFinish - task.time;
            }
            task.slack = task.latestStart - task.earliestStart;
        }
    }

    //Skriver ut info til en task.
    public void printInfo() {
        for (Task task: tasks) {
            System.out.println();
            System.out.println("ID: " + task.id);
            System.out.println("Navn: " + task.name);
            System.out.println("Tid: " + task.time);
            System.out.println("Arbeidskraft nodvendig: " + task.staff);
            System.out.println("Tidligste start: " + task.earliestStart);
            System.out.println("Seneste start: " + task.latestStart);
            System.out.println("Slack: " + task.slack);
            System.out.print("Predecessors: ");
            for (Task dep: task.outEdges) {
                System.out.print(dep.id + " ");
            }
            System.out.println();
        }
    }


    //Oppretter to lister, en over besøkte, og en over liste som sier om tasks er sykel eller ikke. Kaller deretter på isCycleUtil som endrer på disse listene.
    public boolean isCycle() {
        boolean visited[] = new boolean[tasks.length];
        boolean cycleList[] = new boolean[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            if (isCycleUtil(i, visited, cycleList)) {
                return true;
            }
        }
        return false;
    }

    //Ser om en task med id x er del av en sykel og deretter setter true eller false i listen cycleList. 
    public boolean isCycleUtil(int x, boolean[] visited, boolean[] cycleList) {
        visited[x] = true;
        cycleList[x] = true;

        for (Task task: tasks[x].outEdges) {
            int nabo = task.id - 1;
            if (!visited[nabo] && isCycleUtil(nabo, visited, cycleList)) {
                return true;
            } else if (cycleList[nabo]) {
                printOutCycle(cycleList);
                return true;
            }
        }

        cycleList[x] = false;
        return false;
    }

    //Skriver ut hvilke tasks som er i sykelen.
    public void printOutCycle(boolean[] cycleList) {
        System.out.println();
        System.out.println("Stopper fordi det er er sykel.");
        System.out.println();
        System.out.println((("Tasks i sykelen: ")));
        for (int i = 0; i < cycleList.length; i++) {
            if (cycleList[i]) {
                System.out.println("Task: " + tasks[i].id);
            }
        }
    }
}