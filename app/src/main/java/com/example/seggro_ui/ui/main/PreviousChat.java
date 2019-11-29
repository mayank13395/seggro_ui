package com.example.seggro_ui.ui.main;



        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;

        import com.example.seggro_ui.Chat;
        import com.example.seggro_ui.HomeActivity;
        import com.example.seggro_ui.ImageResult;
        import com.example.seggro_ui.NewOldChat;
        import com.example.seggro_ui.Note;
        import com.example.seggro_ui.NoteRepository;
        import com.example.seggro_ui.R;
        import com.example.seggro_ui.UpdatePreviousChat;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PreviousChat extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    int totalRowsData;

    LinearLayout parent;
    LinearLayout layout2;
    View root;
   
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

        Log.i("called","okkkk");


//                NoteRepository noteRepository = new NoteRepository(getActivity().getApplicationContext());
//
//        noteRepository.getTasks().observe(this, new Observer<List<Note>>() {
//            @Override
//
//
//            public void onChanged(@Nullable List<Note> notes) {
//
//
////                Log.i("no. of rows i table", String.valueOf(notes.size()));
//
//                 Log.i("no. of rows i table",  notes.getClass().getName());
//
////                 Log.i("no. of rows i table");
//
//
//
//                 for(Note note : notes) {
//
//                    Note n = new Note();
//                    n=note;
//                    updateOnScreen(n);
//
////                    Log.i("updatedData", note.getTitle());
////                    Log.i("updatedData", note.getDescription());
//////                  Log.i("updatedData", note.getTemperature());
////                    Log.i("updatedData", note.getImagePath());
//
////                    String imagePath =
////                    System.out.println("-----------------------");
////                    System.out.println(note.getId());
////                    System.out.println(note.getTitle());
////                    System.out.println(note.getDescription());
//////                    System.out.println(note.getCreatedAt());
//////                    System.out.println(note.getModifiedAt());
////                    System.out.println(note.getPassword());
////                    System.out.println(note.isEncrypt());
//                }
//            }

//            private void updateOnScreen(Note n) {
//
//                Log.i("Got the first row",n.toString());
//                Log.i("updatedData", n.getTitle());
//                Log.i("updatedData", n.getDescription());
////                  Log.i("updatedData", n.getTemperature());
//                Log.i("updatedData", n.getImagePath());
//
//                ImageView iv = new ImageView(getActivity());
////
//            LinearLayout layout2 = new LinearLayout(getActivity());
////
//            layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            layout2.setOrientation(LinearLayout.HORIZONTAL);
////
//            layout2.addView(iv);
//
////            parent.addView(layout2);
//
//            }
//        });

//        UpdatePreviousChat  updatePreviousChat = new UpdatePreviousChat();

//        if(updatePreviousChat.isVisited ){



//
//            File imgFile = new  File(imagepath);
//
//            if(imgFile.exists()){
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//                ImageView imageView = (ImageView)getView().findViewById(R.id.img1);
//
//                imageView.setImageBitmap(myBitmap);
//
//            }
//        }





    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_previous_chat2, container, false);

        parent = (LinearLayout)root.findViewById(R.id.addPreviousChat);


        Log.i("parentData", String.valueOf(parent));
//        Log.i("parentElement",parent.toString());
//        Log.i("rootElement",getView().toString());
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText("hello");
//            }
//        });

//        for (int i = 0;i<totalRowsData;i++) {
//            ImageView iv = new ImageView(context);
//
//            LinearLayout layout2 = new LinearLayout(context);
//
//            layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            layout2.setOrientation(LinearLayout.VERTICAL);
//
//            parent.addView(iv);
//            parent.addView(layout2);
//
//
//
//        }




        NoteRepository noteRepository = new NoteRepository(getActivity().getApplicationContext());

        noteRepository.getTasks().observe(this, new Observer<List<Note>>() {
            @Override


            public void onChanged(@Nullable List<Note> notes) {


//                Log.i("no. of rows i table", String.valueOf(notes.size()));

                Log.i("no. of rows i table", notes.getClass().getName());

//                 Log.i("no. of rows i table");


                for (Note note : notes) {

                    Note n = new Note();
                    n = note;
                    updateOnScreen(n);

//                    parent.addView(layout2);


//                    Log.i("updatedData", note.getTitle());
//                    Log.i("updatedData", note.getDescription());
////                  Log.i("updatedData", note.getTemperature());
//                    Log.i("updatedData", note.getImagePath());

//                    String imagePath =
//                    System.out.println("-----------------------");
//                    System.out.println(note.getId());
//                    System.out.println(note.getTitle());
//                    System.out.println(note.getDescription());
////                    System.out.println(note.getCreatedAt());
////                    System.out.println(note.getModifiedAt());
//                    System.out.println(note.getPassword());
//                    System.out.println(note.isEncrypt());
                }
            }


        });











        return root;
    }


 int imagenumber = 1;
    private void updateOnScreen(Note n) {
               Log.i("gettingParent", String.valueOf(parent));
               Log.i("Got the first row",n.toString());
               Log.i("updatedData", n.getTitle());
//               Log.i("updatedData", n.getDescription());

               Log.i("updatedData", n.getImagePath());

               ImageView iv = new ImageView(getActivity());

               File imgFile = new  File(n.getImagePath());

               if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    iv.setImageBitmap(myBitmap);

                }else {
                    Log.i("file path error","okkk");


                }

