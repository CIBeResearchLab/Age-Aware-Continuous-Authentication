package com.example.chatapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatActivity extends AppCompatActivity implements TextWatcher {


    private String name;
    private WebSocket webSocket;
    private String SERVER_PATH = "ws://10.224.143.159:3000";  //10.224.142.53:3000" //10.224.136.109
    private EditText messageEdit;
    private View sendBtn, pickImgBtn;
    private RecyclerView recyclerView;
    private int IMAGE_REQUEST_ID = 1;
    private MessageAdapter messageAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        name = getIntent().getStringExtra("name");
        initiateSocketConnection();

    }

    private void initiateSocketConnection() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(SERVER_PATH).build();
        webSocket = client.newWebSocket(request, new SocketListener());

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String string = s.toString().trim();

        if (string.isEmpty()) {
            resetMessageEdit();
        } else {

            sendBtn.setVisibility(View.VISIBLE);
            pickImgBtn.setVisibility(View.INVISIBLE);
        }

    }

    private void resetMessageEdit() {

        messageEdit.removeTextChangedListener(this);

        messageEdit.setText("");
        sendBtn.setVisibility(View.VISIBLE);
        pickImgBtn.setVisibility(View.INVISIBLE);

        messageEdit.addTextChangedListener(this);

    }

    private class SocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);

            runOnUiThread(() -> {
                Toast.makeText(ChatActivity.this,
                        "Socket Connection Successful!",
                        Toast.LENGTH_SHORT).show();

                initializeView();
            });

        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);

            runOnUiThread(() -> {

                try {
                    JSONObject jsonObject = new JSONObject(text);
                    jsonObject.put("isSent", false);

                    messageAdapter.addItem(jsonObject);

                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

                    String path = Environment.getExternalStorageDirectory() +"/";
                    File file = new File(path, "chat_Communication.txt");
                    Date currentTime = Calendar.getInstance().getTime();
                    String OutputReceived = jsonObject.toString() +String.valueOf(currentTime) + "\n";
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file, true);
                        fileOutputStream.write(OutputReceived.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            });

        }
    }

    private void initializeView() {

        messageEdit = findViewById(R.id.messageEdit);
        sendBtn = findViewById(R.id.sendBtn);
        pickImgBtn = findViewById(R.id.pickImgBtn);

        recyclerView = findViewById(R.id.recyclerView);

        messageAdapter = new MessageAdapter(getLayoutInflater());
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        messageEdit.addTextChangedListener(this);

        sendBtn.setOnClickListener(v -> {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name);
                jsonObject.put("message", messageEdit.getText().toString());

                webSocket.send(jsonObject.toString());

                jsonObject.put("isSent", true);
                messageAdapter.addItem(jsonObject);

                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

                resetMessageEdit();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String path = Environment.getExternalStorageDirectory() +"/";
            File file = new File(path, "chat_Communication.txt");
            Date currentTime = Calendar.getInstance().getTime();
            String OutputReceived = jsonObject.toString() +String.valueOf(currentTime)+ "\n";
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(OutputReceived.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pickImgBtn.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

            startActivityForResult(Intent.createChooser(intent, "Pick image"),
                    IMAGE_REQUEST_ID);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST_ID && resultCode == RESULT_OK) {

            try {
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);

                sendImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    private void sendImage(Bitmap image) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);

        String base64String = Base64.encodeToString(outputStream.toByteArray(),
                Base64.DEFAULT);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", name);
            jsonObject.put("image", base64String);

            webSocket.send(jsonObject.toString());

            jsonObject.put("isSent", true);

            messageAdapter.addItem(jsonObject);

            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        //return super.onTouchEvent(event);
        int action = event.getActionMasked(); //event type
        int actionIndex = event.getActionIndex(); //pointer (finger, mouse ....)
        int pointerId = event.getPointerId(actionIndex);

        String path = Environment.getExternalStorageDirectory() +"/";
        Log.d("Test", path);



        File file = new File(path, "chat_MainActivity.txt");


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
                    /* fileOutputStream.write(String.valueOf(initialX).getBytes());
                    fileOutputStream.write(descriptionOfData.getBytes());
                    fileOutputStream.write(String.valueOf(initialY).getBytes());
                    descriptionOfData = "Y value \n";
                    fileOutputStream.write(descriptionOfData.getBytes());
                    fileOutputStream.write(String.valueOf(currentTime).getBytes()); */

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Action = "Action was DOWN";
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

                //Action = "Action was UP";
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