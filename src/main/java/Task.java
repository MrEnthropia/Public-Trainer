import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import static java.time.Duration.*;

//Класс Задача
public class Task implements Comparable<Task> { //Класс Задача и его методы с конструктором
    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

    String name;
    String description;
    LocalDateTime startTime;
    Duration duration;
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
    public Duration setDuration(Duration duration){
        this.duration=duration;
        return duration;
    }
    public LocalDateTime setStartTime(LocalDateTime startTime){
        this.startTime=startTime;
        return startTime;
    }

    public LocalDateTime getStartTime(){
        return this.startTime;
    }

    public LocalDateTime getEndTime(){
        LocalDateTime endTime = this.startTime.plus(this.duration);
        return endTime;
    }



    public Task(String name, String description){
        this.name = name;
        this.description = description;
    }
    public Task(String name, String description,
                int startHour, int startMinute, int startDay, int startMonth, int startYear,
                int desHour, int desMinute) {

        this.name = name;
        this.description = description;

        this.startTime=LocalDateTime.of(startYear,startMonth,startDay,startHour,startMinute);
        Duration hours=ofHours(desHour);
        Duration minutes=ofMinutes(desMinute);
        this.duration= Duration.ofSeconds(hours.getSeconds()+minutes.getSeconds());

    }

    public Task(String name, String description, LocalDateTime startTime, Duration duration){

        this.name = name;
        this.description = description;
        this.startTime=startTime;
        this.duration=duration;
    }

    public Task(String id, String name,STATUS status, String description){

        this.name = name;
        this.description = description;
        this.status=status;

    }
    public Task(String id, String name,STATUS status, String description,
                 int startHour, int startMinute, int startDay, int startMonth, int startYear,
                int desHour, int desMinute){
        this.id=id;
        this.name=name;
        this.status=status;
        this.description=description;

        this.startTime=LocalDateTime.of(startYear,startMonth,startDay,startHour,startMinute);
        Duration hours=ofHours(desHour);
        Duration minutes=ofMinutes(desMinute);
        this.duration= Duration.ofSeconds(hours.getSeconds()+minutes.getSeconds());
    }
    public Task(String id, String name,STATUS status, String description, LocalDateTime startTime, Duration duration){
        this.id=id;
        this.name=name;
        this.status=status;
        this.description=description;

        this.startTime=startTime;

        this.duration= duration;
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

    @Override
    public int compareTo(Task o){return this.startTime.compareTo(o.startTime);}

}

//Класс Подзадача
   class Subtask extends Task  {  //Класс Подзадача и его методы с конструктором


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
    public Subtask(String name, String description,
                   int startHour, int startMinute, int startDay, int startMonth, int startYear,
                   int desHour, int desMinute) {
        super(name, description,
                startHour,startMinute,startDay,startMonth,startYear,
                desHour,desMinute);

    }
    public Subtask(String name, String description,
                   LocalDateTime startTime,
                   Duration duration) {
        super(name, description,
                startTime,
                duration);

    }

    public Subtask(String id, String name,STATUS status, String description, String parentId,
                   int startHour, int startMinute, int startDay, int startMonth, int startYear,
                   int desHour, int desMinute){
        super(id,name,status, description,
                startHour,startMinute,startDay,startMonth,startYear,
                desHour,desMinute);
        this.parentId=parentId;
    }

    public Subtask(String id, String name,STATUS status, String description, String parentId,
                   LocalDateTime startTime,
                   Duration duration){
        super(id,name,status, description,
                startTime,
                duration);
        this.parentId=parentId;
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
        LocalDateTime startTime;
        Duration duration;

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        LocalDateTime endTime;

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
            super(name,description);


        }
        public Epic(String id, String name,STATUS status, String description){
            super(id,name,status,description);
        }

        public String getId() {
            return id;
        }
    }
