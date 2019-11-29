package com.example.seggro_ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Query;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.List;


public class SignUpActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private ViewFlipper VF;
    EasyFlipView myEasyFlipView;
//    private LifecycleOwner appContext;

//    private Animation slide_in_left, slide_in_right, slide_out_left, slide_out_right;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });


//        slide_in_left = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
//        slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
//        slide_out_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
//        slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
//
//        SharedPreferences sp = getSharedPreferences("WeatherData", MODE_PRIVATE);
//
//        sp.getString("stringkey", "default value");
//        sp.getBoolean("boolkey", false);
//        sp.getFloat("floatkey", 0.0f);
//        sp.getLong("longkey", 0l);
//
////        Log.i("data",  sp.getString("stringkey", "default value"));
////        Log.i("data", String.valueOf(sp.getBoolean("boolkey", false)));

    }

    private void validate(String userName, String userPassword) {

//         Insert operation in room Database
//        NoteRepository noteRepository = new NoteRepository(getApplicationContext());
//        String title = "This is the title of the third task";
//        String description = "This is the description of the third task";
//        String temperature = "12";
//        noteRepository.insertTask(title, description, temperature);





//        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            Intent intent;
            intent = new Intent(SignUpActivity.this, Grid_dummy.class);
            startActivity(intent);
            return;
//        }

//        Toast.makeText(SignUpActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
    }


    // flipview click listener


    public void gotologin(View v) {

        myEasyFlipView = (EasyFlipView) findViewById(R.id.flipviewlayout);

        Log.d(" easyflip view", myEasyFlipView.toString());



        TextView view = (TextView) v;

        String textVal = view.getText().toString();

        Log.d("text val", textVal);


        // geting flipview by id
//
        if (textVal.equalsIgnoreCase("signup")) {
            myEasyFlipView.flipTheView();


//            VF.showNext();


            TextView signupbtn = (TextView) findViewById(R.id.signup);
            signupbtn.setText("Login");

            TextView createaccountbtn = (TextView) findViewById(R.id.createaccountText);
            createaccountbtn.setText("Already have an account?");



            TextView login_signuptext = (TextView) findViewById(R.id.login_signuptext);
            login_signuptext.setText("Create your account");

        } else if (textVal == "Login") {
            myEasyFlipView.flipTheView();

//            VF.showPrevious();

            TextView signupbtn = (TextView) findViewById(R.id.signup);
            signupbtn.setText("SignUp");



            TextView createaccountbtn = (TextView) findViewById(R.id.createaccountText);
            createaccountbtn.setText("Create accont?");



            TextView login_signuptext = (TextView) findViewById(R.id.login_signuptext);
            login_signuptext.setText("Login to your account");


//            ViewFlipper viewFlipper = getViewFlipperForItem(i);
//            AnimationFactory.flipTransition(viewFlipper, FlipDirection.LEFT_RIGHT);
//
//            RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            rotate.setDuration(5000);
//            rotate.setInterpolator(new LinearInterpolator());
//
//            ImageView image= (ImageView) findViewById(R.id.imageView);
//
//            image.startAnimation(rotate);

        }
//
//
//
    }

    public void createAccount(View view) {

               NoteRepository noteRepository = new NoteRepository(getApplicationContext());

        noteRepository.getTasks().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                for(Note note : notes) {
                    Log.i("SavedData", note.getTitle());
                    Log.i("SavedData", note.getDescription());
                    Log.i("SavedData", note.getTemperature());
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




//        @Query("SELECT * FROM user")
//        public User[] loadAllUsers();
//
//
//        private AppDatabase db ;
//
//        public UserServices(){
//            db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "database-name").build();
//        }
//
//        public List<UserModel> getAllUsers(){
//            return new GetUsersAsyncTask().exicute().get();
//        }

//        NoteRepository noteRepository = new NoteRepository(getApplicationContext());
//        LiveData<Note> note = noteRepository.getTask(2);

//        NoteRepository noteRepository = new NoteRepository(getApplicationContext());
//        Note note = noteRepository.getTask(2);

//        Log.i("SavedData", String.valueOf(note));
//        System.out.println("saved Content"+ );
//        if(note.hasObservers()){
//            System.out.println(note.getValue());
//            Log.i("SavedData", note.getValue().toString());
//        }
//        else
//        {
//            System.out.println("Phokat");
//            Log.i("SavedData", "Bakwas");
//        }



//        NoteRepository noteRepository = new NoteRepository(getApplicationContext());
//
//        noteRepository.getTasks().observe(appContext, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(@Nullable List<Note> notes) {
//                for(Note note : notes) {
//                    System.out.println("-----------------------");
//                    System.out.println(note.getId());
//                    System.out.println(note.getTitle());
//                    System.out.println(note.getDescription());
//
//
//                }
//            }
//        });
    }
}






