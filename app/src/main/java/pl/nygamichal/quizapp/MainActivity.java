package pl.nygamichal.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //wprowadzenie ButterKnife
    @BindView(R.id.textViewQuestion) TextView textViewQuestion;
    @BindView(R.id.RadioGroup) RadioGroup radioGroup;
    Quiz quiz;//obiekt w klasie, a nie lokalnie
    //Question question;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getIntent().hasExtra("quiz"))
        {
            //odczytane jedynie z intentu
            quiz = Parcels.unwrap(getIntent().getParcelableExtra("quiz"));
        }
        else
        {
            //inicjalizacja i tworzenie od zera
            initQuiz();
        }

        setQuestion(quiz.questions.get(quiz.answersSoFar));
        //setQuestion(question);
    }

    private void initQuiz() {
        quiz = new Quiz();
        Question question = new Question(getString(R.string.question_one));
        question.answers.add(new Answer(getString(R.string.answer_one),false));
        question.answers.add(new Answer(getString(R.string.answer_two),false));
        question.answers.add(new Answer(getString(R.string.answer_three),false));
        question.answers.add(new Answer(getString(R.string.answer_four),true));
        quiz.questions.add(question);

        question = new Question(getString(R.string.question_two));
        question.answers.add(new Answer(getString(R.string.answer_oneone),true));
        question.answers.add(new Answer(getString(R.string.answer_onetwo),false));
        question.answers.add(new Answer(getString(R.string.answer_onethree),false));
        question.answers.add(new Answer(getString(R.string.answer_onefour),false));
        quiz.questions.add(question);
    }

    private void setQuestion(Question question) {
        //question = questionLocal; //dilitujesz, bo localnie pytania wybeirasz, a zmieniemay na dynamiczne
        textViewQuestion.setText(question.text);
        //chodzimy po elemenatach tablicy answers elementami answer
        for (Answer answer:question.answers)
        {
            //RadioButton to lista odp i tylko jedna moze zostać wybrana
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(answer.text);
            radioGroup.addView(radioButton);
        }
    }

    //sprawdza, ktora z 4 odp zostala wybrana przez usera
    private int getSelectedIndex(){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();//pobieramy ID wybranego przycisku
        View radioButton = radioGroup.findViewById(radioButtonID);//znajdujemy widok tego przycisku
        return radioGroup.indexOfChild(radioButton);//zwraca numer przycisku znajduajcego sie na liscie

    }

    //w momencie naciśnięcia przycisku NEXT sprawdzamy czy wybór usera był poprawny
    @OnClick(R.id.buttonNext) public void onClickNext() {
        if(getSelectedIndex()>=0)//sprawdzamy czy user wybral jakas odpowiedz
        {
            if (quiz.questions.get(quiz.answersSoFar).answers.get(getSelectedIndex()).isCorrect)
            {
                Toast.makeText(this,"IS CORRECT",Toast.LENGTH_SHORT).show();
                quiz.correctAnswers++;
            }
            else
            {
                Toast.makeText(this,"IS NOT CORRECT",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Select one!",Toast.LENGTH_LONG).show();
            return;
        }


        Intent intent;
        if (quiz.answersSoFar>=1)
        {
          intent = new Intent(this, HighScoreActivity.class);
        }
        else
        {
            intent = new Intent(this, MainActivity.class);
        }
        quiz.answersSoFar++;
        intent.putExtra("quiz", Parcels.wrap(quiz));
        startActivity(intent);
        this.finish();//aby user nie mógł cofnąć do poprzedniego pytania
    }
}
