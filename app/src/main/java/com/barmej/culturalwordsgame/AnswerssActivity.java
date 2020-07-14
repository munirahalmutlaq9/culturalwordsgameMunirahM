package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerssActivity extends AppCompatActivity {

    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        mTextViewAnswer = findViewById(R.id.text_view_answer);

        String answer ,answer22;
        answer = getIntent().getStringExtra("answer_description");
        answer22 = getIntent().getStringExtra("answer2");
        if(answer != null && answer22 != null){
            mTextViewAnswer.setText(answer + ":" + answer22) ;
        }

    }

    public void onReturnClicked (View view){

        finish();
    }

}
