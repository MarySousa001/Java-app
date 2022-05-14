package gui;

import jdk.jfr.Category;
import model.Category;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CategoryRenderer extends JLabel implements TableCellRenderer {

    public CategoryRenderer(){
        super.setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    //to make the field background color

        Category category = (Category) value;
        if(category == Category.Blue){
            super.setBackground(Color.BLUE);
        } else if (category == Category.Yellow) {
            super.setBackground(Color.YELLOW);
        } else if (category == Category.Red) {
            super.setBackground(Color.RED);
        } else if (category == Category.White) {
            super.setBackground(Color.WHITE);
        } else if (category == Category.Green) {
            super.setBackground(Color.GREEN);
        } else if (category == Category.Purple) {
            super.setBackground(Color.MAGENTA);
        }
        return this;
    }
}
