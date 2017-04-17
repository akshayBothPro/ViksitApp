package pro.viksit.com.viksit.dashboard.fragment;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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
import pro.viksit.com.viksit.util.CircleTransform;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Feroz on 28-03-2017.
 */

public class ChallengeFragment extends Fragment {
    private static final String SCALE = "scale";
    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    public static Fragment newInstance(Activity context, DashboardCard dashboardCard, float scale) {
        Bundle b = new Bundle();
        b.putSerializable("card",dashboardCard);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, ChallengeFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.challenge_card, container, false);
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
            Button start_game = (Button) linearLayout.findViewById(R.id.start_game);
            TextView header = (TextView) linearLayout.findViewById(R.id.header);
            TextView title = (TextView) linearLayout.findViewById(R.id.title);
            TextView experience = (TextView) linearLayout.findViewById(R.id.experience);
            TextView timelimit = (TextView) linearLayout.findViewById(R.id.coins);
            ImageView myprofile = (ImageView) linearLayout.findViewById(R.id.myprofile);
            TextView mypoints = (TextView) linearLayout.findViewById(R.id.mypoints);
            ImageView challengeprofile = (ImageView) linearLayout.findViewById(R.id.challengeprofile);
            TextView challenger_points = (TextView) linearLayout.findViewById(R.id.challenger_points);
            TextView challenger_name = (TextView) linearLayout.findViewById(R.id.challenger_name);
            Button myrank = (Button) linearLayout.findViewById(R.id.myrank);
            Button challenger_rank = (Button) linearLayout.findViewById(R.id.challenger_rank);
            LinearLayout ln = (LinearLayout) linearLayout.findViewById(R.id.lll);


            System.out.println("Image url "+dashboardCard.getImageURL());

            Drawable drawable = getResources().getDrawable(R.mipmap.ic_coins);
            drawable.setColorFilter(getResources().getColor(R.color.title_challenge), PorterDuff.Mode.SRC_IN);

            if(dashboardCard.getImageURL().isEmpty()){

                Picasso.with(getContext()).load(R.drawable.profile_default).resize(screenwidth / 3, screenwidth / 3).transform(new CircleTransform()).into(myprofile);
                Picasso.with(getContext()).load(R.drawable.profile_default).resize(screenwidth / 3, screenwidth / 3).transform(new CircleTransform()).into(challengeprofile);


            }else {

                Picasso.with(getContext()).load(dashboardCard.getImageURL()).resize(screenwidth / 3, screenwidth / 3).transform(new CircleTransform()).into(myprofile);
                Picasso.with(getContext()).load(dashboardCard.getImageURL()).resize(screenwidth / 3, screenwidth / 3).transform(new CircleTransform()).into(challengeprofile);

            }
            if (diagonalInches>=6.5){
                ViewGroup.LayoutParams params = ln.getLayoutParams();
                params.height = screenHeight / 3;
                params.width = screenHeight / 2;
                ln.setLayoutParams(params);
            } else {
                ViewGroup.LayoutParams params = ln.getLayoutParams();
                params.height = screenHeight / 4;
                params.width = screenHeight / 2;
                ln.setLayoutParams(params);
            }

            myprofile.setMinimumHeight(screenwidth / 3);
            myprofile.setMaxHeight(screenwidth / 3);
            challengeprofile.setMinimumHeight(screenwidth / 3);
            challengeprofile.setMaxHeight(screenwidth / 3);

            myrank.setMaxHeight(myprofile.getMaxHeight()/3);
            myrank.setMinHeight(myprofile.getMinimumHeight()/3);
            myrank.setMaxWidth(myprofile.getMaxHeight()/3);
            myrank.setMinWidth(myprofile.getMinimumHeight()/3);

            //experience timelimit
            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            experience.setText(dashboardCard.getItemExperience()+" XP");
            timelimit.setText(dashboardCard.getItemCoins()+"");
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);

            int rank = 2999;//for testing

            if(rank >= 100 && rank <= 999) {
                myrank.setTextSize(10);
                myrank.setText("#" + Integer.toString(rank));
            } else if(rank >= 1000 && rank <= 1999){
                myrank.setText("#1k+");
                myrank.setTextSize(10);
            } else if(rank >= 2000 && rank <= 2999){
                myrank.setText("#2k+");
                myrank.setTextSize(10);
            }

            start_game.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Start challenge clicked",
                            Toast.LENGTH_LONG).show();
                }
            });
        }
        return linearLayout;
    }

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