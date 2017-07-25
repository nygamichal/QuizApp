package pl.nygamichal.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighScoreActivity extends AppCompatActivity {
    @BindView(R.id.textViewResult)
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ButterKnife.bind(this);
        if (getIntent().hasExtra("quiz"))
        {
            Quiz quiz = Parcels.unwrap(getIntent().getParcelableExtra("quiz"));
            if (quiz != null)
            {
                textViewResult.setText(Integer.toString(quiz.correctAnswers));
            }
        }
    }
}
