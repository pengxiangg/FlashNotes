package seedu.flashnotes.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.logic.commands.CommandResult;
import seedu.flashnotes.logic.commands.exceptions.CommandException;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the FlashNotes.
     *
     * @see seedu.flashnotes.model.Model#getFlashNotes()
     */
    ReadOnlyDeck getFlashNotes();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Returns the user prefs' flashnotes file path.
     */
    Path getFlashNotesFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
