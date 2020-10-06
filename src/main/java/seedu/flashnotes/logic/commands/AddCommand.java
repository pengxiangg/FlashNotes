package seedu.flashnotes.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.FlashcardTagPair;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Adds a flashcard to the flashnotes.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the flashnotes. "
            + "Parameters: "
            + PREFIX_QUESTION + "QUESTION "
            + PREFIX_ANSWER + "ANSWER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "Why is the sky blue? "
            + PREFIX_ANSWER + "Because it's a reflection of the sea "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the flashnotes";

    private final FlashcardTagPair toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddCommand(FlashcardTagPair flashcardTagPair) {
        requireNonNull(flashcardTagPair);
        toAdd = flashcardTagPair;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcard(toAdd.getFlashcard())) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.addFlashcard(toAdd.getTag(), toAdd.getFlashcard());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
