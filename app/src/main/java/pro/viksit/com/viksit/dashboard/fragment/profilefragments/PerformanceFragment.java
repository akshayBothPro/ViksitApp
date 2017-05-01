package pro.viksit.com.viksit.dashboard.fragment.profilefragments;


import android.content.Context;
import android.os.Bundle;
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

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.profile.PerformanceHorRecyclerAdapter;

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
    private RecyclerView horizontalRecycler, verticalExpandableRecycler;
    private PerformanceHorRecyclerAdapter hAdapter;

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
        verticalExpandableRecycler = (RecyclerView) view.findViewById(R.id.rv_performance_vertical_expandable_recycler);


        return view;
    }

    public void implementActions(){
        //setting up horizontal recycler view
        horizontalRecycler.setHasFixedSize(true);
        horizontalRecycler.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        horizontalRecycler.setAdapter(hAdapter);
        horizontalRecycler.setNestedScrollingEnabled(false);

        //setting up vertical expandable recycler view



    }

}
