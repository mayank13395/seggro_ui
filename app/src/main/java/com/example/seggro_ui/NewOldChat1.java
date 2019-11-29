package com.example.seggro_ui;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.seggro_ui.ui.main.SectionsPagerAdapter;
import com.google.android.gms.location.LocationListener;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class NewOldChat1 extends AppCompatActivity implements LocationListener {

    private  static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_IMG = 100;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    Uri cameraImageUri;
    private Uri image_uri;
    private File photoFile;

    Uri uriSavedImage;

    private static  final int REQUEST_LOCATION=1;

    LocationManager locationManager;
    String latitiude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_old_chat);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
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


        // getting weather data













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


//        if (intent.resolveActivity(getPackageManager())!=null) {
//
//            Uri uriSavedImage=Uri.fromFile(new File("/Pictures/cropp.png"));
//
//
//
////            try {
////                photoFile = createImageFile();
////                Log.i("imagepath",photoFile.getAbsolutePath());
////
////                if(photoFile !=null) {
////                    Uri photoURI = FileProvider.getUriForFile(this,"com.example.seggro_ui",photoFile);
////                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
////                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
////                }
////            }catch (Exception e) {
////                Log.i("error",e.toString());
////            }
//        }
//        }


//        cameraImageUri = getOutputMediaFileUri(1);

        // set the image file name
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
//        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);









    /** Create a file Uri for saving an image or video */
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

//     private File createImageFile() throws IOException {
//         String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//         String imageFileName = "JPEG_"+timeStamp+"_";
//         File StorageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//         File  image = File.createTempFile(
//                 imageFileName,
//                 ".jpg",
//                 StorageDir
//
//         );
//
//         mCurrentPhotoPath = image.getAbsolutePath();
//         return  image;
//     }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if(requestCode==RESULT_LOAD_IMG ){

                try {
                    final Uri imageUri = data.getData();
                    if (imageUri != null) {
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
//                        ImageView imagetoshow = (ImageView) findViewById(R.id.imagetoshow);
//                        imagetoshow.setImageBitmap(selectedImage);


                        Uri tempUri = getImageUri(getApplicationContext(), selectedImage);

                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        File finalFile = new File(getRealPathFromURI(tempUri));

                        Log.i("fileeeeee",finalFile.toString());

                        Intent photoquestion = new Intent(NewOldChat1.this, Image_data.class);

                        photoquestion.putExtra("imagepath",finalFile.toString());
                        startActivity(photoquestion);
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


                        Uri tempUri = getImageUri(getApplicationContext(), myBitmap);


                        // CALL THIS METHOD TO GET THE ACTUAL PATH
                        File finalFile = new File(getRealPathFromURI(tempUri));

                        Log.i("fileeeeee",finalFile.toString());

                        Intent photoquestion = new Intent(NewOldChat1.this, Image_data.class);

                        photoquestion.putExtra("imagepath",finalFile.toString());
                        startActivity(photoquestion);








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
        Bitmap OutImage = Bitmap.createScaledBitmap(inImage, 1500, 1000,true);
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
}




