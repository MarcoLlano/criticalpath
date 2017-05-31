

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * The test class ProjectTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ProjectTest
{
    private Project project;
    
    @Before
    public void setUp() {
        project = new Project("sample");
    }
    
    //US0
    @Test
    public void createAnEmptyProject() {
//         Project project = new Project();
        
        assertTrue(project instanceof Project);
    }
    
    //US0
    @Test
    public void createAProjectWithName() {
//         Project project = new Project("My First Project");
        
        assertTrue(project instanceof Project);
        assertEquals("sample", project.getName());
    }
    
    //US1
    @Test
    public void calculateTimeToDeliverOfASingleTask() {
        //Project project = new Project("sample");
        Task singleTask = new Task();
        singleTask.setTimeToComplete(5);
        
        project.addTask(singleTask);
        
        assertEquals(5, project.calculateTimeToDelivery());
    }
    
    //US1
    @Test
    public void calculateTimeToDeliveryOf2NonDependentTasks() {
        //Project project = new Project("sample");
        
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
        //Project project = new Project("sample");
        
        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 3);
        
        secondTask.dependsOn(firstTask);
        
        project.addTask(firstTask);
        project.addTask(secondTask);
                
        assertEquals(8, project.calculateTimeToDelivery());
    }
    
    @Test
    public void calculateTimeToDeliverOneTaskDependsOnOtherTwo() {
        //Project project = new Project("sample");
        
        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 6);
        Task thirdTask = new Task("t3", 3);
        
        
        thirdTask.dependsOn(secondTask);
        thirdTask.dependsOn(firstTask);
        
        project.addTask(firstTask);
        project.addTask(secondTask);
        project.addTask(thirdTask);
                
        assertEquals(9, project.calculateTimeToDelivery());
    }
    
    //US1, US5   
    @Test
    public void calculateTimeToCompleteForADiamondShapedDependency() {
//         Project project = new Project("sample");
        
        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 2);
        Task thirdTask = new Task("t3", 6);
        Task fourthTask = new Task("t4", 1);
        
        
        secondTask.dependsOn(firstTask);
        thirdTask.dependsOn(firstTask);
        fourthTask.dependsOn(secondTask);
        fourthTask.dependsOn(thirdTask);
        
        project.addTask(firstTask);
        project.addTask(secondTask);
        project.addTask(thirdTask);
        project.addTask(fourthTask);
                
        assertEquals(12, project.calculateTimeToDelivery());        
    }
    
    //US3
    @Test
    public void modifyTaskTimeToCompleteShouldChangeTheProjectTimeToDelivery() {
//         Project project = new Project("sample");
        Task firstTask = new Task("t1", 5);
        Task secondTask = new Task("t2", 2);
        Task thirdTask = new Task("t3", 6);
        Task fourthTask = new Task("t4", 1);
        
        
        secondTask.dependsOn(firstTask);
        thirdTask.dependsOn(firstTask);
        fourthTask.dependsOn(secondTask);
        fourthTask.dependsOn(thirdTask);
        
        project.addTask(firstTask);
        project.addTask(secondTask);
        project.addTask(thirdTask);
        project.addTask(fourthTask);
                
        assertEquals(12, project.calculateTimeToDelivery()); 
        
        secondTask.setTimeToComplete(10);
        
        assertEquals(16, project.calculateTimeToDelivery());
    }
    
    @Test
    public void projectCannotHaveDuplicateTasks() {
//         Project project = new Project("sample");
        Task firstTask = new Task("t1", 5);
        
        project.addTask(firstTask);
        project.addTask(new Task("t1", 5));
        
        assertEquals(1, project.countTasks());
    }
    
    @Test
    public void projectCanCalculateTheCriticalPath() {
//         Project project = new Project("sample");
        
        Task t1 = new Task("t1", 1);
        Task t2 = new Task("t2", 5);
        Task t3 = new Task("t3", 3);
        Task t4 = new Task("t4", 2);
        Task t5 = new Task("t5", 3);
        Task t6 = new Task("t6", 4);
        Task t7 = new Task("t7", 3);
        
        t2.dependsOn(t1);
        t3.dependsOn(t1);
        
        t4.dependsOn(t2);
        t5.dependsOn(t3);
        
        t6.dependsOn(t4);
        t6.dependsOn(t5);
        
        t7.dependsOn(t6);
        
        project.addTask(t1);
        project.addTask(t2);
        project.addTask(t3);
        project.addTask(t4);
        project.addTask(t5);
        project.addTask(t6);
        project.addTask(t7);
        
        assertEquals(15, project.calculateTimeToDelivery());
        
        //List<Task> criticalPath = project.calculateCriticalPath();
        //assertEquals(5, criticalPath.size());
    }
    
    //US6
    @Test
    public void criticalPathOfSingleTaskIsAListWithOnlyOneElement() {
        Task task = new Task("task1", 6);
        project.addTask(task);
        
        Set<Task> expected = new HashSet<Task>();
        expected.add(task);
        
        Set<Task> criticalPath = project.calculateCriticalPath();
        assertEquals(expected, criticalPath);
    }

    //Task2
    @Test
    public void criticalPathOfMultipleTaskIsAListOfElements() {

        Task t1 = new Task("t1", 1);
        Task t2 = new Task("t2", 5);
        Task t3 = new Task("t3", 3);
        Task t4 = new Task("t4", 2);
        Task t5 = new Task("t5", 3);
        Task t6 = new Task("t6", 4);
        Task t7 = new Task("t7", 3);

        t2.dependsOn(t1);
        t3.dependsOn(t1);

        t4.dependsOn(t2);
        t5.dependsOn(t3);

        t6.dependsOn(t4);
        t6.dependsOn(t5);

        t7.dependsOn(t6);

        project.addTask(t1);
        project.addTask(t2);
        project.addTask(t3);
        project.addTask(t4);
        project.addTask(t5);
        project.addTask(t6);
        project.addTask(t7);


        Set<Task> expected = new HashSet<Task>();
        expected.add(t1);
        expected.add(t2);
        expected.add(t5);
        expected.add(t6);
        expected.add(t7);

        Set<Task> criticalPath = project.calculateCriticalPath();
        assertEquals(expected, criticalPath);
    }
    
}
