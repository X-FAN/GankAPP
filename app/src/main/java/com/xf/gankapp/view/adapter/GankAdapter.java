package com.xf.gankapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xf.gankapp.bean.Gank;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by X-FAN on 2016/6/22.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {


    private List<Gank> mGankList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private void setData(List<Gank> gankList) {
        mGankList = gankList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
