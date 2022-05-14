package model;

import java.io.*;
import java.util.*;

public class Database {
    private final List <TODO> toDo;

    public Database(){toDo = new LinkedList<>();}

    public void addTodo(TODO todo){toDo.add(todo);}
    public void removeTodo(int index){toDo.remove(index);}

    public List<TODO> getTodo(){return Collections.unmodifiableList(toDo);}

    public void saveTofile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        TODO [] todos = toDo.toArray(new TODO[toDo.size()]);
        objectOutputStream.writeObject(todos);
        objectOutputStream.close();
    }
    public void loadFromFile(File file) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            TODO [] todos = (TODO[]) objectInputStream.readObject();
            toDo.clear();
            toDo.addAll(Arrays.asList(todos));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInputStream.close();
    }
}
