package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;
import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;

public class FlashNotesModelManager {
    private static final Logger logger = LogsCenter.getLogger(FlashNotesModelManager.class);

    private final FlashNotes flashNotes;
    private final UserPrefs userPrefs;

    public FlashNotesModelManager(FlashNotes flashNotes, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(flashNotes, userPrefs);
        logger.fine("Initializing with flashnotes: " + flashNotes + " and user prefs " + userPrefs);

        this.flashNotes = new FlashNotes(flashNotes);
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public FlashNotesModelManager() {
        this(new FlashNotes(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }


    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }


    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }


    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    public Path getFlashNotesFilePath() {
        return userPrefs.getFlashNotesFilePath();
    }


    public void setFlashNotesFilePath(Path flashNotesFilePath) {
        requireNonNull(flashNotesFilePath);
        userPrefs.setFlashNotesFilePath(flashNotesFilePath);
    }

    //=========== FlashNotes ================================================================================

    public void setFlashNotes(FlashNotes flashNotes) {
        this.flashNotes.resetData(flashNotes);
    }

    public FlashNotes getFlashNotes() {
        return flashNotes;
    }

    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return flashNotes.hasDeck(deck);
    }

    public void addDeck(Deck deck) {
        flashNotes.addDeck(deck);
    }

}
