package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class answerss_activity extends AppCompatActivity {

    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);


        mTextViewAnswer = findViewById(R.id.text_view_answer);

        String answer;
        answer = getIntent().getStringExtra("answer_description");
        if(answer != null){
            mTextViewAnswer.setText(answer);
        }}

    public void onReturnClicked (View view){

        finish();
    }

}
