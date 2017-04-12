package pro.viksit.com.viksit.dashboard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vipulasri.timelineview.TimelineView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.calendar.adapter.TimeLineViewHolder;
import pro.viksit.com.viksit.calendar.util.VectorDrawableUtils;
import pro.viksit.com.viksit.dashboard.pojo.CompletedTask;
import pro.viksit.com.viksit.dashboard.pojo.Notification;

/**
 * Created by Akshay on 12/04/2017.
 */


public class CompletedTaskTimeLineAdapter extends RecyclerView.Adapter<CompletedTaskTimeLineViewHolder> {

    private ArrayList<CompletedTask> mFeedList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CompletedTaskTimeLineAdapter(Context mContext, ArrayList<CompletedTask> feedList) {
        this.mFeedList = feedList;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public CompletedTaskTimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view;
        view = mLayoutInflater.inflate(R.layout.completed_task_recycler_item, parent, false);

        return new CompletedTaskTimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CompletedTaskTimeLineViewHolder holder, int position) {

        CompletedTask timeLineModel = mFeedList.get(position);

        if(timeLineModel.getCompleted()) {
            if(timeLineModel.getType().equalsIgnoreCase("challenge")){
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.mipmap.ic_challenge));

                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_challenge);
                drawable.setColorFilter(new PorterDuffColorFilter(mContext.getResources().getColor(R.color.selected_circle_color),PorterDuff.Mode.SRC_IN));
            } else if(timeLineModel.getType().equalsIgnoreCase("video")){
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.mipmap.ic_play_circle_outline_white_24dp));
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_play_circle_outline_white_24dp);
                drawable.setColorFilter(new PorterDuffColorFilter(mContext.getResources().getColor(R.color.selected_circle_color),PorterDuff.Mode.SRC_IN));
            } else {
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.drawable.selected_circle_indicator));
            }
        } else if(!timeLineModel.getCompleted()){
            if(timeLineModel.getType().equalsIgnoreCase("challenge")){
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.mipmap.ic_challenge));
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_challenge);
                drawable.setColorFilter(new PorterDuffColorFilter(mContext.getResources().getColor(R.color.unselected_circle_color),PorterDuff.Mode.SRC_IN));
            } else if(timeLineModel.getType().equalsIgnoreCase("video")){
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.mipmap.ic_play_circle_outline_white_24dp));
                Drawable drawable = mContext.getResources().getDrawable(R.mipmap.ic_play_circle_outline_white_24dp);
                drawable.setColorFilter(new PorterDuffColorFilter(mContext.getResources().getColor(R.color.unselected_circle_color),PorterDuff.Mode.SRC_IN));
            } else {
                holder.mTimelineView.setMarker(mContext.getResources().getDrawable(R.drawable.unselected_circle_indicator));
            }
        }

        holder.time.setText(timeLineModel.getTime());
        holder.mMessage.setText(timeLineModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

}
