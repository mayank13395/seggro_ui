package com.example.seggro_ui.ui.main;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.seggro_ui.R;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewImage extends Fragment implements NewimgInterface  {

    private static final String ARG_SECTION_NUMBER = "section_number";


    static final int REQUEST_IMAGE_CAPTURE = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;
    TextView weatherTemp;
    TextView textView123;
    TextView textView1;
    public View root123, root1234;
    String todayWithZeroTime;

//    String txtNow = "123xsf";

    TextView weatherDescription;
    TextView todayDate;

    String cropname;

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        Log.i("okkkk", "0");
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("okkkk", "1");
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date today = new Date();

        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(today));

            String[] arrOfStr = todayWithZeroTime.toString().split("00:00:00");
            this.todayWithZeroTime = arrOfStr[0];
            Log.i("Todays DAte", String.valueOf(this.todayWithZeroTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }



//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//             cropname = bundle.getString("cropname");
//
//             Log.i("cropname",cropname);
//
//        }else {
//            Log.i("error","eeeeee");
//        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.i("okkkk", "2");

        root123 = inflater.inflate(R.layout.fragment_new_image, container, false);


//        New Delhi, 29oct

        todayDate = (TextView)root123.findViewById(R.id.todayDate);
        todayDate.setText(todayWithZeroTime+", "+"New Delhi");


//            String strtext = getArguments().getString("cropname");

//            Log.i("mycropname",strtext);




        SharedPreferences sp = this.getActivity().getSharedPreferences("WeatherData", MODE_PRIVATE);

        String weatherReport =  sp.getString("weatherReport", "default value");
        String weatherTemp =  sp.getString("weatherTemp", "22");
        String cropname =  sp.getString("cropImage", "apple");

       String weatherTemptoadd =   weatherReport.substring(0, 1).toUpperCase() + weatherReport.substring(1);
//
//        sp.getBoolean("boolkey", false);
//        sp.getFloat("floatkey", 0.0f);
//        sp.getLong("longkey", 0l);

        Log.i("data", sp.getString("weatherReport", "default value"));
        Log.i("data",   sp.getString("weatherTemp", "22"));

        Log.i("cropnamedata",   sp.getString("cropImage", "22"));
//        changeWeatherDescription(weatherReport);
//        changeWeatherTemp(weatherTemp);

        textView1 = (TextView)root123.findViewById(R.id.weatherDescription);
        textView1.setText(weatherTemptoadd);

        textView123 = (TextView)root123.findViewById(R.id.weatherTemp);
        textView123.setText(weatherTemp.toString() + " \u2103");



        String uri = "@drawable/"+cropname.toLowerCase();

//        Log.i("rooinChangemage", String.valueOf(root1234));
        // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
        ImageView image = (ImageView)root123.findViewById(R.id.centernewimg);
//        imageview= (ImageView)findViewById(R.id.imageView);
        Drawable res = getResources().getDrawable(imageResource);
        image.setImageDrawable(res);

        image.setImageDrawable(res);

        return root123;

    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState){
//        Log.i("okkkk", "3");
//        changeWeatherTemp(txtNow);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i("okkkk", "3");
        root1234 = view;
        super.onViewCreated(view, savedInstanceState);
//        ListView lv = (ListView) view.findViewById(R.id.lvSome);
//        lv.setAdapter(adapter);
//        changeImage(cropname);
    }

    public void changeWeatherTemp(String mText)
    {
        Log.i("okkkk", "mText - "+ mText);
//
        Log.i("okktkk", "12345646363 - "+String.valueOf(root1234));
        textView123 = (TextView)root1234.findViewById(R.id.weatherTemp);

//
        textView123.setText(mText.toString() + " \u2103");
    }





    public void changeWeatherDescription(String mText)
    {

        textView1 = (TextView)root1234.findViewById(R.id.weatherDescription);
        textView1.setText(mText+" throughout the day");
    }


    public void changeImage(String mText)
    {

        Log.i("imageNAme",mText);
        String uri = "@drawable/"+mText.toLowerCase();

        Log.i("rooinChangemage", String.valueOf(root1234));
        // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
        ImageView image = (ImageView)root1234.findViewById(R.id.centernewimg);
//        imageview= (ImageView)findViewById(R.id.imageView);
        Drawable res = getResources().getDrawable(imageResource);
        image.setImageDrawable(res);

        image.setImageDrawable(res);
    }

    }
