package seedu.flashnotes.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.ReadOnlyUserPrefs;
import seedu.flashnotes.model.UserPrefs;

/**
 * Manages storage of FlashNotes data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FlashNotesStorage flashNotesStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FlashNotesStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FlashNotesStorage flashNotesStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.flashNotesStorage = flashNotesStorage;
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
    public Path getFlashNotesFilePath() {
        return flashNotesStorage.getFlashNotesFilePath();
    }

    @Override
    public Optional<FlashNotes> readFlashNotes() throws DataConversionException, IOException {
        return readFlashNotes(flashNotesStorage.getFlashNotesFilePath());
    }

    @Override
    public Optional<FlashNotes> readFlashNotes(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return flashNotesStorage.readFlashNotes(filePath);
    }

    @Override
    public void saveFlashNotes(FlashNotes flashNotes) throws IOException {
        saveFlashNotes(flashNotes, flashNotesStorage.getFlashNotesFilePath());
    }

    @Override
    public void saveFlashNotes(FlashNotes flashNotes, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        flashNotesStorage.saveFlashNotes(flashNotes, filePath);
    }

}
