import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Класс История
class InMemoryHistoryManager implements HistoryManager {

    LinkedList<Task> historyList = new LinkedList<>();

    HashMap<String, Task> historyMap = new HashMap<>();



    @Override
    public  void add(Task task) {

        if (historyMap.containsKey(task.getId())) {
            String id = task.getId();
            remove(id);
        }
        historyList.addLast(task);
        historyMap.put(task.getId(), task);
        if (historyList.size() > 10) {
            historyList.pollFirst();

        }

    }

    @Override
    public void remove(String id) {
        Task task = historyMap.get(id);
        historyList.remove(task);
        historyMap.remove(id);

    }

    @Override
    public List<Task> getHistory() {

        return historyList;
    }
    public void printHistory(){
       
    }
}
