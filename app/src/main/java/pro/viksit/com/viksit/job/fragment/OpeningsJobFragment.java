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
import android.widget.LinearLayout;


import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.job.adapter.MainAdapter;
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
    private ArrayList<Opening> openingJobs;

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
        context=getContext();

        //
        /*prepareOpeningsData();*/

        return view;
    }



    /*private void prepareOpeningsData(){
        // OpeningJob constructor => (int imageRes, String jobProfile, String companyName, List<OpeningsJobBadge> badges)
        Opening openingJob = new Opening(R.drawable.ic_business_center_black_36dp,"Front-end Developer", "by Parallel Labs", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_accessibility_black_36dp,"Junior software engineer", "by Wipro", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_nfc_black_36dp,"Front-end Developer", "by Parallel Labs", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_drafts_black_36dp,"Junior software engineer", "by Wipro", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_assignment_ind_black_36dp,"Front-end Developer", "by Parallel Labs", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_speaker_notes_black_36dp,"Junior software engineer", "by Wipro", null);
        openingJobs.add(openingJob);

        openingJob = new Opening(R.drawable.ic_public_black_36dp,"Front-end Developer", "by Parallel Labs", null);
        openingJobs.add(openingJob);
    }*/


}
