package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.flashnotes.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_ANSWER_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_QUESTION_BOB;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.core.Messages;
import seedu.flashnotes.commons.core.index.Index;
import seedu.flashnotes.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.testutil.EditFlashcardDescriptorBuilder;
import seedu.flashnotes.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Flashcard editedFlashcard = new FlashcardBuilder().build();
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(editedFlashcard).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new Deck(model.getFlashNotes()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashcard = Index.fromOneBased(model.getFilteredFlashcardList().size());
        Flashcard lastFlashcard = model.getFilteredFlashcardList().get(indexLastFlashcard.getZeroBased());

        FlashcardBuilder flashcardInList = new FlashcardBuilder(lastFlashcard);
        Flashcard editedFlashcard = flashcardInList.withQuestion(VALID_QUESTION_BOB)
                .withAnswer(VALID_ANSWER_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_BOB)
                .withAnswer(VALID_ANSWER_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastFlashcard, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new Deck(model.getFlashNotes()), new UserPrefs());
        expectedModel.setFlashcard(lastFlashcard, editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, new EditFlashcardDescriptor());
        Flashcard editedFlashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new Deck(model.getFlashNotes()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        Flashcard flashcardInFilteredList = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        Flashcard editedFlashcard = new FlashcardBuilder(flashcardInFilteredList)
                .withQuestion(VALID_QUESTION_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, editedFlashcard);

        Model expectedModel = new ModelManager(new Deck(model.getFlashNotes()), new UserPrefs());
        expectedModel.setFlashcard(model.getFilteredFlashcardList().get(0), editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFlashcardUnfilteredList_failure() {
        Flashcard firstFlashcard = model.getFilteredFlashcardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder(firstFlashcard).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FLASHCARD, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_duplicateFlashcardFilteredList_failure() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);

        // edit flashcard in filtered list into a duplicate in flashnotes
        Flashcard flashcardInList = model.getFlashNotes().getFlashcardList()
                .get(INDEX_SECOND_FLASHCARD.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                new EditFlashcardDescriptorBuilder(flashcardInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_invalidFlashcardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashcardList().size() + 1);
        EditCommand.EditFlashcardDescriptor descriptor = new EditFlashcardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of flashnotes
     */
    @Test
    public void execute_invalidFlashcardIndexFilteredList_failure() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of flashnotes list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashNotes().getFlashcardList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FLASHCARD, DESC_AMY);

        // same values -> returns true
        EditCommand.EditFlashcardDescriptor copyDescriptor = new EditCommand.EditFlashcardDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FLASHCARD, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FLASHCARD, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FLASHCARD, DESC_BOB)));
    }

}
