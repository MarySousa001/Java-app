package gui;
import model.Category;
import model.TODO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private TodoTableModel tableModel;
    private JPopupMenu popupMenu;
    private TodoTableListener todoTableListener;

    public TablePanel(){
        tableModel = new TodoTableModel();
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();

        table.setRowHeight(30);
        table.setDefaultRenderer(Category.class, new CategoryRenderer());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(3).setMaxWidth(70);
        columnModel.getColumn(4).setMaxWidth(70);
        columnModel.getColumn(5).setMaxWidth(70);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);

        int gap1 = 4;
        table.setIntercellSpacing(new Dimension(gap1, gap1));

        JMenuItem removeItem = new JMenuItem("Delete Row");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row,row);
                if(e.getButton() == MouseEvent.BUTTON3){
                    popupMenu.show(table, e.getX(),e.getY());
                }
            }
        });

        removeItem.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(todoTableListener != null){
                todoTableListener.rowDeleted(row);
                tableModel.fireTableRowsDeleted(row, row);
            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    public void setData(List<TODO> db){tableModel.setData(db);}
    public void refresh(){
        tableModel.fireTableDataChanged();
    }
    public void setTodoTableListener(TodoTableListener listener){
        this.todoTableListener = listener;
    }
}
