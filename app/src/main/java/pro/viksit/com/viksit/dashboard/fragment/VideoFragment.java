package pro.viksit.com.viksit.dashboard.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.util.DisplayUtil;
import pro.viksit.com.viksit.util.ImageSaver;
import pro.viksit.com.viksit.dashboard.activity.VideoPlayActivity;
import pro.viksit.com.viksit.dashboard.pojo.DashboardCard;
import pro.viksit.com.viksit.dashboard.util.CarouselLinearLayout;
import pro.viksit.com.viksit.dashboard.util.ImageSaverUtil;
import pro.viksit.com.viksit.dashboard.thread.VideoSaveThread;

/**
 * Created by Akshay on 03/04/2017.
 */

public class VideoFragment extends Fragment {

    private static final String SCALE = "scale";
    private RelativeLayout rl_img_con;
    private DashboardCard dashboardCard;
    //private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private int screenWidth;
    private int screenHeight;
    private double diagonalInches;

    public static Fragment newInstance(Activity context, DashboardCard dashboardCard, float scale) {
        Bundle b = new Bundle();
        b.putSerializable("card", dashboardCard);
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
        if (container == null) {
            return null;
        }
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.video_card, container, false);

        //final int postion = this.getArguments().getInt(POSITON);
        if (getArguments() != null) {
            dashboardCard = (DashboardCard) getArguments().getSerializable("card");
            float scale = this.getArguments().getFloat(SCALE);
            Double d ,d1;
            if (diagonalInches>=6.5){
                // 6.5inch device or bigger
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.3);
            }else{
                // smaller device
                d = new Double(screenWidth / 1.2);
                d1= new Double(screenHeight/1.6);
            }
            //
            int screenwidth = d.intValue();
            int screenheitght = d1.intValue();
            System.out.println("Image url " + dashboardCard.getVideoURL());

            int videoindex = dashboardCard.getVideoURL().lastIndexOf("/");
            ImageSaver videosaver = new ImageSaver(getContext()).
                    setParentDirectoryName("dashboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(dashboardCard.getVideoURL().substring(videoindex + 1, dashboardCard.getVideoURL().length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());
            if (!videosaver.checkFile()) {
                ExecutorService executor = Executors.newFixedThreadPool(1);
                Runnable worker = new VideoSaveThread(getString(R.string.resourceserverip) +dashboardCard.getVideoURL(), videosaver);
                executor.execute(worker);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenwidth, screenheitght);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.card_view);
            Button start_video = (Button) linearLayout.findViewById(R.id.start_video);
            TextView header = (TextView) linearLayout.findViewById(R.id.header);
            TextView title = (TextView) linearLayout.findViewById(R.id.title);
            ImageView image = (ImageView) linearLayout.findViewById(R.id.image);
            TextView description = (TextView) linearLayout.findViewById(R.id.description);
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
            rl_img_con = (RelativeLayout) linearLayout.findViewById(R.id.rl_img_con);
            /*image.setMinimumHeight(screenheitght / 3);
            image.setMaxHeight(screenheitght / 3);*/
            System.out.println("Image url " + dashboardCard.getImageURL());

            int index = dashboardCard.getImageURL().lastIndexOf("/");
            ImageSaver imageSaver = new ImageSaver(getContext()).
                    setParentDirectoryName("dashboard").
                    setFileName(new DisplayUtil().getFileNameReplaced(dashboardCard.getImageURL().substring(index + 1, dashboardCard.getImageURL().length()))).
                    setExternal(ImageSaver.isExternalStorageReadable());
            Picasso picasso = Picasso.with(getContext());

            new ImageSaverUtil(imageSaver, picasso, image, screenHeight, getString(R.string.resourceserverip) + dashboardCard.getImageURL(),true).checkImageExist();

            header.setText(dashboardCard.getHeader());
            title.setText(dashboardCard.getTitle());
            description.setText(dashboardCard.getDescription());
            cardView.setLayoutParams(layoutParams);
            CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
            root.setScaleBoth(scale);

            start_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Start game clicked",
                            Toast.LENGTH_LONG).show();
                    gotoVideo(dashboardCard, getContext());

                }
            });
            rl_img_con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoVideo(dashboardCard, getContext());
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

    private void gotoVideo(DashboardCard dashboardCard, Context context) {
        Intent i = new Intent(context, VideoPlayActivity.class);
        i.putExtra("url", getString(R.string.resourceserverip) +dashboardCard.getVideoURL());
        startActivity(i);
    }

}