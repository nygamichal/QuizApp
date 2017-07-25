package pl.nygamichal.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //wprowadzenie ButterKnife
    //@BindView(R.id.RadioGroup) RadioGroup radioGroup;
    @BindView(R.id.textViewQuestion) TextView textViewQuestion;
    @BindView(R.id.RadioGroup) RadioGroup radioGroup;
    Quiz quiz;//obiekt w klasie a nie lokalnie
    Question question;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        quiz = new Quiz();
        Question question = new Question(getString(R.string.question_one));
        question.answers.add(new Answer(getString(R.string.answer_one),false));
        question.answers.add(new Answer(getString(R.string.answer_two),false));
        question.answers.add(new Answer(getString(R.string.answer_three),false));
        question.answers.add(new Answer(getString(R.string.answer_four),true));

        quiz.questions.add(question);


        setQuestion(question);
    }

    private void setQuestion(Question questionLocal) {
        question = questionLocal;
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
        if (question.answers.get(getSelectedIndex()).isCorrect)
        {
            Toast.makeText(this,"IS CORRECT",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"IS NOT CORRECT",Toast.LENGTH_SHORT).show();
        }
    }
}
