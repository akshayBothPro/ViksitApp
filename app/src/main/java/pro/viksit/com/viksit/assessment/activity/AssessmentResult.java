package pro.viksit.com.viksit.assessment.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.assessment.adapter.AssessmentAdapter;
import pro.viksit.com.viksit.assessment.adapter.AssessmentResultAdapter;
import pro.viksit.com.viksit.assessment.adapter.QuestionsRecyclerViewAdapter;
import pro.viksit.com.viksit.assessment.adapter.QuestionsResultRecyclerViewAdapter;
import pro.viksit.com.viksit.assessment.async.FetchAssessmentData;
import pro.viksit.com.viksit.assessment.pojo.AssessmentPOJO;
import pro.viksit.com.viksit.assessment.util.FixedSpeedScroller;
import pro.viksit.com.viksit.util.LockableViewPager;

public class AssessmentResult extends AppCompatActivity implements View.OnClickListener {
    private Button prev,next,view_all;
    private RelativeLayout bottom_buttons,close_layout;
    private ProgressBar progress;
    private SharedPreferences sharedpreferences;
    private ImageView toolbar_close,close_bottomsheet;
    public BottomSheetBehavior mBottomSheetBehavior1;
    public LockableViewPager lockableViewPager;
    private RecyclerView questionListRecycler;
    private AssessmentPOJO assessmentPOJO;
    private AssessmentResultAdapter assessmentAdapter;
    private QuestionsResultRecyclerViewAdapter questionadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_result);
        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        view_all = (Button) findViewById(R.id.view_all);
        view_all = (Button) findViewById(R.id.view_all);
        bottom_buttons = (RelativeLayout) findViewById(R.id.bottom_buttons);
        close_layout = (RelativeLayout) findViewById(R.id.close_layout);
        progress = (ProgressBar) findViewById(R.id.progress);
        sharedpreferences = getSharedPreferences(getResources().getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        toolbar_close = (ImageView) findViewById(R.id.toolbar_close);
        close_bottomsheet = (ImageView) findViewById(R.id.close_bottomsheet);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        lockableViewPager = (LockableViewPager) findViewById(R.id.viewpager);
        questionListRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        try {
            String jsonresponse = new FetchAssessmentData(this, progress, 1858, 10195).execute().get();
            assessmentPOJO =   new Gson().fromJson(jsonresponse, AssessmentPOJO.class);
            assessmentAdapter = new AssessmentResultAdapter(this,getSupportFragmentManager(), assessmentPOJO.getQuestions());
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
            questionadapter = new QuestionsResultRecyclerViewAdapter(this,this,lockableViewPager,assessmentPOJO.getQuestions());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            questionListRecycler.setLayoutManager(mLayoutManager);
            questionListRecycler.setItemAnimator(new DefaultItemAnimator());
            questionListRecycler.setAdapter(questionadapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
