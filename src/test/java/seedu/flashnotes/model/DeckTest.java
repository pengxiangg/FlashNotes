package seedu.flashnotes.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashnotes.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashnotes.testutil.FlashcardBuilder;

public class DeckTest {

    private final Deck deck = new Deck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), deck.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deck.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFlashNotes_replacesData() {
        Deck newData = getTypicalFlashNotes();
        deck.resetData(newData);
        assertEquals(newData, deck);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardException() {
        // Two flashcards with the same identity fields
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Flashcard> newFlashcards = Arrays.asList(WHAT, editedAlice);
        DeckStub newData = new DeckStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> deck.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> deck.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashNotes_returnsFalse() {
        assertFalse(deck.hasFlashcard(WHAT));
    }

    @Test
    public void hasFlashcard_flashcardInFlashNotes_returnsTrue() {
        deck.addFlashcard(WHAT);
        assertTrue(deck.hasFlashcard(WHAT));
    }

    @Test
    public void hasFlashcard_flashcardWithSameDetailFieldsInFlashNotes_returnsTrue() {
        deck.addFlashcard(WHAT);
        Flashcard editedAlice = new FlashcardBuilder(WHAT).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(deck.hasFlashcard(editedAlice));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> deck.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyFlashNotes whose flashcards list can violate interface constraints.
     */
    private static class DeckStub implements ReadOnlyDeck {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        DeckStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
