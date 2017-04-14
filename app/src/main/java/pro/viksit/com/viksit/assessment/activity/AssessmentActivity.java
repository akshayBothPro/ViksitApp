package pro.viksit.com.viksit.assessment.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import me.itangqi.waveloadingview.WaveLoadingView;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.adapter.AssessmentTotalTimer;
import pro.viksit.com.viksit.assessment.adapter.QuestionsRecyclerViewAdapter;
import pro.viksit.com.viksit.assessment.adapter.Questiontimer;
import pro.viksit.com.viksit.assessment.async.FetchAssessmentData;
import pro.viksit.com.viksit.assessment.fragment.QuestionFragment;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.Role;

public class AssessmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Question> questionArrayList;
    public LockableViewPager lockableViewPager;

    private AssessmentAdapter assessmentAdapter;
    private BottomSheetBehavior bottomSheetBehavior;
    private Button jump_to;
    private boolean flag;
    private ArrayList<Role> roles;


    private ImageView toolbar_close,close_bottomsheet;
    private TextView correctanswer,timer;
    private Button prev,next,view_all;
    private BottomSheetBehavior mBottomSheetBehavior1;
    private RelativeLayout bottom_buttons,close_layout;
    private RecyclerView questionListRecycler;
    Animation animFadeOut,animFadeIn,animFadeOut1,animFadeIn1;
    private AssessmentTotalTimer totalTimer;
    private Questiontimer questiontimer;
    private ProgressBar progress;
    public WaveLoadingView mWaveLoadingView;
    private AssessmentPOJO assessmentPOJO;
    private RecyclerView verticalRecycler;
    private QuestionsRecyclerViewAdapter questionadapter;
    private AssessmentResultPojo assessmentResultPojo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        toolbar_close = (ImageView) findViewById(R.id.toolbar_close);
        close_bottomsheet = (ImageView) findViewById(R.id.close_bottomsheet);
        correctanswer = (TextView) findViewById(R.id.correctanswer);
        timer = (TextView) findViewById(R.id.timer);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        view_all = (Button) findViewById(R.id.view_all);
        bottom_buttons = (RelativeLayout) findViewById(R.id.bottom_buttons);
        close_layout = (RelativeLayout) findViewById(R.id.close_layout);
        progress = (ProgressBar) findViewById(R.id.progress);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        lockableViewPager = (LockableViewPager) findViewById(R.id.viewpager);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        verticalRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        new FetchAssessmentData(this,this,progress,1858,10195,lockableViewPager,assessmentAdapter,getSupportFragmentManager(),totalTimer,questiontimer,timer,verticalRecycler,questionadapter).execute();
        assessmentResultPojo = new AssessmentResultPojo();
        assessmentResultPojo.setUser_id(1858);
        assessmentResultPojo.setAssessment_id(10195);
        assessmentResultPojo.setOptions(new HashMap<Integer, Integer>());
        correctanswer.setAllCaps(true);
        view_all.setOnClickListener(this);
        close_bottomsheet.setOnClickListener(this);
        questionListRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        questionListRecycler.setVisibility(View.GONE);
        close_layout.setVisibility(View.GONE);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        animFadeOut1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
        animFadeIn1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        setAnimListner();
        setIconColor();
        prev.setVisibility(View.GONE);

        lockableViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String time=((TextView) ((QuestionFragment)lockableViewPager.getAdapter().instantiateItem(lockableViewPager,lockableViewPager.getCurrentItem())).getView().findViewById(R.id.hiddentext)).getText().toString();
               System.out.println("Time question -> "+time);


                setQuestionTimer(AssessmentActivity.this,Integer.parseInt(time)*1000);
                if(position !=0){
                    prev.setVisibility(View.VISIBLE);
                }else{
                    prev.setVisibility(View.GONE);
                }

                if(position != lockableViewPager.getAdapter().getCount() -1){
                    next.setVisibility(View.VISIBLE);

                }else{
                    next.setVisibility(View.GONE);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBottomSheetBehavior1.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");

                        bottom_buttons.startAnimation(animFadeOut);

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");

                        close_layout.startAnimation(animFadeOut1);



                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                System.out.println("new slideOffset"+slideOffset);

            }
        });
        /*lockableViewPager = (LockableViewPager) findViewById(R.id.viewpager);
        questionArrayList = new ArrayList<>();
        setupData();

        WaveLoadingView mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setAnimDuration(10000000);
        mWaveLoadingView.startAnimation();

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
*/

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    private void setIconColor() {
        Drawable[] drawablesprev  =prev.getCompoundDrawables();
        drawablesprev[0].setColorFilter(getResources().getColor(R.color.assessment_bottom), PorterDuff.Mode.SRC_ATOP);

        Drawable[] drawableviewall  =view_all.getCompoundDrawables();
        drawableviewall[0].setColorFilter(getResources().getColor(R.color.assessment_bottom), PorterDuff.Mode.SRC_ATOP);

        Drawable[] drawablenext  =next.getCompoundDrawables();
        drawablenext[2].setColorFilter(getResources().getColor(R.color.assessment_bottom), PorterDuff.Mode.SRC_ATOP);
        correctanswer.getCompoundDrawables()[0].setColorFilter(getResources().getColor(R.color.linkedIn_color), PorterDuff.Mode.SRC_ATOP);

    }

    private void setAnimListner() {
        animFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottom_buttons.setVisibility(View.GONE);
                close_layout.startAnimation(animFadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                questionListRecycler.setVisibility(View.VISIBLE);
                close_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animFadeOut1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                questionListRecycler.setVisibility(View.GONE);
                close_layout.setVisibility(View.GONE);
                bottom_buttons.startAnimation(animFadeIn1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animFadeIn1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottom_buttons.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_all:
                mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                break;
            case R.id.close_bottomsheet:
                mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.prev:
                if(lockableViewPager.getCurrentItem() !=0){
                    lockableViewPager.setCurrentItem(lockableViewPager.getCurrentItem()-1);
                }
                break;
            case R.id.next:
                if(lockableViewPager.getCurrentItem() !=(lockableViewPager.getAdapter().getCount()-1)){
                    lockableViewPager.setCurrentItem(lockableViewPager.getCurrentItem()+1);
                }
                break;

        }

    }



    public void setResult(Integer question_id,Integer option_id){
        if(assessmentResultPojo.getOptions().containsKey(question_id)){
            assessmentResultPojo.getOptions().put(assessmentResultPojo.getOptions().get(question_id),option_id);
        }else{
            assessmentResultPojo.getOptions().put(question_id,option_id);
        }
    }


    public void printResult(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(assessmentResultPojo));
    }

    public void setCorrectanswer(Integer nos){
        correctanswer.setText("0 of "+nos+" ANSWER");

    }
    public void setQuestionTimer(Context context,int duration ){
        if(questiontimer != null){
            questiontimer.cancel();
            questiontimer=null;
        }
        questiontimer = new Questiontimer(context,mWaveLoadingView,duration,1000);
        questiontimer.start();
    }

    public boolean checkSelectedOption(int option_id){
        if(assessmentResultPojo.getOptions().size() >0){
            return assessmentResultPojo.getOptions().containsValue(option_id);
        }else{
            return  false;
        }

    }

}
