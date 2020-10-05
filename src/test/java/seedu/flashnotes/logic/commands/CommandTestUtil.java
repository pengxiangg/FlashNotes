package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashnotes.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.flashnotes.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_AMY = "Why is the sky blue?";
    public static final String VALID_QUESTION_BOB = "What is macroeconomics?";
    public static final String VALID_ANSWER_AMY = "Because it's sleepy";
    public static final String VALID_ANSWER_BOB = "I don't know";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String QUESTION_DESC_AMY = " " + PREFIX_QUESTION + VALID_QUESTION_AMY;
    public static final String QUESTION_DESC_BOB = " " + PREFIX_QUESTION + VALID_QUESTION_BOB;
    public static final String ANSWER_DESC_AMY = " " + PREFIX_ANSWER + VALID_ANSWER_AMY;
    public static final String ANSWER_DESC_BOB = " " + PREFIX_ANSWER + VALID_ANSWER_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION + ""; // question cannot be blank
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER + ""; // answer cannot be blank
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + ""; // blank tag not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFlashcardDescriptor DESC_AMY;
    public static final EditCommand.EditFlashcardDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_AMY)
                .withAnswer(VALID_ANSWER_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_BOB)
                .withAnswer(VALID_ANSWER_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the flashnotes book, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Deck expectedDeck = new Deck(actualModel.getFlashNotes());
        List<Flashcard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashcardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedDeck, actualModel.getFlashNotes());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashcardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s flashnotes book.
     */
    public static void showFlashcardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashcardList().size());

        Flashcard flashcard = model.getFilteredFlashcardList().get(targetIndex.getZeroBased());
        final String[] splitQuestion = flashcard.getQuestion().question.split("\\s+");
        model.updateFilteredFlashcardList(new QuestionContainsKeywordsPredicate(Arrays.asList(splitQuestion[0])));

        assertEquals(1, model.getFilteredFlashcardList().size());
    }

}
