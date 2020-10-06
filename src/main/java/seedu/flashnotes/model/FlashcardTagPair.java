package seedu.flashnotes.model;

import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.tag.Tag;

public class FlashcardTagPair {

    Flashcard flashcard;
    Tag tag;

    public FlashcardTagPair(Flashcard flashcard, Tag tag) {
        this.flashcard = flashcard;
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }

    public Flashcard getFlashcard() {
        return flashcard;
    }
}
