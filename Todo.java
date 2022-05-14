import javax.annotation.processing.Completion;
import java.time.LocalDateTime;

public class Todo {
    private String text;
    private LocalDateTime due;
    private Category cat;
    private Importance importance;
    private Status completion;

    public Todo(String text, LocalDateTime due, Category cat, Importance importance, Status completion) {

        this.text = text;
        this.due = due;
        this.cat = cat;
        this.importance = importance;
        this.completion = completion;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public void setCompletion(Status completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        return cat.getColor() +"Todo{" +
                "Text = " + text + '\'' +
                ", Due = " + due +
                ", Importance = " + importance +
                ", Completion = " + completion +
                "}\033[0m";
    }
}
