/**
 * Created by DEVOPS on 5/22/2017.
 */
public class Task
{
    private String name;
    private String description;
    private int timeToComplete;
    private String owner;

    private Task preRequisite;

    public Task() {
        description = "";
        owner = "";
        name = "";
    }

    public Task(String name, int timeToComplete) {
        this.name = name;
        this.timeToComplete = timeToComplete;
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
        preRequisite = otherTask;
    }

    public Task getPreRequisite() {
        return preRequisite;
    }

    public int calculateTimeToComplete() {
        int time = getTimeToComplete();
        if (getPreRequisite() != null) {
            time = time + getPreRequisite().getTimeToComplete();
        }
        return time;
    }

    @Override
    public boolean equals(Object otherObject) {
        Task otherTask = (Task) otherObject;
        return this.name.equals(otherTask.name) &&
                timeToComplete == otherTask.timeToComplete;
    }
}
