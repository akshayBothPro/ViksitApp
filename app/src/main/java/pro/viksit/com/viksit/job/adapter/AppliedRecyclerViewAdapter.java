package pro.viksit.com.viksit.job.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.pojo.Applied;

/**
 * Created by Akshay on 19/03/2017.
 */

public class AppliedRecyclerViewAdapter extends  RecyclerView.Adapter<AppliedRecyclerViewAdapter.MyJobViewHolder>{

    private ArrayList<Applied> appliedJobs;

    public AppliedRecyclerViewAdapter(){

    }

    public AppliedRecyclerViewAdapter(ArrayList<Applied> appliedJobs) {
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

        public TextView jobProfile, company, status;
        public CardView cardView;
        public ImageView image;


        public MyJobViewHolder(View view) {
            super(view);
            jobProfile = (TextView) view.findViewById(R.id.tv_applied_item_title);
            company = (TextView) view.findViewById(R.id.tv_applied_item_subtitle);
            status = (TextView) view.findViewById(R.id.tv_applied_item_status);
            image = (ImageView)view.findViewById(R.id.iv_applied_item_image);
            cardView = (CardView)view.findViewById(R.id.cv_applied_card);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            System.out.println(view);
        }

    }

}
