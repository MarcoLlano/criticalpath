import java.util.Set;
import java.util.List;
import java.util.Vector;
import java.util.HashSet;

/**
 * Write a description of class Project here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Project
{
    private String name;
    private Set<Task> tasks; 
    
    public Project() {
        tasks = new HashSet<Task>();
    }
    
    public Project(String name) {
        this.name = name;
        tasks = new HashSet<Task>();
    }
    
    public String getName() {
        return name;
    }
    
    public void addTask(Task task) {
        //if (!tasks.contains(task)) {
            tasks.add(task);
        //}
    }
    
    public int calculateTimeToDelivery() {
        int maxTimeToComplete = 0;
        for (Task task: tasks) {
            int time = task.calculateTimeToComplete();
            if (time > maxTimeToComplete) {
                maxTimeToComplete = time;
                addTaskToCriticalPath(task);
            }
        }
        return maxTimeToComplete;
    }
    
    public int countTasks() {
        return tasks.size();
    }

    public List<Task> addTaskToCriticalPath(Task task) {
        List<Task> path = new Vector<Task>();
        path.add(task);
        return path;
    }
    
    public Set<Task> calculateCriticalPath() {
        List<Task> path = new Vector<Task>();
        Set<Task> taskSize = tasks;
        return taskSize;
    }
}
