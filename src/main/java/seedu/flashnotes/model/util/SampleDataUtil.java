package seedu.flashnotes.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.FlashcardTagPair;
import seedu.flashnotes.model.ReadOnlyFlashNotes;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashNotes} with sample data.
 */
public class SampleDataUtil {
    public static FlashcardTagPair[] getSampleFlashcards() {
        return new FlashcardTagPair[] {
            new FlashcardTagPair(new Flashcard(new Question("When did Singapore gain independence"),
                    new Answer("1965")), new Tag("Singapore")),
                new FlashcardTagPair(new Flashcard(new Question("How many stars are there on Singapore's Flag"),
                        new Answer("5")), new Tag("Singapore")),
                new FlashcardTagPair(new Flashcard(new Question("Who was Singapore's First Prime Minister"),
                        new Answer("Lee Kuan Yew")), new Tag("Singapore")),
                new FlashcardTagPair(new Flashcard(new Question("What is the definition of Macroeconomics"),
                        new Answer("Macroeconomics is a branch of economics that studies how an overall economy, " +
                                "the market systems that operate on a large scale, behaves")),
                        new Tag("Economics")),
                new FlashcardTagPair(new Flashcard(new Question("How do you spell Singaporean"),
                        new Answer("Singaporean")), new Tag("Singapore")),
                new FlashcardTagPair(new Flashcard(new Question("What is the definition of Microeconomics"),
                        new Answer("Microeconomics is a branch of economics that studies the behavior of individuals " +
                                "and firms in making decisions regarding the allocation of scarce resources and the " +
                                "interactions among these individuals and firms")),
                        new Tag("Economics"))
        };
    }

    public static ReadOnlyFlashNotes getSampleFlashNotes() {
        FlashNotes sampleAb = new FlashNotes();
        for (FlashcardTagPair sampleFlashcardTagPair : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcardTagPair.getTag(), sampleFlashcardTagPair.getFlashcard());
        }
        return sampleAb;
    }

    /*
    /**
     * Returns a tag set containing the list of strings given.
     *
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }*/

}
