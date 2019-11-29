package com.example.seggro_ui.ui.main;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.seggro_ui.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewImage1 extends Fragment  {

    private static final String ARG_SECTION_NUMBER = "section_number";


    static final int REQUEST_IMAGE_CAPTURE = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;


    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        View root = inflater.inflate(R.layout.fragment_new_image, container, false);
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText("hello");
//            }
//        });
        return root;


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            try {
//                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), Uri.parse(mCurrentPhotoPath));
//                mImageView.setImageBitmap(mImageBitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


        //open camera and gallery

//    public void openGallery(View view) {
//
//    }
//
//    public void openCaemra(View view) {
//
////        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//
//
//
////        if (takePic.resolveActivity(getActivity().getPackageManager()) != null) {
////            // Create the File where the photo should go
////            File photoFile = null;
////            try {
////                photoFile = createImageFile();
////            } catch (IOException ex) {
////                // Error occurred while creating the File
//////                Log.i(TAG, "IOException");
////            }
////            // Continue only if the File was successfully created
////            if (photoFile != null) {
////                takePic.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
////                startActivityForResult(takePic, REQUEST_IMAGE_CAPTURE);
////            }
////        }
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//
//        cameraImageUri = getOutputMediaFileUri(1);
//
//        // set the image file name
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
//
//
//
//
//
//
//
//
//    }
//
//    /** Create a file Uri for saving an image or video */
//    private static Uri getOutputMediaFileUri(int type) {
//
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
//
//    /** Create a File for saving an image or video */
//    private static File getOutputMediaFile(int type) {
//
//        // Check that the SDCard is mounted
//        File mediaStorageDir = new File(
//                Environment.getExternalStorageDirectory(), Environment.DIRECTORY_PICTURES);
//
//        // Create the storage directory(MyCameraVideo) if it does not exist
//        if (!mediaStorageDir.exists()) {
//
//            if (!mediaStorageDir.mkdirs()) {
//
//                Log.e("Item Attachment",
//                        "Failed to create directory MyCameraVideo.");
//
//                return null;
//            }
//        }
//        java.util.Date date = new java.util.Date();
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//
//        File mediaFile;
//
//        if (type == 1) {
//
//            // For unique video file name appending current timeStamp with file
//            // name
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +"cropImage"+ ".jpg");
//
//        } else {
//            return null;
//        }
//
//        return mediaFile;
//    }
//
//
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  // prefix
//                ".jpg",         // suffix
//                storageDir      // directory
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == REQUEST_IMAGE_CAPTURE) {
//
//
//                // Here you have the ImagePath which you can set to you image view
//
//                Log.e("Image Name", cameraImageUri.getPath());
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(cameraImageUri.getPath());
//
//                ImageView imagetoshow = (ImageView)getView().findViewById(R.id.imagetoshow);
//
//                imagetoshow.setImageBitmap(myBitmap);
//
//
//
//// For further image Upload i suppose your method for image upload is UploadImage
//                File imageFile = new File(cameraImageUri.getPath());
////                uploadImage(imageFile);
//
//            }
//
//
//
//
//        }
//    }
//
    }

    }
