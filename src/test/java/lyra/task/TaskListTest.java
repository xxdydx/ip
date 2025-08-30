package lyra.task;

import lyra.exception.LyraException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Test class for TaskList class.
 * Tests all public methods, edge cases, and exception handling.
 */
public class TaskListTest {
    
    private TaskList taskList;
    private Task task1;
    private Task task2;
    private Task task3;
    
    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        task1 = new Task("First task");
        task2 = new Task("Second task");
        task3 = new Task("Third task");
    }
    
    @Test
    void testDefaultConstructor() {
        assertEquals(0, taskList.getSize());
        assertTrue(taskList.isEmpty());
    }
    
    @Test
    void testConstructorWithExistingTasks() {
        ArrayList<Task> existingTasks = new ArrayList<>();
        existingTasks.add(task1);
        existingTasks.add(task2);
        
        TaskList newTaskList = new TaskList(existingTasks);
        assertEquals(2, newTaskList.getSize());
        assertFalse(newTaskList.isEmpty());
    }
    
    @Test
    void testAddTask() {
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
        assertFalse(taskList.isEmpty());
        
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize());
    }
    
    @Test
    void testAddTaskWithNull() {
        taskList.addTask(null);
        assertEquals(1, taskList.getSize());
        assertNull(taskList.getTasks().get(0));
    }
    
    @Test
    void testDeleteTask_ValidIndex() throws LyraException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        Task deletedTask = taskList.deleteTask(0);
        assertEquals(task1, deletedTask);
        assertEquals(1, taskList.getSize());
        assertEquals(task2, taskList.getTasks().get(0));
    }
    
    @Test
    void testDeleteTask_LastIndex() throws LyraException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        Task deletedTask = taskList.deleteTask(1);
        assertEquals(task2, deletedTask);
        assertEquals(1, taskList.getSize());
        assertEquals(task1, taskList.getTasks().get(0));
    }
    
    @Test
    void testDeleteTask_InvalidNegativeIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.deleteTask(-1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
        assertEquals(1, taskList.getSize()); // Task list should remain unchanged
    }
    
    @Test
    void testDeleteTask_InvalidHighIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.deleteTask(1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
        assertEquals(1, taskList.getSize()); // Task list should remain unchanged
    }
    
    @Test
    void testDeleteTask_EmptyList() {
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.deleteTask(0);
        });
        assertEquals("Invalid task number.", exception.getMessage());
        assertEquals(0, taskList.getSize());
    }
    
    @Test
    void testMarkTaskAsDone_ValidIndex() throws LyraException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTasks().get(0).isDone());
        assertFalse(taskList.getTasks().get(1).isDone());
    }
    
    @Test
    void testMarkTaskAsDone_InvalidNegativeIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.markTaskAsDone(-1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
        assertFalse(taskList.getTasks().get(0).isDone()); // Task should remain unchanged
    }
    
    @Test
    void testMarkTaskAsDone_InvalidHighIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.markTaskAsDone(1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
        assertFalse(taskList.getTasks().get(0).isDone()); // Task should remain unchanged
    }
    
    @Test
    void testMarkTaskAsNotDone_ValidIndex() throws LyraException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        // First mark as done
        taskList.markTaskAsDone(0);
        assertTrue(taskList.getTasks().get(0).isDone());
        
        // Then mark as not done
        taskList.markTaskAsNotDone(0);
        assertFalse(taskList.getTasks().get(0).isDone());
    }
    
    @Test
    void testMarkTaskAsNotDone_InvalidNegativeIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.markTaskAsNotDone(-1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }
    
    @Test
    void testMarkTaskAsNotDone_InvalidHighIndex() {
        taskList.addTask(task1);
        
        LyraException exception = assertThrows(LyraException.class, () -> {
            taskList.markTaskAsNotDone(1);
        });
        assertEquals("Invalid task number.", exception.getMessage());
    }
    
    @Test
    void testGetTasks() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }
    
    @Test
    void testGetTasks_EmptyList() {
        ArrayList<Task> tasks = taskList.getTasks();
        assertEquals(0, tasks.size());
        assertTrue(tasks.isEmpty());
    }
    
    @Test
    void testGetSize() {
        assertEquals(0, taskList.getSize());
        
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
        
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize());
    }
    
    @Test
    void testIsEmpty() {
        assertTrue(taskList.isEmpty());
        
        taskList.addTask(task1);
        assertFalse(taskList.isEmpty());
        
        try {
            taskList.deleteTask(0);
            assertTrue(taskList.isEmpty());
        } catch (LyraException e) {
            fail("Should not throw exception when deleting from non-empty list");
        }
    }
    
    @Test
    void testComplexOperations() throws LyraException {
        // Test a sequence of operations
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.getSize());
        
        // Add tasks
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        
        assertEquals(3, taskList.getSize());
        assertFalse(taskList.isEmpty());
        
        // Mark tasks as done
        taskList.markTaskAsDone(0);
        taskList.markTaskAsDone(2);
        
        assertTrue(taskList.getTasks().get(0).isDone());
        assertFalse(taskList.getTasks().get(1).isDone());
        assertTrue(taskList.getTasks().get(2).isDone());
        
        // Delete middle task
        Task deletedTask = taskList.deleteTask(1);
        assertEquals(task2, deletedTask);
        assertEquals(2, taskList.getSize());
        
        // Verify remaining tasks
        assertEquals(task1, taskList.getTasks().get(0));
        assertEquals(task3, taskList.getTasks().get(1));
    }
}
