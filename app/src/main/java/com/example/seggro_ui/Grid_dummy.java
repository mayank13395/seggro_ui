package com.example.seggro_ui;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.graphics.drawable.AnimatedImageDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Grid_dummy extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    MyRecyclerViewAdapter adapter;
    List<Book> lstBook ;
    RecyclerView scrollView;
    private int overallXScroll = 0;
    LinearLayout topnav;

//    List<Book>

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_dummy);

//
         lstBook = new ArrayList<>();
        lstBook.add(new Book("Apple",R.drawable.apple));
        lstBook.add(new Book("Banana",R.drawable.banana));
//        lstBook.add(new Book("Brinjal",R.drawable.brinjal));
        lstBook.add(new Book("Capsicum",R.drawable.capsicum));
        lstBook.add(new Book("Corn",R.drawable.corn));
        lstBook.add(new Book("Mango",R.drawable.mango));
        lstBook.add(new Book("Brinjal",R.drawable.brinjal));
        lstBook.add(new Book("Cucumber",R.drawable.cucumber));
//        lstBook.add(new Book("Maize",R.drawable.maize));
        lstBook.add(new Book("Orange",R.drawable.orange));
        lstBook.add(new Book("Potato",R.drawable.potato));
        lstBook.add(new Book("Tomato",R.drawable.tomato));
//        lstBook.add(new Book("The Vegitarian",R.drawable.thevigitarian));
//        lstBook.add(new Book("The Vegitarian",R.drawable.thevigitarian));
//        lstBook.add(new Book("The Vegitarian",R.drawable.thevigitarian));
//        lstBook.add(new Book("The Vegitarian",R.drawable.thevigitarian));
//        lstBook.add(new Book("The Vegitarian",R.drawable.thevigitarian));
//


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        MyRecyclerViewAdapter myAdapter = new MyRecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);


        scrollView = (RecyclerView) findViewById(R.id.recyclerview_id);
        topnav = (LinearLayout)findViewById(R.id.heading);
         final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) topnav.getLayoutParams();
        final RelativeLayout.LayoutParams paramsRecycleview = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();

        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;

        scrollView.addItemDecoration(new RecycleViewSpacing(spanCount,spacing,includeEdge));





        // dp as pixel

        float scale = getResources().getDisplayMetrics().density;
//        int dpAsPixels = (int) (sizeInDp*scale + 0.5f);
        final int recycleViewTopPadding = (int) (20*scale + 0.5f);
        final int recycleViewTopPadding2 = (int) (70*scale + 0.5f);





//        scrollView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                Log.i("verticalValue", String.valueOf(dy));
//
//                final int scrollOffset = recyclerView.computeHorizontalScrollOffset();
//
//                final int scrolloffsetvertical = recyclerView.computeVerticalScrollOffset();
//
//
//
//                Log.i("horizontal", String.valueOf(scrollOffset));
//                Log.i("verticalVerticle", String.valueOf(scrolloffsetvertical));
//
//                final int width = recyclerView.getWidth();
//                final int currentPage = scrollOffset / width;
//                final float pageOffset = (float) (scrollOffset % width) / width;
//
//                if(scrolloffsetvertical>0) {
//
//// Changes the height and width to the specified *pixels*
//                    params.height = 300;
////                    scrollView.setPadding(0,recycleViewTopPadding,0,0);
////                    paramsRecycleview.setMargins(20,300,20,0);
//
//                    topnav.setLayoutParams(params);
//                    scrollView.setPadding(0,recycleViewTopPadding,0,0);
//                }else if(scrolloffsetvertical==0){
//                    params.height = 400;
////                    paramsRecycleview.setMargins(20,500,20,0);
////                    scrollView.setPadding(0,recycleViewTopPadding2,0,0);
//                    scrollView.setPadding(0,recycleViewTopPadding2,0,0);
//                    topnav.setLayoutParams(params);
//
////
//                }
//
//                // the following lines just the example of how you can use it
////                if (currentPage == 0) {
//////                    AnimatedImageDrawable leftIndicator = null;
//////                    leftIndicator.setAlpha(pageOffset);
//////                    android.R.attr.indicatorLeft
////                }
//
////                if (adapter.getItemCount() <= 1) {
////                    rightIndicator.setAlpha(pageOffset);
////                } else if (currentPage == adapter.getItemCount() - 2) {
////                    rightIndicator.setAlpha(1f - pageOffset);
////                }
//            }
//        });












       
//       scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
////           @RequiresApi(api = Build.VERSION_CODES.M)
//           @Override
//           public void onScrollChanged() {
//
//
//           }
////               AssistStructure.ViewNode rootScrollView = null;
////               int scrollY = rootScrollView.getScrollY(); // For ScrollView
////               int scrollX = rootScrollView.getScrollX(); // For HorizontalScrollView
//               // DO SOMETHING WITH THE SCROLL COORDINATES
////               int scrollY = rootScrollView.getScrollY();
////               int scrollX = rootScrollView.getScrollX();
//
////               Log.i("scrollyValue", String.valueOf(scrollY));
//
//
//
//           }
//
//
//           public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////
////                overallXScroll = overallXScroll + dx;
////                Log.i("check","overall X  = " + overallXScroll);
////
////            }
//       });

//
//        LinearLayoutManager myLayoutManager = (LinearLayoutManager) scrollView.getLayoutManager();
//        int scrollPosition = myLayoutManager.findFirstVisibleItemPosition();



        //...
//        scrollView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                overallXScroll = overallXScroll + dx;
//                Log.i("check","overall X  = " + overallXScroll);
//
//            }
//        });




    }

    public void gotoplanttreatment(View view) {

//        Intent treatment = new Intent(Grid_dummy.this, NewOldChat.class);
//        startActivity(treatment);
    }

    @Override
    public void onItemClick(View view, int position) {

//        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);

    }



//    scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//        @Override
//        public void onScrollChanged() {
////            int scrollY = rootScrollView.getScrollY(); // For ScrollView
////            int scrollX = rootScrollView.getScrollX(); // For HorizontalScrollView
////            // DO SOMETHING WITH THE SCROLL COORDINATES
//        }
//    });
}



