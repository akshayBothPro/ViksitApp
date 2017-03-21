package pro.viksit.com.viksit.job.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.pojo.Applied;

/**
 * Created by Akshay on 19/03/2017.
 */

public class AppliedRecyclerViewAdapter extends  RecyclerView.Adapter<AppliedRecyclerViewAdapter.MyJobViewHolder>{

    private ArrayList<Applied> appliedJobs = new ArrayList<>();
    private Context context;

    public AppliedRecyclerViewAdapter(){

    }

    public AppliedRecyclerViewAdapter(Context context, ArrayList<Applied> appliedJobs) {
        this.context = context;
        this.appliedJobs = appliedJobs;
    }

    @Override
    public MyJobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.applied_job_card_item, parent, false);

        return new MyJobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyJobViewHolder holder, int position) {
        Applied appliedJob = appliedJobs.get(position);
        if(appliedJob.getStatus().toLowerCase().toString().equalsIgnoreCase("pending")){
            holder.status.setTextColor(context.getResources().getColor(R.color.pending_color));
            holder.rectangle.setStroke(3, context.getResources().getColor(R.color.pending_color));
            holder.rectangle.setColor(context.getResources().getColor(R.color.white_color));
            holder.status.setBackground(holder.rectangle); // set stroke width and stroke color
        } else if(appliedJob.getStatus().toLowerCase().toString().equalsIgnoreCase("rejected")){
            holder.status.setTextColor(context.getResources().getColor(R.color.rejected_color));
            holder.rectangle.setStroke(3, context.getResources().getColor(R.color.rejected_color));
            holder.rectangle.setColor(context.getResources().getColor(R.color.white_color));
            holder.status.setBackground(holder.rectangle); // set stroke width and stroke color
        } else if(appliedJob.getStatus().toLowerCase().toString().equalsIgnoreCase("interview")){
            holder.status.setTextColor(context.getResources().getColor(R.color.interview_color));
            holder.rectangle.setStroke(3, context.getResources().getColor(R.color.interview_color));
            holder.rectangle.setColor(context.getResources().getColor(R.color.white_color));
            holder.status.setBackground(holder.rectangle); // set stroke width and stroke color
        } else if(appliedJob.getStatus().toLowerCase().toString().equalsIgnoreCase("t")){
            holder.status.setTextColor(context.getResources().getColor(R.color.t_color));
            holder.rectangle.setStroke(3, context.getResources().getColor(R.color.t_color));
            holder.rectangle.setColor(context.getResources().getColor(R.color.white_color));
            holder.status.setBackground(holder.rectangle); // set stroke width and stroke color
        }
        holder.jobProfile.setText(appliedJob.getTitle());
        holder.company.setText(appliedJob.getSubtitle());
        holder.status.setText(appliedJob.getStatus());
        holder.image.setImageResource(appliedJob.getImageResID());
    }

    @Override
    public int getItemCount() {
        if (appliedJobs == null)
            return 0;
        else
            return  appliedJobs.size();
    }

    public class MyJobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView jobProfile, company;
        public Button status;
        public CardView cardView;
        public ImageView image;
        public GradientDrawable rectangle;


        public MyJobViewHolder(View view) {
            super(view);
            jobProfile = (TextView) view.findViewById(R.id.tv_applied_item_title);
            company = (TextView) view.findViewById(R.id.tv_applied_item_subtitle);
            status = (Button) view.findViewById(R.id.btn_applied_item_status);
            image = (ImageView)view.findViewById(R.id.iv_applied_item_image);
            cardView = (CardView)view.findViewById(R.id.cv_applied_card);
            cardView.setOnClickListener(this);
            rectangle = (GradientDrawable)context.getResources().getDrawable(R.drawable.status_border);
        }

        @Override
        public void onClick(View view) {
            System.out.println(view);
        }

    }

}
