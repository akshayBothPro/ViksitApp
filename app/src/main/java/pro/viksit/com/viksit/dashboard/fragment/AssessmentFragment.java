package pro.viksit.com.viksit.dashboard.fragment;

import android.app.Activity;
import android.net.Uri;
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
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.util.SaveImageAsync;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Feroz on 28-03-2017.
 */

public class AssessmentFragment extends Fragment {
    private static final String SCALE = "scale";
    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    public static Fragment newInstance(Activity context, DashboardCard dashboardCard, float scale) {
        Bundle b = new Bundle();
        b.putSerializable("card",dashboardCard);
        b.putFloat(SCALE, scale);
        return Fragment.instantiate(context, AssessmentFragment.class.getName(), b);
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
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.assessment_card, container, false);
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
            TextView questions = (TextView) linearLayout.findViewById(R.id.questions);
            TextView experience = (TextView) linearLayout.findViewById(R.id.experience);
            TextView timelimit = (TextView) linearLayout.findViewById(R.id.timelimit);
            ImageView image =(ImageView) linearLayout.findViewById(R.id.image);
            TextView description = (TextView) linearLayout.findViewById(R.id.description);

            System.out.println("Image url "+dashboardCard.getImageURL());

            int index = dashboardCard.getImageURL().lastIndexOf("/");
            ImageSaver imageSaver = new ImageSaver(getContext()).
                    setParentDirectoryName("dashboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(dashboardCard.getImageURL().substring(index+1,dashboardCard.getImageURL().length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());
            if(imageSaver.checkFile()){
                Uri uri = Uri.fromFile(imageSaver.pathFile());
                /*Picasso.with(getContext())
                        .load(uri).resize(screenHeight/4, screenHeight/4).transform(new CircleTransform())
                        .into(image);*/
                if (diagonalInches>=6.5){
                    Picasso.with(getContext())
                            .load(R.drawable.ic_6).resize(screenheitght / 2, screenheitght / 2).transform(new CircleTransform())
                            .into(image);
                } else {
                    Picasso.with(getContext())
                            .load(R.drawable.ic_6).resize(screenheitght / 3, screenheitght / 3).transform(new CircleTransform())
                            .into(image);
                }
                System.out.println("FILE  EXITS >>>>>> ");

            }else {
                System.out.println("FILE NOT EXITS >>>>>> ");
                /*Picasso.with(getContext())
                        .load(dashboardCard.getImageURL()).resize(screenheitght/4, screenheitght/4).transform(new CircleTransform())
                        .into(image);*/
                if (diagonalInches>=6.5){
                    Picasso.with(getContext())
                            .load(R.drawable.ic_6).resize(screenheitght / 2, screenheitght / 2).transform(new CircleTransform())
                            .into(image);

                } else {
                    Picasso.with(getContext())
                            .load(R.drawable.ic_6).resize(screenheitght / 3, screenheitght / 3).transform(new CircleTransform())
                            .into(image);
                }
                new SaveImageAsync(imageSaver).execute(dashboardCard.getImageURL());

            }

            if (diagonalInches>=6.5) {
                image.setMinimumHeight(screenheitght / 2);
                image.setMaxHeight(screenheitght / 2);
            } else {
                image.setMinimumHeight(screenheitght / 3);
                image.setMaxHeight(screenheitght / 3);
            }

            //experience timelimit
            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            questions.setText(dashboardCard.getNumberOfQuestions()+"");
            experience.setText(dashboardCard.getItemExperience()+" XP");
            timelimit.setText(dashboardCard.getItemCoins()+"");
            description.setText(dashboardCard.getDescription());
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);

            start_game.bringToFront();
            start_game.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Start game clicked",
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
