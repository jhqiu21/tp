package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.ViewContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewCommandParser implements Parser<ViewCommand>{
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandField>\\S+)(?<keywords>.*)");
    private static final Logger logger = LogsCenter.getLogger(ViewCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandField = matcher.group("commandField");
        final String keywords = matcher.group("keywords");

        if (commandField.equals(ViewContactCommand.COMMAND_FIELD)) {
            return new ViewContactCommandParser().parse(keywords);
        }

        // TODO: Add ViewEventCommand

        logger.finer("This user input caused a ParseException: view " + args);
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }
}
