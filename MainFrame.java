package gui;
import Controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class  MainFrame extends JFrame {

    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final JFileChooser fileChooser;
    private final JButton addbtn;
    private final JButton closeaddbtn;
    private final JButton savebtn;
    private final JButton impbtn;
    private final JButton exitbtn;
    private final Controller controller;
    private final TablePanel tablePanel;

    Border bl = BorderFactory.createEmptyBorder(10,10,10,10);


    public MainFrame(){
        super("TODO List");


        setLayout(new BorderLayout());
        JSeparator s = new JSeparator();
        toolbar =  new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        controller = new Controller();

        tablePanel.setData(controller.getTodo());
        tablePanel.setBorder(bl);
        tablePanel.setTodoTableListener(controller::removeTodo);

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new todoFileFilter());

        toolbar.setBackground(Color.cyan);
        toolbar.setPreferredSize(new Dimension(700,80));
        setJMenuBar(createMenuBar());

        s.setOrientation(SwingConstants.VERTICAL);
        s.setPreferredSize(new Dimension(110, 30));

        exitbtn = new JButton("Exit");
        exitbtn.setPreferredSize(new Dimension(150, 30));

        savebtn = new JButton("Save");
        savebtn.setPreferredSize(new Dimension(100, 30));

        impbtn = new JButton("Import");
        impbtn.setPreferredSize(new Dimension(100, 30));


        addbtn = new JButton("Add a TODO");
        addbtn.setPreferredSize(new Dimension(120, 30));

        closeaddbtn = new JButton("Close");
        closeaddbtn.setPreferredSize(new Dimension(70, 30));

        toolbar.add(savebtn);
        toolbar.add(impbtn);
        toolbar. add(s);
        toolbar.add(addbtn);
        toolbar.add(closeaddbtn);
        toolbar.add(exitbtn);
        formPanel.setVisible(false);

        savebtn.addActionListener(e -> {
            if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                try {
                    controller.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failure saving in file","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        impbtn.addActionListener(e -> {
            if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                try {
                    controller.loadToFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failure loading file","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        exitbtn.addActionListener(e -> {
            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Please cancel if you did not save the changes or press ok to exit ",
                    "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

            if(action == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });

//        addbtn.addActionListener(e -> formPanel.setVisible(true));
        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formPanel.setVisible(true);
                JOptionPane.showMessageDialog(MainFrame.this,
                        "All fields are require specially the date pattern YYYY-MM-DDTHH:MM",
                        "Alert", JOptionPane.WARNING_MESSAGE);
            }
        });

        closeaddbtn.addActionListener(e -> formPanel.setVisible(false));

        formPanel.setFormListener(e -> {
            controller.addTodo(e);
            tablePanel.refresh();
        });

        add(formPanel, BorderLayout.EAST);
        add(toolbar, BorderLayout.SOUTH);
        add(tablePanel, BorderLayout.CENTER);


        setSize(730 , 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.cyan);
        menuBar.setPreferredSize(new Dimension(700,40));

        JMenu fileMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem importDataItem = new JMenuItem("Import File");
        JMenuItem  exitItem = new JMenuItem("Exit");

        fileMenu.add(save);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                ActionEvent.CTRL_MASK));

        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
                InputEvent.CTRL_MASK));

        importDataItem.addActionListener(e -> {
            if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                try {
                    controller.loadToFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failure loading file","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        save.addActionListener(e -> {
            if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                try {
                    controller.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failure saving in file","Error",JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        exitItem.addActionListener(e -> {
            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Please cancel if you did not save the changes ",
                    "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

            if(action == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
        return menuBar;
    }
}