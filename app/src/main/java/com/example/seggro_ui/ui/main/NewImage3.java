package com.example.seggro_ui.ui.main;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.seggro_ui.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewImage3 extends Fragment  {

    private static final String ARG_SECTION_NUMBER = "section_number";


    static final int REQUEST_IMAGE_CAPTURE = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;
    TextView weatherTemp;
    TextView textView123;
    View root123;
    String txtNow = "123xsf";

    TextView weatherDescription;

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

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log.i("okkkk", "2");

        root123 = inflater.inflate(R.layout.fragment_new_image, container, false);
//        textView123 = (TextView)root123.findViewById(R.id.weatherTemp);
//        Log.i("okktkk", "123 - "+String.valueOf(textView123));
        Log.i("okktkk", "root 0 - "+String.valueOf(root123));
//        View RootView = inflater.inflate(R.layout.fragment_blank, container, false);


//        textView123.setText("HI");
        Log.i("okktkk", "txtNow - "+String.valueOf(txtNow));

        return root123;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Log.i("okkkk", "3");
        changeWeatherTemp(txtNow);
//        textView123 = (TextView)getActivity().findViewById(R.id.weatherTemp);
//        Log.i("okktkk", "123876 - "+String.valueOf(textView123));
        // Displaying the user details on the screen
//        textView123.setText("kjhbguhjg");
    }

//    private void updateUi() {
//        if (root123 == null) { // Check if view is already inflated
//            Log.i("okkkk", "5");
//            return;
//        }
//        Log.i("okktkk", "12345646363 - "+String.valueOf(root123));
//    }

    public void changeWeatherTemp(String  mText)
    {
        Log.i("okkkk", "mText - "+ mText);
        txtNow = mText;
        if (root123 == null) { // Check if view is already inflated
            Log.i("okkkk", "5");
//            changeWeatherTemp(mText);
            return;
        }
        Log.i("okkkk", "4 - "+txtNow);
        Log.i("okktkk", "12345646363 - "+String.valueOf(root123));
        textView123 = (TextView)root123.findViewById(R.id.weatherTemp);
//        Log.i("okktkk", "123456 - "+String.valueOf(textView123));

//        textView123 = (TextView)this.getView().findViewById(R.id.weatherTemp);
//        Log.i("okktkk", "root - "+String.valueOf(textView123));
//
        textView123.setText(txtNow);
    }





    public void changeWeatherDescription(String mText)
    {
        weatherDescription.setText(mText);
    }

    }
