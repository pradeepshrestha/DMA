package com.allblue.kumarshashwat.anydo;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class List_Todo extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener, ListFragment.OnFragmentInteractionListener{
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGESGROCERY= {R.drawable.grocery1,R.drawable.grocery2,R.drawable.grocery3,R.drawable.grocery4,R.drawable.grocery5};
    private static final Integer[] IMAGESWORK= {R.drawable.work1,R.drawable.work2,R.drawable.work3,R.drawable.work5};
    private static final Integer[] IMAGESMOVIES= {R.drawable.movies1,R.drawable.movies2,R.drawable.movies3,R.drawable.movies4,R.drawable.movies5};
    private static final Integer[] IMAGESSCHOOL= {R.drawable.school1,R.drawable.school2,R.drawable.school3,R.drawable.school4,R.drawable.school5};
    private static final Integer[] IMAGESPLACES= {R.drawable.places1,R.drawable.places2,R.drawable.places3,R.drawable.places4,R.drawable.places5};
    private static final Integer[] IMAGESGOALS= {R.drawable.goals1,R.drawable.goals2,R.drawable.goals3,R.drawable.goals4,R.drawable.goals5};
    private static Integer[] IMAGES;
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__todo);
        String k=getIntent().getExtras().getString("name");
        init(k);
        Bundle bundle=new Bundle();
        bundle.putString("From Activity",k);
        BlankFragment imagefragment=new BlankFragment();
        ListFragment listFragment=new ListFragment();
        listFragment.setArguments(bundle);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame,imagefragment,"Image_Fragment");
        fragmentTransaction.add(R.id.list_frame,listFragment,"List_Fragment");
        fragmentTransaction.commit();
    }

        private void init(String k) {
            if(k.matches("GROCERY"))
                IMAGES=IMAGESGROCERY;
            if(k.matches("WORK"))
                IMAGES=IMAGESWORK;
            if(k.matches("MOVIES"))
                IMAGES=IMAGESMOVIES;
            if(k.matches("SCHOOL"))
                IMAGES=IMAGESSCHOOL;
            if(k.matches("PLACES"))
                IMAGES=IMAGESPLACES;
            if(k.matches("DAILY GOALS"))
                IMAGES=IMAGESGOALS;

            for(int i=0;i<IMAGES.length;i++)
                ImagesArray.add(IMAGES[i]);

            mPager = (ViewPager) findViewById(R.id.content_frame);


            mPager.setAdapter(new SlidingImage_Adapter(this,ImagesArray));

           /*CirclePageIndicator indicator = (CirclePageIndicator)
                    findViewById(R.id.indicator);

            indicator.setViewPager(mPager);

            */final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
            //indicator.setRadius(5 * density);

            NUM_PAGES =IMAGES.length;

            // Auto start of viewpager
            final Handler handler = new Handler() {
                @Override
                public void close() {

                }

                @Override
                public void flush() {

                }

                @Override
                public void publish(LogRecord record) {

                }
            };
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mPager.post(Update);
                }
            }, 6000, 6000);

            // Pager listener over indicator
            /*indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });
*/
        }




    @Override
    public void onFragmentInteraction(Uri uri) {
    }

}

