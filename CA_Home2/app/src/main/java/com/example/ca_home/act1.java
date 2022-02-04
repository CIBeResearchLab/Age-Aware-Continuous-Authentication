package com.example.ca_home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
import java.util.Calendar;
import java.util.Date;
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
                            Intent intent = new Intent(act1.this,
                                    com.example.ca_home.act2.class);

                            startActivity(intent);
                        }

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
    private VelocityTracker mVelocityTracker = null;
    private int mActivePointerId;
    private int mActivePointerId2;
    private float initialX;
    private float initialY;
    private float moveX;
    private float moveY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getActionMasked(); //event type
        int actionIndex = event.getActionIndex(); //pointer (finger, mouse ....)
        int pointerId = event.getPointerId(actionIndex);

        String path = Environment.getExternalStorageDirectory() +"/";

        File file = new File(path, "ca_home_MainActivity.txt");


        switch (action) {

            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();
                mActivePointerId = event.getPointerId(0);
                float pressure = event.getPressure(mActivePointerId);

                Date currentTime = Calendar.getInstance().getTime();
                String xyDataDown = "Action was DOWN" + "\n" +
                        String.valueOf(currentTime) + "\n" +
                        "X value " + initialX + "\n" +
                        "Y value " + initialY + "\n" +
                        "Pressure "+ pressure + "\n" ;

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(xyDataDown.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("Action", "Action was DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                moveY = event.getY();

                currentTime = Calendar.getInstance().getTime();
                String xyDataMove = "Action was Move" + "\n" +
                        String.valueOf(currentTime) + "\n" +
                        "X value " + moveX + "\n" +
                        "Y value " + moveY + "\n" ;

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(xyDataMove.getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("Action", "Action was MOVE");

                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                float finalY = event.getY();

                currentTime = Calendar.getInstance().getTime();
                String xyDataUP = "Action was UP" + "\n" +
                        String.valueOf(currentTime) + "\n" +
                        "X value " + finalX + "\n" +
                        "Y value " + finalY + "\n" ;

                Log.d("Action", "Action was UP");
                Log.d("Initial X", String.valueOf(initialX));
                Log.d("final X", String.valueOf(finalX));

                if (initialX < finalX) {
                    //SwipeDir = "Left to Right swipe performed";
                    xyDataUP += "Left to Right swipe performed \n";
                    Log.d("Swipe Direction", "Left to Right swipe performed");
                }

                if (initialX > finalX) {
                    //SwipeDir = "Right to Left swipe performed";
                    xyDataUP += "Right to Left swipe performed \n";
                    Log.d("Swipe Direction", "Right to Left swipe performed");
                }

                if (initialY < finalY) {
                    //SwipeDir = "Up to Down swipe performed";
                    xyDataUP += "Up to Down swipe performed \n";
                    Log.d("Swipe Direction", "Up to Down swipe performed");
                }

                if (initialY > finalY) {
                    //SwipeDir = "Down to Up swipe performed";
                    xyDataUP += "Down to Up swipe performed \n";
                    Log.d("Swipe Direction", "Down to Up swipe performed");
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(xyDataUP.getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case MotionEvent.ACTION_CANCEL:
                //Action = "Action was CANCEL";
                Log.d("Action","Action was CANCEL");
                break;

            case MotionEvent.ACTION_OUTSIDE:
                //Action = "Movement occurred outside bounds of current screen element";
                Log.d("Action", "Movement occurred outside bounds of current screen element");
                break;
        }



        //calculates the velocity
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                if(mVelocityTracker == null) {
                    // Retrieve a new VelocityTracker object to watch the
                    // velocity of a motion.
                    mVelocityTracker = VelocityTracker.obtain();
                }
                else {
                    // Reset the velocity tracker back to its initial state.
                    mVelocityTracker.clear();
                }
                // Add a user's movement to the tracker.
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                Date currentTime = Calendar.getInstance().getTime();

                // When you want to determine the velocity, call
                // computeCurrentVelocity(). Then call getXVelocity()
                // and getYVelocity() to retrieve the velocity for each pointer ID.
                mVelocityTracker.computeCurrentVelocity(1000); // A value of 1 provides pixels per millisecond, 1000 provides pixels per second, etc.
                // Log velocity of pixels per second
                // Best practice to use VelocityTrackerCompat where possible.
                Log.d("", "X velocity: " + mVelocityTracker.getXVelocity(pointerId));
                Log.d("", "Y velocity: " + mVelocityTracker.getYVelocity(pointerId));

                String xyDataMove =
                        "X velocity " + mVelocityTracker.getXVelocity(pointerId) + "\n" +
                                "Y velocity " + mVelocityTracker.getYVelocity(pointerId) + "\n" ;

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(xyDataMove.getBytes());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // Return a VelocityTracker object back to be re-used by others.
                //mVelocityTracker.recycle(); //this is commented because it breaks it
                break;
        }
        //end of calculating the velocity


        Date currentTime = Calendar.getInstance().getTime();
        // Use the pointer ID to find the index of the active pointer
        // and fetch its position
        int pointerIndex = event.findPointerIndex(mActivePointerId);
        int pointerIndex2 = event.findPointerIndex(mActivePointerId2);
        // Get the pointer's current position
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);
        int xPos = -1;
        int xPos2 = -1;
        int yPos = -1;
        int yPos2 = -1;
        if (event.getPointerCount() > 1) {
            mActivePointerId2 = event.getPointerId(1);
            Log.d("debug", "Multitouch event");
            xPos = (int) event.getX(pointerIndex);
            xPos2 = (int) event.getX(pointerIndex2);
            yPos = (int) event.getY(pointerIndex);
            yPos2 = (int) event.getY(pointerIndex2);
            Log.d("Xpoint", String.valueOf(xPos));
            Log.d("Xpoint", String.valueOf(xPos2));
            Log.d("Ypoint", String.valueOf(yPos));
            Log.d("Ypoint", String.valueOf(yPos2));
            String xyMultiTouch =
                    "X value 1 " + xPos + "\n" +
                            "Y value 1 " + yPos + "\n" +
                            "X value 2 " + xPos2 + "\n" +
                            "Y value 2 " + yPos2 +  "\n" ;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(xyMultiTouch.getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Single touch event
            Log.d("debug", "Single touch event");
            xPos = (int) event.getX(pointerIndex);
            yPos = (int) event.getY(pointerIndex);
        }
        double fingerPressure = event.getPressure(actionIndex);
        //double fingerSize = event.getSize(pointerId);
        // Log.d("finger size", String.valueOf(fingerSize));
        Log.d("finger pressure", String.valueOf(fingerPressure));

        //Log.d("Screen Touched!", String.valueOf(event.getActionMasked()));
        return true;
    }

}