package seedu.flashnotes.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.commons.core.LogsCenter;
import seedu.flashnotes.logic.commands.Command;
import seedu.flashnotes.logic.commands.CommandResult;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.logic.parser.FlashNotesParser;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FlashNotesParser flashNotesParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        flashNotesParser = new FlashNotesParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = flashNotesParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDeck(model.getFlashNotes());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDeck getFlashNotes() {
        return model.getFlashNotes();
    }

    @Override
    public ObservableList<Flashcard> getFilteredFlashcardList() {
        return model.getFilteredFlashcardList();
    }

    @Override
    public Path getFlashNotesFilePath() {
        return model.getFlashNotesFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
