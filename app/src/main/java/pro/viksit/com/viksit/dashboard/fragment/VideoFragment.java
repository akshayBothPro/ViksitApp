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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Akshay on 03/04/2017.
 */

public class VideoFragment extends Fragment {

    private static final String SCALE = "scale";
    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    public static Fragment newInstance(Activity context, DashboardCard dashboardCard, float scale) {
        Bundle b = new Bundle();
        b.putSerializable("card",dashboardCard);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, VideoFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null ) {
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.video_card, container, false);

        //final int postion = this.getArguments().getInt(POSITON);
        if(getArguments() != null){
            DashboardCard dashboardCard = (DashboardCard) getArguments().getSerializable("card");
            float scale = this.getArguments().getFloat(SCALE);
            Double d, d1;

            if (diagonalInches>=6.5){
                // 6.5inch device or bigger
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.3);
            }else{
                // smaller device
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.6);
            }
            int screenwidth = d.intValue();;
            int screenheitght = d1.intValue();

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenwidth, screenheitght);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view);
            Button start_video = (Button) linearLayout.findViewById(R.id.start_video);
            TextView header = (TextView) linearLayout.findViewById(R.id.header);
            TextView title = (TextView) linearLayout.findViewById(R.id.title);
            ImageView image = (ImageView) linearLayout.findViewById(R.id.image);
            if (diagonalInches>=6.5){
                Picasso.with(getContext())
                        .load(R.drawable.backgroundimg).resize(screenheitght / 2, screenheitght / 2)
                        .into(image);
                image.setMinimumHeight(screenheitght / 2);
                image.setMaxHeight(screenheitght / 2);
            } else {
                Picasso.with(getContext())
                        .load(R.drawable.backgroundimg).resize(screenheitght / 2, screenheitght / 3)
                        .into(image);
                image.setMinimumHeight(screenheitght / 3);
                image.setMaxHeight(screenheitght / 3);
            }
            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);

            start_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Start game clicked",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
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

        float yInches= displaymetrics.heightPixels/displaymetrics.ydpi;
        float xInches= displaymetrics.widthPixels/displaymetrics.xdpi;
        diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
    }
}