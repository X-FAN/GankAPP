package com.xf.gankapp.view.fragment;


import android.os.Bundle;
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
import com.xf.gankapp.presenter.AllPresenter;
import com.xf.gankapp.util.L;
import com.xf.gankapp.util.T;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements AllContract.View {

    private AllContract.Presenter mPresenter;

    @Bind(R.id.all_gank_show)
    RecyclerView mAllGankShow;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    public AllFragment() {
        mPresenter = new AllPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = initViews(inflater, container);
        return view;
    }

    private View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mAllGankShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subcribeAllGank(20, 1);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showAllGank(List<Gank> gankList) {
        showTip("执行了");
        L.e(gankList.toString());
    }

    @Override
    public void setPresenter(AllContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showTip(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            T.showLong(getActivity(), msg);
        }
    }
}
