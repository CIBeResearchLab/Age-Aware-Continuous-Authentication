package com.example.ca_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class act1 extends AppCompatActivity {

    TextView txt_pass;
    Button btn_nextPass;
    EditText txt_input1;
    EditText txt_input2;
    EditText txt_input3;
    EditText txt_input4;
    EditText txt_input5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        txt_pass = findViewById(R.id.txt_pass);
        btn_nextPass = findViewById(R.id.btn_nextPass);

        txt_input1 = findViewById(R.id.txt_input1);
        txt_input2 = findViewById(R.id.txt_input2);
        txt_input3 = findViewById(R.id.txt_input3);
        txt_input4 = findViewById(R.id.txt_input4);
        txt_input5 = findViewById(R.id.txt_input5);

        int count = getIntent().getIntExtra("count", 0);



        List<String> pass_list = new ArrayList<String>();

        pass_list.add("GmxPV3L");
        pass_list.add("Nv5PHS!8kP8");
        pass_list.add("jxK&5sDpwfE+U");

        txt_pass.setText("GmxPV3L");

        btn_nextPass.setOnClickListener(new View.OnClickListener() {
                    int count = 0;

            @Override
            public void onClick(View v) {


                if(txt_input1.getText().toString().isEmpty() | txt_input2.getText().toString().isEmpty() | txt_input3.getText().toString().isEmpty() |
                        txt_input5.getText().toString().isEmpty() | txt_input4.getText().toString().isEmpty())

                {
                    Toast.makeText(act1.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();

                }

                else
                {

                    String cur_pass = pass_list.get(this.count);


                    if(!txt_input1.getText().toString().equals(cur_pass) | !txt_input2.getText().toString().equals(cur_pass)|! txt_input3.getText().toString().equals(cur_pass) |
                            !txt_input5.getText().toString().equals(cur_pass) | !txt_input4.getText().toString().equals(cur_pass))
                    {
                        Toast.makeText(act1.this, "Please make sure that all the passwords match", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {


                        String path = Environment.getExternalStorageDirectory() +"/";

                        try {


                            File file = new File(path, "ca_home_act_1.txt");
                            Log.d("PATH", String.valueOf(path));

                            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                            fileOutputStream.write(txt_input1.getText().toString().getBytes());
                            fileOutputStream.write("\n".getBytes()[0]);
                            fileOutputStream.write(txt_input2.getText().toString().getBytes());
                            fileOutputStream.write("\n".getBytes()[0]);
                            fileOutputStream.write(txt_input3.getText().toString().getBytes());
                            fileOutputStream.write("\n".getBytes()[0]);
                            fileOutputStream.write(txt_input4.getText().toString().getBytes());
                            fileOutputStream.write("\n".getBytes()[0]);
                            fileOutputStream.write(txt_input5.getText().toString().getBytes());
                            fileOutputStream.write("\n".getBytes()[0]);
                            fileOutputStream.close();




                        }
                        catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        count += 1;

                        if(count == 3)
                        {
                            Intent intent = new Intent(act1.this, com.example.ca_home.act2.class);

                            startActivity(intent);
                        }

                        Log.d("INFO: ", "THIS.COUNT: " + String.valueOf(this.count));
                        Log.d("INFO2: ", "COUNT: " + String.valueOf(count));

                        txt_pass.setText(pass_list.get(this.count));


                        if(count == 2)

                            btn_nextPass.setText("Submit");

                    }


                    // clear all text fields
                    txt_input1.setText("");
                    txt_input2.setText("");
                    txt_input3.setText("");
                    txt_input4.setText("");
                    txt_input5.setText("");




                }
            }
        });


    }
}