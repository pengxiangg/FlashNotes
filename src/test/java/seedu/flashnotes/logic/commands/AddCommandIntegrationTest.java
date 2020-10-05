package seedu.flashnotes.logic.commands;

import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
    }

    @Test
    public void execute_newFlashcard_success() {
        Flashcard validFlashcard = new FlashcardBuilder().build();

        Model expectedModel = new ModelManager(model.getDeck(), new UserPrefs());
        expectedModel.addFlashcard(validFlashcard);

        assertCommandSuccess(new AddCommand(validFlashcard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), expectedModel);
    }

    @Test
    public void execute_duplicateFlashcard_throwsCommandException() {
        Flashcard flashcardInList = model.getDeck().getFlashcardList().get(0);
        assertCommandFailure(new AddCommand(flashcardInList), model, AddCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

}
