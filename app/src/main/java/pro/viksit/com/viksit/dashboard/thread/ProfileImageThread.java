package pro.viksit.com.viksit.dashboard.thread;

import android.content.Context;

import pro.viksit.com.viksit.Util.DisplayUtil;
import pro.viksit.com.viksit.Util.ImageSaver;
import pro.viksit.com.viksit.Util.SaveImageAsync;

/**
 * Created by Feroz on 07-04-2017.
 */

public class ProfileImageThread implements  Runnable{

    private String image_url;
    private Context context;
    public ProfileImageThread(Context context,String profileurl){
        this.context = context;
        this.image_url = profileurl;
    }

    @Override
    public void run() {
        ImageSaver imageSaver = new ImageSaver(context).
                setParentDirectoryName("profile_pic").
                setFileName(new DisplayUtil().getFileNameReplaced("profile_pic.jpg")).
                setExternal(ImageSaver.isExternalStorageReadable());
        new SaveImageAsync(imageSaver).execute(image_url);

    }
}
