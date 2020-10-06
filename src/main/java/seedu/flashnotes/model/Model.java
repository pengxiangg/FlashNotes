package seedu.flashnotes.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' flashnotes file path.
     */
    Path getFlashNotesFilePath();

    /**
     * Sets the user prefs' flashnotes file path.
     */
    void setFlashNotesFilePath(Path flashNotesFilePath);

    /**
     * Replaces flashnotes data with the data in {@code flashNotes}.
     */
    void setFlashNotes(ReadOnlyFlashNotes flashNotes);

    /** Returns the FlashNotes */
    ReadOnlyFlashNotes getFlashNotes();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    boolean hasFlashcard(Tag tag, Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the flashnotes.
     */
    void deleteFlashcard(Tag tag, Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the flashnotes.
     */
    void addFlashcard(Tag tag, Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    void setFlashcard(Tag tag, Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);
}
