package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Represents the in-memory model of the flashnotes data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);


    private final Deck deck;
    private final UserPrefs userPrefs;
    private final FilteredList<Flashcard> filteredFlashcards;

    /**
     * Initializes a ModelManager with the given flashNotes and userPrefs.
     */
    public ModelManager(ReadOnlyDeck deck, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(deck, userPrefs);

        logger.fine("Initializing with deck: " + deck + " and user prefs " + userPrefs);

        this.deck = new Deck(deck.getDeckName(), deck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFlashcards = new FilteredList<>(this.deck.getFlashcardList());
    }

    public ModelManager(String deckName) {
        this(new Deck(deckName), new UserPrefs());
    }


    //=========== FlashNotes ================================================================================

    @Override
    public void setDeck(ReadOnlyDeck deck) {
        this.deck.resetData(deck);
    }

    @Override
    public ReadOnlyDeck getDeck() {
        return deck;
    }

    @Override
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return deck.hasFlashcard(flashcard);
    }

    @Override
    public void deleteFlashcard(Flashcard target) {
        deck.removeFlashcard(target);
    }

    @Override
    public void addFlashcard(Flashcard flashcard) {
        deck.addFlashcard(flashcard);
        updateFilteredFlashcardList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireAllNonNull(target, editedFlashcard);

        deck.setFlashcard(target, editedFlashcard);
    }

    //=========== Filtered Flashcard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Flashcard} backed by the internal list of
     * {@code versionedFlashNotes}
     */
    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return filteredFlashcards;
    }

    @Override
    public void updateFilteredFlashcardList(Predicate<Flashcard> predicate) {
        requireNonNull(predicate);
        filteredFlashcards.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return deck.equals(other.deck)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashcards.equals(other.filteredFlashcards);
    }

}
