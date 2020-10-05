package seedu.flashnotes.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.ReadOnlyUserPrefs;
import seedu.flashnotes.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FlashNotesStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFlashNotesFilePath();

    @Override
    Optional<FlashNotes> readFlashNotes() throws DataConversionException, IOException;

    @Override
    void saveFlashNotes(FlashNotes flashNotes) throws IOException;

}
