
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by DEVOPS on 5/22/2017.
 */
public class ProjectTest
{
    //US0
    @Test
    public void createAnEmptyProject() {
        Project project = new Project();

        assertTrue(project instanceof Project);
    }

    //US0
    @Test
    public void createAProjectWithName() {
        Project project = new Project("My First Project");

        assertTrue(project instanceof Project);
        assertEquals("My First Project", project.getName());
    }

    //US1
    @Test
    public void calculateTimeToDeliverOfASingleTask() {
        Project project = new Project("sample");
        Task singleTask = new Task();
        singleTask.setTimeToComplete(5);

        project.addTask(singleTask);

        assertEquals(5, project.calculateTimeToDelivery());
    }

    //US1
    @Test
    public void calculateTimeToDeliveryOf2NonDependentTasks() {
        Project project = new Project("sample");

        Task firstTask = new Task("t1", 6);
        Task secondTask = new Task("t2", 3);

        project.addTask(firstTask);
        project.addTask(secondTask);

        //project.getTasks().add("another task");

        assertEquals(6, project.calculateTimeToDelivery());
    }

    //US1
    @Test
    public void calculateTimeToDeliveryOf2DependentTasks() {
        Project project = new Project("sample");

        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 3);

        secondTask.dependsOn(firstTask);

        project.addTask(firstTask);
        project.addTask(secondTask);

        assertEquals(8, project.calculateTimeToDelivery());
    }

    //Homework
    @Test
    public void calculateTimeToDeliveryOf1TaskDependentOf2Tasks() {
        Project project = new Project("sample");

        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 8);
        Task thirdTask = new Task("t2", 1);

        Task independentTask = project.calculateMajorDeliveryTimeOfTwoTasks(firstTask, secondTask);

        thirdTask.dependsOn(independentTask);

        project.addTask(thirdTask);
        project.addTask(independentTask);

        assertEquals(9, project.calculateTimeToDelivery());
    }

    @Test
    public void calculateTimeToDeliveryOf1TaskDependentOfMultipleTasks() {
        Project project = new Project("sample");

        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 8);
        Task thirdTask = new Task("t3", 3);
        Task fourthTask = new Task("t4", 4);
        Task fifthTask = new Task("t5", 1);
        Task sixthTask = new Task("t6", 4);

        Task majorBetween2and3 = project.calculateMajorDeliveryTimeOfTwoTasks(thirdTask, secondTask);
        Task majorBetween4and5 = project.calculateMajorDeliveryTimeOfTwoTasks(fourthTask, fifthTask);

        majorBetween2and3.dependsOn(firstTask);
        majorBetween4and5.dependsOn(majorBetween2and3);
        sixthTask.dependsOn(majorBetween4and5);

        project.addTask(firstTask);
        project.addTask(majorBetween2and3);
        project.addTask(majorBetween4and5);
        project.addTask(sixthTask);

        assertEquals(21, project.calculateTimeToDelivery());
    }

    @Test
    public  void projectCannotHaveDuplicateTasks() {
        Project project = new Project("Sample");
        Task firstTask = new Task("t1", 5);

        project.addTask(firstTask);
        project.addTask(new Task("t1", 5));

        assertEquals(1, project.countTasks());
    }



}
