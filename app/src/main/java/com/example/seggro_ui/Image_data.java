package com.example.seggro_ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Image_data extends AppCompatActivity  {

    Uri image_uri;

    int fragmentNumber = 0;
    ViewPager viewPager;
    String imagepath;
    String weatherReport;
    String weatherTemp;
    String cropName;
    private StorageReference mStorageRef;
    File imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_data2);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        PagerAdapter pagerAdapter = new PagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager1);
        viewPager.setAdapter(pagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);

//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        Bundle bundle = getIntent().getExtras();
        imagepath = bundle.getString("imagepath");
         weatherReport = bundle.getString("weatherReport");
         weatherTemp = bundle.getString("weatherTemp");
         cropName = bundle.getString("cropName");

        NoteRepository noteRepository = new NoteRepository(getApplicationContext());

        noteRepository.insertTask(cropName, weatherReport, weatherTemp,imagepath);











        Intent intent = getIntent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");


//           /storage/emulated/0/Pictures/1573209644433.jpg

         imgFile = new File(imagepath);

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.usersPlantImage);

            myImage.setImageBitmap(myBitmap);

        }


//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragmentcontainer, new SecondQuestionFragment());
//        fragmentTransaction.commit();


//        Bundle extras = getIntent().getExtras();
//        byte[] byteArray = extras.getByteArray("picture");
//
//        Log.d("byteArrayimg", String.valueOf(byteArray));
//
//        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//        ImageView imagetoshow = (ImageView)findViewById(R.id.imagetoshow);

//        imagetoshow.setImageBitmap(bitmap);

//        Intent intent = getIntent();
//      String tempimgstr =  intent.getStringExtra("image_uri");


//        Bitmap myBitmap = BitmapFactory.decodeStream( tempimgstr );

//                ImageView imagetoshow = (ImageView)findViewById(R.id.imagetoshow);
//
//                imagetoshow.setImageBitmap(myBitmap);


//        image_uri.parse(tempimgstr.getString());


        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }


        });


        Button button = (Button) findViewById(R.id.gotoNextFragment);

        button.setText("Next");


    }


    public void gotoNextFragment(View view) throws IOException {
        fragmentNumber++;

        if (fragmentNumber < 4) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager1);

            viewPager.setCurrentItem(fragmentNumber, false);
        } else {
            fragmentNumber = 0;
        }

        if (fragmentNumber == 2) {
            Button button = (Button) findViewById(R.id.gotoNextFragment);

            button.setText("Submit");


        }
        if (fragmentNumber == 3) {

//            Intent intent;
//            intent = new Intent(Image_data.this, ImageResult.class);
//            intent.putExtra("imagepath", imagepath.toString());
//            startActivity(intent);

            ///  connecting to firebase storage

            mStorageRef = FirebaseStorage.getInstance().getReference(cropName);

            Uri file = Uri.fromFile(new File(imagepath));
//        StorageReference riversRef = storageRef.child("images/rivers.jpg");

        mStorageRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                        firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String url = uri.toString();
                                Log.e("TAG:", "the url is: " + url);

                                Intent intent;
                                intent = new Intent(Image_data.this, ImageResult.class);
                                intent.putExtra("imagepath", imagepath);
                                intent.putExtra("imageUrl", url);
                                startActivity(intent);

//                                https://us-central1-seggro-test-260412.cloudfunctions.net/function-1?image%3C%3E

//                                try {
//
//                                    URL url123 = new URL("https://us-central1-seggro-test-260412.cloudfunctions.net/function-1?image="+url);
//                                    HttpURLConnection connection = (HttpURLConnection) url123.openConnection();
//                                } catch (MalformedURLException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }


//                                String ref = cropName.getName();
//                                Log.e("TAG:", "the ref is: " + ref);
                            }
                        });

                        // Get a URL to the uploaded content
                        Log.i("result", String.valueOf(taskSnapshot));
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Log.i("Firebase Error", String.valueOf(exception));
                        // Handle unsuccessful uploads
                        // ...
                    }
                });




        }


    }

    public void gotoPrevFragment(View view) {



//        switch (fragmentNumber){
//            case 0:

        if(fragmentNumber>0 && fragmentNumber<4){
            fragmentNumber--;
            ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager1);

            viewPager.setCurrentItem(fragmentNumber, false);

            Button button = (Button) findViewById(R.id.gotoNextFragment);

            button.setText("Next");
        }

//        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        //        private  final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
        private final Context mContext;

        public PagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {


            switch (position) {
                case 0:
                    FirstQuestionFragment photoquestion1 = new FirstQuestionFragment();
                    return photoquestion1;

                case 1:
                    SecondQuestionFragment photoquestion2 = new SecondQuestionFragment();
                    return photoquestion2;

                case 2:
                    ThirdQuestionFragment photoquestion3 = new ThirdQuestionFragment();
                    return photoquestion3;

                default:
                    return null;
            }


        }

        @Override
        public int getCount() {
            return 3;
        }


//       @Override
//       public CharSequence getPageTitle(int position) {
//           return mContext.getResources().getString(TAB_TITLES[position]);
//       }


    }


