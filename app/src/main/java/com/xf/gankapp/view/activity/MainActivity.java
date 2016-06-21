package com.xf.gankapp.view.activity;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xf.gankapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_all, "全部"))
                .addItem(new BottomNavigationItem(R.drawable.ic_android, "android"))
                .addItem(new BottomNavigationItem(R.drawable.ic_ios, "ios"))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
