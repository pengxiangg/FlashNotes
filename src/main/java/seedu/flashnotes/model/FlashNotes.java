package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.UniqueFlashcardList;
import seedu.flashnotes.model.tag.Tag;

/**
 * Wraps all data at the flashnotes level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashNotes implements ReadOnlyFlashNotes {

    //private final UniqueFlashcardList flashcards;
    private final Map<Tag, UniqueFlashcardList> tagUniqueFlashcardListMap;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        //flashcards = new UniqueFlashcardList();
        tagUniqueFlashcardListMap = new HashMap<Tag, UniqueFlashcardList>();
    }

    public FlashNotes() {}

    /**
     * Creates an FlashNotes using the Flashcards in the {@code toBeCopied}
     */
    /*
    public FlashNotes(ReadOnlyFlashNotes toBeCopied) {
        this();
        resetData(toBeCopied);
    }*/

    public FlashNotes(ReadOnlyFlashNotes toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(Tag tag, List<Flashcard> flashcards) {
        UniqueFlashcardList uniqueFlashcards = tagUniqueFlashcardListMap.get(tag);
        uniqueFlashcards.setFlashcards(flashcards);
    }

    public void setMap(Map<Tag, UniqueFlashcardList> map) {
        this.tagUniqueFlashcardListMap.putAll(map);
    }

    /**
     * Resets the existing data of this {@code FlashNotes} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashNotes newData) {
        requireAllNonNull(newData);
        tagUniqueFlashcardListMap.clear();
        setMap(newData.getMap());
    }


    public Map<Tag, FilteredList<Flashcard>> getTagFlashcardMap() {
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        Map<Tag, FilteredList<Flashcard>> returnMap = new HashMap<>();
        for (Tag tag : tags) {
            returnMap.put(tag, new FilteredList<>(
                    this.tagUniqueFlashcardListMap.get(tag).asUnmodifiableObservableList()));
        }
        return returnMap;
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        /*
        if (tagUniqueFlashcardListMap.containsKey(tag)) {
            return tagUniqueFlashcardListMap.get(tag).contains(flashcard);
        } else {
            return false;
        }*/

        return tagUniqueFlashcardListMap.containsValue(flashcard);
    }

    /**
     * Adds a flashcard to the flashnotes.
     * The flashcard must not already exist in the flashnotes.
     */
    public void addFlashcard(Tag tag, Flashcard card) {
        if (!tagUniqueFlashcardListMap.containsKey(tag)) {
            UniqueFlashcardList flashcards = new UniqueFlashcardList();
            tagUniqueFlashcardListMap.put(tag, flashcards);
        }

        tagUniqueFlashcardListMap.get(tag).add(card);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        for (Tag tag : tags) {
            tagUniqueFlashcardListMap.get(tag).setFlashcard(target, editedFlashcard);
        }

        //tagUniqueFlashcardListMap.get(tag).setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashNotes}.
     * {@code key} must exist in the flashnotes.
     */
    public void removeFlashcard(Flashcard key) {
        //tagUniqueFlashcardListMap.get(tag).remove(key);
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        for (Tag tag : tags) {
            tagUniqueFlashcardListMap.get(tag).remove(key);
        }
    }

    //// util methods

    @Override
    public String toString() {
        //return flashcards.asUnmodifiableObservableList().size() + " flashcards";
        // TODO: refine later
        String s = "";
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        for (Tag tag : tags) {
            s = s + tagUniqueFlashcardListMap.get(tag).asUnmodifiableObservableList().toString() + " ";
        }
        return s;
    }

    @Override
    public ObservableList<Flashcard> getAllFlashcardList() {
        ObservableList<Flashcard> returnAllList = FXCollections.observableArrayList();
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        for (Tag tag : tags) {
            returnAllList.addAll(tagUniqueFlashcardListMap.get(tag).asUnmodifiableObservableList());
        }
        return returnAllList;
    }

    @Override
    public ObservableList<FlashcardTagPair> getFlashcardTagPairList() {
        ObservableList<FlashcardTagPair> returnList = FXCollections.observableArrayList();
        Set<Tag> tags = tagUniqueFlashcardListMap.keySet();
        for (Tag tag : tags) {
            ObservableList<Flashcard> flashcards = tagUniqueFlashcardListMap.get(tag).asUnmodifiableObservableList();
            for (Flashcard flashcard : flashcards) {
                returnList.add(new FlashcardTagPair(flashcard, tag));
            }
        }
        return returnList;
    }

    public Map<Tag, UniqueFlashcardList> getMap() {
        return tagUniqueFlashcardListMap;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashNotes // instanceof handles nulls
                && tagUniqueFlashcardListMap.equals(((FlashNotes) other).tagUniqueFlashcardListMap));
    }

    @Override
    public int hashCode() {
        return tagUniqueFlashcardListMap.hashCode();
    }
}
