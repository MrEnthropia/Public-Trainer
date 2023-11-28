import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public class FileBackedTusksManager extends InMemoryTaskManager implements TaskManager {
    InMemoryTaskManager manager=new InMemoryTaskManager();
    InMemoryHistoryManager historyManager=new InMemoryHistoryManager();



    public void save() throws IOException {
        String stringHead="id,type,name,status,description,epic";
        ArrayList<String> taskArray=new ArrayList<>();
        ArrayList<String> epicArray=new ArrayList<>();
        ArrayList<String> historyArray=new ArrayList<>();
        FileWriter fileWriter= new FileWriter("SaveFile.csv");
        for (Map.Entry<String, Task> entry : manager.taskMap.entrySet()){
            String value=toString(entry.getValue());
            taskArray.add(value);
        }
        for (Map.Entry<String, Epic> entry : manager.epicMap.entrySet()){
            String value=toString(entry.getValue());
            epicArray.add(value);
        }
        for (Map.Entry<String, Task> entry : historyManager.historyMap.entrySet()){
            String value=entry.getKey();
            historyArray.add(value);
        }
        String stringTusk=String.join("\n",taskArray);
        String stringEpic=String.join("\n",epicArray);
        String stringHistory=String.join(",",historyArray);
        String finalString=stringHead+"\n"+stringTusk+"\n"+stringEpic+"\n"+"\n"+stringHistory;
        fileWriter.write(finalString);
        fileWriter.close();
    }



    public String toString(Task task){
        return task.getId()+","+TYPE.ЗАДАЧА+","+task.getName()+","+task.getStatus()+","
                +task.getDescription()+",";
    }

    public String toString(Subtask subtask){
        return subtask.getId()+","+TYPE.ПОДЗАДАЧА+","+subtask.getName()+","+subtask.getStatus()+","
                +subtask.getDescription()+",";
    }

    public String toString(Epic epic){
        String epicString=epic.getId()+","+TYPE.ЭПИК+","+epic.getName()+","+epic.getStatus()+","
                +epic.getDescription()+",";

        ArrayList<String> subtaskStringArray= new ArrayList<>();

        for (Subtask subtask: epic.subtaskArray){
            String string=toString(subtask)+subtask.getParentId();
            subtaskStringArray.add(string);
        }
        String subtaskString= String.join("\n",subtaskStringArray);

        return epicString+"\n"+subtaskString;
    }

    public Task fromString() throws FileNotFoundException {

        FileReader fileReader=new FileReader("SaveFile.csv");

        return null;
    }



    @Override
    public void tuskIdGeneration() {

    }

    @Override
    public void genericId(String string) {

    }

    @Override
    public void printAllTusk(String string) {

    }

    @Override
    public void clearAllMap(String string) {

    }

    @Override
    public void removeTusk(String string) {

    }

    @Override
    public void searchId(String string) {

    }

    @Override
    public void updateTask(String string, Task task) {

    }

    @Override
    public void epicSubs(Epic epic) {

    }


}
 enum TYPE{
    ЗАДАЧА,ЭПИК,ПОДЗАДАЧА;
 }
