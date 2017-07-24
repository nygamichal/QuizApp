package pl.nygamichal.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //wprowadzenie ButterKnife
    @BindView(R.id.RadioGroup) RadioGroup radioGroup;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Quiz quiz = new Quiz();
        Question question = new Question(getString(R.string.question_one));
        question.answers.add(new Answer(getString(R.string.answer_one),false));
        question.answers.add(new Answer(getString(R.string.answer_two),false));
        question.answers.add(new Answer(getString(R.string.answer_three),false));
        question.answers.add(new Answer(getString(R.string.answer_four),true));

        quiz.questions.add(question);

    }

    @OnClick(R.id.buttonNext)
    public void onClickNext() {

    }
}
