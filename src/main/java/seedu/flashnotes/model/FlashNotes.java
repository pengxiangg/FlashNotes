package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.List;

public class FlashNotes {

    List<Deck> decks;

    public FlashNotes() {
        decks = new ArrayList<>();
    }

    public FlashNotes(FlashNotes toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setDecks(List<Deck> decks) {
        this.decks = decks;
    }

    public void resetData(FlashNotes newData) {
        requireNonNull(newData);

        setDecks(newData.getDecks());
    }

    public List<Deck> getDecks() { return decks; }

    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    public void addDeck(Deck deck) { decks.add(deck); }

}