//               iv.setPadding(20,10,20,20);
//        iv.getLayoutParams().height = 100;
//
//        iv.getLayoutParams().width = 100;
////
        LinearLayout layoutText = new LinearLayout(getActivity());
//        layoutText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layoutText.setOrientation(LinearLayout.VERTICAL);


        LinearLayout.LayoutParams paramsText = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        paramsText.gravity = Gravity.LEFT;
//        paramsText.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramsText.setMargins(40, 60, 0, 0);

        layoutText.setLayoutParams(paramsText);

        TextView imgDate = new TextView(getActivity());
        imgDate.setText("Date 1");
        imgDate.setTextSize(17);

        LinearLayout.LayoutParams paramsimgdate = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
//        paramsimgdate.setMargins(left, top, right, bottom);



//        params.gravity = Gravity.RIGHT;
//        params.layout
        imgDate.setLayoutParams(paramsimgdate);








        TextView imgName = new TextView(getActivity());
        imgName.setText("Img "+imagenumber);
        imgName.setTextSize(17);
        imagenumber++;
        imgName.setLayoutParams(paramsimgdate);

//        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        params.setMargins(left, top, right, bottom);

//        params2.gravity = Gravity.LEFT;
//        imgName.setLayoutParams(params2);

        layoutText.addView(imgName);
        layoutText.addView(imgDate);



        RelativeLayout layout2 = new RelativeLayout(getActivity());

            layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            layout2.setOrientation(LinearLayout.HORIZONTAL);



        RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(
              400,
                400
        );
        params3.setMargins(20, 20, 20, 20);
       params3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        iv.setLayoutParams(params3);



            layout2.addView(iv);

            layout2.addView(layoutText);
//            layout2.setBackgroundResource(R.drawable.previouschatlayer);

//        CardView card  = new CardView(getActivity());
//        card.setCardElevation(8);
//
//        card.setLayoutParams(params3);



        layout2.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {

                                           byte[] byteArray = new byte[0];

                                           Log.i("ViewonClicked", String.valueOf(v));

                                           for(int index=0; index<((ViewGroup)v).getChildCount(); ++index) {
                                               View nextChild = ((ViewGroup)v).getChildAt(index);

                                               if(nextChild instanceof ImageView){

                                                   ImageView image = (ImageView) nextChild;
//                                                   textVal =  textView.getText().toString();
                                                   Log.i("childiddd", String.valueOf(image));

//                                                   Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

                                                   Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();

                                                   ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                                   bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                                    byteArray = stream.toByteArray();
                                                   Log.i("bitmappp", String.valueOf(byteArray));
//                                                   Intent intent = new Intent(getActivity(), Chat.class);
//                                                   intent.putExtra("imageBitmap",byteArray);
//
//                                                   startActivity(intent);

//                                                   intent =  new Intent(context, NewOldChat.class);
//                                                   intent.putExtra("cropName",textVal);
//                                                   context.startActivity(intent);

//                                                   Intent i = new Intent(getActivity(), DetailActivity.class);
//                                                   startActivity(i);
                                                   /// saving to database .....

                                                   // Insert operation in room Database
//        NoteRepository noteRepository = new NoteRepository(context.getApplicationContext());
//        String title = textVal;
//        String weatherDescription = "This is the description of the  task";
//        String temperature = "weather temp";
//        noteRepository.insertTask(title, weatherDescription,temperature);

                                               }






                                           }


                                           Intent intent = new Intent(getActivity(), Chat.class);
                                           intent.putExtra("imageBitmap",byteArray);

                                           startActivity(intent);


                                           Log.i("LLLLLLchildiddd", "fdfgdfgdfgd");


                                       }
                                   });



//        card.addView(layout2);

            parent.addView(layout2);

            }
}
