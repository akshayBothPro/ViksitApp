package pro.viksit.com.viksit.dashboard.util;

import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.Util.CircleTransform;
import pro.viksit.com.viksit.Util.ImageSaver;
import pro.viksit.com.viksit.Util.SaveImageAsync;

/**
 * Created by Feroz on 11-04-2017.
 */

public class ImageSaverUtil {
    private ImageSaver imageSaver;
    private Picasso picasso;
    private ImageView imageView;
    private int screenHeight;
    private String url;

    public ImageSaverUtil(ImageSaver imageSaver, Picasso picasso, ImageView imageView, int screenHeight, String url) {
        this.imageSaver = imageSaver;
        this.picasso = picasso;
        this.imageView = imageView;
        this.screenHeight = screenHeight;
        this.url = url;
    }

    public void checkImageExist() {
        if (this.imageSaver.checkFile()) {
            Uri uri = Uri.fromFile(imageSaver.pathFile());
            picasso
                    .load(uri).resize(screenHeight / 4, screenHeight / 4).transform(new CircleTransform())
                    .into(imageView);
            System.out.println("FILE  EXITS >>>>>> ");
        } else {
            System.out.println("FILE NOT EXITS >>>>>> ");
            picasso
                    .load(url).resize(screenHeight / 4, screenHeight / 4).transform(new CircleTransform())
                    .into(imageView);

            new SaveImageAsync(imageSaver).execute(url);

        }

    }


}
