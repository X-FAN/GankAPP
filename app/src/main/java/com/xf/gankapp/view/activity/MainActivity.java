package com.xf.gankapp.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.daimajia.androidanimations.library.YoYo;
import com.xf.gankapp.OnScrollChangeListener;
import com.xf.gankapp.R;
import com.xf.gankapp.animator.DownAnimator;
import com.xf.gankapp.animator.UpAnimator;
import com.xf.gankapp.presenter.AllPresenter;
import com.xf.gankapp.presenter.AndroidPresenter;
import com.xf.gankapp.presenter.IOSPresenter;
import com.xf.gankapp.util.L;
import com.xf.gankapp.view.fragment.AllFragment;
import com.xf.gankapp.view.fragment.AndroidFragment;
import com.xf.gankapp.view.fragment.IOSFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements OnScrollChangeListener {

    private boolean mIsBottomShow = true;
    private final String[] FRAGMENT_TAG = new String[]{"all", "android", "ios"};

    private DownAnimator mDownAnimator = new DownAnimator();
    private UpAnimator mUpAnimator = new UpAnimator();
    private FragmentManager mFragmentManager;
    private AllFragment mAllFragment;
    private AndroidFragment mAndroidFragment;
    private IOSFragment mIOSFragment;
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
                if (mAndroidFragment == null) {
                    mAndroidFragment = new AndroidFragment();
                    new AndroidPresenter(mAndroidFragment);
                    transaction.add(R.id.fragment_container, mAndroidFragment, FRAGMENT_TAG[1]);
                } else {
                    transaction.show(mAndroidFragment);
                }
                break;
            case 2://ios
                if (mIOSFragment == null) {
                    mIOSFragment = new IOSFragment();
                    new IOSPresenter(mIOSFragment);
                    transaction.add(R.id.fragment_container, mIOSFragment, FRAGMENT_TAG[2]);
                } else {
                    transaction.show(mIOSFragment);
                }
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
        if (mAndroidFragment != null) {
            transaction.hide(mAndroidFragment);
        }
        if (mIOSFragment != null) {
            transaction.hide(mIOSFragment);
        }
    }


    @Override
    public void onScrollChangeListener(boolean state) {
        if (!mDownAnimator.isRunning() && !mUpAnimator.isRunning()) {
            if (state) {//隐藏
                if (mIsBottomShow) {
                    YoYo.with(mDownAnimator).duration(300).playOn(mBottomNavigationBar);
                    mIsBottomShow = false;
                }
            } else {//显示
                if (!mIsBottomShow) {
                    YoYo.with(mUpAnimator).duration(300).playOn(mBottomNavigationBar);
                    mIsBottomShow = true;
                }
            }
            L.e("mIsBottomShow" + mIsBottomShow);
        }
    }
}
