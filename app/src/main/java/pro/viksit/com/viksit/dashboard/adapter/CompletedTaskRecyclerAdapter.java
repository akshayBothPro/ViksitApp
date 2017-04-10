package pro.viksit.com.viksit.dashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.CompletedTask;

/**
 * Created by Akshay on 10/04/2017.
 */

public class CompletedTaskRecyclerAdapter extends RecyclerView.Adapter<CompletedTaskRecyclerAdapter.MyViewHolder>  {
    private ArrayList<CompletedTask> completedTaskArrayList;
    private Context context;
    private int screenWidth;
    private int screenHeight;

    public CompletedTaskRecyclerAdapter(ArrayList<CompletedTask> completedTaskArrayList,Context context, int screenWidth, int screenHeight) {
        this.completedTaskArrayList = completedTaskArrayList;
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.completed_task_recycler_item, parent, false);

        //
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CompletedTask completedTask = completedTaskArrayList.get(position);
        holder.title.setText(completedTask.getDescription());
        holder.time.setText(completedTask.getTime());

        if(completedTask.getType().equalsIgnoreCase("challenge")){
            holder.image.setBackgroundResource(R.mipmap.ic_challenge);
        } else if(completedTask.getType().equalsIgnoreCase("video")){
            holder.image.setImageResource(R.mipmap.ic_play_circle_outline_white_24dp);
        } else {
            holder.image.setImageResource(R.drawable.selected_circle_indicator);
        }
    }

    @Override
    public int getItemCount() {
        return completedTaskArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView title, time;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView)view.findViewById(R.id.iv_completed_task_item_icon);
            title = (TextView) view.findViewById(R.id.tv_completed_task_item_title);
            time = (TextView) view.findViewById(R.id.tv_completed_task_item_time);

        }

        @Override
        public void onClick(View view) {

        }

    }

}