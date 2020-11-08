package seedu.flashnotes.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.flashnotes.model.FlashNotes;
import seedu.flashnotes.model.ReadOnlyFlashNotes;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.flashcard.Answer;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.Question;
import seedu.flashnotes.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FlashNotes} with sample data.
 */
public class SampleDataUtil {
    public static Flashcard[] getSampleFlashcards() {
        return new Flashcard[]{
            new Flashcard(new Question("What is the definition of Macroeconomics?"),
                        new Answer("It is concerned with large-scale or general economic factors, "
                                + "such as interest rates and national productivity."),
                        new Tag("Economics")),
            new Flashcard(new Question("What is the definition of Microeconomics?"),
                        new Answer("It is concerned with single factors and the effects of individual decisions."),
                        new Tag("Economics")),
            new Flashcard(new Question("What is the definition of demand?"),
                        new Answer("It is the desire and willingness to pay a price for a good or service"),
                        new Tag("Economics")),
            new Flashcard(new Question("When did Singapore gain independence?"),
                        new Answer("1965"), new Tag("Singapore")),
            new Flashcard(new Question("How many stars are there on Singapore's Flag?"),
                        new Answer("5"), new Tag("Singapore")),
            new Flashcard(new Question("Who was Singapore's First Prime Minister?"),
                        new Answer("Lee Kuan Yew"), new Tag("Singapore")),
            new Flashcard(new Question("How do you spell Singaporean?"),
                        new Answer("Singaporean"), new Tag("Singapore")),
            new Flashcard(new Question("Who wrote The Great Gatsby"),
                        new Answer("F. Scott Fitzgerald"), new Tag("Literature")),
            new Flashcard(new Question("Why did Daisy marry Tom in the Great Gatsby?"),
                        new Answer("Daisy most likely married Tom because she knew "
                                + "he could provide her with more material comforts"), new Tag("Literature")),
            new Flashcard(new Question("Why does Myrtle run out in front of Gatsby’s car in the Great Gatsby?"),
                        new Answer(" Myrtle runs out in front of Gatsby’s "
                                + "car because she mistakes it for Tom’s car."),
                        new Tag("Literature"))
        };
    }

    public static Deck[] getSampleDecks() {
        return new Deck[]{new Deck("Economics"), new Deck("Singapore"), new Deck("Literature")
        };
    }

    public static ReadOnlyFlashNotes getSampleFlashNotes() {
        FlashNotes sampleAb = new FlashNotes();
        for (Flashcard sampleFlashcard : getSampleFlashcards()) {
            sampleAb.addFlashcard(sampleFlashcard);
        }

        for (Deck deck : getSampleDecks()) {
            sampleAb.addDeck(deck);
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
