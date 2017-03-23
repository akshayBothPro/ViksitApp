package pro.viksit.com.viksit.role.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.adapter.SessionAdapter;

public class SessionActivity extends AppCompatActivity {
    private VerticalViewPager viewPager;
    private static final float MIN_SCALE = 0.9f;
    private static final float MIN_ALPHA = 0.75f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        viewPager = (VerticalViewPager) findViewById(R.id.sessionviewpager);
        ArrayList<String> count = new ArrayList<>();
        for(int i=0; i<10;i++){
            count.add("Hello "+i);
        }
        SessionAdapter sessionAdapter = new SessionAdapter(getSupportFragmentManager(),count);
        viewPager.setAdapter(sessionAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {

            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position >= 0) {
                    view.setTranslationY(position < 0 ? 0f : -pageHeight * position);
                }
            }
        });
       viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    // Modify the default slide transition to shrink the page as well
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationY(vertMargin - horzMargin / 2);
                    } else {
                        view.setTranslationY(-vertMargin + horzMargin / 2);
                    }

                    // Scale the page down (between MIN_SCALE and 1)
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    // Fade the page relative to its size.
                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        double pageMargin = ((metrics.widthPixels / 1.6) );
        Double widthpage = new Double(pageMargin);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(-widthpage.intValue());



    }
}
