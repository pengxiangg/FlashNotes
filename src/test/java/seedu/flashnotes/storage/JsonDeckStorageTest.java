package seedu.flashnotes.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.flashnotes.testutil.Assert.assertThrows;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHAT_IS_MEIER;
import static seedu.flashnotes.testutil.TypicalFlashcards.WHO_IS_MEIER;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.ReadOnlyDeck;

public class JsonDeckStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonFlashNotesStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFlashNotes_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFlashNotes(null));
    }

    private java.util.Optional<ReadOnlyDeck> readFlashNotes(String filePath) throws Exception {
        return new JsonDeckStorage(Paths.get(filePath)).readDeck(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFlashNotes("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFlashNotes("notJsonFormatFlashNotes.json"));
    }

    @Test
    public void readFlashNotes_invalidFlashcardFlashNotes_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFlashNotes("invalidFlashcard.json"));
    }

    @Test
    public void readFlashNotes_invalidAndValidFlashcardFlashNotes_throwDataConversionException() {
        assertThrows(DataConversionException
                .class, () -> readFlashNotes("invalidAndValidFlashcard.json"));
    }

    @Test
    public void readAndSaveFlashNotes_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFlashNotes.json");
        Deck original = getTypicalFlashNotes();
        JsonDeckStorage jsonFlashNotesStorage = new JsonDeckStorage(filePath);

        // Save in new file and read back
        jsonFlashNotesStorage.saveDeck(original, filePath);
        ReadOnlyDeck readBack = jsonFlashNotesStorage.readDeck(filePath).get();
        assertEquals(original, new Deck(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFlashcard(WHO_IS_MEIER);
        original.removeFlashcard(WHAT);
        jsonFlashNotesStorage.saveDeck(original, filePath);
        readBack = jsonFlashNotesStorage.readDeck(filePath).get();
        assertEquals(original, new Deck(readBack));

        // Save and read without specifying file path
        original.addFlashcard(WHAT_IS_MEIER);
        jsonFlashNotesStorage.saveDeck(original); // file path not specified
        readBack = jsonFlashNotesStorage.readDeck().get(); // file path not specified
        assertEquals(original, new Deck(readBack));

    }

    @Test
    public void saveFlashNotes_nullFlashNotes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashNotes(null, "SomeFile.json"));
    }

    /**
     * Saves {@code flashNotes} at the specified {@code filePath}.
     */
    private void saveFlashNotes(ReadOnlyDeck flashNotes, String filePath) {
        try {
            new JsonDeckStorage(Paths.get(filePath))
                    .saveDeck(flashNotes, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFlashNotes_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFlashNotes(new Deck(), null));
    }
}
