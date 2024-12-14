import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> allTasks = new ArrayList<>();

    // STYLE
    static final String SUCCESS = "\033[42m SUCCESS: \033[0m" + "  ";
    static final String TASK = "\033[47m TASK: \033[0m" + "  ";
    static final String ERROR = "\033[41m ERROR: \033[0m" + "  ";

    public static void main(String[] args) {

        // START
        System.out.println(
                "\n---------------------------------- " + "\033[44m Task Manager \033[0m"
                        + " ----------------------------------");
        while (true) {
            System.out.println();
            System.out.println(
                    " 1- Create task \n 2- Show task \n 3- Update task \n 4- Delete task \n 5- Tasks list \n 6- Latest tasks \n 7- Done tasks \n 8- Projress tasks \n 9- Canceled task \n 0- Exit");
            System.out.println();
            System.out.print("Enter process number: ");
            int num = in.nextInt();

            print_lines();
            switch (num) {
                case 0:
                    System.out.println("Thank you for using my system :)");
                    print_lines();
                    System.exit(0);
                    break;
                case 1:
                    create_task();
                    break;
                case 2:
                    show_task();
                    break;
                case 3:
                    update_task();
                    break;
                case 4:
                    delete_task();
                    break;
                case 5:
                    tasks_list();
                    break;
                case 6:
                    latest_tasks();
                    break;
                case 7:
                    find_tasks_with_status("done");
                    break;
                case 8:
                    find_tasks_with_status("in progress");

                    break;
                case 9:
                    find_tasks_with_status("canceled");
                    break;
                default:
                    System.out.println(ERROR + "Wrong Entery please try again");
                    print_lines();
                    break;
            }

        } // end of loop
    }

    // Tasks Mestods
    // 1
    static void create_task() {
        System.out.println("# Create Task:-");

        in.nextLine(); // clear

        System.out.print(" - Task title: ");
        String title = in.nextLine();

        System.out.print(" - Task description: ");
        String description = in.nextLine();

        System.out.print(" - Task status: ");
        String status = in.nextLine();

        System.out.print(" - Task date: ");
        String date = in.nextLine();

        Task t = new Task(title, description, status, date);

        allTasks.add(t);

        print_lines();
        System.out.println(SUCCESS + "Task created Successfully!");
        System.out.println(TASK + t.toString());
        print_lines();
    }

    // 2
    static void show_task() {
        System.out.println("# Show Task:-");

        System.out.print(" - Task ID: ");
        int id = in.nextInt();

        Task task = find_task(id);

        print_lines();
        if (task != null) {
            System.out.println(SUCCESS + TASK + task.toString());
        } else {
            System.out.println(ERROR + "Can not find task with this ID ");
        }
        print_lines();

    }

    static Task find_task(int id) {
        for (Task task : allTasks) {
            if (task.id == id) {
                return task;
            }
        }
        return null;
    }

    // 3
    static void update_task() {
        System.out.println("# Update Task:-");

        System.out.print(" - Task ID: ");
        int id = in.nextInt();

        // check if task is exist
        Task t = find_task(id);
        print_lines();
        if (t != null) {
            System.out.println(TASK + t.toString());
            print_lines();
        } else {
            System.out.println(ERROR + "Can not find task with this ID ");
            print_lines();
            return;
        }

        System.out.print(" - New title: ");
        String title = in.next();

        System.out.print(" - New description: ");
        String description = in.next();

        System.out.print(" - New status: ");
        String status = in.next();

        System.out.print(" - New date: ");
        String date = in.next();

        Task task = find_Task_and_update(id, title, description, status, date);

        print_lines();
        if (task != null) {
            System.out.println(SUCCESS + "Task updated Successfuly!");
            System.out.println(TASK + task.toString());
        } else {
            System.out.println(ERROR + "Can not find task with this ID ");
        }
        print_lines();

    }

    static Task find_Task_and_update(int id, String t, String de, String s, String dt) {
        for (Task task : allTasks) {
            if (task.id == id) {
                task.title = t;
                task.description = de;
                task.status = s;
                task.date = dt;
                return task;
            }
        }
        return null;
    }

    // 4
    static void delete_task() {
        System.out.println("# Delete Task:-");

        System.out.print(" - Task ID: ");
        int id = in.nextInt();

        boolean deleted = find_Task_and_delete(id);

        print_lines();
        if (deleted) {
            System.out.println(SUCCESS + "Task deleted successfluy!");
        } else {
            System.out.println(ERROR + "Can not find task with this ID ");
        }
        print_lines();

    }

    static boolean find_Task_and_delete(int id) {
        for (Task task : allTasks) {
            if (task.id == id) {
                allTasks.remove(task);
                return true;
            }
        }
        return false;
    }

    // 5
    static void tasks_list() {
        System.out.println("# Tasks list:-");
        System.out.println();

        if (allTasks.size() != 0) {
            for (Task task : allTasks) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println(ERROR + "There is no tasks yet");
        }
        print_lines();
    }

    // 6
    static void latest_tasks() {
        System.out.println("# Latest Tasks:-");
        System.out.println();

        System.out.print("Tasks Number: ");
        int num = in.nextInt();

        int start = Math.max(allTasks.size() - num, 0);
        ArrayList<Task> l_Tasks_Array = new ArrayList<>(allTasks.subList(start, allTasks.size()));

        Collections.reverse(l_Tasks_Array);

        print_lines();
        if (l_Tasks_Array.size() > 0) {
            for (Task task : l_Tasks_Array) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println(ERROR + "You dont have Tasks yet");
        }
        print_lines();
    }

    // 7
    static void find_tasks_with_status(String status) {
        System.out.println(status + " Tasks: ");

        ArrayList<Task> list = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.status.equals(status)) {
                list.add(task);
            }
        }

        print_lines();
        if (list.size() > 0) {
            for (Task task : list) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println(ERROR + "there is no " + status + " tasks yet");
        }
        print_lines();
    }

    // General Methods
    static void print_lines() {
        System.out.println(
                "------------------------------------------------------------------------------------");
    }
}
