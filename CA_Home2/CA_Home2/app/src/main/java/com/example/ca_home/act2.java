package com.example.ca_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class act2 extends AppCompatActivity {

    EditText act2_input11;
    EditText act2_input12;

    EditText act2_input21;
    EditText act2_input22;

    EditText act2_input31;
    EditText act2_input32;

    EditText act2_input41;
    EditText act2_input42;

    EditText act2_input51;
    EditText act2_input52;

    Button act2_btn_submit;
    Button act2_btn_restart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);


        act2_input11 = findViewById(R.id.act2_input11);
        act2_input12 = findViewById(R.id.act2_input12);
        act2_input21 = findViewById(R.id.act2_input21);
        act2_input22 = findViewById(R.id.act2_input22);
        act2_input31 = findViewById(R.id.act2_input31);
        act2_input32 = findViewById(R.id.act2_input32);
        act2_input41 = findViewById(R.id.act2_input41);
        act2_input42 = findViewById(R.id.act2_input42);
        act2_input51 = findViewById(R.id.act2_input51);
        act2_input52 = findViewById(R.id.act2_input52);

        act2_btn_submit = findViewById(R.id.act2_btn_submit);

        act2_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(act2_input11.getText().toString().isEmpty() | act2_input12.getText().toString().isEmpty() | act2_input21.getText().toString().isEmpty() |
                        act2_input22.getText().toString().isEmpty() | act2_input31.getText().toString().isEmpty() |  act2_input32.getText().toString().isEmpty()
                |  act2_input41.getText().toString().isEmpty() |  act2_input42.getText().toString().isEmpty() |  act2_input51.getText().toString().isEmpty()
                |  act2_input52.getText().toString().isEmpty())

                {
                    Toast.makeText(act2.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    String path = Environment.getExternalStorageDirectory() +"/";

                    try {


                        File file = new File(path, "ca_home_act_2.txt");

                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write(act2_input11.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input12.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input21.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input22.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input31.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input32.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input41.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input42.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input51.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input52.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.close();




                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(act2.this,
                            com.example.ca_home.MainActivity.class);

                    finishAffinity();
                    System.exit(0);

                }
            }
        });


        act2_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                act2_input11.setText("");
                act2_input12.setText("");
                act2_input21.setText("");
                act2_input22.setText("");
                act2_input31.setText("");
                act2_input32.setText("");
                act2_input41.setText("");
                act2_input42.setText("");
                act2_input51.setText("");
                act2_input52.setText("");

            }
        });
    }
}