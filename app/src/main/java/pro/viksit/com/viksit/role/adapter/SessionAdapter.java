package pro.viksit.com.viksit.role.adapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import pro.viksit.com.viksit.role.fragment.SessionCards;

public class SessionAdapter extends FragmentStatePagerAdapter {
    private List<String> cmsSlides;
    public SessionAdapter(android.support.v4.app.FragmentManager fm,List<String> cmsSlides) {
        super(fm);
        this.cmsSlides = cmsSlides;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new SessionCards();
        final Bundle bundle = new Bundle();





        fragment.setArguments(bundle);
        return fragment;
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


    /*@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub


    }*/
}