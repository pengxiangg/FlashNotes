package seedu.flashnotes.testutil;

import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * A utility class to help with building FlashNotes objects.
 * Example usage: <br>
 *     {@code FlashNotes ab = new FlashNotesBuilder().withFlashcard("John", "Doe").build();}
 */
public class FlashNotesBuilder {

    private Deck deck;

    public FlashNotesBuilder() {
        deck = new Deck();
    }

    public FlashNotesBuilder(Deck deck) {
        this.deck = deck;
    }

    /**a
     * Adds a new {@code Flashcard} to the {@code FlashNotes} that we are building.
     */
    public FlashNotesBuilder withFlashcard(Flashcard flashcard) {
        deck.addFlashcard(flashcard);
        return this;
    }

    public Deck build() {
        return deck;
    }
}
