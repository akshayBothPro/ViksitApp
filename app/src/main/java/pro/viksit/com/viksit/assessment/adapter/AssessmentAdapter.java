package pro.viksit.com.viksit.assessment.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import pro.viksit.com.viksit.assessment.fragment.QuestionFragment;
import pro.viksit.com.viksit.assessment.pojo.Question;

/**
 * Created by Feroz on 20-03-2017.
 */

public class AssessmentAdapter extends FragmentStatePagerAdapter {
    private List<Question> questionList;

    public AssessmentAdapter(android.support.v4.app.FragmentManager fm, List<Question> questionList) {
        super(fm);
        this.questionList = questionList;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new QuestionFragment();
        final Bundle bundle = new Bundle();
        bundle.putSerializable(QuestionFragment.GET_QUESTION,questionList.get(position));
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


    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View) o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }


    /*@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub


    }*/
}