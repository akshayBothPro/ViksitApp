package pro.viksit.com.viksit.dashboard.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pro.viksit.com.viksit.dashboard.adapter.CardAdapter.CardAdapter;
import pro.viksit.com.viksit.dashboard.fragment.DashboardCards;

/**
 * Created by Feroz on 18-03-2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {
    private List<String> cmsSlides;
    private List<DashboardCards> mFragments ;
    private float mBaseElevation;

    public ViewPagerAdapter(FragmentManager fm, List<String> cmsSlides, float v) {
        super(fm);
        this.cmsSlides = cmsSlides;
        mFragments = new ArrayList<>();
        mBaseElevation = v;
        for(String key:cmsSlides){
            mFragments.add(new DashboardCards());
        }
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mFragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return cmsSlides.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


    @Override
    public void destroyItem(View collection, int position, Object o) {
        View view = (View)o;
        ((ViewPager) collection).removeView(view);
        view = null;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        mFragments.set(position, (DashboardCards) fragment);
        return fragment;
    }


}