package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.Deadline;
import adam.task.Event;
import adam.task.Task;
import adam.task.TaskList;
import adam.task.Todo;
import adam.ui.Ui;

/**
 * @inheritDoc
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private String command;
    private String desc;
    private String[] details;

    /**
     * Returns a command that contains the details of the task to add.
     *
     * @param details Task type, description, and optional date/times of task.
     */
    public AddCommand(String... details) {
        this.details = new String[details.length - 2];
        for (int i = 0; i < this.details.length; i++) {
            this.details[i] = details[i + 2];
        }
        this.command = details[0];
        this.desc = details[1];
    }

    /**
     * Adds the new task created to the list of tasks.
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        Task t = null;
        switch (this.command) {
        case "todo":
            t = new Todo(this.desc);
            taskList.add(t);
            break;
        case "deadline":
            t = new Deadline(this.desc, this.details[0]);
            taskList.add(t);
            break;
        case "event":
            t = new Event(this.desc, this.details[0], this.details[1]);
            taskList.add(t);
            break;
        }
        return ui.showResult(
                "Got it. I've added this task:",
                t.toString(),
                "Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
