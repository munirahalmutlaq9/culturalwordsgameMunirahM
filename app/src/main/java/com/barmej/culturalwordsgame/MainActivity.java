package com.barmej.culturalwordsgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public  int culturalwordsImageCurrent;
    private ImageView culturalImageView;
    TextView text;
    static int[] culturalwordsImage = {R.drawable.icon_1,R.drawable.icon_2,R.drawable.icon_3,R.drawable.icon_4,R.drawable.icon_5,R.drawable.icon_6,
    R.drawable.icon_7,R.drawable.icon_8,R.drawable.icon_9,R.drawable.icon_10,R.drawable.icon_11,R.drawable.icon_12,R.drawable.icon_13};
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    int mCurrentIndix =0;
    private String[] answers;
    private String[] answerDescrib;
    /*describ array and the element in string */

    private String answersCurrent, answerDescriptionCurrent;

    /*describe number of indix*/

    private Random mRandom;




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences(constans.APP_PREF, MODE_PRIVATE);
        String applang = sharedPreferences.getString(constans.APP_LANG, "");
        if (!applang.equals(""))
            LocaleHelper.setLocale(this, applang);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        culturalImageView = findViewById((R.id.image_view_question));
        Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[mCurrentIndix]);
         answers= getResources().getStringArray(R.array.answers);
        answerDescrib = getResources().getStringArray(R.array.answer_description);
        culturalImageView.setImageDrawable(giftDrawble);

        answersCurrent = answers[mCurrentIndix];
        answerDescriptionCurrent = answerDescrib[mCurrentIndix];


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
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
                mCurrentIndix= savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
                if(mCurrentIndix != -1){
                    Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[mCurrentIndix]);
                    culturalImageView.setImageDrawable(giftDrawble);


            }
            Log.i(TAG,"onRestoreInstanceState1");
        }



    public void onChangeImageClicked(View view) {


        if (mCurrentIndix <12) {
           /* mCurrentIndix = mRandom.nextInt(12);*/
            Drawable giftDrawble = ContextCompat.getDrawable(this, culturalwordsImage[++mCurrentIndix]);
            answersCurrent = answers[mCurrentIndix];
            answerDescriptionCurrent = answerDescrib[mCurrentIndix];
            culturalImageView.setImageDrawable(giftDrawble);

        } else {
            Toast.makeText(this, "تم الانتهاء من عرض الصور للكلمات التراثية وسيتم البدء بعرضها منذ البداية مرة أخرى ", Toast.LENGTH_SHORT).show();
            mCurrentIndix =0;

        }
        }


        public void onAnswerClicked (View view){

                Intent intent;
                intent= new Intent(MainActivity.this, AnswerssActivity.class);
            intent.putExtra("answer_description", answersCurrent);
                intent.putExtra("answer2", answerDescriptionCurrent);
                startActivity(intent);

        }

        public void onShareClicked (View view){
            Intent intent = new Intent(MainActivity.this, SharesActivity.class);
            intent.putExtra("question_text_extra", answerDescriptionCurrent);
            intent.putExtra("question_text_extra",culturalwordsImage[mCurrentIndix]);
            startActivity(intent);
        }


    }