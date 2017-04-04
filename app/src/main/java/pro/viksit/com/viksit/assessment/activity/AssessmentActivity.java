package pro.viksit.com.viksit.assessment.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.Role;

public class AssessmentActivity extends AppCompatActivity {

    private ArrayList<Question> questionArrayList;
    private LockableViewPager lockableViewPager;
    private AssessmentAdapter assessmentAdapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private Button jump_to;
    private boolean flag;
    private ArrayList<Role> roles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        lockableViewPager = (LockableViewPager) findViewById(R.id.viewpager);
        questionArrayList = new ArrayList<>();
        setupData();

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setAnimDuration(10000000);
        mWaveLoadingView.startAnimation();
        RecyclerView verticalRecycler = (RecyclerView) findViewById(R.id.recyclerView);

        setRoleData();
        RoleVerticalRecyclerViewAdapter roleVerticalRecyclerViewAdapter = new RoleVerticalRecyclerViewAdapter(roles,getBaseContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        verticalRecycler.setLayoutManager(linearLayoutManager);
        verticalRecycler.setItemAnimator(new DefaultItemAnimator());
        verticalRecycler.setAdapter(roleVerticalRecyclerViewAdapter);


        assessmentAdapter = new AssessmentAdapter(getSupportFragmentManager(), questionArrayList);
        lockableViewPager.setAdapter(assessmentAdapter);
        lockableViewPager.setSwipeLocked(true);
        lockableViewPager.setCurrentItem(1);
       bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottom_sheet)) ;
        jump_to = (Button) findViewById(R.id.jump_to);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                } else {

                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });


        jump_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    flag = true;

                }else {
                    flag = false;
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


    }






    public void setupData(){
        Option option1 = new Option("A. 100 kmph");
        Option option2 = new Option("B. 110 kmph");
        Option option3 = new Option("C. 130 kmph");
        Option option4 = new Option("D. 140 kmph");
        ArrayList<Option> optionArrayList = new ArrayList<>();
        optionArrayList.add(option1);
        optionArrayList.add(option2);
        optionArrayList.add(option3);
        optionArrayList.add(option4);
        Question question = new Question("A train can travel 50% faster than a car. Both start from point A at the same time and reach point B 75 kms away from A at the same time. On the way, however, the train lost about 12.5 minutes while stopping at the stations. The speed of the car is:", optionArrayList);




        Option option5 = new Option("A. The ski instructors at Top of the Peak Ski School work from December through March.");
        Option option6 = new Option("B. Matthew prefers jobs that allow him to work outdoors.");
        Option option7 = new Option("C. Lucinda makes an appointment with the beach resort restaurant manager to interview for the summer waitressing position that was advertised in the newspaper.");
        Option option8 = new Option("D. Doug's ice cream shop stays open until 11 p.m. during the summer months.");
        ArrayList<Option> optionArrayList1 = new ArrayList<>();
        optionArrayList1.add(option5);
        optionArrayList1.add(option6);
        optionArrayList1.add(option7);
        optionArrayList1.add(option8);
        Question question1 = new Question(
                "Applying for Seasonal Employment occurs when a person requests to be considered for a job that is dependent on a particular season or time of year. Which situation below is the best example of Applying for Seasonal Employment?", optionArrayList1);




        questionArrayList.add(question);
        questionArrayList.add(question1);


    }

    private void setRoleData(){
        roles = new ArrayList<>();
        Role role;
        for(int i = 0 ; i < 8 ; i++) {
            //role constructor => (int imageResID, String title, String subtitle, int totalItems, int completedItems)
            role = new Role(R.drawable.ic_7, "Game Designer", "User Interface Developer", 247, 131,"Recommended");
            roles.add(role);
            role = new Role(R.drawable.ic_8, "Game Designer", "User Interface Developer", 247, 231,"Finance");
            roles.add(role);
            role = new Role(R.drawable.ic_9, "Business Analyst", "Mutual Fund Planner", 247, 91,"Sales and Marketing");
            roles.add(role);
            role = new Role(R.drawable.ic_10, "Game Designer", "User Interface Developer", 247, 31,"Recommended");
            roles.add(role);
            role = new Role(R.drawable.ic_11, "Game Designer", "User Interface Developer", 247, 39,"Finance");
            roles.add(role);
            role = new Role(R.drawable.ic_12, "Business Analyst", "Mutual Fund Planner", 247, 51,"Sales and Marketing");
            roles.add(role);
        }
    }
}
