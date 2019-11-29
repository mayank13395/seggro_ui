package com.example.seggro_ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageResult extends AppCompatActivity {


    String imagepath;

    JSONObject json = new JSONObject();

    TextView plantname;
    TextView disease;
    TextView diseaseName;
    TextView solution;
    TextView pesticides;
    String imageUrl;
    JSONObject data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_result);

        Bundle bundle = getIntent().getExtras();
        imagepath = bundle.getString("imagepath");
        imageUrl = bundle.getString("imageUrl");


        File imgFile = new  File(imagepath);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            ImageView myImage = (ImageView) findViewById(R.id.userplantimg);

            myImage.setImageBitmap(myBitmap);

        }

        getJSON(imageUrl);


//        try {


//        String imgURL = "https://gardenbetty-gardenbetty.netdna-ssl.com/wp-content/uploads/2013/08/2013-08-20-01-1080x1440.jpg";

//        String modelURL = "&model=<https://doc-0s-70-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/q5mkc2kngf3a4bb7sfddn5qjqif7u1ra/1575021600000/12818790738385926012/*/1VZ15s720QQUyVs1o1RNm-Cm1_iM_eOJY?e=download>";
//            us-central1-seggro-poc.cloudfunctions.net/getPrediction?image=<
//            String UrlGCP = "https://us-central1-seggro-poc.cloudfunctions.net/getPrediction?image=<"+imageUrl+">";
//            Log.i("UrlGCP:",UrlGCP);
//            URL url123 = new URL(UrlGCP);
//            HttpURLConnection c = (HttpURLConnection) url123.openConnection();
//            c.setRequestMethod("GET");
//            c.setRequestProperty("Content-length", "0");
//            c.setUseCaches(false);
//            c.setAllowUserInteraction(false);
//            c.setConnectTimeout(1000);
//            c.setReadTimeout(1000);
//            c.connect();
//        /
//                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
//                    Log.i("Output", String.valueOf(br));

//                    BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
//
//                    StringBuffer json = new StringBuffer(1024);
//                    String tmp = "";
//
//                    while((tmp = reader.readLine()) != null)
//                        json.append(tmp).append("\n");
//                    reader.close();
//
//                    data = new JSONObject(json.toString());
//                    Log.i("Output", String.valueOf(data));
//                    StringBuilder sb = new StringBuilder();
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                        sb.append(line+"\n");
//                    }
//                    br.close();

//            }



//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


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





//        Uri file = Uri.fromFile(new File(""));
//        StorageReference riversRef = storageRef.child("images/rivers.jpg");
//
//        riversRef.putFile(file)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Get a URL to the uploaded content
//                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                    }
//                });


    }

    public void gotoChatPage(View view) {
        Intent intent;
        intent = new Intent(ImageResult.this, Chat.class);
        intent.putExtra("imagepath", imagepath);
        startActivity(intent);
    }




    public void getJSON(final String imageUrl ) {

        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {

                 String  urltemp =  "https://us-central1-seggro-poc.cloudfunctions.net/getPrediction?image=%3Chttps://gardenbetty-gardenbetty.netdna-ssl.com/wp-content/uploads/2013/08/2013-08-20-01-1080x1440.jpg%3E";

                    String UrlGCP = "https://us-central1-seggro-poc.cloudfunctions.net/getPrediction?image=<"+imageUrl+".png>";
                    Log.i("UrlGCP:",urltemp);
                    URL url = new URL(urltemp);
//                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+longi+"&APPID=5a3e7fe28eafeb6401c1fd25ea458490");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";

                    while((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    data = new JSONObject(json.toString());

                    if(data.getInt("cod") != 200) {
                        System.out.println("Cancelled");
                        return null;
                    }


                } catch (Exception e) {

                    System.out.println("Exception "+ e.getMessage());
                    return null;
                }

                return null;
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            protected void onPostExecute(Void Void) {
                if(data!=null){

                    Log.d("my weather received",  data.toString());

//                    saveData(data.toString());






                }

            }
        }.execute();

    }
}
