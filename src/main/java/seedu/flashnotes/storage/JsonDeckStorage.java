package seedu.flashnotes.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.commons.exceptions.DataConversionException;
import seedu.flashnotes.commons.exceptions.IllegalValueException;
import seedu.flashnotes.commons.util.FileUtil;
import seedu.flashnotes.commons.util.JsonUtil;
import seedu.flashnotes.model.ReadOnlyDeck;

/**
 * A class to access FlashNotes data stored as a json file on the hard disk.
 */
public class JsonDeckStorage implements DeckStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDeckStorage.class);

    private Path filePath;

    public JsonDeckStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDeckFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck() throws DataConversionException {
        return readDeck(filePath);
    }

    /**
     * Similar to {@link #readDeck()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableDeck> jsonFlashNotes = JsonUtil.readJsonFile(
                filePath, JsonSerializableDeck.class);
        if (!jsonFlashNotes.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFlashNotes.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDeck(ReadOnlyDeck flashNotes) throws IOException {
        saveDeck(flashNotes, filePath);
    }

    /**
     * Similar to {@link #saveDeck(ReadOnlyDeck)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDeck(ReadOnlyDeck flashNotes, Path filePath) throws IOException {
        requireNonNull(flashNotes);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDeck(flashNotes), filePath);
    }

}
