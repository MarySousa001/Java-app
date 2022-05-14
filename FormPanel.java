package gui;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class FormPanel extends JPanel {
    private final JLabel nameLabel;
    private final JLabel dateLabel;
    private final JTextField nameField;
    private final JFormattedTextField dateField;
    private final JButton okBtn;
    private FormListener formListener;

    private final JList<String> categoryList;
    private final JComboBox<String> statusCombo;

    private final JRadioButton highRatio;
    private final JRadioButton lowRatio;
    private final JRadioButton normal;
    private final ButtonGroup priorityGroup;


    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 230;
        setPreferredSize(dim);


        nameLabel = new JLabel("Name:  ");
        dateLabel = new JLabel("Date:  ");
        nameField = new JTextField(12);
        dateField = new JFormattedTextField("YYYY-MM-DDTHH:MM");
        categoryList = new JList<>();
        statusCombo = new JComboBox<>();
        okBtn = new JButton("OK");

        //set up mnemonics
        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        highRatio = new JRadioButton("High");
        lowRatio = new JRadioButton("Low");
        normal = new JRadioButton("Normal");

        highRatio.setActionCommand("High");
        lowRatio.setActionCommand("Low");
        normal.setActionCommand("Normal");
        priorityGroup = new ButtonGroup();

        normal.setSelected(true);

        priorityGroup.add(highRatio);
        priorityGroup.add(lowRatio);
        priorityGroup.add(normal);


        //set up list box
        DefaultListModel<String> category = new DefaultListModel<>();
        for (String s : Arrays.asList("Red", "White", "Blue", "Purple", "Yellow", "Green")) {
            category.addElement(s);
        }
        categoryList.setModel(category);
        categoryList.setPreferredSize(new Dimension(100, 120));
        categoryList.setBorder(BorderFactory.createEtchedBorder());
        categoryList.setSelectedIndex(1);



        //set up Combo box
        DefaultComboBoxModel<String> statusModel = new DefaultComboBoxModel<>();
        for (String s : Arrays.asList("Pending", "Started", "Partial", "Completed")) {
            statusModel.addElement(s);
        }
        statusCombo.setModel(statusModel);
        statusCombo.setSelectedIndex(0);
        statusCombo.setEditable(true);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String date = dateField.getText();
                String catCat = String.valueOf(categoryList.getSelectedValue());
                String statusA = (String) statusCombo.getSelectedItem();
                String priority= priorityGroup.getSelection().getActionCommand();

                FormEvent ev = new FormEvent(this, name, date, catCat,
                        statusA, priority);

                if(formListener != null){
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder(new LineBorder(Color.cyan,3),"Add TODO");
        Border outerBorder = BorderFactory.createEmptyBorder(5,0,5,3);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponent();

    }
    public void layoutComponent() {

        setLayout(new GridBagLayout());
        GridBagConstraints gridB = new GridBagConstraints();

        // ----first row -------
        gridB.gridy = 0;

        gridB.weightx = 1;
        gridB.weighty = 0.1;

        gridB.gridx = 0;
        gridB.fill = GridBagConstraints.NONE;
        gridB.anchor = GridBagConstraints.LINE_END;
        gridB.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gridB);

        gridB.gridx = 1;
        gridB.gridy = 0;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.LINE_START;
        add(nameField, gridB);

        // ----- Second row-------
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 0.1;

        gridB.gridx = 0;
        gridB.insets = new Insets(0,0,0, 5);
        gridB.anchor = GridBagConstraints.LINE_END;
        add(dateLabel, gridB);

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.LINE_START;
        add(dateField, gridB);

        // ----- next row -----
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 0.2;

        gridB.gridx = 0;
        gridB.insets = new Insets(0,0,0, 5);
        gridB.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Category "), gridB);

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(categoryList, gridB);

        // ----- next row -----
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 0.2;

        gridB.gridx = 0;
        gridB.insets = new Insets(0,0,0, 5);
        gridB.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Priority "), gridB);

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(statusCombo, gridB);

        //next row----
        gridB.gridy++;
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 0.05;

        gridB.gridx = 0;
        gridB.insets = new Insets(0,0,0, 5);
        gridB.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Status"), gridB);

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(highRatio, gridB);

        //next row----
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 0.1;

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(lowRatio, gridB);

//next row----
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 1;

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(normal, gridB);
        // next row -----
        gridB.gridy++;

        gridB.weightx = 1;
        gridB.weighty = 1.0;

        gridB.gridx = 1;
        gridB.insets = new Insets(0, 0, 0, 0);
        gridB.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gridB);

    }

    public void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}
