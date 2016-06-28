package com.xf.gankapp.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xf.gankapp.R;
import com.xf.gankapp.presenter.AllPresenter;
import com.xf.gankapp.view.fragment.AllFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private final String[] FRAGMENT_TAG = new String[]{"all", "android", "ios"};
    private FragmentManager mFragmentManager;
    private AllFragment mAllFragment;

    @Bind(R.id.bottom_navigation_bar)
    public BottomNavigationBar mBottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_all, "全部"))
                .addItem(new BottomNavigationItem(R.drawable.ic_android, "android"))
                .addItem(new BottomNavigationItem(R.drawable.ic_ios, "ios"))
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                setTabSelection(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        setTabSelection(0);
    }


    /**
     * 切换界面
     *
     * @param position
     */
    private void setTabSelection(int position) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (position) {
            case 0://全部
                if (mAllFragment == null) {
                    mAllFragment = new AllFragment();
                    new AllPresenter(mAllFragment);
                    transaction.add(R.id.fragment_container, mAllFragment, FRAGMENT_TAG[0]);
                } else {
                    transaction.show(mAllFragment);
                }
                break;
            case 1://android

                break;
            case 2://ios
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    /**
     * 隐藏所有界面
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mAllFragment != null) {
            transaction.hide(mAllFragment);
        }
    }
}
