package seedu.flashnotes.model.flashcard;

import static seedu.flashnotes.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.flashnotes.model.tag.Tag;

/**
 * Represents a Flashcard in the flashnotes book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Flashcard {

    // Identity fields
    private final Question question;
    private final Answer answer;

    /**
     * Every field must be present and not null.
     */
    public Flashcard(Question question, Answer answer) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    /**
     * Returns true if both flashcards have the same question have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two flashcards.
     */
    public boolean isSameFlashcard(Flashcard otherFlashcard) {
        if (otherFlashcard == this) {
            return true;
        }

        return otherFlashcard != null
                && otherFlashcard.getQuestion().equals(getQuestion())
                && (otherFlashcard.getAnswer().equals(getAnswer()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Flashcard)) {
            return false;
        }

        Flashcard otherFlashcard = (Flashcard) other;
        return otherFlashcard.getQuestion().equals(getQuestion())
                && otherFlashcard.getAnswer().equals(getAnswer());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getQuestion())
                .append(" Answer: ")
                .append(getAnswer());
        return builder.toString();
    }

}
