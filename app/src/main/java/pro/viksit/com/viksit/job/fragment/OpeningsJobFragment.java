package pro.viksit.com.viksit.job.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.truizlop.sectionedrecyclerview.SectionedSpanSizeLookup;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.adapter.CountSectionAdapter;
import pro.viksit.com.viksit.job.pojo.Opening;

/**
 * A simple {@link Fragment} subclass.
 */
public class OpeningsJobFragment extends Fragment {
    private static final String TAG = OpeningsJobFragment.class.getSimpleName();

    View view;
    Context context;
    /*MainActivity mainActivity;*/
    private RecyclerView recyclerView;
    private ArrayList<Opening> openingJobs = new ArrayList<>();

    public OpeningsJobFragment() {
        // Required empty public constructor
    }

    /*public OpeningsJobsFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_openings_job, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_openings_job);
        context=getContext();

        //
        prepareOpeningsData();
        implementActions();


        return view;
    }

    public void implementActions(){
        ArrayList<Opening> invites = new Opening().getParticularSectionItems(openingJobs,"invites");
        ArrayList<Opening> openings = new Opening().getParticularSectionItems(openingJobs,"open jobs");

        CountSectionAdapter adapter = new CountSectionAdapter(this.context,invites,openings,openingJobs);
        recyclerView.setAdapter(adapter);
       /* GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(adapter, layoutManager);
        layoutManager.setSpanSizeLookup(lookup);*/
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.context);

        recyclerView.setLayoutManager(mLayoutManager);
    }


    private void prepareOpeningsData(){
        // OpeningJob constructor => (int imageRes, String jobProfile, String companyName, List<OpeningsJobBadge> badges)
        Opening openingJob = new Opening("Invites", R.drawable.ic_1,"Front-end Developer", "by Parallel Labs", "Expies in 2 days");
        openingJobs.add(openingJob);

        for(int i = 0 ; i < 7 ; i++) {
            openingJob = new Opening("Invites", R.drawable.ic_3, "Junior software engineer", "by Wipro", "Expies in 2 days");
            openingJobs.add(openingJob);

            openingJob = new Opening("Invites", R.drawable.ic_5, "Front-end Developer", "by Parallel Labs", "Expies in 2 days");
            openingJobs.add(openingJob);

            openingJob = new Opening("Invites", R.drawable.ic_7, "Junior software engineer", "by Wipro", "Expies in 2 days");
            openingJobs.add(openingJob);

            openingJob = new Opening("Open Jobs", R.drawable.ic_9, "Front-end Developer", "by Parallel Labs", "Expies in 2 days");
            openingJobs.add(openingJob);

            openingJob = new Opening("Open Jobs", R.drawable.ic_11, "Junior software engineer", "by Wipro", "Expies in 2 days");
            openingJobs.add(openingJob);

            openingJob = new Opening("Open Jobs", R.drawable.ic_13, "Front-end Developer", "by Parallel Labs", "Expies in 2 days");
            openingJobs.add(openingJob);
        }
    }


}
