package pro.viksit.com.viksit.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Feroz on 18-03-2017.
 */

public class DisplayUtil {

    public int getHeightOfScreen(Context context){
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public int getWidthOfScreen(Context context){
        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public String getFileNameReplaced(String filename){
        return filename.replaceAll(".jpg",".aaa").replaceAll(".png",".aaa").replaceAll(".jpeg",".aaa").replaceAll(".mp4",".aaa");
    }

}
