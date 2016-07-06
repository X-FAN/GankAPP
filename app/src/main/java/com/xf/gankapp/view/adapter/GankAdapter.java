package com.xf.gankapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xf.gankapp.OnItemClickListener;
import com.xf.gankapp.R;
import com.xf.gankapp.bean.Gank;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by X-FAN on 2016/6/22.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {


    private List<Gank> mGankList;
    private LayoutInflater mInflater;
    private OnItemClickListener mListener;

    public GankAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.gank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Gank gank = mGankList.get(position);
        holder.mAuthor.setText(gank.getWho());
        holder.mTitle.setText(gank.getDesc());
        holder.mDate.setText(gank.getPublishedAt());
        holder.mType.setText(gank.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int tempPosition = holder.getAdapterPosition();
                    mListener.onItemClickListener(tempPosition, mGankList.get(tempPosition));
                }
            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mGankList == null ? 0 : mGankList.size();
    }

    public void setData(List<Gank> gankList) {
        mGankList = gankList;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.author)
        TextView mAuthor;
        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.date)
        TextView mDate;
        @Bind(R.id.type)
        TextView mType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
