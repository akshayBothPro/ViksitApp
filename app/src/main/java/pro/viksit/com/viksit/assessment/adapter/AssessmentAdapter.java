package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pro.viksit.com.viksit.assessment.fragment.EndAssessmentFragment;
import pro.viksit.com.viksit.assessment.fragment.QuestionFragment;
import pro.viksit.com.viksit.assessment.pojo.Question;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;

/**
 * Created by Feroz on 20-03-2017.
 */

public class AssessmentAdapter extends FragmentStatePagerAdapter {
    private List<QuestionPOJO> questionList;
    private Context context;
    public AssessmentAdapter(Context context,android.support.v4.app.FragmentManager fm, List<QuestionPOJO> questionList) {
        super(fm);
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position != questionList.size()) {
            fragment = new QuestionFragment();
            final Bundle bundle = new Bundle();
            bundle.putSerializable(QuestionFragment.GET_QUESTION,questionList.get(position));
            bundle.putInt(QuestionFragment.POSITION,position);
            bundle.putInt(QuestionFragment.TOTALCOUNT,questionList.size());
            fragment.setArguments(bundle);
        }        else
            fragment = new EndAssessmentFragment();

        return fragment;
    }

    @Override
    public int getCount() {
        return questionList.size()+1;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


   /* @Override
    public void destroyItem(View collection, int position, Object o) {
       *//* View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;*//*
    }
*/

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub


    }
}