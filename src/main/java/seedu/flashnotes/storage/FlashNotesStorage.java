package seedu.flashnotes.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyDeck;

/**
 * Represents a storage for {@link Deck}.
 */
public interface FlashNotesStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFlashNotesFilePath();

    /**
     * Returns FlashNotes data as a {@link ReadOnlyDeck}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<FlashNotes> readFlashNotes() throws DataConversionException, IOException;

    /**
     * @see #getFlashNotesFilePath()
     */
    Optional<FlashNotes> readFlashNotes(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDeck} to the storage.
     * @param flashNotes cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFlashNotes(FlashNotes flashNotes) throws IOException;

    /**
     * @see #saveFlashNotes (ReadOnlyDeck)
     */
    void saveFlashNotes(FlashNotes flashNotes, Path filePath) throws IOException;

}
