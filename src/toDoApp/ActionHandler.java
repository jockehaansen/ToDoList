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
    TaskManager taskManager = new TaskManager();
    private JLabel lastClickedLabel;
    private GUI gui;
    
    public ActionHandler(GUI gui) throws IOException {
        this.gui = gui;
        taskManager = new TaskManager();
        taskManager.dbToList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            JButton buttonClicked = (JButton) e.getSource();

            if (buttonClicked.getText() == "Lägg till"){
                gui.addTaskWindow();
                System.out.println("test: " + taskManager.getTaskList());
                System.out.println("Efter lägg till" + taskManager.getTaskList().size());
                System.out.println("Tryckte på lägg till");
            } else if (buttonClicked.getText() == "Ta bort") {
                System.out.println("Tryckte på Ta bort");
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
                System.out.println(gui.getTitleField().getText());
                System.out.println(gui.getDescriptionArea().getText());
                System.out.println(gui.getDate().getText());
                try {
                    taskManager.createTask(gui.getTitleField().getText(),
                            gui.getDescriptionArea().getText(),
                            LocalDate.parse(gui.getDate().getText()));
                    taskManager.updateDatabase();
                    gui.updateGridPane();
                    gui.getTitleField().setText("");
                    gui.getDescriptionArea().setText("");
                    gui.getDate().setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Efter create task"+taskManager.getTaskList().size());
                gui.getAddTaskFrame().dispose();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JLabel) {
            JLabel clickedLabel = (JLabel) e.getSource();

            if (lastClickedLabel != null && lastClickedLabel != clickedLabel) {
                lastClickedLabel.setBackground(null);
                lastClickedLabel.setOpaque(false);
            }

            clickedLabel.setBackground(Color.GRAY);
            clickedLabel.setOpaque(true);
            lastClickedLabel = clickedLabel;
            gui.getMainPanel().revalidate();
            gui.getMainPanel().repaint();
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
