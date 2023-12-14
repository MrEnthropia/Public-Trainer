import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {


    @Test
    void add() {
        InMemoryHistoryManager historyManager= new InMemoryHistoryManager();
        //Task task= new Task("T1","Задача 1",STATUS.NEW,"Тестовая задача");
        //historyManager.add(task);
        final List<Task> history= historyManager.getHistory();
        assertNotNull(history,"История пуста");
        //assertNotNull(task.id,"id не присвоен");
        assertEquals(1,history.size(),"История пуста");
    }

    @Test
    void remove() {
        InMemoryHistoryManager historyManager= new InMemoryHistoryManager();
        //Task task= new Task("T1","Задача 1",STATUS.NEW,"Тестовая задача");
        //historyManager.add(task);
        //historyManager.remove(task.getId());
        final List<Task> history= historyManager.getHistory();
        //assertNotEquals(history,task,"Объект не удалён");
    }

    @Test
    void getHistory() {
        InMemoryHistoryManager historyManager= new InMemoryHistoryManager();
        final List<Task> history= historyManager.getHistory();
        assertEquals(history,historyManager.getHistory(),"Истории не совпадают");
    }

    @Test
    void printHistory() {
    }
}