package pro.viksit.com.viksit.job.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.activity.JobDetailActivity;
import pro.viksit.com.viksit.job.adapter.AppliedRecyclerViewAdapter;
import pro.viksit.com.viksit.job.pojo.Applied;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedJobFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = AppliedJobFragment.class.getSimpleName();

    View view;
    Context context;
    private RecyclerView recyclerView;
    private ArrayList<Applied> appliedJobs;
    private AppliedRecyclerViewAdapter mAdapter;
    private TextView all;
    private TextView pending;
    private TextView test;
    private TextView interview;
    private GradientDrawable circle;
    //
    public AppliedJobFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_applied_job, container, false);
        context=getContext();
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_applied_job);
        all = (TextView)view.findViewById(R.id.tv_all);
        pending = (TextView)view.findViewById(R.id.tv_p);
        test = (TextView)view.findViewById(R.id.tv_t);
        interview = (TextView)view.findViewById(R.id.tv_i);
        /*ShapeDrawable drawable = (ShapeDrawable) getResources().getDrawable(R.drawable.circle_border);
        drawable.setColorFilter(Color.parseColor("#eb4b5f"), PorterDuff.Mode.SRC_IN);
        p.setBackground(drawable);*/

        prepareAppliedData();
        implementActionsListeners();
        //
        return view;
    }

    private void implementActionsListeners(){
        setFilterButtonColors(R.color.pending_color,pending);
        setFilterButtonColors(R.color.t_color,test);
        setFilterButtonColors(R.color.interview_color,interview);

        circle = (GradientDrawable)getResources().getDrawable(R.drawable.status_border);
        circle.setColor(getResources().getColor(R.color.all_background));
        circle.setStroke(3,getResources().getColor(R.color.all_background));
        all.setBackground(circle);
        //
        mAdapter = new AppliedRecyclerViewAdapter(context, appliedJobs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Module position: " + position);
                        /*Intent intent = new Intent(RoleActivity.this, ModuleViewActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);*/
                        Intent j = new Intent(getActivity(),JobDetailActivity.class);
                        startActivity(j);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

        all.setOnClickListener(this);
        pending.setOnClickListener(this);
        test.setOnClickListener(this);
        interview.setOnClickListener(this);
        /*r.setOnClickListener(this);*/
    }

    private void setFilterButtonColors(int color, TextView button){

        circle = (GradientDrawable)getResources().getDrawable(R.drawable.status_border);
        circle.setStroke(3, getResources().getColor(color));
        circle.setColor(getResources().getColor(R.color.white_color));

        button.setBackground(circle); // set stroke width and stroke color
        button.setTextColor(getResources().getColor(color));
    }

    private void prepareAppliedData(){
        appliedJobs = new ArrayList<>();
        // AppliedJob constructor => (int imageRes, String jobProfile, String companyName, String status)
        Applied appliedJob = new Applied(R.drawable.ic_1,"Front-end Developer", "by Parallel Labs","PENDING");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_1,"Junior software engineer", "by Wipro","REJECTED");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_2,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_3,"Junior software engineer", "by Wipro","PENDING");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_4,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_5,"Junior software engineer", "by Wipro","REJECTED");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.drawable.ic_6,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);
    }

    @Override
    public void onClick(View v) {
        String search = "";

        if(v.getId() == R.id.tv_all){
            search = "all";
        } else if(v.getId() == R.id.tv_p){
            search = "pending";
        } else if(v.getId() == R.id.tv_t){
            search = "t";
        } else if(v.getId() == R.id.tv_i){
            search = "interview";
        }/* else if(v.getId() == R.id.btn_r){
            search = "rejected";
        }*/

        if(search.equalsIgnoreCase("all")){
            mAdapter = new AppliedRecyclerViewAdapter(context, appliedJobs);
        }else {
            ArrayList<Applied> list = new ArrayList();
            for (Applied applied : appliedJobs) {
                if (applied.getStatus().toLowerCase().toString().equalsIgnoreCase(search)) {
                    list.add(applied);
                }
            }
            mAdapter = new AppliedRecyclerViewAdapter(context, list);
        }
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}