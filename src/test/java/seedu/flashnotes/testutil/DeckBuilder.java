package seedu.flashnotes.testutil;

import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * A utility class to help with building FlashNotes objects.
 * Example usage: <br>
 *     {@code FlashNotes ab = new FlashNotesBuilder().withFlashcard("John", "Doe").build();}
 */
public class DeckBuilder {

    private Deck deck;

    public DeckBuilder() {
        deck = new Deck();
    }

    public DeckBuilder(Deck deck) {
        this.deck = deck;
    }

    /**a
     * Adds a new {@code Flashcard} to the {@code FlashNotes} that we are building.
     */
    public DeckBuilder withFlashcard(Flashcard flashcard) {
        deck.addFlashcard(flashcard);
        return this;
    }

    public Deck build() {
        return deck;
    }
}
