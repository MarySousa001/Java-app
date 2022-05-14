import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CLIMenu {

    CLIMenu(ArrayList<Todo> arrayList ) {
        String text;
        LocalDateTime dat;
        Category catchoose;
        Importance low;
        Status status;

        Scanner in = new Scanner(System.in);
        boolean newMenu = true;
        while(newMenu) {
            System.out.println("""
                     Please select an item between 1 and 5 and press enter\s
                    1. List todo
                    2. Add todo
                    3. Update todo
                    4. Delete todo
                    5. Quit""");
            int logIn;
            logIn = in.nextInt();
            switch (logIn) {
                case 1:

                    for(int i=0; i<=arrayList.size()-1; i++){
                        System.out.print((i+1) + ".- ");
                        System.out.println(arrayList.get(i));
                    }
                    break;

                case 2:
                    System.out.println("Enter a title for the todo");
                    text = in.next();
                    System.out.println("Enter a due date for the todo in the format YYYY-MM-DDTHH:MM");
                    dat = LocalDateTime.parse(in.next());
                    System.out.println("""
                            Select a category for the todo
                            Select an item between 1 and 6 and press enter 
                            1. Red
                            2. White
                            3. Blue
                            4. Purple
                            5. Yellow
                            6. Green""");
                    int colorIn;
                    colorIn = in.nextInt();
                    switch (colorIn) {
                        case 1 -> catchoose = Category.valueOf("Red");
                        case 2 -> catchoose = Category.valueOf("White");
                        case 3 -> catchoose = Category.valueOf("Blue");
                        case 4 -> catchoose = Category.valueOf("Purple");
                        case 5 -> catchoose = Category.valueOf("Yellow");
                        case 6 -> catchoose = Category.valueOf("Green");
                        default -> catchoose = null;
                    }
                    System.out.println("""
                            Select an importance for the todo\s
                            Select an item between 1 and 3 and press enter
                            1. LOW
                            2. NORMAL
                            3. HIGH""");
                    int imporIn;
                    imporIn= in.nextInt();
                    switch (imporIn) {
                        case 1 -> low = Importance.Low;
                        case 2 -> low = Importance.Normal;
                        case 3 -> low = Importance.High;
                        default -> low = null;
                    }
                    System.out.println("Select a completion status:\n 1.- Started \n 2.- Partial \n 3.- Completed");
                    int statusIn;
                    statusIn = in.nextInt();
                    switch (statusIn) {
                            case 1 -> status = Status.Started;
                            case 2 -> status = Status.Partial;
                            case 3 -> status = Status.Completed;
                            default -> status = null;
                        }
                        arrayList.add(new Todo(text, dat, catchoose,low,status));
                        break;
                case 3:
//                  for(Todo t: arrayList){
//                  System.out.println(t);
                    System.out.println("Which todo do you want to update?");
                    int updat;
                    updat = in.nextInt();
                        System.out.println("""
                                Select an item between 1 and 5 and press enter
                                1.- Title
                                2.- Due date
                                3.- Category
                                4.- Importance
                                5.- Completion\s""");
                        int updateItem;
                        updateItem = in.nextInt();
                    switch (updateItem) {
                        case 1 -> {
                            System.out.println("Enter a title for the todo");
                            String newTitle = in.next();
                            arrayList.get(updat - 1).setText(newTitle);
                        }
                        case 2 -> {
                            System.out.println("Enter a due date for the todo in the format YYYY-MM-DDTHH:MM");
                            LocalDateTime newDate = LocalDateTime.parse(in.next());
                            arrayList.get(updat - 1).setDue(newDate);
                        }
                        case 3 -> {
                            System.out.println("""
                                    Select a category for the todo
                                    Select an item between 1 and 6 and press enter 
                                    1. Red
                                    2. White
                                    3. Blue
                                    4. Purple
                                    5. Yellow
                                    6. Green""");
                            int colorNew;
                            colorNew = in.nextInt();
                            switch (colorNew) {
                                case 1 -> arrayList.get(updat - 1).setCat(Category.valueOf("Red"));
                                case 2 -> arrayList.get(updat - 1).setCat(Category.valueOf("White"));
                                case 3 -> arrayList.get(updat - 1).setCat(Category.valueOf("Blue"));
                                case 4 -> arrayList.get(updat - 1).setCat(Category.valueOf("Purple"));
                                case 5 -> arrayList.get(updat - 1).setCat(Category.valueOf("Yellow"));
                                case 6 -> arrayList.get(updat - 1).setCat(Category.valueOf("Green"));
                            }
                        }
                        case 4 -> {
                            System.out.println("""
                                    Select an importance for the todo\s
                                    Select an item between 1 and 3 and press enter
                                    1. LOW
                                    2. NORMAL
                                    3. HIGH""");
                            int imporNew;
                            imporNew = in.nextInt();
                            switch (imporNew) {
                                case 1 -> arrayList.get(updat - 1).setImportance(Importance.Low);
                                case 2 -> arrayList.get(updat - 1).setImportance(Importance.Normal);
                                case 3 -> arrayList.get(updat - 1).setImportance(Importance.High);
                            }
                        }
                        case 5 -> {
                            System.out.println("Select a completion status:\n 1.- Started \n 2.- Partial \n 3.- Completed");
                            int statusNew;
                            statusNew = in.nextInt();
                            switch (statusNew) {
                                case 1 -> arrayList.get(updat - 1).setCompletion(Status.Started);
                                case 2 -> arrayList.get(updat - 1).setCompletion(Status.Partial);
                                case 3 -> arrayList.get(updat - 1).setCompletion(Status.Completed);
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("Which number todo do you wish to delete");
                    int deletNum;
                    deletNum = in.nextInt();
                    arrayList.remove(deletNum -1);
                    break;
                case 5:
                    newMenu = false;
                    break;
            }
        }
        in.close();
    }
}

