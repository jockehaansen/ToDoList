package toDoApp;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager implements TaskOperations{
    List<Task> taskList = new ArrayList<>();
    File dbFile = new File("src/taskDatabase");

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public List showList(){
        //visar inläst lista
        return showList();
    }

    public List updateList(Task task, boolean add){
        if(add){
            taskList.add(task);
        }
        else
            taskList.remove(task);
        return taskList;
    }

    public void dbToList() throws IOException {
        taskList.clear();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dbFile))) {
            String tempLine;
            while ((tempLine = bufferedReader.readLine()) != null) {
                Task task = new Task();
                task.setTitle(tempLine.substring(0, tempLine.indexOf(",")));
                task.setContent(tempLine.substring(tempLine.indexOf(",") + 2, tempLine.lastIndexOf(",")));
                task.setDate(LocalDate.parse(tempLine.substring(tempLine.lastIndexOf(",") + 2)));
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
    }

    public void updateDatabase() throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dbFile))) {
            for (Task task : taskList) {
                bufferedWriter.write(task.getTitle() + ", " + task.getContent() + ", " + task.getDate());
                bufferedWriter.newLine();
            }
        }
    }


    public void createTask(String titel, String description, LocalDate date){
        Task task = new Task(titel,description,date);
        this.taskList.add(task);

    }

    public Task findTask(String input){
        for (Task task:taskList
             ) {
            if (input.matches(task.getTitle())){
                return task;
            }
        }
        return null;
    }


    @Override
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    @Override
    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void editTask(Task task) {
        //saknas logik
    }

    public List<Task> getTaskList() {
        return taskList;
    }


    public static void main(String[] args) throws IOException {
        TaskManager tm = new TaskManager();
        tm.dbToList();
        tm.updateDatabase();
    }


}