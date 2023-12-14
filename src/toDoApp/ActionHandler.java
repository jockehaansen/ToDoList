package toDoApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDate;

public class ActionHandler implements ActionListener, MouseListener {

    TaskManager taskManager;
    String markedTask = "";
    Task taskMarkedForDeletion = null;



    private JLabel lastClickedLabel;

    private GUI gui;

    public ActionHandler(GUI gui, TaskManager taskManager) throws IOException {
        this.gui = gui;
        this.taskManager = taskManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            JButton buttonClicked = (JButton) e.getSource();

            if (buttonClicked.getText() == "Lägg till uppgift"){
                gui.addTaskWindow();
                System.out.println("Tryckte på lägg till");
            } else if (buttonClicked.getText() == "Ta bort markerad") {
                markedTask = lastClickedLabel.getText();
                for (Task task: taskManager.getTaskList()) {
                    if (markedTask.matches(task.getTitle())){
                        taskMarkedForDeletion = task;
                    }
                }
                taskManager.removeTask(taskMarkedForDeletion);
                System.out.println("Removed task: " + taskMarkedForDeletion);

                try {
                    taskManager.updateDatabase();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                gui.createLabels();

            } else if (buttonClicked.getText() == "Redigera") {
                System.out.println("Tryckte på Redigera");
            } else if (buttonClicked.getText().equals("Markera som klar") && lastClickedLabel != null) {
                lastClickedLabel.setBackground(Color.GREEN);
                lastClickedLabel.setOpaque(true);
                gui.getMainPanel().revalidate();
                gui.getMainPanel().repaint();
            } else if (buttonClicked.getText() == "Visa klarade uppgifter") {
                System.out.println("Tryckte på Visa klarade uppgifter");
            } else if (buttonClicked.getText() == "Visa ej klara uppgifter") {
                System.out.println("Tryckte på Visa ej klara uppgifter");
            } else if (buttonClicked.getText() == "Spara") {
                System.out.println(gui.getDate().getText());
                System.out.println(gui.getTitleField().getText());
                System.out.println(gui.getDescriptionArea().getText());
                System.out.println(gui.getDate().getText());

                try {
                    taskManager.createTask(gui.getTitleField().getText(),
                            gui.getDescriptionArea().getText(),
                            LocalDate.parse(gui.getDate().getText()));
                    taskManager.updateDatabase();
                    gui.resetTextFields();
                    gui.updateGridPane();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Efter create task"+taskManager.getTaskList().size());
                gui.createLabels();
                gui.getAddTaskFrame().dispose();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() instanceof JLabel) {
            JLabel clickedLabel = (JLabel) e.getSource();

            if (lastClickedLabel != null && lastClickedLabel != clickedLabel && lastClickedLabel.getBackground() != Color.green) {
                lastClickedLabel.setBackground(null);
                lastClickedLabel.setOpaque(false);
            }
            if(clickedLabel.getBackground() != Color.green){
                clickedLabel.setBackground(Color.GRAY);
                clickedLabel.setOpaque(true);
            }

            lastClickedLabel = clickedLabel;
            gui.getMainPanel().revalidate();
            gui.getMainPanel().repaint();

            gui.getTaskDescription().setText(taskManager.findTask(clickedLabel.getText()).getContent());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}