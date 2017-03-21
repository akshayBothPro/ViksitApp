package pro.viksit.com.viksit.job.fragment;


import android.content.Context;
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


import java.util.ArrayList;

import pro.viksit.com.viksit.R;
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
    private Button all;
    private Button p;
    private Button t;
    private Button i;
    private Button r;
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
        all = (Button)view.findViewById(R.id.btn_all);
        p = (Button)view.findViewById(R.id.btn_p);
        t = (Button)view.findViewById(R.id.btn_t);
        i = (Button)view.findViewById(R.id.btn_i);
        r = (Button)view.findViewById(R.id.btn_r);
        /*ShapeDrawable drawable = (ShapeDrawable) getResources().getDrawable(R.drawable.circle_border);
        drawable.setColorFilter(Color.parseColor("#eb4b5f"), PorterDuff.Mode.SRC_IN);
        p.setBackground(drawable);*/

        prepareAppliedData();
        implementActionsListeners();
        //
        return view;
    }

    private void implementActionsListeners(){
        setFilterButtonColors(R.color.pending_color,p);
        setFilterButtonColors(R.color.t_color,t);
        setFilterButtonColors(R.color.interview_color,i);
        setFilterButtonColors(R.color.rejected_color,r);

        circle = (GradientDrawable)getResources().getDrawable(R.drawable.status_border);
        circle.setColor(getResources().getColor(R.color.black));
        circle.setStroke(3,getResources().getColor(R.color.black));
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
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );

        all.setOnClickListener(this);
        p.setOnClickListener(this);
        t.setOnClickListener(this);
        i.setOnClickListener(this);
        r.setOnClickListener(this);
    }

    private void setFilterButtonColors(int color, Button button){

        circle = (GradientDrawable)getResources().getDrawable(R.drawable.circle_border);
        circle.setStroke(3, getResources().getColor(color));

        button.setBackground(circle); // set stroke width and stroke color
        button.setTextColor(getResources().getColor(color));
    }

    private void prepareAppliedData(){
        appliedJobs = new ArrayList<>();
        // AppliedJob constructor => (int imageRes, String jobProfile, String companyName, String status)
        Applied appliedJob = new Applied(R.mipmap.ic_assignment_black_24dp,"Front-end Developer", "by Parallel Labs","PENDING");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_notifications_active_black_24dp,"Junior software engineer", "by Wipro","REJECTED");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_event_note_black_24dp,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_monetization_on_black_24dp,"Junior software engineer", "by Wipro","PENDING");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_tag_faces_black_24dp,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_adjust_black_24dp,"Junior software engineer", "by Wipro","REJECTED");
        appliedJobs.add(appliedJob);

        appliedJob = new Applied(R.mipmap.ic_work_black_24dp,"Front-end Developer", "by Parallel Labs","INTERVIEW");
        appliedJobs.add(appliedJob);
    }

    @Override
    public void onClick(View v) {
        String search = "";

        if(v.getId() == R.id.btn_all){
            search = "all";
        } else if(v.getId() == R.id.btn_p){
            search = "pending";
        } else if(v.getId() == R.id.btn_t){
            search = "t";
        } else if(v.getId() == R.id.btn_i){
            search = "interview";
        } else if(v.getId() == R.id.btn_r){
            search = "rejected";
        }

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