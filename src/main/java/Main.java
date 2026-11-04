import tasks.TaskManager;
import tasks.TaskStatus;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length > 3 || args.length == 0){System.out.println("Incorrect Number of Arguments");return;}

        if (args[0].equals("exit")){System.out.println("Session has been terminated");return;}

        TaskManager taskManager = new TaskManager();

        switch (args[0].toLowerCase()) {
            case "add" -> {
                if (args.length != 2) {
                    return;
                }

                taskManager.addTask(args[1]);

            }

            case "update" -> {
                if (args.length != 3) {
                    return;
                }
                taskManager.updateTask(Integer.parseInt(args[1]), args[2]);

            }

            case "delete" -> {
                if (args.length != 2) {
                    return;
                }
                taskManager.deleteTask(Integer.parseInt(args[1]));
            }

            case "mark-in-progress" -> {
                if (args.length != 2) {
                    return;
                }
                taskManager.markInProgress(Integer.parseInt(args[1]));
            }

            case "mark-done" -> {
                if (args.length != 2) {
                    return;
                }
                taskManager.markDone(Integer.parseInt(args[1]));
            }

            case "list" -> {

                if (args.length > 2) {
                    return;
                }
                if (args.length == 2) {
                    switch (args[1].toLowerCase()) {

                        case "done" -> taskManager.listTasks(TaskStatus.DONE.getLabel());
                        case "todo" -> taskManager.listTasks(TaskStatus.TODO.getLabel());
                        case "in-progress" -> taskManager.listTasks(TaskStatus.IN_PROGRESS.getLabel());
                        default -> {
                            return;
                        }


                    }
                return;
                }
                taskManager.listTasks();
            }
            default -> System.out.println("Unknown command.");
        }
    }
}
