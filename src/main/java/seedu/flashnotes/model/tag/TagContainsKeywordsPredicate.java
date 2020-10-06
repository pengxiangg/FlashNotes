package seedu.flashnotes.model.tag;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * Tests that a {@code Flashcard}'s {@code Tag} matches any of the keywords given.
 */
/*
public class TagContainsKeywordsPredicate implements Predicate<Flashcard> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        Set<String> tags = flashcard.getTags().stream()
                .map(t -> t.toStringWithSpace()).collect(Collectors.toSet());
        return keywords.stream()
                .anyMatch(keyword -> tags.contains(keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
*/
