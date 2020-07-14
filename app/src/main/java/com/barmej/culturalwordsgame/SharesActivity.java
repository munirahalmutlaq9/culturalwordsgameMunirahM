package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;



public class SharesActivity extends AppCompatActivity {
    private String mQuestionText;
    public EditText mEditTextShareTiltle;
    public ImageView imageViewShare;
        public int idImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mEditTextShareTiltle = findViewById(R.id.edit_text_share_title);
        mQuestionText= getIntent().getStringExtra("question_text_extra");

       imageViewShare = findViewById(R.id.image_view_question);
       idImage= getIntent().getIntExtra("question_text_extra",0);
        Drawable giftDrawble = ContextCompat.getDrawable(this, idImage);
         imageViewShare.setImageDrawable(giftDrawble);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);

        String mQuestionTitle = sharedPreferences.getString("share_title", "");
        mEditTextShareTiltle.setText(mQuestionTitle);



    }
    public void onShareQuestionClicked(View view)
    {
        String mQuestionTitle = mEditTextShareTiltle.getText().toString();
        int mIdImage = idImage;
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,mQuestionTitle+ "\n" + mQuestionText );
        /*shareIntent.putExtra(Intent.EXTRA_TEXT,mIdImage+ "\n"  + idImage);*/
        shareIntent.setType("text/plain");

        startActivity(shareIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", mQuestionTitle);

        editor.apply();

    }}
