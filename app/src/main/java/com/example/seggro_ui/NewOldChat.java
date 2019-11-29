package com.example.seggro_ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.example.seggro_ui.ui.main.NewImage;
import com.google.android.gms.location.LocationListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seggro_ui.ui.main.SectionsPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewOldChat extends AppCompatActivity implements LocationListener {

    private  static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_IMG = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;
    private Uri image_uri;
    private File photoFile;
     int maxSize = 800;
    int outWidth;
    int outHeight;

    String weatherReport;
    Double weatherTemp = 22.0;


    NewImage newImage;

    Uri uriSavedImage;

    private static  final int REQUEST_LOCATION=1;

    LocationManager locationManager;
    String latitude,longitude;



//    private static  final int REQUEST_LOCATION=1;

    Button getlocationBtn;
    TextView showLocationTxt;

//    LocationManager locationManager;
//    String latitude,longitude;
    JSONObject data;
    double lat;
    double longi;

    ViewPager viewPager;
    String cropName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_old_chat);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Log.i("above","okk");


        Log.i("below","okk");

        // getting weather data

//        ActivityCompat.requestPermissions(this,new String[]
//                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//
////        showLocationTxt=findViewById(R.id.show_location);
////        getlocationBtn=findViewById(R.id.getLocation);
////
////        getlocationBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//
//                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//                //Check gps is enable or not
//
//                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
//                {
//                    //Write Function To enable gps
//
//                    OnGPS();
//                }
//                else
//                {
//                    //GPS is already On then
//
//                    getLocation();
//                    getJSON(lat,longi);
//
//
//                }


//              NewImage   newImage = new NewImage();
//////        MyFragment fragment  = new MyFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.view_pager,newImage);
//        fragmentTransaction.commit();


        Bundle bundle = getIntent().getExtras();
        cropName = bundle.getString("cropName");


        String uri = "@drawable/"+cropName.toLowerCase();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//        ImageView image = (ImageView)findViewById(R.id.topcropimage);
//        imageview= (ImageView)findViewById(R.id.imageView);
        Drawable res = getResources().getDrawable(imageResource);
//        imageView.setImageDrawable(res);

//        image.setImageDrawable(res);

        TextView headtext = (TextView)findViewById(R.id.cropname);

        Log.i("abccc","o2k532");
        if(cropName!=null) {
            headtext.setText("Check your "+cropName+" health");
        }

        Log.i("abccc","o2kfbfgsg");

//        updateImage();

        Log.i("abccc","o2k");
        getSize();
      Log.i("abccc","ok");
            }

    private void updateImage() {

        Log.i("updateImage method", String.valueOf(viewPager.getCurrentItem()));

        if(viewPager.getCurrentItem() == 0) {
            NewImage frag1 = (NewImage) viewPager
                    .getAdapter()
                    .instantiateItem(viewPager, viewPager.getCurrentItem());
            Log.i("NewImageFrag", String.valueOf(frag1));
//            frag1.changeWeatherDescription("apple");
        }



    }




    public void openGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

