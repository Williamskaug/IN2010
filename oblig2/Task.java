import java.util.ArrayList;
import java.util.List;

class Task {
    int id, time, staff, slack;
    public static int totalTime = 0;
    String name, filename;
    int earliestStart, latestStart, earliestFinish, latestFinish;
    List < Task > outEdges = new ArrayList < Task > ();
    List < Task > dependencies = new ArrayList < Task > ();
    int cntPredecessors;
    boolean visited = false;

    public Task(int id) {
        this.id = id;
    }
    public void addEdge(Task task) {
        outEdges.add(task);
    }
    public List < Task > getEdges() {
        return outEdges;
    }
    public void addDependencies(Task task) {
        dependencies.add(task);
    }
    public List < Task > getDependencies() {
        return dependencies;
    }
    public int getEarliestFinish() {
        return earliestStart + time;
    }
    public int getLatestFinish() {
        return latestStart + time;
    }
    public boolean isVisited() {
        return visited;
    }
    public void visit() {
        visited = true;
    }
    public int getStaff() {
        return staff;
    }
    public int getId() {
        return id;
    }
    public int getTime() {
        return totalTime;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public void setEarliestStart() {
        for (Task depTask: outEdges) {
            if (depTask.earliestStart < earliestStart + time) {
                depTask.earliestStart = earliestStart + time;
            }
            if (depTask.earliestStart + depTask.time > totalTime) {
                totalTime = depTask.earliestStart + depTask.time;
            }
            depTask.setEarliestStart();
        }
    }
    public void setLatestStart() {
        int least = Integer.MAX_VALUE;
        for (Task depTask: outEdges) {
            if (least > depTask.latestStart) {
                least = depTask.latestStart;
            }
            latestFinish = least;
        }
    }
}