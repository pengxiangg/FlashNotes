package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the flashnotes level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class Deck implements ReadOnlyDeck {

    private final UniqueFlashcardList flashcards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
    }

    public Deck() {}

    /**
     * Creates a Deck using the Flashcards in the {@code toBeCopied}
     */
    public Deck(ReadOnlyDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code FlashNotes} with {@code newData}.
     */
    public void resetData(ReadOnlyDeck newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the flashnotes.
     * The flashcard must not already exist in the flashnotes.
     */
    public void addFlashcard(Flashcard card) {
        flashcards.add(card);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashNotes}.
     * {@code key} must exist in the flashnotes.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        //return flashcards.asUnmodifiableObservableList().size() + " flashcards";
        // TODO: refine later
        return flashcards.asUnmodifiableObservableList().toString();
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deck // instanceof handles nulls
                && flashcards.equals(((Deck) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
