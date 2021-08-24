package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command class represents the the actions specified by the user.
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Command {
    /**
     * A method that executes the respective command. To be overrided by subclasses.
     *
     */
    public void execute(TaskList tl, Storage st, Ui ui) {

    }

    /**
     * A method that checks if the user input arguments are in a valid format.
     *
     * @return A boolean indicating if the arguments are in a valid format.
     */
    public boolean isArgumentValid() {
        return true;
    }

    /**
     * A boolean flag that indicates whether the program should quit or not.
     *
     * @return A boolean indicating if the program should terminate.
     */
    public boolean isExit() {
        return false;
    }
}
