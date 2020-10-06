package seedu.flashnotes.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.HOW;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHO;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class ListTagCommandTest {
    private Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashNotes(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        ListTagCommand listTagsFirstCommand = new ListTagCommand(firstPredicate);
        ListTagCommand listTagsSecondCommand = new ListTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listTagsFirstCommand.equals(listTagsFirstCommand));

        // same values -> returns true
        ListTagCommand listTagsFirstCommandCopy = new ListTagCommand(firstPredicate);
        assertTrue(listTagsFirstCommand.equals(listTagsFirstCommandCopy));

        // different types -> returns false
        assertFalse(listTagsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listTagsFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(listTagsFirstCommand.equals(listTagsSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        ListTagCommand command = new ListTagCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = preparePredicate("owesMoney friends");
        ListTagCommand command = new ListTagCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(WHO, WHAT, HOW), model.getFilteredFlashcardList());
    }

    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
