package Controller;
import gui.FormEvent;
import model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Controller {
    Database db = new Database(); //the data will be save in this database
    public List<TODO> getTodo(){
        return db.getTodo();
    }// to collect all data save in database
    public void removeTodo(int index){
        db.removeTodo(index);
    }//to delete

    public void addTodo(FormEvent ev){
        String name = ev.getName();
        String due = ev.getDate();
        String catId = ev.getCategory();
        String impor = ev.getPriority();
        String stat = ev.getStatus();

        LocalDateTime dateTime = LocalDateTime.parse(due);

        Category category = switch (catId) {
            case "Red" -> Category.Red;
            case "White" -> Category.White;
            case "Blue" -> Category.Blue;
            case "Purple" -> Category.Purple;
            case "Yellow" -> Category.Yellow;
            case "Green" -> Category.Green;
            default -> null;
        };
        Importance importance = switch (impor) {
            case "Low" -> Importance.Low;
            case "High" -> Importance.High;
            case "Normal" -> Importance.Normal;
            default -> throw new IllegalStateException("Unexpected value: " + impor);
        };
        Status status = switch (stat) {
            case "Pending" -> Status.Pending;
            case "Started" -> Status.Started;
            case "Partial" -> Status.Partial;
            case "Completed" -> Status.Completed;
            default -> null;
        };
        // add a new task todo from a form
        TODO todo = new TODO(name, dateTime, category, importance, status);
        db.addTodo(todo);

    }
    //saving data into a file
    public void saveToFile(File file) throws IOException {
        db.saveTofile(file);

    }
    //loading the data from file
    public void loadToFile(File file) throws IOException {
        db.loadFromFile(file);
    }}
