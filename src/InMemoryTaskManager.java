import java.util.*;

//Интерфейс работы с историей
interface HistoryManager{
    void add(Task task);
    void remove(String id);
    List<Task> getHistory();
}

//Интерфейс для класса Менеджера
interface TaskManager{

    //Методы взаимодействия и отображения
    void tuskIdGeneration();
    void  genericId(String string);
    void printAllTusk(String string);
    void clearAllMap(String string);
    void removeTusk(String string);
    void searchId(String string);
    void updateTask(String string, Task task);
    void epicSubs(Epic epic);

}


//Утилитарный класс Менеджер
//final class Manager{
//    InMemoryHistoryManager historyManager= new InMemoryHistoryManager();
//
//    InMemoryHistoryManager getDefaultHistory(){
//        return historyManager;
//    }
//
//    public Manager() {
//    }
//
//    private void getDefault(){}
//}

//Класс Менеджер
public class InMemoryTaskManager implements TaskManager {



    InMemoryHistoryManager historyManager= new InMemoryHistoryManager();


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
    @Override
    public void tuskIdGeneration() {
        taskCount++;
    }//Метод генерации id


    @Override
    public void genericId(String string) {
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


    //Методы создания Задач

    public Task createTask(Task t){ //Для задач
        genericId("Задача");
        String id="T"+ taskCount;
        Task task=new Task(t.name, t.description);
        task.setStatus(STATUS.NEW);
        task.setId(id);
        addTask(id,task);
        return task;
    }

    public Subtask createTask(Subtask s){ //Для Подзадач
        genericId("Подзадача");
        String id="S"+subtaskCount;
        Subtask subtask=new Subtask(s.name, s.description);
        subtask.setStatus(STATUS.NEW);
        subtask.setId(id);
        addSubtask(id,subtask);
        return subtask;

    }


    public Epic createTask(Epic e, Subtask...subtasks){ //Для Эпиков с подзадачами
        genericId("Эпик");
        String id="E"+ epicCount;
        Epic epic=new Epic(e.name,e.description);
        epic.setStatus(STATUS.NEW);
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
    @Override
    public void printAllTusk(String taskType){ //Протестировано
        HashMap map=allTask.get(taskType);
        ArrayList<Task> values=new ArrayList<>(map.values());
        System.out.println(values.toString());

    }
    @Override
    public void clearAllMap(String taskType){ //Протестировано
        if (taskType.equals("Задачи")){
            ArrayList<String> list=new ArrayList<>();
            for (Map.Entry<String, Task> entry : taskMap.entrySet()) {
                String id=entry.getKey();
                list.add(id);
            }
            for (String string: list){
                removeTusk(string);
            }
        }
        if (taskType.equals("Подзадачи")){
            ArrayList<String> list=new ArrayList<>();
            for (Map.Entry<String, Subtask> entry : subTaskMap.entrySet()) {
                String id=entry.getKey();
                list.add(id);
            }
            for (String string: list){
                removeTusk(string);
            }
        }
        if (taskType.equals("Эпики")){
            ArrayList<String> list=new ArrayList<>();
            for (Map.Entry<String, Epic> entry : epicMap.entrySet()) {
                String id=entry.getKey();
                list.add(id);
            }
            for (String string: list){
                removeTusk(string);
            }
        }

    }

    @Override
    public void removeTusk(String id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            historyManager.remove(id);

        } else if (epicMap.containsKey(id)) {//доработать удаление подзадач
            Epic epic=epicMap.get(id);
            for (Subtask subtask : epic.subtaskArray) {
               if (subTaskMap.containsKey(subtask.getId())){
                   String ID =subtask.getId();
                   subTaskMap.remove(ID);
                   historyManager.remove(ID);
               }
            }
            epic.subtaskArray.clear();
            epicMap.remove(id);
            historyManager.remove(id);


        } else if (subTaskMap.containsKey(id)) {
            Subtask subtask=subTaskMap.get(id);
            Epic epic=epicMap.get(subtask.getParentId());
            epic.subtaskArray.remove(subtask);
            subTaskMap.remove(id);
            historyManager.remove(id);


        }else {
            System.out.println("Задачи с таким номером не найдена");
        }
    }

    @Override
    public void searchId(String id) { //Протестировано. Добавить id-count  для других типов
        if (taskMap.containsKey(id)) {
            Task value = taskMap.get(id);
            historyManager.add(value);
            System.out.println(value);

        } else if (epicMap.containsKey(id)) {
            Epic value = epicMap.get(id);
            historyManager.add(value);
            System.out.println(value);

        } else if (subTaskMap.containsKey(id)) {
            Subtask value = subTaskMap.get(id);
            historyManager.add(value);
            System.out.println(value);
        }else {
            System.out.println("Поиск не дал результатов");
        }
    }
    @Override
    public void updateTask(String id, Task task){ //Протестировано
        if (taskMap.containsKey(id)){
            taskMap.put(id,task);
            task.setStatus(STATUS.NEW);
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
    public void updateTask(String id, Task task,STATUS status){ //Протестировано
        if (taskMap.containsKey(id)){
            taskMap.put(id,task);
            task.setStatus(status);
        }else {
            System.out.println("Задачи под номером "+id+" не существует.");
        }

    }

    public void updateTask(String id, Subtask subtask,STATUS status){ //Протестировано
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

    @Override
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
                epic.setStatus(STATUS.IN_PROGRESS);
            }else if (d==epic.subtaskArray.size()){
                epic.setStatus(STATUS.DONE);
            }

    }



    public boolean equals(Task task){
        if (Objects.equals(task.status, STATUS.DONE)) {
            return true;
        }else if (Objects.equals(task.status, STATUS.NEW)){
            return false;
        }
        return false;
    }


}
enum STATUS{
    NEW,
    IN_PROGRESS,
    DONE
}


