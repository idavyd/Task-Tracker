package tasks;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final Path path = Path.of("tasks.json");
    ObjectMapper mapper = new ObjectMapper();

    public TaskManager() throws IOException {
        if (Files.notExists(path)) {
            Files.createFile(path);
            mapper.writeValue(path.toFile(), new ArrayList<Task>());
        }
    }
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Task {

        private int id;
        private String description;
        private String status;
        private final String createdAt;
        private String updatedAt;

        public Task(int id, String description) {
            this.id = id;
            this.description = description;
            this.status = String.valueOf(TaskStatus.TODO.getLabel());
            this.createdAt = String.valueOf(LocalDateTime.now());
            this.updatedAt = this.createdAt;
        }

        public Task(
                int id,
                String description,
                String status,
                String createdAt,
                String updatedAt
        ) {
            this.id = id;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

    private List<Task> returnTasks() throws IOException {
        JsonNode root = mapper.readTree(path.toFile());
        List<Task> tasks = new ArrayList<>();

        for (JsonNode node : root) {
            int id = node.get("id").asInt();
            String description = node.get("description").asText();
            String status = node.get("status").asText();
            String createdAt = node.get("createdAt").asText();
            String updatedAt = node.get("updatedAt").asText();
            Task task = new Task(id, description, status, createdAt, updatedAt);
            tasks.add(task);

        }
        return tasks;
    }

    public void addTask(String newTaskDescription) throws IOException {
        List<Task> tasks = this.returnTasks();

        int newTaskId = tasks.isEmpty() ? 1 : tasks.getLast().id + 1;

        Task task = new Task(newTaskId, newTaskDescription);

        tasks.add(task);

        this.mapper.writeValue(this.path.toFile(), tasks);
        System.out.println("Task added successfully (ID: " + task.id + ")");
    }

    public void updateTask( int taskId, String newTaskDescription) throws IOException {
        List<Task> tasks = this.returnTasks();

        boolean isFound = false;

        for (Task task : tasks) {
            if (task.id == taskId) {
                task.description = newTaskDescription;
                task.updatedAt = String.valueOf(LocalDateTime.now());

                isFound = true;
                break;
            }
        }

        if (isFound) {
            this.mapper.writeValue(this.path.toFile(), tasks);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task with ID " + taskId + " was not found.");
        }


        }

    public void deleteTask ( int taskId) throws IOException {
        List<Task> tasks = this.returnTasks();

        boolean isRemoved = tasks.removeIf(task -> task.id == taskId);
        if (isRemoved){
            this.mapper.writeValue(this.path.toFile(), tasks);
            System.out.println("Task has been removed");
        }
        else {
            System.out.println("Task with ID '" + taskId + "' was not found");

        }





    }

    public void listTasks (String listStatus) throws IOException {
        List<Task> tasks = this.returnTasks();

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                if (task.status.equals(listStatus)){
                    System.out.println("-----------");
                    System.out.println("TASK " + task.id);
                    System.out.println("Description:  " + task.description);
                    System.out.println("Status:  " + task.status);
                    System.out.println("Created At:  " + task.createdAt);
                    System.out.println("Updated At:  " + task.updatedAt);

                    if (tasks.getLast().id == task.id) {
                        System.out.println("-----------");

                    }
                }



            }
        }
        else{
            System.out.println("<< No Tasks In the list! >>");

        }
    }

    public void listTasks () throws IOException {
        List<Task> tasks = this.returnTasks();

        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println("-----------");
                System.out.println("TASK " + task.id);
                System.out.println("Description:  " + task.description);
                System.out.println("Status:  " + task.status);
                System.out.println("Created At:  " + task.createdAt);
                System.out.println("Updated At:  " + task.updatedAt);

                if (tasks.getLast().id == task.id) {
                    System.out.println("-----------");

                }


            }
        }
        else{
                System.out.println("<< No Tasks In the list! >>");

            }

        }

    public void markInProgress(int taskId) throws IOException {
        List<Task> tasks = this.returnTasks();

        for(Task task:tasks){
            if (task.id == taskId){
                task.status = String.valueOf(TaskStatus.IN_PROGRESS.getLabel());
                task.updatedAt = String.valueOf(LocalDateTime.now());
            }

        }
        this.mapper.writeValue(this.path.toFile(), tasks);

        System.out.println("Status Change to in-progress");


    }

    public void markDone(int taskId) throws IOException {
        List<Task> tasks = this.returnTasks();

        for(Task task:tasks){
            if (task.id == taskId){
                task.status = String.valueOf(TaskStatus.DONE.getLabel());
                task.updatedAt = String.valueOf(LocalDateTime.now());
            }

        }

        this.mapper.writeValue(this.path.toFile(), tasks);

        System.out.println("Status changed to Done");

    }


}
