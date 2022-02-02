package com.example.testui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xw.repo.BubbleSeekBar;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button prev_btn;
    BubbleSeekBar arousal_sb;
    RadioButton better_rb, worse_rb, same_rb;
    Button submit_btn;
    TextView exit_text;

//    ArrayList<String> user_data = new ArrayList<String>();

    ArrayList<String> emotion;
    float sb_val;
    String valence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        exit_text = (TextView) findViewById(R.id.exit_text);
        exit_text.setVisibility(View.GONE);

        emotion = new ArrayList<String>();
        emotion = (ArrayList<String>) getIntent().getSerializableExtra("emotion_lst");

        Log.d("emotions", emotion.toString());

        better_rb = (RadioButton) findViewById(R.id.better_rb);
        better_rb.setOnCheckedChangeListener(this);
        worse_rb = (RadioButton) findViewById(R.id.worse_rb);
        worse_rb.setOnCheckedChangeListener(this);
        same_rb = (RadioButton) findViewById(R.id.same_rb);
        same_rb.setOnCheckedChangeListener(this);

        prev_btn = (Button) findViewById(R.id.prev_btn);
        prev_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToQ1();
            }
        });

        arousal_sb = (BubbleSeekBar) findViewById(R.id.arousal_sb);
        sb_val = arousal_sb.getProgressFloat();
//        arousal_sb.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
//            @Override
//            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                Log.d("arousal", String.valueOf(sb_val));
//            }
//
//            @Override
//            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
//
//            }
//
//            @Override
//            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//
//            }
//        });


        submit_btn = (Button) findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb_val = arousal_sb.getProgressFloat();

                if (better_rb.isChecked())
                    valence = "positive";
                else if (worse_rb.isChecked())
                    valence = "negative";
                else if (same_rb.isChecked())
                    valence = "same";

                emotion.add(String.valueOf(sb_val));
                emotion.add(valence);
                try {
                    saveToCSV(emotion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exit_text.setVisibility(View.VISIBLE);
                Log.d("arousal", emotion.toString());
            }
        });


    }

    public void saveToCSV(ArrayList<String> emotions) throws IOException {
        final String FNAME = "Physiology";
        File main_dir;
        Log.d("directory", String.valueOf(Environment.DIRECTORY_DOWNLOADS));
//        main_dir = new File(Environment.getExternalStorageDirectory(), FNAME);
        main_dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), FNAME);
        if (!main_dir.exists()) {
            main_dir.mkdir();
            Log.d("created at", String.valueOf(main_dir));

        }
        Log.d("storage", String.valueOf(main_dir));

        File affect_fl;
        FileWriter affect_fw;
        BufferedWriter affect_bw;
        PrintWriter affect_pw;

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSSS").
                format(new Timestamp(System.currentTimeMillis()));
        affect_fl = new File(main_dir, "user_data_" + timeStamp + ".txt");
        affect_fw = new FileWriter(affect_fl, true);
        affect_bw = new BufferedWriter(affect_fw);
        affect_pw = new PrintWriter(affect_bw);

        emotions.add("\n");
        for (String record: emotions) {
            affect_pw.println(record);
        }
        affect_pw.close();

    }
    public void goToQ1(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("emotion_lst", emotion);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            if(buttonView.getId() == R.id.better_rb) {
                worse_rb.setChecked(false);
                same_rb.setChecked(false);
            }
            if(buttonView.getId() == R.id.worse_rb) {
                better_rb.setChecked(false);
                same_rb.setChecked(false);
            }
            if(buttonView.getId() == R.id.same_rb) {
                better_rb.setChecked(false);
                worse_rb.setChecked(false);
            }


        }
    }
}