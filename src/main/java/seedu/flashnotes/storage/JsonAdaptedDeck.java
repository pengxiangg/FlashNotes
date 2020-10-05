package seedu.flashnotes.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.flashcard.Flashcard;

public class JsonAdaptedDeck {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final String deckName;
    private final List<JsonAdaptedFlashcard> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given deck details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("deckName") String deckName,
                           @JsonProperty("cards") List<JsonAdaptedFlashcard> cards) {
        this.deckName = deckName;
        this.flashcards.addAll(cards);
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(ReadOnlyDeck deck) {
        deckName = deck.getDeckName();
        flashcards.addAll(deck.getFlashcardList().stream()
                .map(JsonAdaptedFlashcard::new).collect(Collectors.toList()));
    }

    public Deck toModelType() throws IllegalValueException {
        Deck deck = new Deck(deckName);
        for (JsonAdaptedFlashcard jsonAdaptedFlashcard : flashcards) {
            Flashcard flashcard = jsonAdaptedFlashcard.toModelType();
            if (deck.hasFlashcard(flashcard)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            deck.addFlashcard(flashcard);
        }
        return deck;
    }


}
