package com.example.seggro_ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class Chat extends AppCompatActivity {

   EditText mEdit ;

    LinearLayout myRoot;

    String imagepath;

   JSONObject json = new JSONObject();

    TextView plantname;
    TextView disease;
    TextView diseaseName;
    TextView solution;
    TextView pesticides;
    ScrollView scroll;
    EditText editText;
//    private Object UpdatePreviousChat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        editText = (EditText)findViewById(R.id.userinput);
        scroll = (ScrollView)findViewById(R.id.chatscroller);
        myRoot = (LinearLayout) findViewById(R.id.addData);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("onFOcused","okk");

                    final int val =  myRoot.getBottom()+ 500;
                    Log.i("onFOcused", String.valueOf(val));
//
                    scroll.post(new Runnable() {
                public void run() {
                    scroll.smoothScrollTo(0, val+500);
//                    scroll.fullScroll(View.FOCUS_DOWN);
                }
            });
////
//                    Toast.makeText(getApplicationContext(), "Got the focus", Toast.LENGTH_LONG).show();

                } else {
//                    Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });




        mEdit   = (EditText)findViewById(R.id.userinput);


        Bundle bundle = getIntent().getExtras();



//
        if(bundle.containsKey("imagepath")) {
//            Log
            imagepath = bundle.getString("imagepath");
            if(imagepath!=null) {

                File imgFile = new  File(imagepath);

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    ImageView myImage = (ImageView) findViewById(R.id.userplantimg);

                    myImage.setImageBitmap(myBitmap);

                }
            }

        }else   if(bundle.containsKey("imageBitmap")) {
            Log.i("bitmap-gotit ", "okkkk");
//
            byte[] byteArray = bundle.getByteArray("imageBitmap");
            Log.i("bytearrayyyy", String.valueOf(byteArray));

            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ImageView image = (ImageView) findViewById(R.id.userplantimg);

            image.setImageBitmap(bmp);


        }


        try {
            json.put("plant-namae","Brinjal");
            json.put("id","126253545");
            json.put("Disease-Name","xypz");
            json.put("Disease","yes");
            json.put("Solution","your plant need a bath");
            json.put("Pesticide","Plant needs N20");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.i("json-data", json.toString());

        //parsing json




        plantname = (TextView)findViewById(R.id.plantName);
        disease = (TextView)findViewById(R.id.disease);
        diseaseName = (TextView)findViewById(R.id.diseaseName);
        solution = (TextView)findViewById(R.id.solution);
        pesticides = (TextView)findViewById(R.id.pesticides);


//        plantname.setText(json.get());
        try {

            plantname.setText (json.getString("plant-namae"));

            disease.setText (json.getString("Disease"));
            diseaseName.setText (json.getString("Disease-Name"));
            solution.setText (json.getString("Solution"));
            pesticides.setText (json.getString("Pesticide"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


//         myRoot = (LinearLayout) findViewById(R.id.addData);
////        LinearLayout a = new LinearLayout(this);
//        ImageView image = new ImageView(this);
//        image.setOrientation(LinearLayout.HORIZONTAL);
//        a.addView(view1);
//        a.addView(view2);
//        a.addView(view3);
//        myRoot.addView(a);

        UpdatePreviousChat  updatePreviousChat = new UpdatePreviousChat();
        updatePreviousChat.isVisited = true;

    }


    public void sendMessage(View view) {

        scroll = (ScrollView)findViewById(R.id.chatscroller);

        String userIput =  mEdit.getText().toString();

        Log.i("sdfsdfsfdsf",userIput);

//        LinearLayout a = new LinearLayout(this);
        TextView a = new TextView(this);
        a.setText(userIput);
        a.setTextSize(16);
        a.setPadding(20,20,20,20);
        a.setGravity(Gravity.RIGHT );
        a.setBackground(ContextCompat.getDrawable(this, R.drawable.userchattext));
//        a.margin


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 20, 40, 20);

//         int scrollAmount = a.getLayout().getLineTop(a.getLineCount()) - a.getHeight();
        // if there is no need to scroll, scrollAmount will be <=0

        params.gravity =  Gravity.RIGHT;
//        params.gravity = Gravity.BOTTOM;

        a.setLayoutParams(params);
//        a.setGravity(Gravity.BOTTOM |Gravity.RIGHT);

        if(!userIput.isEmpty())  {
            myRoot.addView(a);

//            a.scrollTo(0, scrollAmount);
//            scroll.smoothScrollTo(0, 200);
//            scroll.FullScroll (FocusSearchDirection.Down);

//            messageView.append(blabla);
            scroll.post(new Runnable() {
                public void run() {
                    scroll.smoothScrollTo(0, myRoot.getBottom());
                }
            });
////            if (scrollAmount > 0)
////                a.scrollTo(0, scrollAmount);
////            else
////                a.scrollTo(0, 0);
        }
        mEdit.setText("");
        }






    public void setCropTabularData () {


    }









//        android:background="@drawable/chattext"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:textSize="16dp"
//        android:layout_marginTop="10dp"
//        android:textColor="#fff"
//        android:padding="10dp"






    }

