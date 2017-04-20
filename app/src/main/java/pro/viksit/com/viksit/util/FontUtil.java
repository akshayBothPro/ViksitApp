package pro.viksit.com.viksit.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Feroz on 19-04-2017.
 */

public class FontUtil {
    private AssetManager mngr;
    private HashMap<AssetTypefaces, Typeface> hashMapFonts;

    private enum AssetTypefaces
    {
        RobotoLight,
        RobotoThin,
        RobotoCondensedBold,
        RobotoCondensedLight,
        RobotoCondensedRegular
    }

    public FontUtil(Context context)
    {
        AssetManager mngr = context.getAssets();

        hashMapFonts = new HashMap<AssetTypefaces, Typeface>();
        hashMapFonts.put(AssetTypefaces.RobotoLight, Typeface.createFromAsset(mngr, "fonts/Roboto-Light.ttf"));
        hashMapFonts.put(AssetTypefaces.RobotoThin, Typeface.createFromAsset(mngr, "fonts/Roboto-Thin.ttf"));
        hashMapFonts.put(AssetTypefaces.RobotoCondensedBold, Typeface.createFromAsset(mngr, "fonts/RobotoCondensed-Bold.ttf"));
        hashMapFonts.put(AssetTypefaces.RobotoCondensedLight, Typeface.createFromAsset(mngr, "fonts/RobotoCondensed-Light.ttf"));
        hashMapFonts.put(AssetTypefaces.RobotoCondensedRegular, Typeface.createFromAsset(mngr, "fonts/RobotoCondensed-Regular.ttf"));
    }

    private Typeface getTypeface(String fontName)
    {
        try
        {
            AssetTypefaces typeface = AssetTypefaces.valueOf(fontName);
            return hashMapFonts.get(typeface);
        }
        catch (IllegalArgumentException e)
        {
            // e.printStackTrace();
            return Typeface.DEFAULT;
        }
    }

    public void setupLayoutTypefaces(View v)
    {
        try
        {
            if (v instanceof ViewGroup)
            {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++)
                {
                    View child = vg.getChildAt(i);
                    setupLayoutTypefaces(child);
                }
            }
            else if (v instanceof TextView)
            {
                ((TextView) v).setTypeface(getTypeface(v.getTag().toString()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // ignore
        }
    }
}

