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

        //inläsning av fil och uppskapanden av tasks som läggs i listan
        try {
            this.bufferedReader = new BufferedReader(new java.io.FileReader(dbFile));
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        String tempLine;
        while((tempLine = bufferedReader.readLine()) != null){
            Task task = new Task();
            task.setTitle(tempLine.substring(0,tempLine.indexOf(",")));
            task.setContent(tempLine.substring(tempLine.indexOf(",")+2, tempLine.lastIndexOf(",")));
            task.setDate(LocalDate.parse(tempLine.substring(tempLine.lastIndexOf(",")+2)));
            taskList.add(task);
        }
    };

    public void updateDatabase() throws IOException {
        //skriver om databasen när en task tas bort eller läggs till i listan
        bufferedWriter = new BufferedWriter(new FileWriter(dbFile));
        for (Task task:this.taskList) {
            try{
                bufferedWriter.append(task.getTitle());
                bufferedWriter.append(", ");
                bufferedWriter.append(task.getContent());
                bufferedWriter.append(", ");
                bufferedWriter.append(task.getDate().toString());
                bufferedWriter.newLine();

                bufferedWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        bufferedWriter.close();
    }

    public void createTask(String titel, String description, LocalDate date){
        Task task = new Task(titel,description,date);
        this.taskList.add(task);
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
