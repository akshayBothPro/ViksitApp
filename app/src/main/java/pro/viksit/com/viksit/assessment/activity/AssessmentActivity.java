package pro.viksit.com.viksit.assessment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import me.itangqi.waveloadingview.WaveLoadingView;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.LockableViewPager;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.adapter.AssessmentTotalTimer;
import pro.viksit.com.viksit.assessment.adapter.QuestionsRecyclerViewAdapter;
import pro.viksit.com.viksit.assessment.adapter.Questiontimer;
import pro.viksit.com.viksit.assessment.async.FetchAssessmentData;
import pro.viksit.com.viksit.assessment.async.SaveAssessmentData;
import pro.viksit.com.viksit.assessment.fragment.EndAssessmentFragment;
import pro.viksit.com.viksit.assessment.fragment.QuestionFragment;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.assessment.pojo.AssessmentResultPojo;
import pro.viksit.com.viksit.assessment.pojo.Option;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;
import pro.viksit.com.viksit.assessment.pojo.QuestionResult;
import pro.viksit.com.viksit.assessment.util.FixedSpeedScroller;
import pro.viksit.com.viksit.role.adapter.RoleVerticalRecyclerViewAdapter;
import pro.viksit.com.viksit.role.pojo.Role;

public class AssessmentActivity extends AppCompatActivity implements View.OnClickListener {



