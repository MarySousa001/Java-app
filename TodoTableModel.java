package gui;

import model.Category;
import model.Importance;
import model.Status;
import model.TODO;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TodoTableModel extends AbstractTableModel {

    private List<TODO> db;
    private final String[] colNames = {"ID","Name", "Date due", "Category", "Status", "Importance"};

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, Category.class, Status.class, Importance.class};

    @Override
    public String getColumnName(int column) {return colNames[column];}

    @Override
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }

    public TodoTableModel(){ }

    public void setData(List<TODO> db){this.db = db;}

    @Override
    public int getRowCount() {return db.size();}

    @Override
    public int getColumnCount() {return 6;}

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        TODO todoT = db.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> todoT.getId();
            case 1 -> todoT.getName();
            case 2 -> todoT.getDue();
            case 3 -> todoT.getCat();
            case 4 -> todoT.getStatus();
            case 5 -> todoT.getImportance();
            default -> null;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws ArrayIndexOutOfBoundsException {
        TODO todoT = db.get(rowIndex);
//        if(0 == columnIndex){
//            todoT.setId((Integer) aValue);
//        }
        if( 1 == columnIndex){
            todoT.setName((String) aValue);
        }
        else if(3 == columnIndex){
            todoT.setCat(Category.valueOf((String) aValue));
        }
        else if(4 == columnIndex){
            todoT.setStatus(Status.valueOf((String) aValue));
        }
        else if(5 == columnIndex){
            todoT.setImportance(Importance.valueOf((String) aValue));
        }
    }
}
