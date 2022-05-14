package gui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String name;
    private String date;
    private String category;
    private String status;
    private String priority;

    //to collect data from the form
    public FormEvent(Object source, String name, String date,
                     String category, String status, String priority) {
        super(source);
        this.name = name;
        this.date = date;
        this.category = category;
        this.status = status;
        this.priority = priority;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getPriority() {return priority;}
    public void setPriority(String priority) {this.priority = priority;}

}
