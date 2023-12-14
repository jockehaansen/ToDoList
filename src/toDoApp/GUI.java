package toDoApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JFrame addTaskFrame = new JFrame();
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel menuPanel;
    private JPanel addTaskMainPanel = new JPanel(new GridLayout(4,1));
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton markAsDoneButton;
    private JButton doneTaskButton;
    private JButton unDoneTaskButton;
    private JTextArea taskDescription = new JTextArea(20, 40);
    private JTextField titleField = new JTextField();
    private JTextArea descriptionArea = new JTextArea(20, 40);
    private JTextField date = new JTextField();
    private JButton save = new JButton("Spara");
    private JPanel gridPane = new JPanel(new GridLayout(15,1,15,15));
    private JScrollPane sideScrollPanel = new JScrollPane(gridPane);
    TaskManager taskManager = new TaskManager();
    ActionHandler actionListener = new ActionHandler(this,taskManager);




    public GUI() throws IOException {
        taskManager.dbToList();
        System.out.println("Task-list init size: " + taskManager.getTaskList().size());
        setTitle("ToDo Applikation");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        mainPanel = new JPanel();
        sidePanel = new JPanel();
        menuPanel = new JPanel(new GridLayout(2,3));
        addButton = new JButton("Lägg till uppgift");
        editButton = new JButton("Redigera uppgift");
        removeButton = new JButton("Ta bort markerad");
        markAsDoneButton = new JButton("Markera som klar");
        markAsDoneButton.addActionListener(actionListener);

        doneTaskButton = new JButton("Visa klarade uppgifter");
        unDoneTaskButton = new JButton("Visa ej klara uppgifter");


        createLabels();




        mainPanel.add(taskDescription);

        menuPanel.add(addButton);
        menuPanel.add(editButton);
        menuPanel.add(removeButton);
        menuPanel.add(markAsDoneButton);
        menuPanel.add(doneTaskButton);
        menuPanel.add(unDoneTaskButton);

        addButton.addActionListener(actionListener);
        editButton.addActionListener(actionListener);
        removeButton.addActionListener(actionListener);
        doneTaskButton.addActionListener(actionListener);
        unDoneTaskButton.addActionListener(actionListener);

        // test för att see hur panelerna ser ut
        mainPanel.setBackground(Color.red);
        sidePanel.setBackground(Color.GREEN);
        menuPanel.setBackground(Color.yellow);

        mainPanel.setPreferredSize(new Dimension(500, getHeight()));
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setPreferredSize(new Dimension(getWidth(), 50));
        add(menuPanel, BorderLayout.NORTH);
        add(sideScrollPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.EAST);

        setVisible(true);
    }

    public Task addTaskWindow(){
        addTaskFrame.add(addTaskMainPanel);
        titleField.setToolTipText("Titel:");
        setSize(800, 500);
        addTaskMainPanel.setSize(300,300);
        addTaskMainPanel.add(titleField);
        addTaskMainPanel.add(descriptionArea);
        addTaskMainPanel.add(date);
        addTaskMainPanel.add(save);

        save.addActionListener(actionListener);

        addTaskFrame.setSize(800,500);
        addTaskFrame.setVisible(true);
        addTaskFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        return null;
    }

    public void updateGridPane() {
        gridPane.removeAll();

        for (Task task : taskManager.getTaskList()) {
            JLabel label = new JLabel(task.getTitle());
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    clickedLabel.setBackground(Color.GRAY);
                    Component[] labels = gridPane.getComponents();
                    for (Component comp : labels) {
                        if (comp instanceof JLabel && comp != clickedLabel) {
                            comp.setBackground(null);
                        }
                    }
                }
            });
            label.setPreferredSize(new Dimension(300, label.getPreferredSize().height));
            label.setOpaque(true);
            gridPane.add(label);
        }

        revalidate();
        repaint();
    }


    public void createLabels(){
        gridPane.removeAll();
        for (Task task:taskManager.getTaskList()
        ) {
            JLabel label = new JLabel(task.getTitle());
            label.addMouseListener(actionListener);
            label.setPreferredSize(new Dimension(300, label.getPreferredSize().height));
            label.setOpaque(true);
            gridPane.add(label);
        }

        gridPane.repaint();
        gridPane.revalidate();
    }

    public void resetTextFields(){
        date.setText(null);
        titleField.setText(null);
        descriptionArea.setText(null);
    }

    public Task removeTask(){
        return null;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JTextField getDate() {
        return date;
    }

    public JButton getSave() {
        return save;
    }

    public void showDescription(Task task) {
        taskDescription.setText(task.getContent());
    }

    public JFrame getAddTaskFrame() {
        return addTaskFrame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}