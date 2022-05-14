package gui;

import javax.swing.*;
import java.awt.*;


public class Toolbar extends JPanel  {
    private final JButton info2;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());

        JSeparator a = new JSeparator();

        info2 = new JButton("Right click on the selected row to delete it");
        info2.setPreferredSize(new Dimension(700, 27));

        a.setOrientation(SwingConstants.HORIZONTAL);
        a.setPreferredSize(new Dimension(700, 5));

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(info2);
        add(a);

    }

}
