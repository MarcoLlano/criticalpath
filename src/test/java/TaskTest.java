
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by DEVOPS on 5/22/2017.
 */
public class TaskTest
{
    /**
     * US1
     */
    @Test
    public void createInstanceOfTask() {
        Task emptyTask = new Task();

        assertTrue(emptyTask instanceof Task);
        assertTrue(emptyTask instanceof Object);
    }

    // US1
    @Test
    public void readDescriptionProperty() {
        Task emptyTask = new Task();

        assertEquals("", emptyTask.getDescription());
    }

    // US1
    @Test
    public void writeDescriptionProperty() {
        Task task = new Task();
        task.setDescription("this is a task");

        assertEquals("this is a task", task.getDescription());
    }

    // US1
    @Test
    public void readOwnerProperty() {
        Task task = new Task();

        assertEquals("", task.getOwner());
    }

    // US1
    @Test
    public void writeOwnerProperty() {
        Task task = new Task();

        task.setOwner("raul");
        assertEquals("raul", task.getOwner());
    }

    //US1
    @Test
    public void createTaskWithNameAndTimeToComplete() {
        Task task = new Task("t1", 4);

        assertEquals("t1", task.getName());
        assertEquals(4, task.getTimeToComplete());
    }

    @Test
    public void taskAreDifferentIfTheyHaveADifferentName() {
        Task task = new Task("t1", 5);
        Task other = new Task("t2", 5);

        assertFalse(task.equals(other));
    }

    @Test
    public void taskAreDifferentIfTheyHaveADifferentTimeToComplete() {
        Task task = new Task("t1", 5);
        Task other = new Task("t2", 5);

        assertFalse(task.equals(other));
    }
}
