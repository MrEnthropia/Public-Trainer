import java.util.*;

public class Manager {

    //Переменная для генерации id
    public static int taskCount;
    public static int epicCount;
    public static int subtaskCount;

    //Список всех типов задач
    HashMap<String, HashMap> allTask = new HashMap<>();

    //Список Задач и метод добавления в него
    HashMap<String, Task> taskMap = new HashMap<>();

    public void addTask(String id, Task task) {
        taskMap.put(id, task);
    }

    //Список Эпиков и метод добавления в него
    HashMap<String, Epic> epicMap = new HashMap<>();

    public void addEpic(String id, Epic epic) {
        epicMap.put(id, epic);
    }

    //Список Подзадач и метод добавления в него
    HashMap<String, Subtask> subTaskMap = new HashMap<>();
    public void addSubtask(String id, Subtask subtask){
        subTaskMap.put(id,subtask);
    }




    //Методы Менеджера

    public void tuskIdGeneration() {
        taskCount++;
    }//Метод генерации id


    //Методы создания Задач
    public Task createTask(Task t){ //Для задач
        tuskIdGeneration();
        String id="T"+ taskCount;
        Task task=new Task(t.name, t.description);
        task.setStatus("NEW");
        task.setId(id);
        addTask(id,task);
        return task;
    }

    public Subtask createTask(Subtask s){ //Для Подзадач
        tuskIdGeneration();
        genericId("Подзадача");
        String id="S"+taskCount;
        Subtask subtask=new Subtask(s.name, s.description);
        subtask.setStatus("NEW");
        subtask.setId(id);
        addSubtask(id,subtask);
        return subtask;

    }


    public Epic createTask(Epic e, Subtask...subtasks){ //Для Эпиков с подзадачами
        tuskIdGeneration();
        String id="E"+ taskCount;
        Epic epic=new Epic(e.name,e.description);
        epic.setStatus("NEW");
        epic.setId(id);
        addEpic(id,epic);
        //Цикл по созданию Позадач
        epic.subtaskArray.addAll(Arrays.asList(subtasks));
        for (Subtask subtask: epic.subtaskArray){
            subtask.setParentId(id);
        }


        return epic;

    }

    //Методы менеджера
    public void printAllTusk(String taskType){ //Протестировано
        HashMap map=allTask.get(taskType);
        ArrayList<Task> values=new ArrayList<>(map.values());
        System.out.println(values.toString());

    }
    public void clearAllMap(String taskType){ //Протестировано
        HashMap value= allTask.get(taskType);
        value.clear();
    }
    public void searchId(String id) { //Протестировано. Добавить id-count  для других типов
        if (taskMap.containsKey(id)) {
            Task value = taskMap.get(id);
            System.out.println(value);

        } else if (epicMap.containsKey(id)) {
            Task value = epicMap.get(id);
            System.out.println(value);
        } else if (subTaskMap.containsKey(id)) {
            Task value = subTaskMap.get(id);
            System.out.println(value);
        }else {
            System.out.println("Поиск не дал результатов");
        }
    }
    public void updateTask(String id, Task task){ //Протестировано
        if (taskMap.containsKey(id)){
            taskMap.put(id,task);
            task.setStatus("NEW");
        }else {
            System.out.println("Задачи под номером "+id+" не существует.");
        }

    }
    public void updateTask(String id, Epic epic){  //
        if (epicMap.containsKey(id)){
            Task e=epicMap.get(id);
            epic.setStatus(e.getStatus());
            epicMap.put(id,epic);
        }else {
            System.out.println("Эпика под номером "+id+" не существует.");
        }


    }
    public void updateTask(String id, Subtask subtask){ //
        if (subTaskMap.containsKey(id)){
            Task s=subTaskMap.get(id);
            subtask.setStatus(s.getStatus());
            subTaskMap.put(id,subtask);
        }else {
            System.out.println("Подзадачи под номером "+id+" не существует.");
        }
    }
    public void updateTask(String id, Task task,String status){ //Протестировано
        if (taskMap.containsKey(id)){
            taskMap.put(id,task);
            task.setStatus(status);
        }else {
            System.out.println("Задачи под номером "+id+" не существует.");
        }

    }

    public void updateTask(String id, Subtask subtask,String status){ //Протестировано
        if (subTaskMap.containsKey(id)){
            subtask.setStatus(status);
            subTaskMap.put(id,subtask);
            String epicId=subtask.getParentId();
            Epic epic=epicMap.get(epicId);
            for (Subtask subtask1: epic.subtaskArray){
                if (Objects.equals(subtask1.getId(), subtask.id)){
                    int index=epic.subtaskArray.indexOf(subtask);
                    epic.subtaskArray.set(index,subtask);

                }
            }

            checkEpicStatus(epic);
        }else {
            System.out.println("Подзадачи под номером "+id+" не существует.");
        }
    }

    public void epicSubs(Epic epic){
        String id= epic.getId();
        System.out.println(id);
        for (Map.Entry<String,Subtask>entry: subTaskMap.entrySet() ){
            String parentId=entry.getValue().getParentId();
            System.out.println(parentId);
            if (parentId.equals(id)){
                System.out.println(entry.getValue());
            }
        }

    }




    public void checkEpicStatus(Epic epic){
        int d = 0;
        for (Subtask subtask : epic.subtaskArray) {
            if (equals(subtask)) {
                d++;
            }
        }
            if (0<d&&d<epic.subtaskArray.size()){
                epic.setStatus("IN_PROGRESS");
            }else if (d==epic.subtaskArray.size()){
                epic.setStatus("DONE");
            }

    }


    public boolean equals(Task task){
        if (Objects.equals(task.status, "DONE")) {
            return true;
        }else if (Objects.equals(task.status, "NEW")){
            return false;
        }
        return false;
    }
    public void genericId(String string){
        if (string.equals("Задача")){
            taskCount++;
        }
        if (string.equals("Подзадача")){
            subtaskCount++;
        }
        if (string.equals("Эпик")){
            epicCount++;
        }

    }


}