//    public void gotoNextFragment(View view,int number) {
//
//
//        ViewPager viewPager = (ViewPager)findViewById(R.id.view_pager1);
//
//        viewPager.setCurrentItem(2,false);
//
//
////        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////        fragmentTransaction.replace(R.id.view_pager1, new ThirdQuestionFragment());
////        fragmentTransaction.commit();
////        ThirdQuestionFragment f1 = new ThirdQuestionFragment();
////        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
////        ft.replace(R.id.fragmentcontainer, f1); // f1_container is your FrameLayout container
////        ft.setTransition(ft.TRANSIT_FRAGMENT_OPEN);
////        ft.addToBackStack(null);
////        ft.commit();
//
//    }

//    public void GetText() throws UnsupportedEncodingException {
//        // Get user defined values
//        String Name = "fgf";
////        Email   = email.getText().toString();
////        Login   = login.getText().toString();
////        Pass   = pass.getText().toString();
//
//        // Create data variable for sent values to server
//        String data = "";
////        String data = URLEncoder.encode("name", "UTF-8")
////                + "=" + URLEncoder.encode(Name, "UTF-8");
//
////        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
////                + URLEncoder.encode(Email, "UTF-8");
////
////        data += "&" + URLEncoder.encode("user", "UTF-8")
////                + "=" + URLEncoder.encode(Login, "UTF-8");
////
////        data += "&" + URLEncoder.encode("pass", "UTF-8")
////                + "=" + URLEncoder.encode(Pass, "UTF-8");
//
//        String text = "";
//        BufferedReader reader = null;
//
//        // Send data
//        try {
//
//            // Defined URL  where to send data
//            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
//            Log.i("url", "url");
//
//            // Send POST data request
//
//            URLConnection conn = url.openConnection();
//
//            conn.setDoOutput(true);
//            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//            wr.write( data );
//            wr.flush();
//
//            Log.i("outstream", "os");
//
//            // Get the server response
//
//            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//
//            // Read Server Response
//            while ((line = reader.readLine()) != null) {
//                Log.i("line", "dfdfdfdf");
//                // Append server response in string
//                sb.append(line + "\n");
//            }
////            Log.i("dataaa",sb.toString());
//
//
//            text = sb.toString();
//            Log.i("output", text);
//
//        } catch (Exception ex) {
//
//        } finally {
//            try {
//                Log.i("hg1", "Method finally");
//
//                reader.close();
//            } catch (Exception ex) {
//            }
//        }
//
//        Log.i("hg2", "Method execu");
//
//        // Show response on activity
////        content.setText( text  );
//
//
//    }
//}




//    public void getData() {
//        OkHttpClient client = new OkHttpClient();
//
//        String url = "https://jsonplaceholder.typicode.com/todos/1";
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if (response.isSuccessful()) {
//
//                    final String myResponse = response.body().string();
//
//                    Log.i("dataaa",myResponse);
//
////                    MainActivity.this.runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            mTextViewResult.setText(myResponse);
////                        }
////                    });
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                e.printStackTrace();
//
//                Log.i("errorrr",e.toString());
//            }
////
////            @Override
////            public void onFailure(Call call, IOException e) {
////                e.printStackTrace();
////            }
//
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.isSuccessful()) {
////                    final String myResponse = response.body().string();
////
////                    MainActivity.this.runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            mTextViewResult.setText(myResponse);
////                        }
////                    });
////                }
////            }
//        });
//    }

//    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
//
//    OkHttpClient client = new OkHttpClient();
//
//    String post(String url, String json) throws IOException {
//        RequestBody body = RequestBody.create(json, JSON);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return response.body().string();
//        }
//        catch (Exception e) {
//            Log.i("erprprpr",e.toString());
//            return e.toString();
//        }
//
//    }
//
//
//    String bowlingJson(String player1, String player2) {
//        return "{'winCondition':'HIGH_SCORE',"
//                + "'name':'Bowling',"
//                + "'round':4,"
//                + "'lastSaved':1367702411696,"
//                + "'dateStarted':1367702378785,"
//                + "'players':["
//                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
//                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
//                + "]}";
//    }



//    @Override
//    public void onBackPressed() {
//        Log.i("okkk clicked","Gotit");
//        // your code.
//    }




  @Override
    public void onPause() {
        super.onPause();
        //Pause the thread
        Log.i("lifecycle","onPauseCalled");
//        this.androidDisplay.androidGameThread.paused = true;
        //Call the method
//        this.activityPaused();
    }

    /* Called when the activity is resumed */
    @Override
    public void onResume() {
        super.onResume();
        Log.i("lifecycle","onResumeCalled");


        Button button = (Button) findViewById(R.id.gotoNextFragment);

        button.setText("Next");



        //Resume the thread
//        this.androidDisplay.androidGameThread.paused = false;
        //Call the method
//        this.activityResumed();
    }

    /* Called when the activity is stopped */
    @Override
    public void onStop() {
        super.onStop();
        Log.i("lifecycle","onStopCalled");
        //Call the method
//        this.activityStopped();
    }

    /* Called when the activity is restarted */
    public void onRestart() {
        super.onRestart();
        Log.i("lifecycle","onRestartalled");
        //Call the method
//        this.activityRestarted();
    }

    /* Called when the activity is destroyed */
    public void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle","onDestroyCalled");
        //Call the method
//        this.activityDestroy();
        //Destroy
        this.finish();
    }
}








