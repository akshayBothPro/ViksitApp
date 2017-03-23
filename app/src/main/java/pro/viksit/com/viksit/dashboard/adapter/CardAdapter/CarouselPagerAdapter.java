package pro.viksit.com.viksit.dashboard.adapter.CardAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pro.viksit.com.viksit.MainActivity;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.activity.DashboardActivity;
import pro.viksit.com.viksit.dashboard.fragment.ItemFragment;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Feroz on 21-03-2017.
 */

public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.9f;
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
    private DashboardActivity context;
    private FragmentManager fragmentManager;
    private float scale;
    ArrayList<String> values;
    private int lastposition;
    public CarouselPagerAdapter(DashboardActivity context, FragmentManager fm, ArrayList<String> values) {
        super(fm);
        this.fragmentManager = fm;
        this.context = context;
        this.values = values;
    }

    @Override
    public Fragment getItem(int position) {
        // make the first pager bigger than others
        try {
            if (position == values.size())
                scale = BIG_SCALE;
            else
                scale = SMALL_SCALE;

            position = position % MainActivity.count;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ItemFragment.newInstance(context, position, scale);
    }

    @Override
    public int getCount() {

        return values.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        try {
            if (positionOffset >= 0f && positionOffset <= 1f) {
                CarouselLinearLayout cur = getRootView(position);
                if(position != context.pager.getAdapter().getCount()-1) {
                    CarouselLinearLayout next = getRootView(position + 1);
                    next.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
                }
                cur.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageSelected(final int position) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(4, 0, 4, 0);

        if(context.lastposition == position || position> context.lastposition  ){
            context.dots = new ImageView[context.pager.getAdapter().getCount()-context.lastposition];
            context.pager_indicator.removeAllViews();

            for (int i = 0; i < context.pager.getAdapter().getCount()-context.lastposition; i++) {
                context.dots[i] = new ImageView(context);
                context.dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));


                context.pager_indicator.addView(context.dots[i], params);
            }
        }else {
            context.dots = new ImageView[context.loop];
            context.pager_indicator.removeAllViews();

            for (int i = 0; i < context.loop; i++) {
                context.dots[i] = new ImageView(context);
                context.dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));

                context.pager_indicator.addView(context.dots[i], params);
            }
        }

        context.dots[position %context.loop ].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressWarnings("ConstantConditions")
    private CarouselLinearLayout getRootView(int position) {
        return (CarouselLinearLayout) fragmentManager.findFragmentByTag(this.getFragmentTag(position))
                .getView().findViewById(R.id.root_container);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + context.pager.getId() + ":" + position;
    }
}