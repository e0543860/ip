package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;

/**
 * TaskList encapsulates all the functions related to managing the list of tasks.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class TaskList {
    private ArrayList<Task> listOfTasks = new ArrayList<>();
    private ArrayList<String> saveFileInput;
    private Storage storage;

    public TaskList() {

    }

    public TaskList(ArrayList<String> saveFileInput, Storage storage) {
        this.saveFileInput = saveFileInput;
        this.storage = storage;
        initialiseTaskList();
    }

    /**
     * Reads the save file and adds tasks to the list according to the save file.
     *
     */
    public void initialiseTaskList() {
        System.out.println("Loading save file...");
        ArrayList<String> tasks = new ArrayList<>();
        saveFileInput.add("T");
        tasks.add(saveFileInput.get(0));
        int index = 1;

        while (index < saveFileInput.size()) {
            String currentStr = saveFileInput.get(index);

            //if the currentStr is a task type
            if ((Parser.getTaskID(currentStr) != -1)) {

                int id = Parser.getTaskID(tasks.get(0));
                boolean isDone = tasks.get(1).equals("1");
                String desc = tasks.get(2);
                String date = "";
                String time = "";
                if (tasks.size() >= 4) {
                    date = tasks.get(3);
                }
                if (tasks.size() >= 5) {
                    time = tasks.get(4);
                }
                addTask(id, desc, date, time, isDone);

                tasks.clear();
                tasks.add(saveFileInput.get(index));
            } else if (Parser.getTaskID(currentStr) == -1) {
                tasks.add(saveFileInput.get(index));
            }
            index++;
        }

    }

    /**
     * Adds a task to the list after a task has been provided.
     *
     * @param t The task provided.
     */
    public void addTask(Task t) {
        System.out.println("Got it. I'll add this task:");
        listOfTasks.add(t);
        System.out.println(t);
        System.out.println("Now you've got " + listOfTasks.size() + " tasks in your list.");
        this.storage.updateSavefile(this);
    }

    /**
     * Adds a task to the list by specifying the arguments for a new task.
     *
     * @param id The integer id for the type of task to be created.
     * @param desc The description of the task.
     * @param date The date of the task.
     * @param time The time of the task.
     * @param status A boolean indicating if the task should be marked as done or not.
     */
    public void addTask(int id, String desc, String date, String time, boolean status) {
        switch (id) {
        case(0):
            Todo newTodo = new Todo(desc, "", "");
            if (status) {
                newTodo.markAsDone();
            }
            listOfTasks.add(newTodo);
            break;
        case(1):
            Deadline newDeadline = new Deadline(desc, date, time);
            if (status) {
                newDeadline.markAsDone();
            }
            listOfTasks.add(newDeadline);
            break;
        case(2):
            Event newEvent = new Event(desc, date, time);
            if (status) {
                newEvent.markAsDone();
            }
            listOfTasks.add(newEvent);
            break;
        default:
        }

    }

    /**
     * Removes a specified task from the list.
     *
     * @param i The index of the task.
     */
    public void removeTask(int i) {
        if (i > listOfTasks.size()) {
            throw new DukeException("Task does not exist!");
        } else {
            listOfTasks.remove(i);
        }
        this.storage.updateSavefile(this);
    }

    /**
     * Calculates the total size of the task list.
     *
     * @return The size of the task list.
     */
    public int numberOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Removes all tasks from the task list.
     *
     */
    public void removeAllTasks() {
        listOfTasks.clear();
    }

    /**
     * Marks a specified task from the list as done.
     *
     * @param i The index of the task.
     */
    public void markAsDone(int i) {
        if (i > listOfTasks.size()) {
            throw new DukeException("duke.task.Task does not exist!");
        } else {
            listOfTasks.get(i).markAsDone();
        }
        this.storage.updateSavefile(this);
    }

    /**
     * Returns a task, specified by its index.
     *
     * @param i The index of the task.
     * @return The specified task.
     */
    public Task getTask(int i) {
        if (i > listOfTasks.size()) {
            throw new DukeException("duke.task.Task does not exist!");
        } else {
            return listOfTasks.get(i);
        }
    }

    /**
     * Returns a task's string representation, specified by its index.
     *
     * @param i The index of the task.
     * @return The specified task's string representation.
     */
    public String getTaskString(int i) {
        if (i > listOfTasks.size()) {
            throw new DukeException("duke.task.Task does not exist!");
        } else {
            return listOfTasks.get(i).toString();
        }
    }

    /**
     * Prints all tasks in the task list.
     *
     */
    public void printAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i + 1) + "." + listOfTasks.get(i).toString());
        }
    }

    /**
     * Prints all the tasks list that occur on the specified date.
     *
     * @param s The specified date.
     */
    public void printAllTasksOnDate(String s) {
        System.out.println("Here are the tasks in your list due on " + s + ":");
        int counter = 1;

        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getDate().contains(s)) {
                System.out.println(counter + ". " + listOfTasks.get(i).toString());
                counter++;
            }
        }
    }

    public void printAllTasksWith(String s) {
        System.out.println("Here are the tasks in your list with the word: " + s + ":");
        int counter = 1;

        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getDesc().contains(s)) {
                System.out.println(counter + ". " + listOfTasks.get(i).toString());
                counter++;
            }
        }
    }


}
