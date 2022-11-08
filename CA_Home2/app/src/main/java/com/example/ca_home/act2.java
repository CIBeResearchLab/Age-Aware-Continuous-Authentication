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
import java.util.Calendar;
import java.util.Date;

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
        act2_btn_restart = findViewById(R.id.act2_btn_restart);

        act2_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(act2_input11.getText().toString().isEmpty() | act2_input12.getText().toString().isEmpty() | act2_input21.getText().toString().isEmpty() |
                        act2_input22.getText().toString().isEmpty() | act2_input31.getText().toString().isEmpty() |  act2_input32.getText().toString().isEmpty()
                |  act2_input41.getText().toString().isEmpty() |  act2_input42.getText().toString().isEmpty() |  act2_input51.getText().toString().isEmpty()
                |  act2_input52.getText().toString().isEmpty())

                {
                    Toast.makeText(act2.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();

                }
                */


                //else
                {
                    String path = Environment.getExternalStorageDirectory() +"/";

                    try {



                        File file = new File(path, "ca_home_act_2.txt");

                        // FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        // fileOutputStream.write("personal_user".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input11.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("personal_pass".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input12.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("bank_user".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input21.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("bank_pass".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input22.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("utility_user".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input31.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("utility_pass".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input32.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("work_user".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input41.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("work_pass".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input42.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("school_user".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input51.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);

                        // fileOutputStream.write("school_pass".toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.write(act2_input52.getText().toString().getBytes());
                        // fileOutputStream.write("\n".getBytes()[0]);
                        // fileOutputStream.close();


                        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write("streaming_service_account_like_YouTube_user".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input11.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("streaming_service_account_like_YouTube_pass".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input12.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("gaming_user".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input21.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("gaming_pass".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input22.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("chat_account_to_communicate_with_friends_user".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input31.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("chat_account_to_communicate_with_friends_pass".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input32.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("bank_or_money_savings_user".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input41.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("bank_or_money_savings_pass".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input42.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("school_user".toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);
                        fileOutputStream.write(act2_input51.getText().toString().getBytes());
                        fileOutputStream.write("\n".getBytes()[0]);

                        fileOutputStream.write("school_pass".toString().getBytes());
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
                    Log.d("finished affinity","bvb");
                    System.exit(0);
                    Log.d("hbhy","exit");

                }
            }
        });




        act2_btn_restart.setOnClickListener(new View.OnClickListener() {
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
