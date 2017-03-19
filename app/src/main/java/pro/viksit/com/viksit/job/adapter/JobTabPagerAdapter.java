package pro.viksit.com.viksit.job.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pro.viksit.com.viksit.job.fragment.AppliedJobFragment;
import pro.viksit.com.viksit.job.fragment.OpeningsJobFragment;

/**
 * Created by Akshay on 19/03/2017.
 */


public class JobTabPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;

    public JobTabPagerAdapter(FragmentManager fm, String[] tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                OpeningsJobFragment openingsJobFragment = new OpeningsJobFragment();
                return openingsJobFragment;
            case 1:
                AppliedJobFragment appliedJobFragment = new AppliedJobFragment();
                return appliedJobFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
