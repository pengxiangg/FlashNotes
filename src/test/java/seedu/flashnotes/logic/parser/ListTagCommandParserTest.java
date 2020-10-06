package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.ListTagCommand;
import seedu.flashnotes.model.tag.TagContainsKeywordsPredicate;

public class ListTagCommandParserTest {

    private ListTagsCommandParser parser = new ListTagsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListTagsCommand() {
        // no leading and trailing whitespaces
        ListTagCommand expectedListTagCommand =
                new ListTagCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues")));
        assertParseSuccess(parser, "friends colleagues", expectedListTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n friends \n \t colleagues  \t", expectedListTagCommand);
    }
}
