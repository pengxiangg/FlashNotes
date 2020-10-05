package seedu.flashnotes.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.ReadOnlyUserPrefs;
import seedu.flashnotes.model.UserPrefs;

/**
 * Manages storage of FlashNotes data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DeckStorage deckStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashNotesStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DeckStorage deckStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.deckStorage = deckStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FlashNotes methods ==============================

    @Override
    public Path getDeckFilePath() {
        return deckStorage.getDeckFilePath();
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck() throws DataConversionException, IOException {
        return readDeck(deckStorage.getDeckFilePath());
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return deckStorage.readDeck(filePath);
    }

    @Override
    public void saveDeck(ReadOnlyDeck flashNotes) throws IOException {
        saveDeck(flashNotes, deckStorage.getDeckFilePath());
    }

    @Override
    public void saveDeck(ReadOnlyDeck flashNotes, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        deckStorage.saveDeck(flashNotes, filePath);
    }

}
