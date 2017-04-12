package pro.viksit.com.viksit.dashboard.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.adapter.CompletedTaskTimeLineAdapter;
import pro.viksit.com.viksit.dashboard.pojo.CompletedTask;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;
import pro.viksit.com.viksit.role.activity.RoleDetailActivity;
import pro.viksit.com.viksit.role.util.RecyclerItemClickListener;

public class CompletedTaskFragment extends Fragment {

    private static final String SCALE = "scale";

    private CardView cardView;
    private TextView header;
    private TextView title;
    private TextView remainingTasks;
    private RecyclerView verticalRecycler;
    private CompletedTaskTimeLineAdapter adapter;
    private ArrayList<CompletedTask> completedTaskArrayList;

    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    public static Fragment newInstance(Activity context, DashboardCard dashboardCard, float scale) {
        Bundle b = new Bundle();
        b.putSerializable("card",dashboardCard);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, CompletedTaskFragment.class.getName(), b);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null ) {
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_completed_task, container, false);

        if(getArguments() != null){
            DashboardCard dashboardCard = (DashboardCard) getArguments().getSerializable("card");
            float scale = this.getArguments().getFloat(SCALE);

            Double d, d1;
            if (diagonalInches>=6.5){
                // 6.5inch device or bigger
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.3);
            }else{
                // smaller device
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.6);
            }
            int screenwidth = d.intValue();;
            int screenheitght = d1.intValue();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenwidth, screenheitght);
            cardView = (CardView) linearLayout.findViewById(R.id.card_view);
            header = (TextView) linearLayout.findViewById(R.id.tv_completed_task_header);
            title = (TextView) linearLayout.findViewById(R.id.tv_completed_task_title);
            verticalRecycler = (RecyclerView) linearLayout.findViewById(R.id.rv_completed_task);
            remainingTasks = (TextView) linearLayout.findViewById(R.id.tv_remaining_task);

            setDummylistData();
            implementActions();

            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);

        }

        return linearLayout;
    }


    private void implementActions(){

        verticalRecycler.setHasFixedSize(true);
        adapter = new CompletedTaskTimeLineAdapter(getContext(),completedTaskArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(adapter);
        verticalRecycler.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), verticalRecycler ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println("Vposition: " + position);
                        Intent intent = new Intent(getActivity(), RoleDetailActivity.class);
                       /* Bundle bundle = new Bundle();
                        bundle.putSerializable("role", roles.get(position));
                        intent.putExtras(bundle);*/
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do something
                    }
                })
        );



    }

    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        float yInches= displaymetrics.heightPixels/displaymetrics.ydpi;
        float xInches= displaymetrics.widthPixels/displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
    }

    private void setDummylistData(){
        completedTaskArrayList = new ArrayList<>();
        CompletedTask task;

        task = new CompletedTask("challenge", "Won against Siddharth","at 12:10pm",false);
        completedTaskArrayList.add(task);

        task = new CompletedTask("video", "Won against Siddharth","at 12:10pm",true);
        completedTaskArrayList.add(task);

        task = new CompletedTask("challenge", "Won against Siddharth","at 12:10pm",true);
        completedTaskArrayList.add(task);

        task = new CompletedTask("video", "Won against Siddharth","at 12:10pm",false);
        completedTaskArrayList.add(task);

        task = new CompletedTask("", "Won against Siddharth","at 12:10pm",false);
        completedTaskArrayList.add(task);
    }

}
