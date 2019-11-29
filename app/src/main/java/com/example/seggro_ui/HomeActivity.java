package com.example.seggro_ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.seggro_ui.ui.main.NewImage;
import com.google.android.gms.location.LocationListener;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements LocationListener {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    private Toast toast;
    private final String PREFERENCE_FILE_KEY = "myAppPreference";
    LocationManager locationManager;
    String latitude,longitude;
    private static  final int REQUEST_LOCATION=1;
    double lat;
    double longi;
    private  static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_IMG = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;
    private Uri image_uri;
    private File photoFile;
    final int maxSize = 500;
    int outWidth;
    int outHeight;
    String weatherReport;
    Double weatherTemp;
    NewImage newImage;
    Uri uriSavedImage;
    Button getlocationBtn;
    TextView showLocationTxt;
    JSONObject data;
    ViewPager viewPager;
    String cropName = null;
    public  static final int PERMISSIONS_MULTIPLE_REQUEST = 123;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_home);

        Button nextPage = findViewById(R.id.acceptTerms);
        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                statusCheck();
//                changeLanguage();
                Intent homeIntent = new Intent(HomeActivity.this, SignUpActivity.class);
                startActivity(homeIntent);
            }
        });


        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.languageSelectionOptions);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                // checkedId is the RadioButton selected

                radioButton = (RadioButton) findViewById(checkedId);

                radioButton.setTextColor(Color.parseColor("#008577"));
////                radioButton.setTextSize(30);
//                RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.languageSelectionOptions);
//
//                int count = radioGroup3.getChildCount();
////                ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
//                for (int i=0;i<count;i++) {
//                    View o = radioGroup.getChildAt(i);
//                    if (o instanceof RadioButton) {
//
//
//                        if(o.getId()!=checkedId) {
//
//                            RadioButton radioButtonch = (RadioButton) findViewById(o.getId());
//
//                            radioButtonch.setTextColor(Color.BLACK);
////                            radioButtonch.setTextSize(20);
//
//                        }
////                        listOfRadioButtons.add((RadioButton)o);
//                    }
//                }
//                radioButton.setTextSize(10);
            }
        });

        checkAndroidVersion();
//
//        ActivityCompat.requestPermissions(this,new String[]
//                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Check gps is enable or not

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //Write Function To enable gps

            OnGPS();
        }
        else
        {
            //GPS is already On then

            getLocation();
            getJSON(lat,longi);

        }

//        isStoragePermissionGranted();


    }


    // check Android version
    private void checkAndroidVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();

        } else {
            // write your logic here
        }

    }

    private void changeLanguage() {
        final String[] listItems = {"English", "Hindi"};

        radioGroup = (RadioGroup) findViewById(R.id.languageSelectionOptions);

        Integer selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

//        radioButton.setTextColor(Color.parseColor("#008577"));


        Log.d("radio button", String.valueOf(radioButton.getText()));


//        toast.makeText(MyAndroidAppActivity.this,
//                radioButton.getText(), Toast.LENGTH_SHORT).show();


    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;

        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();

    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            Intent homeIntent = new Intent(HomeActivity.this, SignUpActivity.class);
            startActivity(homeIntent);

        }else {
            Intent homeIntent = new Intent(HomeActivity.this, SignUpActivity.class);
            startActivity(homeIntent);
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) { }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0) {
                    boolean cameraPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if(cameraPermission && readExternalFile)
                    {
                        // write your logic here
                        Log.i("permissions","Granted");
                    }
                }
                break;
        }
    }

    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                lat=LocationGps.getLatitude();
                longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

//                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);


            }
            else if (LocationNetwork !=null)
            {
                lat=LocationNetwork.getLatitude();
                longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

//                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationPassive !=null)
            {
                lat=LocationPassive.getLatitude();
                longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

//                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();


            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }


    public void getJSON(final double lat, final double longi) {

        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+longi+"&APPID=5a3e7fe28eafeb6401c1fd25ea458490");

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

                    saveData(data.toString());






                }

            }
        }.execute();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveData(String result) {
        String oneObjectsItem = "snow";
        Double  test = 22.0;
        try {

            JSONObject json = new JSONObject(result);
//            JSONObject json= (JSONObject) new JSONTokener(result).nextValue();
            JSONObject json2 = json.getJSONObject("main");

            test = (Double) json2.get("temp");
            JSONArray jArray = json.getJSONArray("weather");

            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Pulling items from the array
                    oneObjectsItem = oneObject.getString("description");
//                    String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                    Log.i("Weather ddescription",oneObjectsItem);

                } catch (JSONException e) {
                    // Oops
                }
            }

            Log.i("weather temp",test.toString());


//            weatherDescription

        } catch (JSONException e) {
            e.printStackTrace();
        }

//
//        TextView weatherTemp = (TextView)findViewById(R.id.lat);
//        TextView weatherDescription = (TextView)findViewById(R.id.longi);

        test = test - 273.15;

        test = Math.round(test*100)/100.0d;
//        d = Math.round(d*100)/100.0d;
//        Math.round((test * 10d) / 10d);


        weatherReport = oneObjectsItem;
        weatherTemp = test;

        Log.i("weaqther",weatherReport);
        Log.i("weaqther",weatherTemp.toString());


        // shared preferenes for weather data

        SharedPreferences sp = getSharedPreferences("WeatherData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();



        editor.putString("weatherReport", weatherReport);
        editor.putBoolean("boolkey", true);
//        editor.putFloat("floatkey", 4.3434f);
        editor.putString("weatherTemp",weatherTemp.toString());



        editor.apply();


    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.CAMERA) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                requestPermissions(
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_MULTIPLE_REQUEST);
            }
        } else {
            // write your logic code if permission already granted
        }
    }

}