//        Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);
//        startActivity(photoquestion);

    }

    public void openCaemra(View view) {


//        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


//        if (takePic.resolveActivity(getActivity().getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
////                Log.i(TAG, "IOException");
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                takePic.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
//                startActivityForResult(takePic, REQUEST_IMAGE_CAPTURE);
//            }
//        }

//        add extra content
//
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New_Crop_image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From_the_camera");


        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

//        uriSavedImage=Uri.fromFile(new File("/Pictures/cropp.png"));


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);





        if (resultCode == RESULT_OK) {



//            newImage = new NewImage();
//
//
//
//            newImage.changeWeatherTemp(test.toString());
//            newImage.changeWeatherDescription(oneObjectsItem);ffd




            if(requestCode==RESULT_LOAD_IMG ){


//                if ((getResources().getConfiguration().screenLayout &
//                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
//                        Configuration.SCREENLAYOUT_SIZE_LARGE) {
//
//                    maxSize = 500;
//                    Log.i("IN Large Screen","okk");
//                    // on a large screen device ...
//
//                }else if ((getResources().getConfiguration().screenLayout &
//                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
//                        Configuration.SCREENLAYOUT_SIZE_SMALL) {
//
////                    maxSize = 500;
//                    Log.i("IN small Screen","okk");
//                    // on a large screen device ...
//
//                }else  if ((getResources().getConfiguration().screenLayout &
//                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
//                        Configuration.SCREENLAYOUT_SIZE_XLARGE) {
//
////                    maxSize = 500;
//                    Log.i("IN XL Screen","okk");
//                    // on a large screen device ...
//
//                }else  if ((getResources().getConfiguration().screenLayout &
//                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
//                        Configuration.SCREENLAYOUT_SIZE_NORMAL) {
//
////                    maxSize = 500;
//                    Log.i("IN normal Screen","okk");
//                    // on a large screen device ...
//
//                }

                try {
                    final Uri imageUri = data.getData();
                    if (imageUri != null) {
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                        ImageView imagetoshow = (ImageView) findViewById(R.id.imagetoshow);
//                        imagetoshow.setImageBitmap(selectedImage);
                        int inWidth = selectedImage.getWidth();
                        int inHeight = selectedImage.getHeight();
                        if(inWidth > inHeight){
                            outWidth = maxSize;
                            outHeight = (inHeight * maxSize) / inWidth;
                        } else {
                            outHeight = maxSize;
                            outWidth = (inWidth * maxSize) / inHeight;

                        }

                        Uri tempUri = getImageUri(getApplicationContext(), selectedImage);

                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        File finalFile = new File(getRealPathFromURI(tempUri));

                        Log.i("fileeeeee",finalFile.toString());

                        Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);

                        photoquestion.putExtra("imagepath",finalFile.toString());
                        photoquestion.putExtra("weatherReport",weatherReport);
                        photoquestion.putExtra("weatherTemp",weatherTemp);
                        photoquestion.putExtra("cropName",cropName);



                        startActivity(photoquestion);
                        this.finish();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }





            }


            else if(requestCode == REQUEST_IMAGE_CAPTURE) {

//                Bitmap myBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//                ImageView imagetoshow = (ImageView)findViewById(R.id.imagetoshow);
//                imagetoshow.setImageBitmap(myBitmap);


                try {
                    Bitmap myBitmap = null;
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_uri);
                    if(myBitmap!=null) {

//                        ImageView imagetoshow = (ImageView)findViewById(R.id.imagetoshow);
//
//                        imagetoshow.setImageBitmap(myBitmap);

                        int inWidth = myBitmap.getWidth();
                        int inHeight = myBitmap.getHeight();
                        if(inWidth > inHeight){
                            outWidth = maxSize;
                            outHeight = (inHeight * maxSize) / inWidth;
                        } else {
                            outHeight = maxSize;
                            outWidth = (inWidth * maxSize) / inHeight;
                        }


                        Uri tempUri = getImageUri(getApplicationContext(), myBitmap);


                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        File finalFile = new File(getRealPathFromURI(tempUri));

                        Log.i("fileeeeee",finalFile.toString());

                        Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);

                        photoquestion.putExtra("imagepath",finalFile.toString());
                        photoquestion.putExtra("weatherReport",weatherReport);
                        photoquestion.putExtra("weatherTemp",weatherTemp.toString());
                        photoquestion.putExtra("cropName",cropName);
                        startActivity(photoquestion);
                        this.finish();








//                        Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);
//
//                        photoquestion.putExtra("BitmapImage", myBitmap);
//
////                photoquestion.putExtra("picture", byteArray);
//                        startActivity(photoquestion);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

//
////                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
////                byte[] byteArray = stream.toByteArray();
////                myBitmap.recycle();
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


//                Bitmap myBitmap = BitmapFactory.decodeStream( image_uri. );




                }




            // for image to sho from gallery





            // Here you have the ImagePath which you can set to you image view

//            Log.e("Image Name", image_uri.getPath());
//
//            Bitmap myBitmap = null;
//            try {
//                myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), image_uri);
//
//
////                ByteArrayOutputStream stream = new ByteArrayOutputStream();
////                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
////                byte[] byteArray = stream.toByteArray();
////                myBitmap.recycle();
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


//                Bitmap myBitmap = BitmapFactory.decodeStream( image_uri. );

//            if(myBitmap!=null) {
//
//                ImageView imagetoshow = (ImageView)findViewById(R.id.imagetoshow);
//
//                imagetoshow.setImageBitmap(myBitmap);
//
//                Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);
//
//                photoquestion.putExtra("BitmapImage", myBitmap);
//
////                photoquestion.putExtra("picture", byteArray);
//                startActivity(photoquestion);
//            }






        }else {
//            Toast.makeText(PostImage.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }











//            Bitmap bmp = BitmapFactory.decodeResource(myBitmap);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();


//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            myBitmap.recycle();
//
//
//            Intent photoquestion = new Intent(NewOldChat.this, Image_data.class);
//
//            photoquestion.putExtra("picture", byteArray);
//            startActivity(photoquestion);





// For further image Upload i suppose your method for image upload is UploadImage
//                File imageFile = new File(cameraImageUri.getPath());
//                uploadImage(imageFile);






        }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage , outWidth,outHeight,true);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), OutImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    @Override
    public void onLocationChanged(Location location) {

    }
