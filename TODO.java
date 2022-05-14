package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TODO implements Serializable {

    private static int count =0;
    private int id;
    private int min = 1;
    private int max = 1000;
    private String name;
    private LocalDateTime due;
    private Category cat;
    private Importance importance;
    private Status status;

    public TODO(String name, LocalDateTime due, Category cat, Importance importance, Status status) {
        this.name = name;
        this.due = due;
        this.cat = cat;
        this.importance = importance;
        this.status = status;
        count = (int)(Math.random() * (max - min + 1) + min);
        this.id = count;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String text) {this.name = name;}
    public Category getCat() {return cat;}
    public void setCat(Category cat) {this.cat = cat;}
    public Importance getImportance() {return importance;}
    public void setImportance(Importance importance) {this.importance = importance;}
    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
    public LocalDateTime getDue() {return due;}
    public void setDue(LocalDateTime due) {this.due = due;}

    @Override
    public String toString() {
        //return text + "  " + due + "  " + importance + "  " + completion;
        return name +", " +  due + "," + cat +", "+ importance +", "+ status ;
    }
}
