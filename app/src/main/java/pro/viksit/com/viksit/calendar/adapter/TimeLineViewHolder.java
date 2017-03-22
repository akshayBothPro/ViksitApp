package pro.viksit.com.viksit.calendar.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import pro.viksit.com.viksit.R;

/**
 * Created by Feroz on 22-03-2017.
 */

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    TextView mDate,mMessage,date,month;
    TimelineView mTimelineView;
    CardView cardView;
    LinearLayout main_layout,line;
    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        mDate = (TextView) itemView.findViewById(R.id.text_timeline_date);
        mMessage = (TextView) itemView.findViewById(R.id.text_timeline_title);
        mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        date = (TextView) itemView.findViewById(R.id.date);
        month = (TextView) itemView.findViewById(R.id.month);
        main_layout = (LinearLayout) itemView.findViewById(R.id.main_layout);
        line = (LinearLayout) itemView.findViewById(R.id.line);

        mTimelineView.initLine(viewType);


    }
}
