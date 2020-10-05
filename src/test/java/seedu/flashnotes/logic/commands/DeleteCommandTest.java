package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Flashcard flashcardToDelete = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);

        ModelManager expectedModel = new ModelManager(model.getDeck(), new UserPrefs());
        expectedModel.deleteFlashcard(flashcardToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Flashcard flashcardToDelete = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_FLASHCARD_SUCCESS, flashcardToDelete);

        Model expectedModel = new ModelManager(model.getDeck(), new UserPrefs());
        expectedModel.deleteFlashcard(flashcardToDelete);
        showNoFlashcard(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flashnotes list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDeck().getFlashcardList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_FLASHCARD);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_FLASHCARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_FLASHCARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFlashcard(Model model) {
        model.updateFilteredFlashcardList(p -> false);

        assertTrue(model.getFilteredFlashcardList().isEmpty());
    }
}
