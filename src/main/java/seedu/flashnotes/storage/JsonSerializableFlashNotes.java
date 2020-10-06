package seedu.flashnotes.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.FlashcardTagPair;
import seedu.flashnotes.model.ReadOnlyFlashNotes;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * An Immutable FlashNotes that is serializable to JSON format.
 */
@JsonRootName(value = "flashnotes")
class JsonSerializableFlashNotes {

    public static final String MESSAGE_DUPLICATE_FLASHCARD = "Flashcards list contains duplicate flashcard(s).";

    private final List<JsonAdaptedFlashcardTagPair> flashcards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFlashNotes} with the given flashcards.
     */
    @JsonCreator
    public JsonSerializableFlashNotes(@JsonProperty("flashcards") List<JsonAdaptedFlashcardTagPair> flashcards) {
        this.flashcards.addAll(flashcards);
    }

    /**
     * Converts a given {@code ReadOnlyFlashNotes} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFlashNotes}.
     */
    public JsonSerializableFlashNotes(ReadOnlyFlashNotes source) {
        flashcards.addAll(source.getFlashcardTagPairList().stream()
                .map(JsonAdaptedFlashcardTagPair::new).collect(Collectors.toList()));
    }

    /**
     * Converts this flashnotes into the model's {@code FlashNotes} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FlashNotes toModelType() throws IllegalValueException {
        FlashNotes flashNotes = new FlashNotes();
        for (JsonAdaptedFlashcardTagPair jsonAdaptedFlashcardTagPair : flashcards) {
            FlashcardTagPair flashcardTagPair = jsonAdaptedFlashcardTagPair.toModelType();
            if (flashNotes.hasFlashcard(flashcardTagPair.getFlashcard())) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FLASHCARD);
            }
            flashNotes.addFlashcard(flashcardTagPair.getTag(), flashcardTagPair.getFlashcard());
        }
        return flashNotes;
    }

}
