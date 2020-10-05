package seedu.flashnotes.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashnotes.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.commons.util.JsonUtil;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.testutil.TypicalFlashcards;

public class JsonSerializableDeckTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonSerializableFlashNotesTest");
    private static final Path TYPICAL_FLASHCARDS_FILE = TEST_DATA_FOLDER.resolve("typicalFlashcards.json");
    private static final Path INVALID_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("invalidFlashcard.json");
    private static final Path DUPLICATE_FLASHCARD_FILE = TEST_DATA_FOLDER.resolve("duplicateFlashcard.json");

    @Test
    public void toModelType_typicalFlashcardsFile_success() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(TYPICAL_FLASHCARDS_FILE,
                JsonSerializableDeck.class).get();
        Deck deckFromFile = dataFromFile.toModelType();
        Deck typicalFlashcardsDeck = TypicalFlashcards.getTypicalFlashNotes();
        System.out.println(deckFromFile);
        System.out.println(typicalFlashcardsDeck);
        assertEquals(deckFromFile, typicalFlashcardsDeck);
    }

    @Test
    public void toModelType_invalidFlashcardFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(INVALID_FLASHCARD_FILE,
                JsonSerializableDeck.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }


    @Test
    public void toModelType_duplicateFlashcards_throwsIllegalValueException() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FLASHCARD_FILE,
                JsonSerializableDeck.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDeck.MESSAGE_DUPLICATE_FLASHCARD,
                dataFromFile::toModelType);
    }

}