    public LockableViewPager lockableViewPager;
    private AssessmentAdapter assessmentAdapter;
    private ImageView toolbar_close,close_bottomsheet;
    private TextView correctanswer,timer;
    private Button prev,next,view_all;
    public BottomSheetBehavior mBottomSheetBehavior1;
    private RelativeLayout bottom_buttons,close_layout;
    private RecyclerView questionListRecycler;
    Animation animFadeOut,animFadeIn,animFadeOut1,animFadeIn1;
    private AssessmentTotalTimer totalTimer;
    private Questiontimer questiontimer;
    private ProgressBar progress;
    public WaveLoadingView mWaveLoadingView;
    private AssessmentPOJO assessmentPOJO;
    private QuestionsRecyclerViewAdapter questionadapter;
    private AssessmentResultPojo assessmentResultPojo;
    public final static String TAG = "AssessmentActivity";
    private SharedPreferences sharedpreferences;
    public long remaining_time=0;
    public long question_time_taken =0;
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
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        questionListRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        //questionListRecycler.setVisibility(View.GONE);
        try {
            String jsonresponse = new FetchAssessmentData(this,progress,1858,10195).execute().get();
            if (jsonresponse != null && !jsonresponse.equalsIgnoreCase("null")
                    && !jsonresponse.equalsIgnoreCase("") && !jsonresponse.contains("HTTP Status")
                    ) {
                assessmentPOJO =   new Gson().fromJson(jsonresponse, AssessmentPOJO.class);
                System.out.println("Ass mine -> "+assessmentPOJO.getName());
                assessmentAdapter = new AssessmentAdapter(this,getSupportFragmentManager(), assessmentPOJO.getQuestions());
                lockableViewPager.setAdapter(assessmentAdapter);
                lockableViewPager.setSwipeLocked(true);
                lockableViewPager.setCurrentItem(0);
                lockableViewPager.setPageTransformer(true, new RotateUpTransformer());
                try {
                    Field mScroller;
                    mScroller = ViewPager.class.getDeclaredField("mScroller");
                    mScroller.setAccessible(true);
                    FixedSpeedScroller scroller = new FixedSpeedScroller(lockableViewPager.getContext(), new DecelerateInterpolator());
                    // scroller.setFixedDuration(5000);
                    mScroller.set(lockableViewPager, scroller);
                } catch (NoSuchFieldException e) {
                } catch (IllegalArgumentException e) {
                } catch (IllegalAccessException e) {
                }
                setCorrectanswer(assessmentPOJO.getQuestions().size(),true);
                questionadapter = new QuestionsRecyclerViewAdapter(this,this,lockableViewPager,assessmentPOJO.getQuestions());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                questionListRecycler.setLayoutManager(mLayoutManager);
                questionListRecycler.setItemAnimator(new DefaultItemAnimator());
                questionListRecycler.setAdapter(questionadapter);
                totalTimer = new AssessmentTotalTimer(this,timer,assessmentPOJO.getDurationInMinutes()*60000,1000);
                totalTimer.start();
                setQuestionTimer(this,assessmentPOJO.getQuestions().get(0).getDurationInSec() *1000);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(AssessmentActivity.TAG+10195, jsonresponse);
                editor.apply();
                editor.commit();
            }else{

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assessmentResultPojo = new AssessmentResultPojo();
        assessmentResultPojo.setUser_id(1858);
        assessmentResultPojo.setAssessment_id(10195);
        assessmentResultPojo.setOptions(new HashMap<Integer, QuestionResult>());
        correctanswer.setAllCaps(true);
        view_all.setOnClickListener(this);
        close_bottomsheet.setOnClickListener(this);

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
                if(position != assessmentPOJO.getQuestions().size()) {
                    setCorrectanswer(assessmentResultPojo.getOptions().size(), false);
                    String time = ((TextView) ((QuestionFragment) lockableViewPager.getAdapter().instantiateItem(lockableViewPager, lockableViewPager.getCurrentItem())).getView().findViewById(R.id.hiddentext)).getText().toString();
                    System.out.println("Time question -> " + time);
                    setQuestionTimer(AssessmentActivity.this, Integer.parseInt(time) * 1000);
                    if(correctanswer.getVisibility()== View.INVISIBLE){
                        correctanswer.setVisibility(View.VISIBLE);
                    }
                    if(timer.getVisibility()== View.INVISIBLE){
                        setTotalTimer(remaining_time,timer);
                        timer.setVisibility(View.VISIBLE);

                    }
                }else{
                    TextView unanswered = ((TextView) ((EndAssessmentFragment) lockableViewPager.getAdapter().instantiateItem(lockableViewPager, lockableViewPager.getCurrentItem())).getView().findViewById(R.id.unanswered));
                    TextView time = ((TextView) ((EndAssessmentFragment) lockableViewPager.getAdapter().instantiateItem(lockableViewPager, lockableViewPager.getCurrentItem())).getView().findViewById(R.id.time));

                    unanswered.setText(checkUnanswered()+"");
                    setQuestionTimer(AssessmentActivity.this,(int)remaining_time);
                    correctanswer.setVisibility(View.INVISIBLE);
                    timer.setVisibility(View.INVISIBLE);
                    setTotalTimer(remaining_time,time);
                }
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
                        bottomSheet.requestLayout();
                        bottomSheet.invalidate();
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
                //System.out.println("new slideOffset"+slideOffset);

            }
        });

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

        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_check_circle_black_24dp).mutate();

        drawable.setColorFilter(getResources().getColor(R.color.linkedIn_color), PorterDuff.Mode.SRC_ATOP);
        correctanswer.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
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
                //questionListRecycler.setVisibility(View.VISIBLE);
                close_layout.setVisibility(View.INVISIBLE);
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
                //questionListRecycler.setVisibility(View.GONE);
                close_layout.setVisibility(View.INVISIBLE);
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



    public void setResult(Integer question_id,Integer option_id,Long duration){
        if(assessmentResultPojo.getOptions().containsKey(question_id)){
            QuestionResult questionResult = new QuestionResult(question_id,option_id,duration);
            assessmentResultPojo.getOptions().put(assessmentResultPojo.getOptions().get(question_id).getQuestion_id(),questionResult);
        }else{
            QuestionResult questionResult = new QuestionResult(question_id,option_id,duration);
            assessmentResultPojo.getOptions().put(question_id,questionResult);
        }
    }


    public void printResult(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(assessmentResultPojo));
    }

    public void setCorrectanswer(Integer nos,Boolean flag){
        if(flag)
        correctanswer.setText("0 of "+(nos-1)+" ANSWER");
        else
            correctanswer.setText(nos+" of "+(lockableViewPager.getAdapter().getCount()-1)+" ANSWER");

    }


    public void setQuestionTimer(Context context,long duration ){
        if(questiontimer != null){
            questiontimer.cancel();
            questiontimer=null;
        }
        questiontimer = new Questiontimer(context,mWaveLoadingView,duration,1000);
        questiontimer.start();
    }


    public void setTotalTimer(long time,TextView tx){
        if(totalTimer != null){
            totalTimer.cancel();
            totalTimer=null;
        }
        totalTimer = new AssessmentTotalTimer(this,tx,time,1000);
        totalTimer.start();
    }
    public boolean checkSelectedOption(int option_id){
        if(assessmentResultPojo.getOptions().size() >0){
            return assessmentResultPojo.getOptions().containsValue(option_id);
        }else{
            return  false;
        }
    }

    public boolean checkQuestion(int question_id){
        if(assessmentResultPojo.getOptions().size() >0){
            return assessmentResultPojo.getOptions().containsKey(question_id);
        }else{
            return  false;
        }
    }

    public void checkRecylclerIconChange(int position,int question){
        questionListRecycler.setVisibility(View.VISIBLE);
        System.out.println("position CXCXX "+position);
        questionListRecycler.getAdapter().notifyItemChanged(position);
    }

    public int checkUnanswered(){
        return (assessmentPOJO.getQuestions().size() - assessmentResultPojo.getOptions().size())+1;
    }

    public void submitAssessment(){
        if(questiontimer != null){
            questiontimer.cancel();
            questiontimer=null;
        }
        if(totalTimer != null){
            totalTimer.cancel();
            totalTimer=null;
        }
        assessmentResultPojo.setDuration(remaining_time);
        new SaveAssessmentData(this,progress,assessmentPOJO,assessmentResultPojo).execute();
    }

}
