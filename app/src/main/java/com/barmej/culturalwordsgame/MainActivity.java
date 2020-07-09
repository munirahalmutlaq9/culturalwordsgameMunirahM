package com.barmej.culturalwordsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView culturalImageView;
    TextView text;
    int culturalwordsImage [] = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5,R.drawable.icon_6,
    R.drawable.icon_7,R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_10,R.drawable.icon_11,R.drawable.icon_12,R.drawable.icon_13};
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    int mCurrentIndix =0;

    private String[] answers;
    private String[] answer_description;
    /*describ array and the element in string */

    private String answers_current, answer_description_Current ;
    private int culturalwordsImageCurrent;
    /*describe number of indix*/

    private Random mRandom;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences(constans.APP_PREF, MODE_PRIVATE);
        String applang = sharedPreferences.getString(constans.APP_LANG, "");
        if (!applang.equals(""))
            LocaleHelper.setLocale(this, applang);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        ImageView culturalImageView = findViewById((R.id.image_view_question));
        Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[mCurrentIndix]);
         answers= getResources().getStringArray(R.array.answers);
        answer_description = getResources().getStringArray(R.array.answer_description);
        culturalImageView.setImageDrawable(giftDrawble);

        answers_current = answers[mCurrentIndix];
        answer_description_Current = answer_description[mCurrentIndix];


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_change_language) {
            showLanguageDailog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void showLanguageDailog (){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_Lang_text)
                .setItems(R.array.Language,new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogeInterface , int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;

                        }
                        saveLanguage (language);
                        LocaleHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                }).create();
        alertDialog.show();
    }

    public void saveLanguage (String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang",lang);
        editor.apply();
    }




/*   here if i wante to write start and another state of play life app*/



protected void onSaveInstanceState (Bundle outStates)

         {
            super.onSaveInstanceState(outStates);
            outStates.putInt("BUNDLE_CURRENT_INDEX",mCurrentIndix);
            Log.i(TAG,"onSaveInstanceState");
        }

        protected void onRestoreInstanceState(Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            if( savedInstanceState != null )
            {
                mCurrentIndix= savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
                if(mCurrentIndix != -1){
                    Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[mCurrentIndix++]);
                    culturalImageView.setImageDrawable(giftDrawble);

                }
            }
            Log.i(TAG,"onRestoreInstanceState1");
        }



    public void onChangeImageClicked(View view) {

        culturalImageView = findViewById(R.id.image_view_question);
        if (mCurrentIndix < 13) {
            mCurrentIndix = mRandom.nextInt(13);
            Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[++mCurrentIndix]);
            answers_current = answers[mCurrentIndix];
            answer_description_Current = answer_description[mCurrentIndix];
            culturalImageView.setImageDrawable(giftDrawble);

        }
        }


        public void onAnswerClicked (View view){

                Intent intent;
                intent= new Intent(MainActivity.this,answerss_activity.class);
            intent.putExtra("answer_description",answers_current);
                intent.putExtra("answer_description",answer_description_Current);
                startActivity(intent);

        }

        public void onShareClicked (View view){
            Intent intent = new Intent(MainActivity.this, SharesActivity.class);
            intent.putExtra("question_text_extra",answer_description_Current);
            startActivity(intent);
        }


    }