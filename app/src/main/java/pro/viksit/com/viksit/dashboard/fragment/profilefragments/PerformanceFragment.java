package pro.viksit.com.viksit.dashboard.fragment.profilefragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.profile.PerformanceHorRecyclerAdapter;
import pro.viksit.com.viksit.dashboard.adapter.profile.PerformanceVerExpandableAdapter;
import pro.viksit.com.viksit.dashboard.pojo.ParentSkill;
import pro.viksit.com.viksit.dummy.pojo.SkillReportPOJO;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerformanceFragment extends Fragment {

    View view;
    Context context = getContext();
    private AppBarLayout appBarLayout;
    private ImageView image;
    private ImageButton upload;
    private TextView rank, pointsXP, profileName;
    private RecyclerView horizontalRecycler, verExpandableRecycler;
    private PerformanceHorRecyclerAdapter hAdapter;
    private PerformanceVerExpandableAdapter vAdapter;
    private int lastExpandedPosition;

    private SharedPreferences sharedpreferences;
    List<SkillReportPOJO> skills;

    public PerformanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_performance, container, false);

        appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        image = (ImageView) view.findViewById(R.id.iv_image);
        upload = (ImageButton) view.findViewById(R.id.ibtn_change_photo);
        rank = (TextView) view.findViewById(R.id.tv_rank);
        pointsXP = (TextView) view.findViewById(R.id.tv_xp_points);
        profileName = (TextView) view.findViewById(R.id.tv_profile_name);
        horizontalRecycler = (RecyclerView) view.findViewById(R.id.rv_performance_horizontal_recycler);
        verExpandableRecycler = (RecyclerView) view.findViewById(R.id.rv_performance_vertical_expandable_recycler);

        skills = getDynamicData(skills);



        return view;
    }

    public void implementActions(){
        //setting up horizontal recycler view
        hAdapter = new PerformanceHorRecyclerAdapter(getContext(), (ArrayList<SkillReportPOJO>) skills);
        horizontalRecycler.setHasFixedSize(true);
        horizontalRecycler.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        horizontalRecycler.setAdapter(hAdapter);
        horizontalRecycler.setNestedScrollingEnabled(false);

        //setting up vertical expandable recycler view
        vAdapter = new PerformanceVerExpandableAdapter(this.getContext(),skills.get(0).getParentSkills());
        verExpandableRecycler.setAdapter(vAdapter);
        verExpandableRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        vAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                ParentSkill expandedRecipe = skills.get(0).getParentSkills().get(parentPosition);

                String toastMsg = "ee "+expandedRecipe.getName();
                Toast.makeText(PerformanceFragment.this.getContext(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
                if (lastExpandedPosition != -1
                        && parentPosition != lastExpandedPosition) {
                    vAdapter.collapseParent(lastExpandedPosition);
                }
                lastExpandedPosition = parentPosition;
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                ParentSkill collapsedRecipe = skills.get(0).getParentSkills().get(parentPosition);

                String toastMsg = "cc "+collapsedRecipe.getName();
                Toast.makeText(PerformanceFragment.this.getContext(),
                        toastMsg, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public List<SkillReportPOJO> getDynamicData(List<SkillReportPOJO> skills){
        sharedpreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        Map<String,?> keys = sharedpreferences.getAll();
        skills = new ArrayList<>();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();//"2016-09-13 12:15:00",
        for(Map.Entry<String,?> entry : keys.entrySet()){
            if(entry.getKey().contains(getResources().getString(R.string.skillreportstore))){
                skills.add(gson.fromJson( entry.getValue().toString(),SkillReportPOJO.class));
            }
        }

        return skills;
    }

}
