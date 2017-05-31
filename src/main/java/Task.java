import java.util.Vector;
/**
 * Write a description of class Task here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Task
{
    private String name;
    private String description;
    private int timeToComplete;
    private String owner;
    
    private Vector<Task> preRequisites;
    
    public Task() {
        description = "";
        owner = "";
        name = "";
        preRequisites = new Vector<Task>();
    }
    
    public Task(String name, int timeToComplete) {
        this.name = name;
        this.timeToComplete = timeToComplete;
        preRequisites = new Vector<Task>();
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setTimeToComplete(int timeToComplete) {
        this.timeToComplete = timeToComplete;
    }
    
    public String getName() {
        return name;
    }
    
    public int getTimeToComplete() {
        return timeToComplete;
    }
    
    public void dependsOn(Task otherTask) {
        preRequisites.add(otherTask);
    }
    
    public int calculateTimeToComplete() {
        int maxPreRequisiteTime = 0;
        for (Task preReq: preRequisites) {
            if (preReq.calculateTimeToComplete() > maxPreRequisiteTime) {
                maxPreRequisiteTime = preReq.calculateTimeToComplete();
            }
        }
        
        return getTimeToComplete() + maxPreRequisiteTime;
    }
    
    @Override
    public boolean equals(Object otherObject) {
        Task otherTask = (Task) otherObject;
        return name.equals(otherTask.name) && 
            timeToComplete == otherTask.timeToComplete;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() + 53 * timeToComplete;
    }
    
    @Override
    public String toString() {
        return String.format("<n:%s , t:%s>", name, timeToComplete);
    }
}
