package pro.viksit.com.viksit.dashboard.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Feroz on 21-03-2017.
 */

public class ItemFragment extends Fragment {

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURE = "resource";

    private int screenWidth;
    private int screenHeight;

    private int[] imageArray = new int[]{R.mipmap.ic_add_black_24dp, R.mipmap.ic_arrow_back_black_24dp,
            R.mipmap.ic_info_black_24dp, R.mipmap.ic_add_black_24dp, R.mipmap.ic_event_note_black_24dp,
            R.mipmap.ic_adjust_black_24dp, R.mipmap.ic_add_black_24dp, R.mipmap.ic_add_black_24dp,
            R.mipmap.ic_add_black_24dp, R.mipmap.ic_add_black_24dp};

    public static Fragment newInstance(Activity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int postion = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);
        Double d = new Double(screenWidth / 1.2);
        Double d1= new Double(screenHeight/1.7);
        int screenwidth = d.intValue();;
        int screenheitght = d1.intValue();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenwidth, screenheitght);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_image, container, false);
        CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view);
        cardView.setLayoutParams(layoutParams);
        //TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        //ImageView imageView = (ImageView) linearLayout.findViewById(R.id.pagerImg);

        /*textView.setText("Carousel item: " + postion);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(imageArray[postion]);

        //handling click event
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}