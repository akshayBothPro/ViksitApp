package pro.viksit.com.viksit.job.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.adapter.AppliedRecyclerViewAdapter;
import pro.viksit.com.viksit.job.pojo.Applied;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppliedJobFragment extends Fragment {
    private static final String TAG = AppliedJobFragment.class.getSimpleName();

    View view;
    Context context;
    private RecyclerView recyclerView;
    private ArrayList<Applied> appliedJobs;
    private AppliedRecyclerViewAdapter mAdapter;
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
        implementActionsListeners();

        //
        prepareAppliedData();
        return view;
    }

    private void implementActionsListeners(){
        mAdapter = new AppliedRecyclerViewAdapter(context, appliedJobs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(mLayoutManager);
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

}