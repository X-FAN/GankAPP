package com.xf.gankapp.view.fragment;


import android.content.Context;
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

import com.xf.gankapp.OnScrollChangeListener;
import com.xf.gankapp.R;
import com.xf.gankapp.bean.Gank;
import com.xf.gankapp.contract.AndroidContract;
import com.xf.gankapp.util.CommonUtils;
import com.xf.gankapp.util.T;
import com.xf.gankapp.view.adapter.GankAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AndroidFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AndroidContract.View {
    private AndroidContract.Presenter mPresenter;
    private OnScrollChangeListener mListener;

    private GankAdapter mGankAdapter;
    @Bind(R.id.all_gank_show)
    RecyclerView mAllGankShow;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnScrollChangeListener) {
            mListener = (OnScrollChangeListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnScrollChangeListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initViews(inflater, container);
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
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    public AndroidFragment() {
    }


    private View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_android, container, false);
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
                    mListener.onScrollChangeListener(true);
                } else if (dy < -5) {
                    mListener.onScrollChangeListener(false);

                }
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        mPresenter.subscribeAndroidGank(20, 1);
    }

    @Override
    public void showAndroidGank(List<Gank> gankList) {
        mGankAdapter.setData(gankList);
        hideLoading();
    }

    @Override
    public void setPresenter(AndroidContract.Presenter presenter) {
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

}
