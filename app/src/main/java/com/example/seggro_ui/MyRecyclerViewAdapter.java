package com.example.seggro_ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seggro_ui.ui.main.NewImage;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

//    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    private List<Book> mData ;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Book> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final Context context;
        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();


            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String textVal = null;
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());


            View viewLinear = ((ViewGroup)view).getChildAt(0);
            Log.i("ggggggggLLLLchildiddd", String.valueOf(viewLinear));



            for(int index=0; index<((ViewGroup)viewLinear).getChildCount(); ++index) {
                View nextChild = ((ViewGroup)viewLinear).getChildAt(index);

                if(nextChild instanceof TextView){
                    TextView textView = (TextView) nextChild;
                      textVal =  textView.getText().toString();
                    Log.i("childiddd", textVal);

                    /// saving to database .....

                    // Insert operation in room Database
//        NoteRepository noteRepository = new NoteRepository(context.getApplicationContext());
//        String title = textVal;
//        String weatherDescription = "This is the description of the  task";
//        String temperature = "weather temp";
//        noteRepository.insertTask(title, weatherDescription,temperature);

                }

                Log.i("LLLLLLchildiddd", "fdfgdfgdfgd");




            }


//            GPSTracker gpsTracker = new GPSTracker(this);
//
//            Call<WeatherPOJO> listCall = service.getWeather(gpsTracker.getLatitude(),gpsTracker.getLongitude(),"metric",passwordString);



//            NoteRepository noteRepository = new NoteRepository(context.getApplicationContext());
//            String title = "cropName";
//            String description = "This is the description of the crop";
//            noteRepository.insertTask(title, description);




            //


            SharedPreferences sp = context.getApplicationContext().getSharedPreferences("WeatherData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();



            editor.putString("cropImage", textVal);




            editor.apply();

            final Intent intent;


            intent =  new Intent(context, NewOldChat.class);
            intent.putExtra("cropName",textVal);
            context.startActivity(intent);

//            Intent treatment = new Intent(Grid_dummy.this, NewOldChat.class);
//            startActivity(treatment);
        }
    }

    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData[id];
//    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
