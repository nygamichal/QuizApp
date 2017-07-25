package pl.nygamichal.quizapp;

import org.parceler.Parcel;

/**
 * Created by Admin on 24.07.2017.
 */
@Parcel
public class Answer {
    public String text;
    public boolean isCorrect;

    public Answer() {
    }

    public Answer(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
