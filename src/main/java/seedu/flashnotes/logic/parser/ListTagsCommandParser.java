package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.flashnotes.logic.commands.ListTagCommand;
import seedu.flashnotes.logic.parser.exceptions.ParseException;
import seedu.flashnotes.model.tag.Tag;


/**
 * Parses input arguments and creates a new ListTagsCommand object
 */
public class ListTagsCommandParser implements Parser<ListTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListTagsCommand
     * and returns a ListTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTagCommand.MESSAGE_USAGE));
        }

        return new ListTagCommand(new Tag(trimmedArgs));
    }
}
