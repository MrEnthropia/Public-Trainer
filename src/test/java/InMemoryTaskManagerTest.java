import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager manager= new InMemoryTaskManager();
    Task task0=new Task("T0","Task0",STATUS.NEW,"Test task");
    Task task1= new Task("Task","Test task",
            8,30,15,12,2023,
            6,30);
    Subtask subtask1=new Subtask("Sub","Test sub",
            8,30,15,12,2023,
            6,30);
    Subtask subtask2=new Subtask("Subaba","Sub test",
            8,30,15,12,2023,
            6,30);
    Epic epic1=new Epic("Epicaey","Test Epic");


    @Test
    void addTask() {
        manager.addTask("T1",task1);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task1,taskMap.get("T1"),"Задача не совпадает");

    }

    @Test
    void testAddTask() {
        manager.addTask(task0);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task0,taskMap.get("T0"),"Задача не совпадает");
    }

    @Test
    void addEpic() {
        manager.addTask("T1",task1);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task1,taskMap.get("T1"),"Задача не совпадает");
    }

    @Test
    void testAddEpic() {
        manager.addTask(task0);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task0,taskMap.get("T0"),"Задача не совпадает");
    }

    @Test
    void addSubtask() {
        manager.addTask("T1",task1);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task1,taskMap.get("T1"),"Задача не совпадает");
    }

    @Test
    void testAddSubtask() {
        manager.addTask(task0);
        final Map<String,Task> taskMap=manager.taskMap;
        assertNotNull(taskMap,"Задача не добавилась");
        assertEquals(task0,taskMap.get("T0"),"Задача не совпадает");
    }

    @Test
    void tuskIdGeneration() {

    }

    @Test
    void genericId() {
    }

    @Test
    void createTask() {
        Task task2=manager.createTask(task1);
        final String id=task2.getId();
        final String name=task2.getName();
        final STATUS status= task2.getStatus();
        final String description= task2.getDescription();
        final Map<String,Task> taskMap=manager.taskMap;

        assertNotNull(id,"id не назначен");
        assertEquals(id,task2.getId(),"id не совпадают");
        assertNotNull(name,"имя не назначено");
        assertEquals(name,task2.getName(),"имя не совпадает");
        assertNotNull(status,"статус не назначен");
        assertEquals(STATUS.NEW,task2.getStatus(),"статус не новый");
        assertNotNull(description,"описание не назначено");
        assertEquals(description,task2.getDescription(),"описание не совпадает");

        assertNotNull(taskMap,"Таблица пуста");
        assertNotNull(taskMap.get(id),"Задача не найдена");
        assertEquals(task2,taskMap.get(id),"Задача не совпадает");

    }

    @Test
    void testCreateTask() {
        Subtask subtask=manager.createTask(subtask1);
        final String id=subtask.getId();
        final String name=subtask.getName();
        final STATUS status= subtask.getStatus();
        final String description= subtask.getDescription();
        final Map<String,Subtask> subtaskMap=manager.subTaskMap;

        assertNotNull(id,"id не назначен");
        assertEquals(id,subtask.getId(),"id не совпадают");
        assertNotNull(name,"имя не назначено");
        assertEquals(name,subtask.getName(),"имя не совпадает");
        assertNotNull(status,"статус не назначен");
        assertEquals(STATUS.NEW,subtask.getStatus(),"статус не новый");
        assertNotNull(description,"описание не назначено");
        assertEquals(description,subtask.getDescription(),"описание не совпадает");

        assertNotNull(subtaskMap,"Таблица пуста");
        assertNotNull(subtaskMap.get(id),"Подзадача не найдена");
        assertEquals(subtask,subtaskMap.get(id),"Подзадача не совпадает");
    }

    @Test
    void testCreateTask1() {
        Subtask SUBTASK1=manager.createTask(subtask1);
        Subtask SUBTASK2=manager.createTask(subtask2);
        Epic epic=manager.createTask(epic1,SUBTASK1,SUBTASK2);
        final String id=epic.getId();
        final String name=epic.getName();
        final STATUS status= epic.getStatus();
        final String description= epic.getDescription();

        final Map<String,Epic> epicMap=manager.epicMap;

        final String parentId=epic.getId();
        final List<Subtask> epicArray=epic.subtaskArray;

        assertNotNull(id,"id не назначен");
        assertEquals(id,epic.getId(),"id не совпадают");
        assertNotNull(name,"имя не назначено");
        assertEquals(name,epic.getName(),"имя не совпадает");
        assertNotNull(status,"статус не назначен");
        assertEquals(STATUS.NEW,epic.getStatus(),"статус не новый");
        assertNotNull(description,"описание не назначено");
        assertEquals(description,epic.getDescription(),"описание не совпадает");


        for (Subtask subtask: epicArray){
            assertNotNull(subtask.getId(),"У подзадачи "+subtask.name+" отсутствует id");
            assertEquals(STATUS.NEW,subtask.getStatus(),"У подзадачи "+subtask.name+" статус не новый");
            assertEquals(parentId,subtask.getParentId(),"У подзадачи"+subtask.name+"отсутствует родительский id");
        }





    }

    @Test
    void printAllTusk() {
        //The test is not required
    }

    @Test
    void clearAllMap() {
        manager.addTask(task0);
        manager.addSubtask(subtask1);
        manager.addEpic(epic1);

        manager.clearAllMap("Задачи");
        manager.clearAllMap("Подзадачи");
        manager.clearAllMap("Эпики");

        boolean taskEmpty=manager.taskMap.isEmpty();
        boolean subtaskEmpty=manager.subTaskMap.isEmpty();
        boolean epicEmpty=manager.epicMap.isEmpty();

        assertTrue(taskEmpty,"Список задач не пуст");
        assertTrue(subtaskEmpty,"Список подзадач не пуст");
        assertTrue(epicEmpty,"Список эпиков не пуст");
    }

    @Test
    void removeTusk() {
        manager.addTask(task0);

        manager.removeTusk("T0");
        


    }

    @Test
    void searchId() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void testUpdateTask() {
    }

    @Test
    void testUpdateTask1() {
    }

    @Test
    void testUpdateTask2() {
    }

    @Test
    void testUpdateTask3() {
    }

    @Test
    void epicSubs() {
    }

    @Test
    void checkEpicStatus() {
    }

    @Test
    void testEquals() {
    }
}