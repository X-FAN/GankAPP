package com.xf.gankapp.view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xf.gankapp.R;
import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.contract.AllContract;
import com.xf.gankapp.util.CommonUtils;
import com.xf.gankapp.util.T;
import com.xf.gankapp.view.activity.MainActivity;
import com.xf.gankapp.view.adapter.GankAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements AllContract.View, SwipeRefreshLayout.OnRefreshListener {

    private AllContract.Presenter mPresenter;

    private GankAdapter mGankAdapter;
    @Bind(R.id.all_gank_show)
    RecyclerView mAllGankShow;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    public AllFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater, container);
    }

    private View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(this);
        mAllGankShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGankAdapter = new GankAdapter(getActivity());
        mAllGankShow.setAdapter(mGankAdapter);
        mAllGankShow.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    ((MainActivity) getActivity()).mBottomNavigationBar.setVisibility(View.GONE);
                } else if (dy < -5) {
                    ((MainActivity) getActivity()).mBottomNavigationBar.setVisibility(View.VISIBLE);

                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CommonUtils.lazyLoad(getActivity(), new Handler(), new Runnable() {
            @Override
            public void run() {
                showLoading();
                onRefresh();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void showAllGank(List<Gank> gankList) {
        mGankAdapter.setData(gankList);
        hideLoading();
    }

    @Override
    public void setPresenter(AllContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showTip(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            T.showLong(getActivity(), msg);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.subscribeAllGank(20, 1);
    }
}
