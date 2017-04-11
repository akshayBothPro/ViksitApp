package pro.viksit.com.viksit.dashboard.util;

import android.os.AsyncTask;

import pro.viksit.com.viksit.Util.ImageSaver;
import pro.viksit.com.viksit.Util.SaveImageAsync;

/**
 * Created by Feroz on 11-04-2017.
 */

public class VideoSaveThread implements Runnable {

    private String url;
    private ImageSaver videosaver;
    public VideoSaveThread(String url, ImageSaver videosaver){
        this.url = url;
        this.videosaver = videosaver;
    }

    @Override
    public void run() {
        new SaveImageAsync(videosaver).execute(url);

    }
}
