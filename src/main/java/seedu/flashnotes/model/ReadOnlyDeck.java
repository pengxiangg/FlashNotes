package seedu.flashnotes.model;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an flashnotes
 */
public interface ReadOnlyDeck {

    /**
     * Returns an unmodifiable view of the flashcards list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Flashcard> getFlashcardList();


    String getDeckName();
}
