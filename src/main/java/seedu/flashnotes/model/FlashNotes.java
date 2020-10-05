package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import java.util.List;

public class FlashNotes {

    List<Deck> decks;

    public List<Deck> getDecks() { return decks; }

    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    public void addDeck(Deck deck) { decks.add(deck); }

}
