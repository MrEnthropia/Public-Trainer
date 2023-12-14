import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpTaskManager {

    HttpServer httpServer;

    {
        try {
            httpServer = HttpServer.create(new InetSocketAddress(8080),0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        httpServer.createContext("/priorityTask", new TaskHandler());

        System.out.println("Сервер запущен");
    }




}
class TaskHandler implements HttpHandler{
    InMemoryTaskManager manager=new InMemoryTaskManager();
    GsonBuilder gsonBuilder= new GsonBuilder();



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.serializeNulls();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class,new LocalDateFormater());
        Gson gson=gsonBuilder.create();
        String taskMessage ="{\"name\":\"Test1\",\"id\":\"12345\"}";
        System.out.println("Соединение с сервером установлено. Пожалуйста подождите.");
        exchange.sendResponseHeaders(200,0);
        TestTask test1=gson.fromJson(taskMessage,TestTask.class);
        TestTask test2=new TestTask("Test2",3214,
                30,12,4,6,2024);
        String message ="Welcome to my page";
        String message2=gson.toJson(test2);
        System.out.println(test1.getName());
        System.out.println(test1.getId());

        try(OutputStream os= exchange.getResponseBody()) {
            os.write(message.getBytes(StandardCharsets.UTF_8));
            os.write(taskMessage.getBytes());
            os.write(message2.getBytes());


        }
    }
}
class TestTask{
    String name;
    int id;
    LocalDateTime testTime;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    String n=null;
    TestTask(String name, int id,
             int startHour, int startMinute, int startDay, int startMonth, int startYear){
        this.name=name;
        this.id= id;
        this.testTime=LocalDateTime.of(startYear,startMonth,startDay,startHour,startMinute);
    }

    @Override
    public String toString() {
        return "TestTask{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
class LocalDateFormater extends TypeAdapter<LocalDateTime>{

    private final DateTimeFormatter formatterWriter= DateTimeFormatter.ofPattern("dd--MM--yyyy--HH--mm");

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.format(formatterWriter));
    }

    @Override
    public LocalDateTime read(final JsonReader jsonReader) throws IOException {
        return LocalDateTime.parse(jsonReader.nextString(), formatterWriter);
    }
}