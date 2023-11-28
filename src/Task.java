import java.util.ArrayList;

//Класс Задача
public class Task { //Класс Задача и его методы с конструктором
    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

    String name;
    String description;

    String id;
    STATUS status;

    public String setId(String id){
        this.id=id;
        return id;
    }
    public STATUS setStatus(STATUS status){
        this.status= status;
        return status;
    }
    @Override
    public String toString(){
        return "Задача: "+name+". "+
                "Описание: "+description+". "+
                "Статус: "+status;
    }




    public Task(String name, String description) {

        this.name = name;
        this.description = description;



    }


    public STATUS getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public InMemoryTaskManager getInMemoryTaskManager() {
        return inMemoryTaskManager;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

//Класс Подзадача
   class Subtask extends Task {  //Класс Подзадача и его методы с конструктором


    String id;
    String parentId;



    public String setId(String id){
        this.id=id;
        return id;
    }
    public String setParentId(String id){
        this.parentId=id;
        return id;
    }
    public Subtask(String name, String description) {
        super(name, description);


    }

    public String getParentId() {
        return parentId;
    }

    public String getId() {
        return id;
    }

    @Override
    public STATUS getStatus() {
        return status;
    }

    @Override
    public STATUS setStatus(STATUS status) {
        this.status = status;
        return status;
    }
}
    //Класс Эпик
    class Epic extends Task { //Класс Эпика и его методы с конструктором


        String id;

        @Override
        public STATUS getStatus() {
            return status;
        }

        @Override
        public STATUS setStatus(STATUS status) {
            this.status = status;
            return status;
        }


        public ArrayList<Subtask> subtaskArray=new ArrayList<>(); //Список Подзадач


        public String setId(String id){
            this.id=id;
            return id;
        }




        public Epic(String name, String description) {
            super(name, description);


        }

        public String getId() {
            return id;
        }
    }
