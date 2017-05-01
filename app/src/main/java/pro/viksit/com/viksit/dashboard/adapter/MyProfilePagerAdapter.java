package pro.viksit.com.viksit.dashboard.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pro.viksit.com.viksit.dashboard.fragment.profilefragments.AccountFragment;
import pro.viksit.com.viksit.dashboard.fragment.profilefragments.PerformanceFragment;

/**
 * Created by Akshay on 28/04/2017.
 */

public class MyProfilePagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;

    public MyProfilePagerAdapter(FragmentManager fm, String[] tabTitles) {
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
                PerformanceFragment performanceFragment = new PerformanceFragment();
                return performanceFragment;
            case 1:
                AccountFragment accountFragment = new AccountFragment();
                return accountFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
