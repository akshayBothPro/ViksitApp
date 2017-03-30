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
import pro.viksit.com.viksit.Util.DisplayUtil;
import pro.viksit.com.viksit.Util.ImageSaver;
import pro.viksit.com.viksit.Util.SaveImageAsync;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;

/**
 * Created by Feroz on 28-03-2017.
 */

public class AssessmentFragment extends Fragment {
    private static final String SCALE = "scale";
    private int screenWidth;
    private int screenHeight;

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
            Double d = new Double(screenWidth / 1.2);
            Double d1= new Double(screenHeight/1.6);
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
            System.out.println("Image url "+dashboardCard.getImage_url());

            int index = dashboardCard.getImage_url().lastIndexOf("/");
            ImageSaver imageSaver = new ImageSaver(getContext()).
                    setParentDirectoryName("dashboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(dashboardCard.getImage_url().substring(index+1,dashboardCard.getImage_url().length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());
            if(imageSaver.checkFile()){
                Uri uri = Uri.fromFile(imageSaver.pathFile());
                Picasso.with(getContext())
                        .load(uri).resize(150,150)
                        .into(image);
                System.out.println("FILE  EXITS >>>>>> ");

            }else {
                System.out.println("FILE NOT EXITS >>>>>> ");
                Picasso.with(getContext())
                        .load(dashboardCard.getImage_url()).resize(150, 150)
                        .into(image);
                new SaveImageAsync(imageSaver).execute(dashboardCard.getImage_url());

            }//experience timelimit
            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            questions.setText(dashboardCard.getNosofQuestion()+"");
            experience.setText(dashboardCard.getExperience()+" XP");
            timelimit.setText(dashboardCard.getCoins()+"");
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);


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
    }
}
