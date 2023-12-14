import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class FileBackedTusksManager extends InMemoryTaskManager implements TaskManager {
//    InMemoryTaskManager manager=new InMemoryTaskManager();
//    InMemoryHistoryManager historyManager=new InMemoryHistoryManager();
//    File directory= new File("C://Projects//JavaMarathon2021//Trainer//saves//SaveFile.csv");
//
//
//
//
//    public void save() throws IOException {
//        String stringHead="id,type,name,status,description,epic";
//        ArrayList<String> taskArray=new ArrayList<>();
//        ArrayList<String> epicArray=new ArrayList<>();
//        ArrayList<String> historyArray=new ArrayList<>();
//        FileWriter fileWriter= new FileWriter(directory);
//        for (Map.Entry<String, Task> entry : manager.taskMap.entrySet()){
//            String value=toString(entry.getValue());
//            taskArray.add(value);
//        }
//        for (Map.Entry<String, Epic> entry : manager.epicMap.entrySet()){
//            String value=toString(entry.getValue());
//            epicArray.add(value);
//        }
//        for (Map.Entry<String, Task> entry : historyManager.historyMap.entrySet()){
//            String value=entry.getKey();
//            historyArray.add(value);
//        }
//        String stringTusk=String.join("\n",taskArray);
//        String stringEpic=String.join("\n",epicArray);
//        String stringHistory=String.join(",",historyArray);
//        String finalString=stringHead+"\n"+stringTusk+"\n"+stringEpic+"\n"+"\n"+stringHistory;
//        fileWriter.write(finalString);
//        fileWriter.close();
//    }
//
//
//
//    public String toString(Task task){
//        return task.getId()+","+TYPE.ЗАДАЧА+","+task.getName()+","+task.getStatus()+","
//                +task.getDescription()+",";
//    }
//
//    public String toString(Subtask subtask){
//        return subtask.getId()+","+TYPE.ПОДЗАДАЧА+","+subtask.getName()+","+subtask.getStatus()+","
//                +subtask.getDescription()+",";
//    }
//
//    public String toString(Epic epic){
//        String epicString=epic.getId()+","+TYPE.ЭПИК+","+epic.getName()+","+epic.getStatus()+","
//                +epic.getDescription()+",";
//
//        ArrayList<String> subtaskStringArray= new ArrayList<>();
//
//        for (Subtask subtask: epic.subtaskArray){
//            String string=toString(subtask)+subtask.getParentId();
//            subtaskStringArray.add(string);
//        }
//        String subtaskString= String.join("\n",subtaskStringArray);
//
//        return epicString+"\n"+subtaskString;
//    }
//
//    static public void loadFile(File file,InMemoryTaskManager manager,
//                                InMemoryHistoryManager historyManager) throws IOException {
//
//        FileReader fileReader=new FileReader(file);
//        BufferedReader csvReader= new BufferedReader(fileReader);
//        LinkedList<String> data=new LinkedList<>();
//        LinkedList<String> history=new LinkedList<>();
//
//        for (int i=0;csvReader.ready();i++){
//            String string=csvReader.readLine();
//            data.add(i,string);
//        }
//
//        for (String string: data){
//            String[] preTask=string.split(",");
//
//            try {
//                if (preTask[1].equals("ЗАДАЧА")){
//                Task task=new Task(preTask[0],preTask[2],STATUS.valueOf(preTask[3]),preTask[4]);
//                task.setId(preTask[0]);     //хз почему так
//                manager.addTask(task);
//            }else if (preTask[1].equals("ПОДЗАДАЧА")){
//                Subtask subtask=new Subtask(preTask[0],preTask[2],STATUS.valueOf(preTask[3]),preTask[4],preTask[5]);
//                subtask.setId(preTask[0]);  //хз почему так
//                manager.addSubtask(subtask);
//            }else if (preTask[1].equals("ЭПИК")){
//                Epic epic=new Epic(preTask[0],preTask[2],STATUS.valueOf(preTask[3]),preTask[4]);
//                epic.setId(preTask[0]);     //хз почему так
//                manager.addEpic(epic);
//            }
//                }
//            catch (ArrayIndexOutOfBoundsException ae){
//            }
//        }
//        String historyData=data.peekLast();
//        String[] preHistory=historyData.split(",");
//        for (String string: preHistory){
//            if (manager.taskMap.containsKey(string)) {
//                Task value = manager.taskMap.get(string);
//                historyManager.add(value);
//
//            } else if (manager.epicMap.containsKey(string)) {
//                Epic value = manager.epicMap.get(string);
//                historyManager.add(value);
//
//            } else if (manager.subTaskMap.containsKey(string)) {
//                Subtask value = manager.subTaskMap.get(string);
//                historyManager.add(value);
//            }
//        }
//    }
//
//
//    @Override
//    public void tuskIdGeneration() {
//        //технический метод
//    }
//
//    @Override
//    public void genericId(String string) {
//        //технический метод
//    }
//
//    @Override
//    public void printAllTusk(String string) {
//        manager.printAllTusk(string);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void clearAllMap(String string) {
//        manager.clearAllMap(string);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void removeTusk(String string) {
//        manager.removeTusk(string);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void searchId(String string) {
//        manager.searchId(string);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void updateTask(String string, Task task) {
//        manager.updateTask(string,task);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void epicSubs(Epic epic) {
//        manager.epicSubs(epic);
//        try {
//            save();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
 enum TYPE{
    ЗАДАЧА,ЭПИК,ПОДЗАДАЧА;
 }
