

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Marco on 5/22/2017.
 */
public class Project
{
    private String name;
    private Vector<Task> tasks;

    public Project() {
        tasks = new Vector<Task>();
    }

    public Project(String name) {
        this.name = name;
        tasks = new Vector<Task>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public int calculateTimeToDelivery() {
        int maxTimeToComplete = 0;
        for (Task task: tasks) {
            int time = task.calculateTimeToComplete();
            if (time > maxTimeToComplete) {
                maxTimeToComplete = time;
            }
        }
        return maxTimeToComplete;
    }

    public Task calculateMajorDeliveryTimeOfTwoTasks(Task firstTask, Task secondTask) {
        if (firstTask.getTimeToComplete() > secondTask.getTimeToComplete()) {
            return firstTask;
        }

        return secondTask;
    }

    @Test
    public void projectCannotHaveDuplicatedTasks() {
        Project project = new Project("Sample");
        Task firstTask = new Task("t1", 5);

        project.addTask(firstTask);
        project.addTask(firstTask);

        assertEquals(5, project.calculateTimeToDelivery());
    }

    @Test
    public void tasksAreEqualIfTheyHaveTheSameNameAndTimeToComplete() {
        Task task = new Task("t1", 5);
        Task expected = new Task("t1", 5);

        assertTrue(task.equals(expected));
    }

    public int countTasks() {
        return tasks.size();
    }
}
