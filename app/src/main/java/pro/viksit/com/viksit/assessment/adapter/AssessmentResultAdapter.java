package pro.viksit.com.viksit.assessment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

import pro.viksit.com.viksit.assessment.fragment.EndAssessmentFragment;
import pro.viksit.com.viksit.assessment.fragment.QuestionFragment;
import pro.viksit.com.viksit.assessment.fragment.QuestionResultFragment;
import pro.viksit.com.viksit.assessment.pojo.QuestionPOJO;

/**
 * Created by Feroz on 20-03-2017.
 */

public class AssessmentResultAdapter extends FragmentStatePagerAdapter {
    private List<QuestionPOJO> questionList;
    private Context context;
    public AssessmentResultAdapter(Context context, android.support.v4.app.FragmentManager fm, List<QuestionPOJO> questionList) {
        super(fm);
        this.questionList = questionList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
            fragment = new QuestionResultFragment();
            final Bundle bundle = new Bundle();
            bundle.putSerializable(QuestionResultFragment.GET_QUESTION,questionList.get(position));
            bundle.putInt(QuestionResultFragment.POSITION,position);
            bundle.putInt(QuestionResultFragment.TOTALCOUNT,questionList.size());
            fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return questionList.size();
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