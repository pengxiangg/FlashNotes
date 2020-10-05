package seedu.flashnotes.logic.commands;

import static seedu.flashnotes.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashnotes.testutil.TypicalFlashcards.getTypicalFlashNotes;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.Model;
import seedu.flashnotes.model.ModelManager;
import seedu.flashnotes.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFlashNotes_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFlashNotes_success() {
        Model model = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFlashNotes(), new UserPrefs());
        expectedModel.setFlashNotes(new Deck());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