//
//
//    private void getLocation() {
//
//        //Check Permissions again
//
//        if (ActivityCompat.checkSelfPermission(NewOldChat.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(NewOldChat.this,
//
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,new String[]
//                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//        }
//        else
//        {
//            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
//
//            if (LocationGps !=null)
//            {
//                lat=LocationGps.getLatitude();
//                longi=LocationGps.getLongitude();
//
//                latitude=String.valueOf(lat);
//                longitude=String.valueOf(longi);
//
////                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
//
//
//            }
//            else if (LocationNetwork !=null)
//            {
//                lat=LocationNetwork.getLatitude();
//                longi=LocationNetwork.getLongitude();
//
//                latitude=String.valueOf(lat);
//                longitude=String.valueOf(longi);
//
////                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
//            }
//            else if (LocationPassive !=null)
//            {
//                lat=LocationPassive.getLatitude();
//                longi=LocationPassive.getLongitude();
//
//                latitude=String.valueOf(lat);
//                longitude=String.valueOf(longi);
//
////                showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
//            }
//            else
//            {
//                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            //Thats All Run Your App
//        }
//
//    }
//
//    private void OnGPS() {
//
//        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
//
//        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialog, int which) {
//                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//            }
//        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                dialog.cancel();
//            }
//        });
//        final AlertDialog alertDialog=builder.create();
//        alertDialog.show();
//    }
//
//
//    public void getJSON(final double lat, final double longi) {
//
//        new AsyncTask<Void, Void, Void>() {
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//
//            }
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+longi+"&APPID=5a3e7fe28eafeb6401c1fd25ea458490");
//
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                    BufferedReader reader =
//                            new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                    StringBuffer json = new StringBuffer(1024);
//                    String tmp = "";
//
//                    while((tmp = reader.readLine()) != null)
//                        json.append(tmp).append("\n");
//                    reader.close();
//
//                    data = new JSONObject(json.toString());
//
//                    if(data.getInt("cod") != 200) {
//                        System.out.println("Cancelled");
//                        return null;
//                    }
//
//
//                } catch (Exception e) {
//
//                    System.out.println("Exception "+ e.getMessage());
//                    return null;
//                }
//
//                return null;
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            protected void onPostExecute(Void Void) {
//                if(data!=null){
//
//                    Log.d("my weather received",  data.toString());
//
//                    saveData(data.toString());
//
//
//
//
//
//
//                }
//
//            }
//        }.execute();
//
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private void saveData(String result) {
//        String oneObjectsItem = "snow";
//        Double  test = 22.0;
//        try {
//
//            JSONObject json = new JSONObject(result);
////            JSONObject json= (JSONObject) new JSONTokener(result).nextValue();
//            JSONObject json2 = json.getJSONObject("main");
//
//            test = (Double) json2.get("temp");
//            JSONArray jArray = json.getJSONArray("weather");
//
//            for (int i=0; i < jArray.length(); i++)
//            {
//                try {
//                    JSONObject oneObject = jArray.getJSONObject(i);
//                    // Pulling items from the array
//                    oneObjectsItem = oneObject.getString("description");
////                    String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
//                    Log.i("Weather ddescription",oneObjectsItem);
//
//                } catch (JSONException e) {
//                    // Oops
//                }
//            }
//
//            Log.i("weather temp",test.toString());
//
//
////            weatherDescription
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
////
////        TextView weatherTemp = (TextView)findViewById(R.id.lat);
////        TextView weatherDescription = (TextView)findViewById(R.id.longi);
//
//        test = test - 273.15;
//
//        test = Math.round(test*100)/100.0d;
////        d = Math.round(d*100)/100.0d;
////        Math.round((test * 10d) / 10d);
//
//
//        weatherReport = oneObjectsItem;
//    weatherTemp = test;
//
//
//        // inserting data to database.....
//
//
//
//
//
//
//
//
//
//        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + viewPager.getCurrentItem());
//                /*
//                   this method is to get Fragment from ViewPager adapter.
//                   i have commented this code because i am using above code to get Fragment from ViewPager.
//                */
//        //Fragment page = myPagerAdapter.getItem(mViewPager.getCurrentItem());
//        if (page != null){
//            switch (viewPager.getCurrentItem()){
//                case 0:
//                    Log.i("abc", "1");
//                    NewImage oneFragment = (NewImage) page;
//                    oneFragment.changeWeatherTemp(test);
//                    oneFragment.changeWeatherDescription(oneObjectsItem);
////                    oneFragment.changeImage(cropName);
//                    break;
//                case 1:
//                    Log.i("abc", "2");
////                    TwoFragment twoFragment = (TwoFragment) page;
////                    twoFragment.print();
//                    break;
//            }
//
//        }
//
//
//    }




  public void   getSize() {

        Log.i("getsizecalled","okk");
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

            maxSize = 500;
            Log.i("IN Large Screen","okk");
            // on a large screen device ...

        }else if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_SMALL) {

//                    maxSize = 500;
            Log.i("IN small Screen","okk");
            // on a large screen device ...

        }else  if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE) {

//                    maxSize = 500;
            Log.i("IN XL Screen","okk");
            // on a large screen device ...

        }else  if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_NORMAL) {

//                    maxSize = 500;
            Log.i("IN normal Screen","okk");
            // on a large screen device ...

        }
    }
}




