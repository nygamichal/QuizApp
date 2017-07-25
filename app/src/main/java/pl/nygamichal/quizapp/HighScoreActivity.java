package pl.nygamichal.quizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

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
        SharedPreferences sharedPreferences = getSharedPreferences("pl.nygamichal.quizapp", MODE_PRIVATE);
        if (getIntent().hasExtra("quiz"))
        {
            Quiz quiz = Parcels.unwrap(getIntent().getParcelableExtra("quiz"));
            if (quiz != null)
            {
                textViewResult.setText(Integer.toString(quiz.correctAnswers));
                if (quiz.correctAnswers > sharedPreferences.getInt("highscore",0))
                {
                    Toast.makeText(this,"Hey! New High score!",Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putInt("highscore",quiz.correctAnswers).apply();//commit LUB apply
                    //commit - jest natychmiastowy, wywoalnie kolejnej lini kodu czeka, az commit sie skonczy,
                    //apply - jest asynchroniczna i nie czeka, aż zapisze sie do sharedPreferences lecz od razu pokazuje wynik
                }
            }
        }
    }
}
