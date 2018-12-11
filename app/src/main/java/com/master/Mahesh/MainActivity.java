package com.master.Mahesh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.master.Mahesh.room.RowDatabase;
import com.master.Mahesh.room.RowEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private View mRevealView;
    private View mRevealBackgroundView;
    private AppBarLayout appBarLayout;
    private int fromColor;
    DynamicFragmentAdapter mDynamicFragmentAdapter;
    List<RowEntity> prevAvailableList = new ArrayList<>();
    private String TAG = "MainActivity";
    private Spinner sp_replication,sp_treatment;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    DynamicFragment activeFragment;
    private int mCurrentFgmtOnBackPress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("TreatmentReplicationValue",MODE_PRIVATE);
        String treatment = sp.getString("treatment","#@");
        if(treatment.equals("#@")) {
            spe = sp.edit();
            spe.putString("treatment", "Treatment1");
            spe.putInt("treatmentPosition", 0);
            spe.putString("replication", "R1");
            spe.putInt("replicationPosition", 0);
            spe.commit();
        }
    }

    private void initViews() {
        mRevealView = findViewById(R.id.reveal);
        mRevealBackgroundView = findViewById(R.id.revealBackground);
        toolbar = findViewById(R.id.commToolbar);
        appBarLayout = findViewById(R.id.appbar);
        fromColor = R.color.colorTabOne;

        sp_replication = findViewById(R.id.sp_replication);
        sp_treatment = findViewById(R.id.sp_treatment);

        viewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.e("TAB CHANGED", "  onTabSelected  " + tab.getPosition());
                mCurrentFgmtOnBackPress = tab.getPosition();
                getFrgData(String.valueOf(tab.getPosition()));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                Log.e("TAB CHANGED", "  onTabUnselected " + tab.getPosition());
                saveData(tab.getPosition());
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("TAB CHANGED", "  onTabReselected  " + tab.getPosition());
            }
        });

        //set listener to spinner
        sp_treatment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTreatment = (String) parent.getSelectedItem();
                Log.e(TAG,"Selected treatment value => "+selectedTreatment);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        setDynamicFragmentToTabLayout();
    }

    public void saveFrgData(final List<RowEntity> prevAvailableList, final ArrayList<MyDataLiust> arrayList, final int tab_position, boolean flagForData) {
        /**
         *  Insert and get data using Database Async way
         */
        Log.e(TAG, "Before save the data tab position is " + tab_position);
        if (flagForData) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    // Insert Data
                    for (int i = 0; i < arrayList.size(); i++) {
                        RowDatabase
                                .getInstance(MainActivity.this)
                                .getRowDao()
                                .insert(new RowEntity(String.valueOf(tab_position), String.valueOf(arrayList.get(i).val1), String.valueOf(arrayList.get(i).val2), String.valueOf(arrayList.get(i).val3), String.valueOf(arrayList.get(i).val4)));
                    }
                }
            });
        } else {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    // Insert Data
                    for (int i = 0; i < arrayList.size(); i++) {
                        RowDatabase
                                .getInstance(MainActivity.this)
                                .getRowDao()
                                .updateRow(String.valueOf(prevAvailableList.get(i).getId()), String.valueOf(tab_position), String.valueOf(arrayList.get(i).val1), String.valueOf(arrayList.get(i).val2), String.valueOf(arrayList.get(i).val3), String.valueOf(arrayList.get(i).val4));
                    }
                }
            });
        }
    }

    public List<RowEntity> getFrgData(final String tab_position) {
        /**
         *  Insert and get data using Database Async way
         */
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // get Data by tab position
                List<RowEntity> list = RowDatabase
                        .getInstance(MainActivity.this.getApplicationContext())
                        .getRowDao()
                        .getRowByPositon(tab_position);
                Log.e(TAG, "List size =>> " + list.size());
                if (list.size() > 0) {
                    prevAvailableList = list;
                } else {
                    Log.e(TAG, "Data is not available for this tab position");
                    if (prevAvailableList.size() > 0)
                        Log.e(TAG, "Data is not available for this tab position prevAvailableList.size() > 0 ");
                    prevAvailableList.clear();
                }
            }
        });
        return prevAvailableList;
    }

    private void setDynamicFragmentToTabLayout() {
        for (int i = 0; i < 10; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText("PLANT: " + i));
        }
        mDynamicFragmentAdapter = new DynamicFragmentAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    private void animateAppAndStatusBar(int cx, final int toColor) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                mRevealView,
                cx,
                appBarLayout.getWidth(), 0,
                appBarLayout.getWidth() / 2);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mRevealView.setBackgroundColor(getResources().getColor(toColor));
            }
        });

        mRevealBackgroundView.setBackgroundColor(getResources().getColor(fromColor));
        animator.setStartDelay(200);
        animator.setDuration(125);
        animator.start();
        mRevealView.setVisibility(View.VISIBLE);
        fromColor = toColor;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change_treatment,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_change_treatment) {
            startActivity(new Intent(this,SecondActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData(mCurrentFgmtOnBackPress);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData(mCurrentFgmtOnBackPress);
    }

    public void saveData(int position){
        activeFragment = (DynamicFragment) mDynamicFragmentAdapter.instantiateItem(mTabLayout, position);//viewPager.getCurrentItem()
        ArrayList<MyDataLiust> data1 = activeFragment.getData1();

        if (prevAvailableList.size() > 0) {
            saveFrgData(prevAvailableList, data1, position, false);
        } else {
            saveFrgData(prevAvailableList, data1, position, true);
        }
    }
}
