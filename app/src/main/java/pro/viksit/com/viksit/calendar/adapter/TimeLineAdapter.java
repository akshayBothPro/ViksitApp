package pro.viksit.com.viksit.calendar.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vipulasri.timelineview.TimelineView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.calendar.pojo.CalendarData;

/**
 * Created by Feroz on 22-03-2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder>  {

    private List<CalendarData> calendarDataList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private final SimpleDateFormat monthsdf = new SimpleDateFormat("MMM");
    private final SimpleDateFormat datesdf = new SimpleDateFormat("dd");
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private ArrayList<String> historyDate = new ArrayList<>();
    public TimeLineAdapter(List<CalendarData> calendarDataList,Context context) {
        this.calendarDataList = calendarDataList;
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_timeline_line_padding, parent, false);


        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        CalendarData calendarData = calendarDataList.get(position);


        holder.mDate.setText(sdf.format(calendarData.getEvent_date()));
        holder.mMessage.setText(calendarData.getEvent_name());
        if(historyDate.contains(sdf.format(calendarData.getEvent_date()))) {


            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.linkedIn_color));
            if(isToday(calendarData.getEvent_date())){
                holder.main_layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
                holder.date.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.month.setTextColor(mContext.getResources().getColor(R.color.white));
            }

            try {
                if(ft.parse(ft.format(new Date())).compareTo(ft.parse(ft.format(calendarData.getEvent_date()))) == 0){
                    holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.linkedIn_color));
                    holder.line.setVisibility(View.INVISIBLE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {

            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.gray_pill));
            holder.date.setText(datesdf.format(calendarData.getEvent_date()));
            holder.month.setText(monthsdf.format(calendarData.getEvent_date()));
            if(isToday(calendarData.getEvent_date())){
                holder.main_layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));

            }
            holder.date.setTextColor(mContext.getResources().getColor(R.color.gray_pill));
            holder.month.setTextColor(mContext.getResources().getColor(R.color.gray_pill));
            historyDate.add(sdf.format(calendarData.getEvent_date()));
        }
        holder.date.setText(datesdf.format(calendarData.getEvent_date()));
        holder.month.setText(monthsdf.format(calendarData.getEvent_date()));

    }

    @Override
    public int getItemCount() {
        return (calendarDataList!=null? calendarDataList.size():0);
    }


    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * <p>Checks if a date is today.</p>
     * @param date the date, not altered, not null.
     * @return true if the date is today.
     * @throws IllegalArgumentException if the date is <code>null</code>
     */
    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }


}
