package seedu.flashnotes.model;

import java.util.Map;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.UniqueFlashcardList;
import seedu.flashnotes.model.tag.Tag;

/**
 * Unmodifiable view of an flashnotes
 */
public interface ReadOnlyFlashNotes {

    /**
     * Returns an unmodifiable view of the flashcards list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Flashcard> getAllFlashcardList();

    Map<Tag, UniqueFlashcardList> getMap();


}
