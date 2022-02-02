package com.example.testui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    CheckBox neutral_cb, happy_cb, fear_cb, sad_cb, disgust_cb, surprise_cb, angry_cb;
    ImageButton neutral_btn, happy_btn, fear_btn, sad_btn, disgust_btn, surprise_btn, angry_btn;
    Button next_btn;
    RadioButton other_rbn;
    EditText other_text;
    ArrayList<String> emotion, emotion_lst;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_layout = findViewById(R.id.q1_layout);
        emotion = new ArrayList<String>();

        other_text = (EditText) findViewById(R.id.other_text);
//        other_text.setFocusable(false);

//        other_rbn = (RadioButton) findViewById(R.id.other_rb);
//        other_rbn.setOnClickListener(new View.OnClickListener() {
//            @Override()
//            public void onClick(View view){
//                other_text.setFocusable(true);
//            }
//        });

//        Next Button
        next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQ2();
            }
        });

//        All checkboxes buttons
        neutral_cb = (CheckBox) findViewById(R.id.neutral_cb);
        happy_cb = (CheckBox) findViewById(R.id.happy_cb);
        sad_cb = (CheckBox) findViewById(R.id.sad_cb);
        fear_cb = (CheckBox) findViewById(R.id.fear_cb);
        disgust_cb = (CheckBox) findViewById(R.id.disgust_cb);
        surprise_cb = (CheckBox) findViewById(R.id.surprise_cb);
        angry_cb = (CheckBox) findViewById(R.id.angry_cb);

        emotion_lst = new ArrayList<String>();
        emotion_lst = (ArrayList<String>) getIntent().getSerializableExtra("emotion_lst");
        if (!(emotion_lst==null || emotion_lst.size()==0)) {
//        if (!emotion_lst.isEmpty()){
            if (emotion_lst.contains("neutral")){
                neutral_cb.setChecked(true);
            }
            if (emotion_lst.contains("happy")){
                happy_cb.setChecked(true);
            }
            if (emotion_lst.contains("fear")){
                fear_cb.setChecked(true);
            }
            if (emotion_lst.contains("angry")){
                angry_cb.setChecked(true);
            }
            if (emotion_lst.contains("disgust")){
                disgust_cb.setChecked(true);
            }
            if (emotion_lst.contains("surprise")){
                surprise_cb.setChecked(true);
            }
            if (emotion_lst.contains("sad")){
                sad_cb.setChecked(true);
            }

        }

//        All ImageButtons
        ImageButton neutral_btn = (ImageButton) findViewById(R.id.neutral_btn);
        neutral_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!neutral_cb.isChecked())
                    neutral_cb.setChecked(true);
                else
                    neutral_cb.setChecked(false);
            }
        });

        happy_btn = (ImageButton) findViewById(R.id.happy_btn);
        happy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!happy_cb.isChecked())
                    happy_cb.setChecked(true);
                else
                    happy_cb.setChecked(false);
            }
        });

        fear_btn = (ImageButton) findViewById(R.id.fear_btn);
        fear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fear_cb.isChecked())
                    fear_cb.setChecked(true);
                else
                    fear_cb.setChecked(false);
            }
        });

        sad_btn = (ImageButton) findViewById(R.id.sad_btn);
        sad_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sad_cb.isChecked())
                    sad_cb.setChecked(true);
                else
                    sad_cb.setChecked(false);
            }
        });

        disgust_btn = (ImageButton) findViewById(R.id.disgust_btn);
        disgust_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!disgust_cb.isChecked())
                    disgust_cb.setChecked(true);
                else
                    disgust_cb.setChecked(false);
            }
        });

        surprise_btn = (ImageButton) findViewById(R.id.surprise_btn);
        surprise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!surprise_cb.isChecked())
                    surprise_cb.setChecked(true);
                else
                    surprise_cb.setChecked(false);
            }
        });

        angry_btn = (ImageButton) findViewById(R.id.angry_btn);
        angry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!angry_cb.isChecked())
                    angry_cb.setChecked(true);
                else
                    angry_cb.setChecked(false);
            }
        });
    }

    public void goToQ2(){
        Intent intent = new Intent(this, MainActivity2.class);

        EditText other_text = (EditText) findViewById(R.id.other_text);
        String other_str = other_text.getText().toString();
        if (!other_str.matches("")) {
            emotion.add(other_str);
        }
        if (neutral_cb.isChecked()){
            emotion.add("neutral");
        }
        if (happy_cb.isChecked()) {
            emotion.add("happy");
        }
        if (fear_cb.isChecked()) {
            emotion.add("fear");
        }
        if (disgust_cb.isChecked()) {
            emotion.add("disgust");
        }
        if (angry_cb.isChecked()) {
            emotion.add("angry");
        }
        if (surprise_cb.isChecked()) {
            emotion.add("surprise");
        }
        if (sad_cb.isChecked()) {
            emotion.add("sad");
        }
        intent.putExtra("emotion_lst", emotion);
        startActivity(intent);
    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (isChecked){
//            if(buttonView.getId() == R.id.neutral_rb) {
//                happy_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.happy_rb) {
//                neutral_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.sad_rb) {
//                neutral_rb.setChecked(false);
//                happy_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.fear_rb) {
//                neutral_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                happy_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.angry_rb) {
//                neutral_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                happy_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.disgust_rb) {
//                neutral_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                happy_rb.setChecked(false);
//                surprise_rb.setChecked(false);
//            }
//            if(buttonView.getId() == R.id.surprise_rb) {
//                neutral_rb.setChecked(false);
//                sad_rb.setChecked(false);
//                fear_rb.setChecked(false);
//                angry_rb.setChecked(false);
//                disgust_rb.setChecked(false);
//                happy_rb.setChecked(false);
//            }
//
//        }
//    }
}