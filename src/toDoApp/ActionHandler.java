package toDoApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class ActionHandler implements ActionListener {
    TaskManager taskManager = new TaskManager();
    
    private GUI gui;
    
    public ActionHandler(GUI gui){
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton){
            JButton buttonClicked = (JButton) e.getSource();

            if (buttonClicked.getText() == "Lägg till"){
                gui.addTaskWindow();
                System.out.println("test: " + taskManager.getTaskList());
                System.out.println("Efter lägg till" + taskManager.getTaskList().size());
                //Task t = gui.addTaskWindow();
                //taskManager.addTask(t);
                System.out.println("Tryckte på lägg till");
            } else if (buttonClicked.getText() == "Ta bort") {
                System.out.println("Tryckte på Ta bort");
            } else if (buttonClicked.getText() == "Redigera") {
                System.out.println("Tryckte på  Redigera");
            } else if (buttonClicked.getText() == "Visa klarade uppgifter") {
                System.out.println("Tryckte på Visa klarade uppgifter");
            } else if (buttonClicked.getText() == "Visa ej klara uppgifter") {
                System.out.println("Tryckte på Visa ej klara uppgifter");
            } else if (buttonClicked.getText() == "Spara") {
                System.out.println(gui.getTitleField().getText());
                System.out.println(gui.getDescriptionArea().getText());
                System.out.println(gui.getDate().getText());
                taskManager.createTask(gui.getTitleField().getText(),
                        gui.getDescriptionArea().getText(),
                        LocalDate.parse(gui.getDate().getText()));
                System.out.println("Efter create task"+taskManager.getTaskList().size());

                try {
                    taskManager.updateDatabase();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
