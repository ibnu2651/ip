package duke;

import duke.command.*;

/**
 * Parses the user input and creates the commands requested by the user.
 */
public class Parser {
    /**
     * Returns an instance of a Parser object to parse the user input.
     */
    public Parser() {
    }

    /**
     * Returns the command object after parsing through the user input.
     *
     * @param fullCommand The user input.
     * @return The command object created.
     * @throws DukeException If the user input is an invalid command or format.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        String[] splitCommand;
        String[] splitDesc;
        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            splitCommand = fullCommand.split(" ",2);
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a todo cannot be empty.");
            } else {
                return new AddCommand(command, splitCommand[1]);
            }
        case "deadline":
            splitCommand = fullCommand.split(" ",2);
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a deadline cannot be empty.");
            }
            splitDesc = splitCommand[1].split(" /by ", 2);
            if (splitDesc.length == 1) {
                throw new DukeException("Date/time of a deadline cannot be empty.");
            }

            return new AddCommand(command, splitDesc[0], splitDesc[1]);
        case "event":
            splitCommand = fullCommand.split(" ",2);
            if (splitCommand.length == 1) {
                throw new DukeException("Description of a event cannot be empty.");
            }

            splitDesc = splitCommand[1].split(" /from ", 2);
            if (splitDesc.length==1) {
                throw new DukeException("Start date/time of a event cannot be empty.");
            }

            String[] splitFrom = splitDesc[1].split(" /to ", 2);
            if (splitFrom.length==1) {
                throw new DukeException("End date/time of a event cannot be empty.");
            }

            return new AddCommand(command, splitDesc[0], splitFrom[0], splitFrom[1]);
        case "mark":
            splitCommand = fullCommand.split(" ");
            if (splitCommand.length == 1) {
                throw new DukeException("duke.task.Task number of mark cannot be empty.");
            }
            return new MarkCommand(Integer.parseInt(splitCommand[1]));
        case "unmark":
            splitCommand = fullCommand.split(" ");
            if (splitCommand.length == 1) {
                throw new DukeException("duke.task.Task number of unmark cannot be empty.");
            }
            return new UnmarkCommand(Integer.parseInt(splitCommand[1]));
        case "delete":
            splitCommand = fullCommand.split(" ");
            if (splitCommand.length == 1) {
                throw new DukeException("duke.task.Task number of delete cannot be empty.");
            }
            return new DeleteCommand(Integer.parseInt(splitCommand[1]));
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException("I don't know what that means.");
        }
    }
}
