public class Main {




    public static void main(String[] args) {

        Manager manager= new Manager();

        //Инициализация спиков задач
        manager.allTask.put("Задачи",manager.taskMap);
        manager.allTask.put("Эпики",manager.epicMap);
        manager.allTask.put("Подзадачи",manager.subTaskMap);


        //Задача 1
        Task task1=new Task("Сделать домашку","Срок до 20.10");
        //Задача 2
        Task task2=new Task("Проверить окно","Дует");
        //Задача3
        Task task3=new Task("Сделать домашку","Срок ЗАВТРА!!!!");

        //Эпик 1
        Epic epic1 = new Epic("Приготовить ужин","Сегодня будет суп");

        //Подзадачи 1 Эпика
        Subtask subtask1 = new Subtask("Почистить картошку","4 шт");

        Subtask subtask2 = new Subtask("Нарезать мясо","1 кг");

        //Эпик 2
        Epic epic2 = new Epic("Собрать вещи","Через неделю отправка");

        //Подзадачи 2 Эпика
        Subtask subtask3 =new Subtask("Найти шестригранники","Были в шкафу");


        //Создание задач методами
        Task TASK1 = manager.createTask(task1);
        Task TASK2= manager.createTask(task2);
        Subtask SUBTASK1=manager.createTask(subtask1);
        Subtask SUBTASK2=manager.createTask(subtask2);
        Epic EPIC1= manager.createTask(epic1,SUBTASK1,SUBTASK2); //В параметры вносятся Эпики и их Подзадачи
        Subtask SUBTASK3=manager.createTask(subtask3);
        Epic EPIC2= manager.createTask(epic2,SUBTASK3);

        System.out.println(Manager.subtaskCount);




    }
}
