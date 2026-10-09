import org.example.TodoList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoListTest {

    @Test
    void addAndList() {
        TodoList t = new TodoList();
        t.add(" task1 ");

        assertEquals(1, t.size());
        assertEquals("task1", t.getAll().getFirst());
    }

    @Test
    void remove() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");

        assertTrue(t.remove(0));
        assertEquals(1, t.size());
        assertFalse(t.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        TodoList t = new TodoList();
        t.add(" ");

        assertEquals(0, t.size());
    }

    @Test
    void clearRemovesAllTasks() {
        TodoList t = new TodoList();
        t.add("a");
        t.add("b");

        t.clear();

        assertEquals(0, t.size());
        assertTrue(t.getAll().isEmpty());
    }

    @Test
    void markDone() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");

        assertFalse(t.isDone(0));
        assertTrue(t.markDone(0));
        assertTrue(t.isDone(0));
        assertFalse(t.isDone(1));
    }

    @Test
    void markDoneInvalidIndex() {
        TodoList t = new TodoList();
        t.add("task1");

        assertFalse(t.markDone(-1));
        assertFalse(t.markDone(1));
    }

    @Test
    void searchFindsMatchingTasks() {
        TodoList t = new TodoList();
        t.add("Buy milk");
        t.add("Do homework");
        t.add("Buy bread");

        List<String> result = t.search("Buy");

        assertEquals(2, result.size());
        assertEquals("Buy milk", result.get(0));
        assertEquals("Buy bread", result.get(1));
    }

    @Test
    void searchIsCaseInsensitive() {
        TodoList t = new TodoList();
        t.add("Buy Milk");

        assertEquals(1, t.search("milk").size());
        assertEquals(1, t.search("BUY").size());
    }

    @Test
    void searchReturnsEmptyListWhenNothingMatches() {
        TodoList t = new TodoList();
        t.add("Buy milk");

        assertTrue(t.search("homework").isEmpty());
    }

    @Test
    void removeKeepsCompletionStatusCorrect() {
        TodoList t = new TodoList();
        t.add("task1");
        t.add("task2");

        t.markDone(0);
        t.remove(0);

        assertEquals(1, t.size());
        assertFalse(t.isDone(0));
    }

    @Test
    void clearResetsCompletionStatus() {
        TodoList t = new TodoList();
        t.add("task1");
        t.markDone(0);

        t.clear();
        t.add("task2");

        assertFalse(t.isDone(0));
    }
}