package seedu.flashnotes.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * An Immutable FlashNotes that is serializable to JSON format.
 */
@JsonRootName(value = "flashnotes")
class JsonSerializableFlashNotes {

    public static final String MESSAGE_DUPLICATE_DECK = "Deck list contains duplicate deck(s).";

    private final List<JsonAdaptedDeck> decks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashNotes} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashNotes(@JsonProperty("flashnotes") List<JsonAdaptedDeck> decks) {
        this.decks.addAll(decks);
    }

    /**
     * Converts a given {@code ReadOnlyFlashNotes} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashNotes}.
     */
    public JsonSerializableFlashNotes(FlashNotes source) {
        decks.addAll(source.getDecks().stream()
                .map(JsonAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this flashnotes into the model's {@code FlashNotes} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */

    public FlashNotes toModelType() throws IllegalValueException {
        FlashNotes flashNotes = new FlashNotes();
        for (JsonAdaptedDeck jsonAdaptedDeck : decks) {
            Deck deck = jsonAdaptedDeck.toModelType();
            if (flashNotes.hasDeck(deck)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DECK);
            }
            flashNotes.addDeck(deck);
        }
        return flashNotes;
    }

}
