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
import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyDeck;

/**
 * A class to access FlashNotes data stored as a json file on the hard disk.
 */
public class JsonFlashNotesStorage implements FlashNotesStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFlashNotesStorage.class);

    private Path filePath;

    public JsonFlashNotesStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFlashNotesFilePath() {
        return filePath;
    }

    @Override
    public Optional<FlashNotes> readFlashNotes() throws DataConversionException {
        return readFlashNotes(filePath);
    }

    /**
     * Similar to {@link #readFlashNotes()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<FlashNotes> readFlashNotes(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFlashNotes> jsonFlashNotes = JsonUtil.readJsonFile(
                filePath, JsonSerializableFlashNotes.class);
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
    public void saveFlashNotes(FlashNotes flashNotes) throws IOException {
        saveFlashNotes(flashNotes, filePath);
    }

    /**
     * Similar to {@link #saveFlashNotes(FlashNotes)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFlashNotes(FlashNotes flashNotes, Path filePath) throws IOException {
        requireNonNull(flashNotes);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFlashNotes(flashNotes), filePath);
    }

}
