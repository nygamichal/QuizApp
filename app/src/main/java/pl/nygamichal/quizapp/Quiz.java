package pl.nygamichal.quizapp;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by Admin on 24.07.2017.
 */
@Parcel
public class Quiz {
    public ArrayList<Question> questions = new ArrayList<>();
    public int allQuestions;
    public int correctAnswers;
}
