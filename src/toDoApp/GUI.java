package toDoApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUI extends JFrame {
    JFrame addTaskFrame = new JFrame();

    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel menuPanel;
    private JPanel addTaskMainPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton doneTaskButton;
    private JButton unDoneTaskButton;
    private JTextArea taskDescription = new JTextArea(20, 40);
    private JList<String> listOfTasks;
    private JTextArea showTitles = new JTextArea(20, 20);
    private JTextField titleField = new JTextField();
    private JTextArea descriptionArea = new JTextArea();
    private JTextField date = new JTextField();
    private JButton save = new JButton();
    ActionHandler actionListener = new ActionHandler(this);
    TaskManager taskManager = new TaskManager();
    List taskTitles = new List();



    public GUI() throws IOException {
        for (Task task:taskManager.getTaskList()){
            taskTitles.add(task.getTitle());
        }
       listOfTasks = new JList<>();
       listOfTasks.setBackground(Color.BLUE);


        setTitle("ToDo Applikation");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        mainPanel = new JPanel();
        sidePanel = new JPanel();
        menuPanel = new JPanel();
        addButton = new JButton("Lägg till");
        editButton = new JButton("Redigera");
        removeButton = new JButton("Ta bort");
        doneTaskButton = new JButton("Visa klarade uppgifter");
        unDoneTaskButton = new JButton("Visa ej klara uppgifter");
        //sidePanel.add(showTitles);
        sidePanel.add(listOfTasks);
        mainPanel.add(taskDescription);

        menuPanel.add(addButton);
        menuPanel.add(editButton);
        menuPanel.add(removeButton);
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
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void addTasksToJList(){
        //lägga till befintliga tasks i en Jlist som ska visas upp på startskärmen
    }

    public void startWindow(){
    }

    public Task addTaskWindow(){
        Task task = null;
        addTaskMainPanel = new JPanel(new GridLayout(4,1));
        addTaskFrame.add(addTaskMainPanel);
        titleField = new JTextField();
        titleField.setToolTipText("Titel:");
        descriptionArea = new JTextArea(20, 40);
        date = new JTextField();
        save = new JButton("Spara");
        setSize(600, 500);
        addTaskMainPanel.setSize(300,300);
        addTaskMainPanel.add(titleField);
        addTaskMainPanel.add(descriptionArea);
        addTaskMainPanel.add(date);
        addTaskMainPanel.add(save);

        save.addActionListener(actionListener);

        addTaskFrame.setSize(600,500);
        addTaskFrame.setVisible(true);
        addTaskFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        return task;


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
}
