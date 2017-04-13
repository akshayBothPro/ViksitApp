package pro.viksit.com.viksit.dashboard.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.DisplayUtil;
import pro.viksit.com.viksit.Util.ImageSaver;
import pro.viksit.com.viksit.dashboard.thread.VideoSaveThread;

public class VideoPlayActivity extends AppCompatActivity {
    private VideoView myVideoView;
    private MediaController mediaControls;
    private int position = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        if (mediaControls == null) {
            mediaControls = new MediaController(VideoPlayActivity.this);
            mediaControls.setBackgroundColor(getResources().getColor(R.color.white));
            myVideoView = (VideoView) findViewById(R.id.video_view);

            progressDialog = new ProgressDialog(VideoPlayActivity.this);
            progressDialog.setTitle(getString(R.string.app_name));
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            try {

                if(getIntent() != null) {
                    String url = getIntent().getStringExtra("url");
                    int videoindex = url.lastIndexOf("/");

                    ImageSaver videosaver = new ImageSaver(this).
                            setParentDirectoryName("dashboard").
                            setFileName(new DisplayUtil().getFileNameReplaced(url.substring(videoindex+1,url.length()))).
                            setExternal(ImageSaver.isExternalStorageReadable());
                    //set the media controller in the VideoView
                    myVideoView.setMediaController(mediaControls);

                    //set the uri of the video to be played
                    if(videosaver.checkFile()) {
                        System.out.println("VIDEO FILE  EXITS >>>>>> ");

                        myVideoView.setVideoURI(Uri.fromFile(videosaver.pathFile()));
                    }else {
                        System.out.println("VIDEO FILE NOT EXITS >>>>>> ");
                        ExecutorService executor = Executors.newFixedThreadPool(1);
                        Runnable worker = new VideoSaveThread(url,videosaver);
                        executor.execute(worker);
                        executor.shutdown();
                        myVideoView.setVideoURI(Uri.parse(url));
                    }
                }

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            myVideoView.requestFocus();
            //we also set an setOnPreparedListener in order to know when the video file is ready for playback
            myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaPlayer) {
                    // close the progress bar and play the video
                    progressDialog.dismiss();
                    //if we have a position on savedInstanceState, the video playback should start from here
                    myVideoView.seekTo(position);
                    if (position == 0) {
                        myVideoView.start();
                    } else {
                        //if we come from a resumed activity, video playback will be paused
                        myVideoView.pause();
                    }
                }
            });
            myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    System.out.println("Video completed..");
                }
            });
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(VideoPlayActivity.this, DashboardActivity.class);
        startActivity(i);
    }


}
