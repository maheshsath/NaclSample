package com.master.Mahesh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.master.Mahesh.callBack.CallBackListener;
import com.master.Mahesh.callBack.RegisterListener;


public class MainActivity extends AppCompatActivity implements CallBackListener {

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    private View mRevealView;
    private View mRevealBackgroundView;
    private AppBarLayout appBarLayout;
    private int fromColor;
    DynamicFragmentAdapter mDynamicFragmentAdapter;
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews(){
        mRevealView = findViewById(R.id.reveal);
        mRevealBackgroundView = findViewById(R.id.revealBackground);
        toolbar = findViewById(R.id.commToolbar);
        appBarLayout = findViewById(R.id.appbar);
        fromColor = R.color.colorTabOne;

        //setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        mTabLayout =  findViewById(R.id.tabs);
        viewPager.setOffscreenPageLimit(0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.e("TAB CHANGED","  onTabSelected  "+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("TAB CHANGED","  onTabUnselected "+tab.getPosition());

                int pos = viewPager.getCurrentItem();
                DynamicFragment activeFragment = (DynamicFragment) mDynamicFragmentAdapter.getItem(viewPager.getCurrentItem());

                activeFragment.getData1();
               // RegisterListener r = new RegisterListener();
               // mDynamicFragmentAdapter.setmCustomeListener(MainActivity.this);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("TAB CHANGED","  onTabReselected  "+tab.getPosition());


            }
        });
        setDynamicFragmentToTabLayout();
    }
    private void setDynamicFragmentToTabLayout() {
        for (int i = 0; i < 10; i++) {

            mTabLayout.addTab(mTabLayout.newTab().setText("Category: " + i));

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
    public void getData(String ss) {
        Log.e(TAG,ss);
    }
}
