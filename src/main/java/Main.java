import java.io.IOException;


public class Main {




    public static void main(String[] args) throws IOException {

        InMemoryTaskManager manager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager=new InMemoryHistoryManager();
        manager.historyManager=historyManager;
        FileBackedTusksManager file=new FileBackedTusksManager();
        //file.manager=manager;
        file.historyManager=historyManager;
        HttpTaskManager httpTaskManager=new HttpTaskManager();



        //Инициализация спиков задач
        manager.allTask.put("Задачи", manager.taskMap);
        manager.allTask.put("Эпики", manager.epicMap);
        manager.allTask.put("Подзадачи", manager.subTaskMap);


        //Задача 1
        Task task1=new Task("Сделать домашку","Срок до 20.12",
                8,30,15,12,2023,
                6,30);
        //Задача 2
        Task task2=new Task("Проверить окно","Дует",
                13,30,7,12,2023,
                2,0);
        //Задача3
        Task task3=new Task("Сделать домашку","Срок ЗАВТРА!!!!",
                8,30,7,12,2023,
                1,30);

        //Эпик 1
        Epic epic1 = new Epic("Приготовить ужин","Сегодня будет суп");

        //Подзадачи 1 Эпика
        Subtask subtask1 = new Subtask("Почистить картошку","4 шт",
                11, 0,7,12,2023,
                1,25);

        Subtask subtask2 = new Subtask("Нарезать мясо","1 кг",
                12,40,7,12,2023,
                0,40);

        //Эпик 2
        Epic epic2 = new Epic("Собрать вещи","Через неделю отправка");

        //Подзадачи 2 Эпика
        Subtask subtask3 =new Subtask("Найти шестригранники","Были в шкафу",
                14,3,10,12,2023,
                3,0);


        //Создание задач методами
        Task TASK1 = manager.createTask(task1);
        Task TASK2= manager.createTask(task2);
        Subtask SUBTASK1= manager.createTask(subtask1);
        Subtask SUBTASK2= manager.createTask(subtask2);
        Epic EPIC1= manager.createTask(epic1,SUBTASK1,SUBTASK2); //В параметры вносятся Эпики и их Подзадачи
        Subtask SUBTASK3= manager.createTask(subtask3);
        Epic EPIC2= manager.createTask(epic2,SUBTASK3);




        file.updateTask("S2",SUBTASK2,STATUS.DONE);
        manager.updateTask("T1",task3);
//        file.searchId("T1");
//        file.searchId("T1");
//        file.searchId("T2");
//        file.searchId("E2");
//        file.searchId("E1");
//       FileBackedTusksManager.loadFile(file.directory,manager,historyManager);

//        System.out.println(manager.prioritySet);
//        System.out.println(TASK1);
//        System.out.println(manager.getPrioritizedTask());
        httpTaskManager.httpServer.start();
        System.out.println("Сервер запущен");




    }


}
