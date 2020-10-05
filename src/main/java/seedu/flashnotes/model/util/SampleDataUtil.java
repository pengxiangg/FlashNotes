package seedu.flashnotes.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashnotes.model.Deck;
import seedu.flashnotes.model.ReadOnlyDeck;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashNotes} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[] {
            new Flashcard(new Question("When was the last time you tried something new?"),
                    new Answer("yesterday"), getTagSet("friends")),
            new Flashcard(new Question("What is the most sensible thing you have ever heard someone say?"),
                    new Answer("YOLO"), getTagSet("colleagues", "friends")),
            new Flashcard(new Question("What gets you excited about life?"),
                    new Answer("My phone"), getTagSet("neighbours")),
            new Flashcard(new Question("What do you wish you spent more time doing five years ago?"),
                    new Answer("Watching drama"), getTagSet("family")),
            new Flashcard(new Question("Do you ask enough questions or do you settle for what you know?"),
                    new Answer("nope, too lazy"), getTagSet("classmates")),
            new Flashcard(new Question("Who do you love and what are you doing about it?"),
                    new Answer("Drama XD"), getTagSet("colleagues"))
        };
    }

    public static ReadOnlyDeck getSampleFlashNotes() {
        Deck sampleAb = new Deck();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
