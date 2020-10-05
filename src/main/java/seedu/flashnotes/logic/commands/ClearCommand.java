package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.Model;

/**
 * Clears the flashnotes.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Flash Notes has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDeck(new Deck());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
