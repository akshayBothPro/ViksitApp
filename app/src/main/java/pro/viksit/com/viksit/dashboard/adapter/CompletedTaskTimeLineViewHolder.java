package pro.viksit.com.viksit.dashboard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import pro.viksit.com.viksit.R;

/**
 * Created by Akshay on 12/04/2017.
 */

public class CompletedTaskTimeLineViewHolder extends RecyclerView.ViewHolder {
    TextView mMessage,time;
    TimelineView mTimelineView;

    public CompletedTaskTimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        mMessage = (TextView) itemView.findViewById(R.id.tv_completed_task_item_title);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        time = (TextView) itemView.findViewById(R.id.tv_completed_task_item_time);

        mTimelineView.initLine(viewType);


    }
}
