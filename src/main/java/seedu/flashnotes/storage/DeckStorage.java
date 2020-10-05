package seedu.flashnotes.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.ReadOnlyDeck;

/**
 * Represents a storage for {@link Deck}.
 */
public interface DeckStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDeckFilePath();

    /**
     * Returns FlashNotes data as a {@link ReadOnlyDeck}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDeck> readDeck() throws DataConversionException, IOException;

    /**
     * @see #getDeckFilePath()
     */
    Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyDeck} to the storage.
     * @param flashNotes cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDeck(ReadOnlyDeck flashNotes) throws IOException;

    /**
     * @see #saveDeck(ReadOnlyDeck)
     */
    void saveDeck(ReadOnlyDeck flashNotes, Path filePath) throws IOException;

}
