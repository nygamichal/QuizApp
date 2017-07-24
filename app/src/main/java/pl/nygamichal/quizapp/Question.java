package pl.nygamichal.quizapp;

import java.util.ArrayList;

/**
 * Created by Admin on 24.07.2017.
 */

public class Question {
    public String text;
    public ArrayList<Answer> answers = new ArrayList<>();

    public Question(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
    }
}
